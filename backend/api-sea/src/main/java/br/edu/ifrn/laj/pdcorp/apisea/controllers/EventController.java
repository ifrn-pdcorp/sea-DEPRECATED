package br.edu.ifrn.laj.pdcorp.apisea.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrn.laj.pdcorp.apisea.dtos.EventDTO;
import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiEventException;
import br.edu.ifrn.laj.pdcorp.apisea.models.Activity;
import br.edu.ifrn.laj.pdcorp.apisea.services.EventService;

@RestController
@RequestMapping("/events")
public class EventController {

	@Autowired
	private EventService eventService;

	@GetMapping
	public List<EventDTO> findAll() {
		return eventService.findAllIsActive();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(eventService.findById(id));
		} catch (ApiEventException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping
	public EventDTO add(@RequestBody @Valid EventDTO event) {
		return eventService.add(event.convertToModel());
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid EventDTO event) {
		try {
			return ResponseEntity.ok(eventService.update(id, event.convertToModel()));
		} catch (ApiEventException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/activities/{id}")
	public ResponseEntity<?> addActivity(@PathVariable Long idEvent, @RequestBody Activity activity){
		try {
			return ResponseEntity.ok(eventService.addActivity(activity, idEvent));
		} catch (ApiEventException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{id}/deactivate")
	public ResponseEntity<?> deactivate(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(eventService.deactivate(id));
		} catch (ApiEventException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
