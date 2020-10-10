package br.edu.ifrn.laj.pdcorp.apisea.validators.user;

import org.springframework.util.ObjectUtils;

import br.edu.ifrn.laj.pdcorp.apisea.enums.ExceptionMessages;
import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiException;
import br.edu.ifrn.laj.pdcorp.apisea.models.User;
import br.edu.ifrn.laj.pdcorp.apisea.validators.BusinessRule;

public class EmailInvalidFormat implements BusinessRule {
	
	private User emailInvalidFormat;
	
	public EmailInvalidFormat(User emailInvalidFormat) {
		this.emailInvalidFormat = emailInvalidFormat;
	}

	@Override
	public boolean check() {
		return !ObjectUtils.isEmpty(emailInvalidFormat);
	}

	@Override
	public ApiException getException() {
		return new ApiException(ExceptionMessages.USER_EMAIL_INVALID_FORMAT);
	}

}