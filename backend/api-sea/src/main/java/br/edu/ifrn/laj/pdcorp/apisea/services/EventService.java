package br.edu.ifrn.laj.pdcorp.apisea.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.laj.pdcorp.apisea.models.Activity;
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

	public Event findById(Long id) {
		Optional<Event> optional = eventRepository.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}
	
	public Event addActivity(Activity activity, Long idEvent) {
		Event event = this.findById(idEvent);
		event.addActivity(activity);
		return add(event);
	}

	public List<Event> findAll() {
		return eventRepository.findAll();
	}

	public Event update(Event event) {
		return eventRepository.save(event);
	}

	public Event deactivate(Event event) {
		event.setActive(false);
		return eventRepository.save(event);
	}

}
