package br.edu.ifrn.laj.pdcorp.apisea.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrn.laj.pdcorp.apisea.models.Event;
import br.edu.ifrn.laj.pdcorp.apisea.models.Subscription;
import br.edu.ifrn.laj.pdcorp.apisea.models.User;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

	Subscription findByUserAndEvent(User user, Event event);
	
	List<Subscription> findAllByEvent(Event event);
	
	List<Subscription> findAllByUser(User user);
	
}
