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
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrn.laj.pdcorp.apisea.dtos.SubscriptionDTO;
import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiSubscriptionException;
import br.edu.ifrn.laj.pdcorp.apisea.models.Subscription;
import br.edu.ifrn.laj.pdcorp.apisea.services.SubscriptionService;

@RestController
public class SubscriptionController {
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	@GetMapping("/subscriptions/{id}")
	public ResponseEntity<?> findId(@PathVariable Long id){
		try {
			return ResponseEntity.ok(subscriptionService.findById(id));
		} catch (ApiSubscriptionException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/users/{userId}/subscriptions")
	public ResponseEntity<?> findAllByUserId(@PathVariable Long userId){
		try {
			return ResponseEntity.ok(subscriptionService.findAllByUserId(userId));
		} catch (ApiSubscriptionException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/events/{eventId}/subscriptions")
	public ResponseEntity<?> findAllByEventId(@PathVariable Long eventId){
		try {
			return ResponseEntity.ok(subscriptionService.findAllByEventId(eventId));
		} catch (ApiSubscriptionException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/subscriptions")
	public ResponseEntity<?> add(@RequestBody @Valid Subscription subscription){
		try {
			return ResponseEntity.ok(subscriptionService.add(subscription));
		} catch (ApiSubscriptionException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/subscriptions/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid Subscription subscription){
		try {
			return ResponseEntity.ok(subscriptionService.update(id, subscription));
		} catch (ApiSubscriptionException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
