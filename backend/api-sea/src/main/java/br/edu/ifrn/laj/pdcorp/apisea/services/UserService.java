package br.edu.ifrn.laj.pdcorp.apisea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import br.edu.ifrn.laj.pdcorp.apisea.enums.ExceptionMessages;
import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiUserException;
import br.edu.ifrn.laj.pdcorp.apisea.models.User;
import br.edu.ifrn.laj.pdcorp.apisea.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public User save(User user) throws ApiUserException {
		if(!ObjectUtils.isEmpty(repository.findByEmail(user.getEmail()))) {
			throw new ApiUserException(ExceptionMessages.EMAIL_EXISTS_DB);
		}
		return repository.save(user); 
	}

}
