package br.edu.ifrn.laj.pdcorp.apisea.dtos;

public class SessionDTO {

	private UserDTO user;
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

}