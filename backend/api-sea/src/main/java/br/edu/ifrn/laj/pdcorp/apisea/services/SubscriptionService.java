package br.edu.ifrn.laj.pdcorp.apisea.services;

import java.util.List;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.laj.pdcorp.apisea.models.Event;
import br.edu.ifrn.laj.pdcorp.apisea.models.Subscription;
import br.edu.ifrn.laj.pdcorp.apisea.models.User;
import br.edu.ifrn.laj.pdcorp.apisea.repositories.SubscriptionRepository;

@Service
public class SubscriptionService {
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	public Subscription add(Subscription subscription) {
		throw new NotYetImplementedException("Not Yet Implemented!");
	}
	
	public Subscription findById(Long id) {
		throw new NotYetImplementedException("Not Yet Implemented!");
	}
	
	public List<Subscription> findAll() {
		throw new NotYetImplementedException("Not Yet Implemented!");
	}
	
	public List<Subscription> findAllByEvent(Event event) {
		throw new NotYetImplementedException("Not Yet Implemented!");
	}
	
	public List<Subscription> findAllByUser(User user) {
		throw new NotYetImplementedException("Not Yet Implemented!");
	}
	
	public Subscription update(Subscription subscription) {
		throw new NotYetImplementedException("Not Yet Implemented!");
	}
	
	public void delete(Subscription subscription) {
		throw new NotYetImplementedException("Not Yet Implemented!");
	}

}
