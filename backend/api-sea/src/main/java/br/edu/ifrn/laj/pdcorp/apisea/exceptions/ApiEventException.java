package br.edu.ifrn.laj.pdcorp.apisea.exceptions;

import br.edu.ifrn.laj.pdcorp.apisea.enums.ExceptionMessages;

public class ApiEventException extends Exception {
	
	private ExceptionMessages messageException;

	public ApiEventException(ExceptionMessages message) {
		super(message.getDescription());
		this.messageException = message;
	}

	public ExceptionMessages getMessageException() {
		return messageException;
	}

}
