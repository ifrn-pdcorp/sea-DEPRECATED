package br.edu.ifrn.laj.pdcorp.apisea.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.edu.ifrn.laj.pdcorp.apisea.models.enums.UserType;

/**
 * User represents a valid user of API. It's the credentials to access all available resources.
 * @author Dannylo Johnathan
 * @since 22/05/2020
 */
@Entity
@Table(name = "user")
public class User {
	@Id
	private Long id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	@Column(name = "password", nullable = false)
	private String password;
	private String school;
	private String city;
	private UserType type;
	private boolean emailVerified;
	
	public User() {
		this.emailVerified = false;
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
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
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
	
	public boolean isEmailVerified() {
		return emailVerified;
	}
	
	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

}
