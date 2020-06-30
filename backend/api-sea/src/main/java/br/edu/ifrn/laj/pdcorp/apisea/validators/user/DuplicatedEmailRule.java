package br.edu.ifrn.laj.pdcorp.apisea.validators.user;

import java.util.List;

import org.springframework.util.ObjectUtils;

import br.edu.ifrn.laj.pdcorp.apisea.enums.ExceptionMessages;
import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiException;
import br.edu.ifrn.laj.pdcorp.apisea.models.User;
import br.edu.ifrn.laj.pdcorp.apisea.validators.BusinessRule;

public class DuplicatedEmailRule implements BusinessRule {
	
	private User duplicatedUser;
	
	public DuplicatedEmailRule(User duplicatedUser) {
		this.duplicatedUser = duplicatedUser;
	}

	@Override
	public boolean check() {
		return !ObjectUtils.isEmpty(duplicatedUser);
	}

	@Override
	public ApiException getException() {
		return new ApiException(ExceptionMessages.USER_EMAIL_EXISTS_DB);
	}

}
