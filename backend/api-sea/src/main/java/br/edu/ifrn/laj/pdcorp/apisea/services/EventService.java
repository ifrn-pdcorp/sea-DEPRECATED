package br.edu.ifrn.laj.pdcorp.apisea.services;

import java.util.List;

import br.edu.ifrn.laj.pdcorp.apisea.models.Event;

public interface EventService {

	Event add(Event event);

	Event findById(Long id);

	List<Event> findAll();

	Event update(Event event);

	void deactivate(Event event);

}
