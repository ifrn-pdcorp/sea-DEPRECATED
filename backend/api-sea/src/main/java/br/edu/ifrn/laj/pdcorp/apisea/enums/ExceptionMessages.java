package br.edu.ifrn.laj.pdcorp.apisea.enums;

public enum ExceptionMessages {

	EMAIL_EXISTS_DB("E-mail já registrado no banco de dados, tente outro.");
	
	private String description;
	
	private ExceptionMessages(String desciption) {
		this.description = desciption;
	}

	public String getDescription() {
		return description;
	}
	
}

