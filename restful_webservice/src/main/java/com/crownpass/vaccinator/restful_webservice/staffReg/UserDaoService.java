package com.crownpass.vaccinator.restful_webservice.staffReg;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<>();

	private static int usersCount = 3;
	static String white="#ffffff";
	static String lightBlue="#add8e6";
	static String darkBlue="#00008B";

	static {
		users.add(new User(1, "012GFJJK","11/02/2022", "11.20", "covishield",1, "IR00988", "Oxford","Elizabeth","Emp121", "green"));
		users.add(new User(2, "012GFJJK","11/02/2022", "11.20", "covishield",2, "IR00988", "Oxford","Elizabeth","Emp121", "green"));
		users.add(new User(3, "012GFJJK","11/02/2022", "11.20", "covishield",1, "IR00988", "Oxford","Elizabeth","Emp121", "green"));
	}

	public List<User> findAll() {
		return users;
	}

	public User save(User user) {
		if (user.getId() == null) {
			user.setId(++usersCount);
		
		}
		
		users.add(user);
		return user;
	}

	public User findOne(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public User deleteById(int id) {
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}

}
