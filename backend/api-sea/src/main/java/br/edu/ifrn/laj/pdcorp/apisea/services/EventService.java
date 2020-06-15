package br.edu.ifrn.laj.pdcorp.apisea.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.laj.pdcorp.apisea.enums.ExceptionMessages;
import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiEventException;
import br.edu.ifrn.laj.pdcorp.apisea.models.Event;
import br.edu.ifrn.laj.pdcorp.apisea.repositories.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	public Event add(Event event) {
		event.setActive(true);
		return eventRepository.save(event);
	}

	public Event findById(Long id) throws ApiEventException {
		Optional<Event> optional = eventRepository.findById(id);
		if (optional.isEmpty())
			throw new ApiEventException(ExceptionMessages.EVENT_DOESNT_EXISTS_DB);
		return optional.get();
	}

	public List<Event> findAll() {
		return eventRepository.findAll();
	}

	public Event update(Long id, Event event) throws ApiEventException {
		Event existent = this.findById(id);
		BeanUtils.copyProperties(event, existent, "id", "active");
		return eventRepository.save(existent);
	}

	public Event deactivate(Long id) throws ApiEventException {
		Event event = this.findById(id);
		event.setActive(false);
		return eventRepository.save(event);
	}

}
