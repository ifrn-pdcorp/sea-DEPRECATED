package br.edu.ifrn.laj.pdcorp.apisea.validators.user;

import org.springframework.util.StringUtils;

import br.edu.ifrn.laj.pdcorp.apisea.enums.ExceptionMessages;
import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiException;
import br.edu.ifrn.laj.pdcorp.apisea.models.User;
import br.edu.ifrn.laj.pdcorp.apisea.repositories.UserRepository;
import br.edu.ifrn.laj.pdcorp.apisea.validators.BusinessRule;

public class EmailOrPasswordEmptyRule implements BusinessRule {
	
	private User user;
	
	public EmailOrPasswordEmptyRule(User user) {
		this.user = user;
	}

	@Override
	public boolean check() {
		return (StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getPassword())) ? true : false;
	}

	@Override
	public ApiException getException() {
		return new ApiException(ExceptionMessages.CREDENTIALS_IS_WORNG);
	}

}
