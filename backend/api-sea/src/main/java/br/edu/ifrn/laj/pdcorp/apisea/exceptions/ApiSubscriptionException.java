package br.edu.ifrn.laj.pdcorp.apisea.exceptions;

import br.edu.ifrn.laj.pdcorp.apisea.enums.ExceptionMessages;

public class ApiSubscriptionException extends Exception {
	
	private ExceptionMessages messageException;

	public ApiSubscriptionException(ExceptionMessages message) {
		super(message.getDescription());
		this.messageException = message;
	}

	public ExceptionMessages getMessageException() {
		return messageException;
	}

}
