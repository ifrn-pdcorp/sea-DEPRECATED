package br.edu.ifrn.laj.pdcorp.apisea.services;

import java.util.List;
import java.util.Optional;

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
		Optional<Subscription> optional = subscriptionRepository.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}

	public List<Subscription> findAll() {
		return subscriptionRepository.findAll();
	}

	public List<Subscription> findAllByEvent(Event event) {
		return subscriptionRepository.findAllByEvent(event);
	}

	public List<Subscription> findAllByUser(User user) {
		return subscriptionRepository.findAllByUser(user);
	}

	public Subscription update(Subscription subscription) {
		throw new NotYetImplementedException("Not Yet Implemented!");
	}

	public void delete(Subscription subscription) {
		throw new NotYetImplementedException("Not Yet Implemented!");
	}

}
