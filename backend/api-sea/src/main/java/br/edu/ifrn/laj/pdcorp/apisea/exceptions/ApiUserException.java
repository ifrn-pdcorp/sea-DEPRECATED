package br.edu.ifrn.laj.pdcorp.apisea.exceptions;

import br.edu.ifrn.laj.pdcorp.apisea.enums.ExceptionMessages;

public class ApiUserException extends Exception {
	
	private ExceptionMessages messageException;

	public ApiUserException(ExceptionMessages message) {
		super(message.getDescription());
	}

	public ExceptionMessages getMessageException() {
		return messageException;
	}

	
	
}
