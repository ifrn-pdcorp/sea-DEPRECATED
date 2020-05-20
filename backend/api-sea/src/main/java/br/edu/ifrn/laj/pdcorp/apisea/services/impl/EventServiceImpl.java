package br.edu.ifrn.laj.pdcorp.apisea.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.laj.pdcorp.apisea.models.Event;
import br.edu.ifrn.laj.pdcorp.apisea.repositories.EventRepository;
import br.edu.ifrn.laj.pdcorp.apisea.services.EventService;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepository;

	@Override
	public Event add(Event event) {
		event.setActive(true);
		return eventRepository.save(event);
	}

	@Override
	public Event findById(Long id) {
		Optional<Event> optional = eventRepository.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	public List<Event> findAll() {
		return eventRepository.findAll();
	}

	@Override
	public Event update(Event event) {
		return eventRepository.save(event);
	}

	@Override
	public Event deactivate(Event event) {
		event.setActive(false);
		return eventRepository.save(event);
	}

}
