package br.edu.ifrn.laj.pdcorp.apisea.validators.user;


import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrn.laj.pdcorp.apisea.models.User;
import br.edu.ifrn.laj.pdcorp.apisea.validators.AbstractValidationMediator;
import br.edu.ifrn.laj.pdcorp.apisea.validators.BusinessInvalidation;
import br.edu.ifrn.laj.pdcorp.apisea.validators.BusinessInvalidationImpl;

public class UserValidationMediator extends AbstractValidationMediator<User>{

	public UserValidationMediator() {
		super(new BusinessInvalidationImpl());
	}

	@Override
	protected BusinessInvalidation verify(User user) {
		BusinessInvalidation invalidation = getBusinessInvalidation();
			invalidation
				.withRule(new EmailOrPasswordEmptyRule(user))
				.exists();
		
		return invalidation;
	}

}
