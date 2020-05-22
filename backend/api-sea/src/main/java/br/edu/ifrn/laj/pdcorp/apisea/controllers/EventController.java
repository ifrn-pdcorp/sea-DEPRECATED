package br.edu.ifrn.laj.pdcorp.apisea.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrn.laj.pdcorp.apisea.models.Event;
import br.edu.ifrn.laj.pdcorp.apisea.services.EventService;

@RestController
@RequestMapping("/events")
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@GetMapping
	public List<Event> findAll() {
		return eventService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Event> findById(@PathVariable Long id) {
		Event event = eventService.findById(id);
		if (event == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(event);
	}

	@PostMapping
	public Event add(@RequestBody @Valid Event event) {
		return eventService.add(event);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Event> update(@PathVariable Long id, @RequestBody @Valid Event event) {
		Event existent = eventService.findById(id);
		if (existent == null)
			return ResponseEntity.notFound().build();
		BeanUtils.copyProperties(event, existent, "id");
		existent = eventService.update(existent);
		return ResponseEntity.ok(existent);
	}

	@PutMapping("/{id}/deactivate")
	public ResponseEntity<Event> deactivate(@PathVariable Long id) {
		Event event = eventService.findById(id);
		if (event == null)
			return ResponseEntity.notFound().build();
		event = eventService.deactivate(event);
		return ResponseEntity.ok(event);
	}

}
