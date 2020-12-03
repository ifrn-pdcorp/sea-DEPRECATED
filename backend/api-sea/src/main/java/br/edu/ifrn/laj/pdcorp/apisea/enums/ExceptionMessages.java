package br.edu.ifrn.laj.pdcorp.apisea.enums;

public enum ExceptionMessages {

	USER_EMAIL_EXISTS_DB("E-mail já registrado no banco de dados, tente outro."),
	USER_DOESNT_EXISTS_DB("Usuário não encontrado para o e-mail informado."),
	CREDENTIALS_IS_WORNG("E-mail ou senha incorretos."),
	CREDENTIALS_REQUEST_FORBBIDEN("A requisição foi negada pela API por não ser autenticada."),
	SPEAKER_DOESNT_EXIST_DB("Palestrante não encontrado na base de dados."),
	EVENT_DOESNT_EXISTS_DB("Evento não encontrado."),
	USER_REQUEST_FORBBIDEN("A requisição não permitida para este usuário."),
	SUBSCRIPTION_DOESNT_EXISTS_DB("Inscrição não encontrada."), 
	ACTIVITY_IS_NOT_VALID("Atividade não é válida par	a esta operação."),
	ACTIVITY_DOESNT_EXIST_IN_EVENT("Atividade não está registrada no evento associado."),
	SUBSCRIPTION_ALREADY_EXISTS("Inscrição já existe."), 
	INVALID_DATETIME_FOR_SUBSCRIPTION("A data atual não está dentro do período de inscrição"),
	DATA_VALIDATION("Dados estão violando as regras de integridade. "),
	EVENT_IS_NOT_ACTIVE_FOR_SUBSCRIPTION("Não é possível se inscrever em eventos inativos.");
	
	private String description;
	
	private ExceptionMessages(String desciption) {
		this.description = desciption;
	}

	public String getDescription() {
		return description;
	}
	
}
