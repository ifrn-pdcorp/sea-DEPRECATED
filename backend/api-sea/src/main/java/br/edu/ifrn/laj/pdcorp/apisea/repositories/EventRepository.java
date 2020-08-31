package br.edu.ifrn.laj.pdcorp.apisea.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.laj.pdcorp.apisea.models.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
	
	@Transactional
	List<Event> findAllByActiveIsTrue();

}
