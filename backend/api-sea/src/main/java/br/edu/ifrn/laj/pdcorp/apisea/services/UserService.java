package br.edu.ifrn.laj.pdcorp.apisea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.laj.pdcorp.apisea.models.User;
import br.edu.ifrn.laj.pdcorp.apisea.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public User save(User user) {
		return repository.save(user);
	}

}
