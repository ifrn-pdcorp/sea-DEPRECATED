package br.edu.ifrn.laj.pdcorp.apisea.models.enums;

public enum UserType {

	PROFESSOR(1, "Professor"),
	STUDENT(2, "Estudante"),
	CHEER(3, "Coordenador");
	
	private int code;
	private String description;
	
	private UserType(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
