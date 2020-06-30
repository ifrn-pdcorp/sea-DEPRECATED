package br.edu.ifrn.laj.pdcorp.apisea.validators.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import br.edu.ifrn.laj.pdcorp.apisea.models.User;
import br.edu.ifrn.laj.pdcorp.apisea.repositories.UserRepository;
import br.edu.ifrn.laj.pdcorp.apisea.validators.AbstractValidationMediator;
import br.edu.ifrn.laj.pdcorp.apisea.validators.BusinessInvalidation;
import br.edu.ifrn.laj.pdcorp.apisea.validators.BusinessInvalidationImpl;

@Component
public class UserValidationMediator extends AbstractValidationMediator<User>{

	@Autowired
	private UserRepository repository;
	
	public UserValidationMediator() {
		super(new BusinessInvalidationImpl());
	}

	@Override
	protected BusinessInvalidation verify(User user) {
		BusinessInvalidation invalidation = getBusinessInvalidation();
			invalidation
				.withRule(new EmailOrPasswordEmptyRule(user))
				.withRule(new DuplicatedEmailRule(repository.findByEmail(user.getEmail())))
				.exists();
		
		return invalidation;
	}

}
