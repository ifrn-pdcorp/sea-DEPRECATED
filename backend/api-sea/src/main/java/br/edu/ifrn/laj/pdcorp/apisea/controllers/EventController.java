package br.edu.ifrn.laj.pdcorp.apisea.controllers;

import java.security.Principal;
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
	public ResponseEntity<?> add(Principal principal, @RequestBody @Valid EventDTO event) {

		try {
			return ResponseEntity.ok(eventService.add(principal, event.convertToModel()));
		} catch (ApiEventException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(Principal principal, @PathVariable Long id, @RequestBody @Valid EventDTO event) {
		try {
			return ResponseEntity.ok(eventService.update(principal, id, event.convertToModel()));
		} catch (ApiEventException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{idEvent}/activities")
	public ResponseEntity<?> addActivity(@PathVariable Long idEvent, @RequestBody Activity activity){
		try {
			return ResponseEntity.ok(eventService.addActivity(activity, idEvent));
		} catch (ApiEventException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{id}/deactivate")
	public ResponseEntity<?> deactivate(Principal principal, @PathVariable Long id) {
		try {
			return ResponseEntity.ok(eventService.deactivate(principal, id));
		} catch (ApiEventException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
