package br.edu.ifrn.laj.pdcorp.apisea.dtos;

import javax.persistence.Column;

import br.edu.ifrn.laj.pdcorp.apisea.models.User;
import br.edu.ifrn.laj.pdcorp.apisea.models.enums.UserType;

public class UserDTO {

	private Long id;
	private String name;
	private String email;
	private String school;
	private String city;
	private UserType type;

	private UserDTO(Long id, String name, String email, String school, String city, UserType type) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.school = school;
		this.city = city;
		this.type = type;
	}
	
	public static UserDTO convertFromModel(User user) {
		return new UserDTO(user.getId(), 
				user.getName(), 
				user.getEmail(), 
				user.getSchool(), 
				user.getCity(), 
				user.getType());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

}
