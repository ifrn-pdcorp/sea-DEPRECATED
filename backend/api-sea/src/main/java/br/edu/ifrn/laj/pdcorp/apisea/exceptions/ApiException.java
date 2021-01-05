package br.edu.ifrn.laj.pdcorp.apisea.exceptions;

import br.edu.ifrn.laj.pdcorp.apisea.enums.ExceptionMessages;

public class ApiException extends Exception {

	private ExceptionMessages messageException;

	public ApiException(ExceptionMessages message) {
		super(message.getDescription());
	}

	public ApiException(String message) {
		super(message);
	}

	public ExceptionMessages getMessageException() {
		return messageException;
	}

}
