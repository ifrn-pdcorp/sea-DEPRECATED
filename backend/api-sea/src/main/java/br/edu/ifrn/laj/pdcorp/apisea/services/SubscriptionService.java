package br.edu.ifrn.laj.pdcorp.apisea.services;

import java.security.Principal;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.laj.pdcorp.apisea.dtos.SubscriptionDTO;
import br.edu.ifrn.laj.pdcorp.apisea.enums.ExceptionMessages;
import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiEventException;
import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiSubscriptionException;
import br.edu.ifrn.laj.pdcorp.apisea.models.Activity;
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
	@Autowired
	private ActivityService activityService;

	public SubscriptionDTO findById(Principal principal, Long id) throws ApiSubscriptionException {
		Subscription subscription = this.findSubscriptionById(id);
		User user = this.findUserAuthenticated(principal);

		if (!(user.getId().equals(subscription.getUser().getId())
				|| user.getId().equals(subscription.getEvent().getOwner().getId())))
			throw new ApiSubscriptionException(ExceptionMessages.USER_REQUEST_FORBBIDEN);

		return SubscriptionDTO.convertFromModel(subscription);
	}
	
	
	public SubscriptionDTO registerNewActivity(Principal principal, Activity activity, Long idSubscription) throws ApiSubscriptionException, ApiEventException {
		SubscriptionDTO validated = findById(principal, idSubscription);
		Subscription subscription = this.findSubscriptionById(validated.getId());
		
		subscription.registerNewActivity(activity);
		activity.addParticipant(subscription);
		this.activityService.update(activity);
		return this.update(principal, subscription.getId(), subscription);
	}

	public List<SubscriptionDTO> findAllByEventId(Principal principal, Long eventId) throws ApiSubscriptionException {
		Event event = this.findEventById(eventId);
		User user = this.findUserAuthenticated(principal);

		if (!user.getId().equals(event.getOwner().getId()))
			throw new ApiSubscriptionException(ExceptionMessages.USER_REQUEST_FORBBIDEN);

		return SubscriptionDTO.convertFromModel(subscriptionRepository.findAllByEvent(event));
	}

	public List<SubscriptionDTO> findAllByUser(Principal principal) throws ApiSubscriptionException {
		User user = this.findUserAuthenticated(principal);
		return SubscriptionDTO.convertFromModel(subscriptionRepository.findAllByUser(user));
	}

	public SubscriptionDTO add(Principal principal, Subscription subscription) throws ApiSubscriptionException {
		User user = this.findUserAuthenticated(principal);
		Event event = this.findEventById(subscription.getEvent().getId());

		subscription.setEvent(event);
		subscription.setUser(user);

		if (subscriptionRepository.findByUserAndEvent(user, event) != null)
			throw new ApiSubscriptionException(ExceptionMessages.SUBSCRIPTION_ALREADY_EXISTS);

		subscription.setLastChangeDate(Calendar.getInstance());

		return SubscriptionDTO.convertFromModel(subscriptionRepository.save(subscription));
	}

	public SubscriptionDTO update(Principal principal, Long id, Subscription subscription)
			throws ApiSubscriptionException {
		Subscription existent = this.findSubscriptionById(id);
		User user = this.findUserAuthenticated(principal);

		if (!user.getId().equals(existent.getUser().getId()))
			throw new ApiSubscriptionException(ExceptionMessages.USER_REQUEST_FORBBIDEN);

		BeanUtils.copyProperties(subscription, existent, "id", "user", "event");
		existent.setLastChangeDate(Calendar.getInstance());

		return SubscriptionDTO.convertFromModel(subscriptionRepository.save(existent));
	}

	public void delete(Principal principal, Long id) throws ApiSubscriptionException {
		Subscription existent = this.findSubscriptionById(id);

		User user = this.findUserAuthenticated(principal);

		if (!user.getId().equals(existent.getUser().getId()))
			throw new ApiSubscriptionException(ExceptionMessages.USER_REQUEST_FORBBIDEN);

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

	private User findUserAuthenticated(Principal principal) throws ApiSubscriptionException {
		User user = userRepository.findByEmail(principal.getName());
		if (user == null)
			throw new ApiSubscriptionException(ExceptionMessages.USER_DOESNT_EXISTS_DB);
		return user;
	}

}
