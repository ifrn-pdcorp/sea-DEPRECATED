package br.edu.ifrn.laj.pdcorp.apisea.enums;

public enum ExceptionMessages {

	USER_EMAIL_EXISTS_DB("E-mail já registrado no banco de dados, tente outro."),
	USER_DOESNT_EXISTS_DB("Usuário não encontrado para o e-mail informado."),
	CREDENTIALS_IS_WORNG("E-mail ou senha incorretos."),
	CREDENTIALS_REQUEST_FORBBIDEN("A requisição foi negada pela API por não ser autenticada."),
	USER_REQUEST_FORBBIDEN("A requisição não permitida para este usuário."),
	EVENT_DOESNT_EXISTS_DB("Evento não encontrado."),
	SUBSCRIPTION_DOESNT_EXISTS_DB("Inscrição não encontrada."), 
	SUBSCRIPTION_ALREADY_EXISTS("Inscrição já existe.");
	
	private String description;
	
	private ExceptionMessages(String desciption) {
		this.description = desciption;
	}

	public String getDescription() {
		return description;
	}
	
}
