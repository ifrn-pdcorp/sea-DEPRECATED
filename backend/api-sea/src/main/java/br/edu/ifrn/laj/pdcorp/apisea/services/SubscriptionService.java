package br.edu.ifrn.laj.pdcorp.apisea.services;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.laj.pdcorp.apisea.enums.ExceptionMessages;
import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiEventException;
import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiSubscriptionException;
import br.edu.ifrn.laj.pdcorp.apisea.models.Event;
import br.edu.ifrn.laj.pdcorp.apisea.models.Subscription;
import br.edu.ifrn.laj.pdcorp.apisea.models.User;
import br.edu.ifrn.laj.pdcorp.apisea.repositories.EventRepository;
import br.edu.ifrn.laj.pdcorp.apisea.repositories.SubscriptionRepository;
import br.edu.ifrn.laj.pdcorp.apisea.repositories.UserRepository;;

@Service
public class SubscriptionService {

	@Autowired
	private SubscriptionRepository subscriptionRepository;
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private UserRepository userRepository;

	public Subscription add(Subscription subscription) throws ApiSubscriptionException {
		User user = this.findUserById(subscription.getUser().getId());
		Event event = this.findEventById(subscription.getEvent().getId());

		subscription.setEvent(event);
		subscription.setUser(user);

		if (subscriptionRepository.findByUserAndEvent(user, event) != null)
			throw new ApiSubscriptionException(ExceptionMessages.SUBSCRIPTION_ALREADY_EXISTS);

		subscription.setLastChangeDate(Calendar.getInstance());

		return subscriptionRepository.save(subscription);
	}

	public Subscription findById(Long id) throws ApiSubscriptionException {
		return this.findSubscriptionById(id);
	}

	public List<Subscription> findAll() {
		return subscriptionRepository.findAll();
	}

	public List<Subscription> findAllByEventId(Long eventId) throws ApiSubscriptionException {
		Event event = this.findEventById(eventId);
		return subscriptionRepository.findAllByEvent(event);
	}

	public List<Subscription> findAllByUserId(Long userId) throws ApiSubscriptionException {
		User user = this.findUserById(userId);
		return subscriptionRepository.findAllByUser(user);
	}

	public Subscription update(Long id, Subscription subscription) throws ApiSubscriptionException {
		Subscription existent = this.findSubscriptionById(id);

		BeanUtils.copyProperties(subscription, existent, "id", "user", "event");
		existent.setLastChangeDate(Calendar.getInstance());

		return subscriptionRepository.save(existent);
	}

	public void delete(Subscription subscription) throws ApiSubscriptionException {
		Subscription existent = this.findSubscriptionById(subscription.getId());
		subscriptionRepository.delete(existent);
	}

	private Subscription findSubscriptionById(Long id) throws ApiSubscriptionException {
		Optional<Subscription> optional = subscriptionRepository.findById(id);
		if (optional.isEmpty())
			throw new ApiSubscriptionException(ExceptionMessages.SUBSCRIPTION_DOESNT_EXISTS_DB);
		return optional.get();
	}

	private Event findEventById(Long id) throws ApiSubscriptionException {
		Optional<Event> optional = eventRepository.findById(id);
		if (optional.isEmpty())
			throw new ApiSubscriptionException(ExceptionMessages.EVENT_DOESNT_EXISTS_DB);
		return optional.get();
	}

	private User findUserById(Long id) throws ApiSubscriptionException {
		Optional<User> optional = userRepository.findById(id);
		if (optional.isEmpty())
			throw new ApiSubscriptionException(ExceptionMessages.USER_DOESNT_EXISTS_DB);
		return optional.get();
	}

}
