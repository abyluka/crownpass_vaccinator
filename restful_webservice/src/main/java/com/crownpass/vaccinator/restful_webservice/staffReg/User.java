package com.crownpass.vaccinator.restful_webservice.staffReg;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

//@ApiModel(description="All details about the user.")
@Entity
public class User {

	@Id
	@GeneratedValue
	private Integer id;
	@Size(min=1, message="Number of dose minumum length is 1")
	private Integer dose_count;
	@Size(min=6, message="vaccine_date should have atleast 6 characters")
	@NotEmpty
	private String vaccine_date;
	@Column(nullable = true, length = 64)
	private String time_slot;
	@Size(max = 4, message="name_vaccine should be maximum 4 digits")
	private String name_vaccine;
	@Size(min=4, message="batch_no should have atleast 4 characters")
	private String batch_no;
	@Size(min=2, message="vaccine_station should have atleast 2 characters")
	private String vaccine_station;
	@Size(min=2, message="name_nurse should have atleast 2 characters")
	private String name_nurse;
	@Size(min=2, message="staff_id should have atleast 2 characters")
	@Pattern(regexp="(^$|[0-9]{10})")
	private String staff_id;
	@Size(min=4, message="vaccine_status  should have atleast 4 characters")
	private String vaccine_status;
	@Size(min=4, message="crownpassId should have atleast 4 characters")
	@Column(unique = true)
	private String crownpassId;
	
	@OneToMany(mappedBy="user")
	private List<Post> posts;

	protected User() {

	}

	public User(Integer id, String crownpassId,String vaccine_date,String time_slot, String name_vaccine,Integer dose_count, String batch_no, String vaccine_station, String name_nurse,String staff_id, String vaccine_status) {
		super();
		this.id = id;
		this.crownpassId=crownpassId;
		this.vaccine_date = vaccine_date;
		this.time_slot=time_slot;
		this.name_vaccine = name_vaccine;
		this.dose_count=dose_count;
		this.batch_no=batch_no;
		this.vaccine_station=vaccine_station;
		this.name_nurse= name_nurse;
		this.staff_id=staff_id;
		this.vaccine_status=vaccine_status;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCrownpassId() {
		return crownpassId;
	}

	public void setCrownpassId(String crownpassId) {
		this.crownpassId = crownpassId;
	}
	public String getVaccine_dates() {
		return vaccine_date;
	}

	public void setVaccine_date(String vaccine_date) {
		this.vaccine_date = vaccine_date;
	}
	public  String getTime_slot() {
		return time_slot;
	}

	public void setTime_slo(String time_slot) {
		this.time_slot = time_slot;
	}
	public String getName_vaccine() {
		return name_vaccine;
	}

	public void setName_vaccine(String name_vaccine) {
		this.name_vaccine = name_vaccine;
	}
	public Integer getDose_count() {
		return dose_count;
	}

	public void setDose_count(Integer dose_count) {
		this.dose_count = dose_count;
	}
	public String getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}
	public String getVaccine_station() {
		return vaccine_station;
	}

	public void setVaccine_station(String vaccine_station) {
		this.vaccine_station = vaccine_station;
	}
	public String getName_nurse() {
		return name_nurse;
	}

	public void setName_nurse(String name_nurse) {
		this.name_nurse = name_nurse;
	}
	public String getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public String getVaccine_status() {
		return vaccine_status;
	}

	public void setVaccine_status(String vaccine_status) {
		this.vaccine_status = vaccine_status;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return String.format("User [ id=%s, crownpassId=%s, vaccine_date=%s, time_slot=%s, name_vaccine=%s,dose_count=%s, batch_no=%s, vaccine_station=%s, name_nurse=%s, staff_id=%s , vaccine_status=%s]", id, crownpassId, vaccine_date, time_slot,  name_vaccine,dose_count, batch_no, vaccine_station, name_nurse, staff_id, vaccine_status);
	}

}
