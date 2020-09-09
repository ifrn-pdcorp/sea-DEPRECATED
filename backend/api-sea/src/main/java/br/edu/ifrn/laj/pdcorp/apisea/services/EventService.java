package br.edu.ifrn.laj.pdcorp.apisea.services;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.laj.pdcorp.apisea.dtos.EventDTO;
import br.edu.ifrn.laj.pdcorp.apisea.enums.ExceptionMessages;
import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiEventException;
import br.edu.ifrn.laj.pdcorp.apisea.models.Activity;
import br.edu.ifrn.laj.pdcorp.apisea.models.Event;
import br.edu.ifrn.laj.pdcorp.apisea.models.User;
import br.edu.ifrn.laj.pdcorp.apisea.repositories.EventRepository;
import br.edu.ifrn.laj.pdcorp.apisea.repositories.UserRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private UserRepository userRepository;

	public EventDTO add(Principal principal, Event event) throws ApiEventException {
		User user = this.findUserAuthenticated(principal);
		event.setActive(true);
		event.setOwner(user);
		return EventDTO.convertFromModel(eventRepository.save(event));
	}
	
	public EventDTO update(Event event) {
		return EventDTO.convertFromModel(eventRepository.save(event));
	}

	public EventDTO findById(Long id) throws ApiEventException {
		Optional<Event> optional = eventRepository.findById(id);
		if (optional.isEmpty())
			throw new ApiEventException(ExceptionMessages.EVENT_DOESNT_EXISTS_DB);
		return EventDTO.convertFromModel(optional.get());
	}
	
	private Event findModelById(Long id) throws ApiEventException {
		Optional<Event> optional = eventRepository.findById(id);
		if (optional.isEmpty())
			throw new ApiEventException(ExceptionMessages.EVENT_DOESNT_EXISTS_DB);
		return optional.get();
	}
	
	
	public List<EventDTO> findAll() {
		List<Event> events = eventRepository.findAll();
		return EventDTO.convertFromModel(events);
	}

	public List<EventDTO> findAllIsActive() {
		List<Event> events = eventRepository.findAllByActiveIsTrue();
		return EventDTO.convertFromModel(events);
	}
	
	public EventDTO addActivity(Activity activity, Long idEvent) throws ApiEventException {
		Event event = this.findModelById(idEvent);
	    event.addActivity(activity);
	    return this.update(event);
	}

	public EventDTO update(Principal principal, Long id, Event event) throws ApiEventException {
		Optional<Event> optional = eventRepository.findById(id);
		if (optional.isEmpty())
			throw new ApiEventException(ExceptionMessages.EVENT_DOESNT_EXISTS_DB);

		Event existent = optional.get();
		User user = this.findUserAuthenticated(principal);

		if (!existent.getOwner().getId().equals(user.getId()))
			throw new ApiEventException(ExceptionMessages.USER_REQUEST_FORBBIDEN);

		BeanUtils.copyProperties(event, existent, "id", "active", "owner");
		return EventDTO.convertFromModel(eventRepository.save(existent));
	}

	public EventDTO deactivate(Principal principal, Long id) throws ApiEventException {
		Optional<Event> optional = eventRepository.findById(id);
		if (optional.isEmpty())
			throw new ApiEventException(ExceptionMessages.EVENT_DOESNT_EXISTS_DB);
		
		Event event = optional.get();
		User user = this.findUserAuthenticated(principal);

		if (!event.getOwner().getId().equals(user.getId()))
			throw new ApiEventException(ExceptionMessages.USER_REQUEST_FORBBIDEN);
		
		event.setActive(false);
		return EventDTO.convertFromModel(eventRepository.save(event));
	}

	private User findUserAuthenticated(Principal principal) throws ApiEventException {
		User user = userRepository.findByEmail(principal.getName());
		if (user == null)
			throw new ApiEventException(ExceptionMessages.USER_DOESNT_EXISTS_DB);
		return user;
	}

}
