package com.crownpass.vaccinator.restful_webservice.staffReg;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.crownpass.vaccinator.restful_webservice.FileUtils.FileUploadUtil;

@RestController
public class UserJPAResource {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@GetMapping("/vaccinator/recordvaccine")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/vaccinator/recordvaccine/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent())
			throw new UserNotFoundException("id-" + id);

		// "all-users", SERVER_PATH + "/users"
		// retrieveAllUsers
		EntityModel<User> resource = EntityModel.of(user.get());//new EntityModel<User>(user.get());

		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

		resource.add(linkTo.withRel("all-users"));

		// HATEOAS

		return resource;
	}

	@DeleteMapping("/vaccinator/recordvaccine/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	@PutMapping({"/vaccinator/recordvaccine/{Id}"})
    public ResponseEntity<User> updateTodo(@PathVariable("Id") int id, @RequestBody User user) {
		Optional<User> studentOptional = userRepository.findById(id);

		if (!studentOptional.isPresent())
			return ResponseEntity.notFound().build();

		user.setId(id);
		if(user.getDose_count().equals(1)) {
			user.setVaccine_status(UserDaoService.lightBlue);
		}
		else if(user.getDose_count().equals(2)) {
			user.setVaccine_status(UserDaoService.darkBlue);
		}
		else {
			user.setVaccine_status(UserDaoService.white);
		}
		
		userRepository.save(user);

		return ResponseEntity.status(HttpStatus.OK).build();
    }

	//
	// input - details of user
	// output - CREATED & Return the created URI

	// HATEOAS

	@PostMapping("/vaccinator/recordvaccine")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user)  {	
		if(user.getDose_count().equals(1)) {
			user.setVaccine_status(UserDaoService.lightBlue);
		}
		else if(user.getDose_count().equals(2)) {
			user.setVaccine_status(UserDaoService.darkBlue);
		}
		else {
			user.setVaccine_status(UserDaoService.white);
		}
		User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}
	
	@GetMapping("/vaccinator/recordvaccine/{id}/posts")
	public List<Post> retrieveAllUsers(@PathVariable int id) {
		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}
		
		return userOptional.get().getPosts();
	}

	
	@PostMapping("/vaccinator/recordvaccine/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
		
		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}

		User user = userOptional.get();
		
		post.setUser(user);
		
		postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}

}
