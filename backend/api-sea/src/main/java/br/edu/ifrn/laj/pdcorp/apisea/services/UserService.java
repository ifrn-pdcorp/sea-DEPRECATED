package br.edu.ifrn.laj.pdcorp.apisea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import br.edu.ifrn.laj.pdcorp.apisea.dtos.UserDTO;
import br.edu.ifrn.laj.pdcorp.apisea.enums.ExceptionMessages;
import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiUserException;
import br.edu.ifrn.laj.pdcorp.apisea.models.User;
import br.edu.ifrn.laj.pdcorp.apisea.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public UserDTO save(User user) throws ApiUserException {
		if(!ObjectUtils.isEmpty(repository.findByEmail(user.getEmail()))) {
			throw new ApiUserException(ExceptionMessages.USER_EMAIL_EXISTS_DB);
		}
		return UserDTO.convertFromModel(repository.save(user)); 
	}
	
	public UserDTO findByUsername(String email) throws ApiUserException {
		User user = repository.findByEmail(email);
		if(ObjectUtils.isEmpty(user)) {
			throw new ApiUserException(ExceptionMessages.USER_DOESNT_EXISTS_DB);
		}
		return UserDTO.convertFromModel(user);
	}
	
	

}
