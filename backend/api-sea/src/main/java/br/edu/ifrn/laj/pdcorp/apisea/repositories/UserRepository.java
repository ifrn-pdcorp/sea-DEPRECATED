package br.edu.ifrn.laj.pdcorp.apisea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ifrn.laj.pdcorp.apisea.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
	
	User findByEmailAndPassword(String email, String password);
}
