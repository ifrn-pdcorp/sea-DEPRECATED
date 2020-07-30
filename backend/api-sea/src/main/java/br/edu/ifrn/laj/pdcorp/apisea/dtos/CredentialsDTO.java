package br.edu.ifrn.laj.pdcorp.apisea.dtos;

import java.io.Serializable;

public class CredentialsDTO implements Serializable{
	
	private String user;
	private String password;
	
	public CredentialsDTO() {}
	
	public CredentialsDTO(String email, String user) {
		this.user = email;
		this.password = password;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
