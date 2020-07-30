package br.edu.ifrn.laj.pdcorp.apisea.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.laj.pdcorp.apisea.dtos.EventDTO;
import br.edu.ifrn.laj.pdcorp.apisea.enums.ExceptionMessages;
import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiEventException;
import br.edu.ifrn.laj.pdcorp.apisea.models.Event;
import br.edu.ifrn.laj.pdcorp.apisea.repositories.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	public EventDTO add(Event event) {
		event.setActive(true);
		return EventDTO.convertFromModel(eventRepository.save(event));
	}

	public EventDTO findById(Long id) throws ApiEventException {
		Optional<Event> optional = eventRepository.findById(id);
		if (optional.isEmpty())
			throw new ApiEventException(ExceptionMessages.EVENT_DOESNT_EXISTS_DB);
		return EventDTO.convertFromModel(optional.get());
	}

	public List<EventDTO> findAll() {
		List<Event> events = eventRepository.findAll();
		return EventDTO.convertFromModel(events);
	}
	
	public List<EventDTO> findAllIsActive() {
		List<Event> events = eventRepository.findAllByActiveIsTrue();
		return EventDTO.convertFromModel(events);
	}

	public EventDTO update(Long id, Event event) throws ApiEventException {
		Optional<Event> optional = eventRepository.findById(id);
		if (optional.isEmpty())
			throw new ApiEventException(ExceptionMessages.EVENT_DOESNT_EXISTS_DB);
		Event existent = optional.get();
		BeanUtils.copyProperties(event, existent, "id", "active");
		return EventDTO.convertFromModel(eventRepository.save(existent));
	}

	public EventDTO deactivate(Long id) throws ApiEventException {
		Optional<Event> optional = eventRepository.findById(id);
		if (optional.isEmpty())
			throw new ApiEventException(ExceptionMessages.EVENT_DOESNT_EXISTS_DB);
		Event event = optional.get();
		event.setActive(false);
		return EventDTO.convertFromModel(eventRepository.save(event));
	}

}
