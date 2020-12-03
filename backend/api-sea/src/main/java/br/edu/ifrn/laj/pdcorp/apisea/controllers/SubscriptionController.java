package br.edu.ifrn.laj.pdcorp.apisea.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiEventException;
import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiSubscriptionException;
import br.edu.ifrn.laj.pdcorp.apisea.models.Activity;
import br.edu.ifrn.laj.pdcorp.apisea.models.Subscription;
import br.edu.ifrn.laj.pdcorp.apisea.services.SubscriptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Subscription Endpoint", description = "Control of subscriptions", tags="Subscription Endpoint")
@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

	@Autowired
	private SubscriptionService subscriptionService;
	
	
	@ApiOperation(value = "Visualizar inscrição por id")
	@GetMapping("/{id}")
	public ResponseEntity<?> findId(Principal principal, @PathVariable Long id) {
		try {
			return ResponseEntity.ok(subscriptionService.findById(principal, id));
		} catch (ApiSubscriptionException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@ApiOperation(value = "Listar todos os usuários por id")
	@GetMapping
	public ResponseEntity<?> findAllByUserId(Principal principal) {
		try {
			return ResponseEntity.ok(subscriptionService.findAllByUser(principal));
		} catch (ApiSubscriptionException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@ApiOperation(value = "Visualizar inscrições por id do evento")
	@GetMapping("/events/{eventId}")
	public ResponseEntity<?> findAllByEventId(Principal principal, @PathVariable Long eventId) {
		try {
			return ResponseEntity.ok(subscriptionService.findAllByEventId(principal, eventId));
		} catch (ApiSubscriptionException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@ApiOperation(value = "Adicionar inscrição")
	@PostMapping
	public ResponseEntity<?> add(Principal principal, @RequestBody @Valid Subscription subscription) {
		try {
			return ResponseEntity.ok(subscriptionService.add(principal, subscription));
		} catch (ApiSubscriptionException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@ApiOperation(value = "Atualizar atividade por id da inscrição e o id da atividade")
	@PutMapping("/{subscriptionId}/activities/{activityId}")
	public ResponseEntity<?> addNewActivity(Principal principal, @PathVariable Long subscriptionId, @RequestBody Long activityId){
		try {
			return ResponseEntity.ok(this.subscriptionService.registerNewActivity(principal, activityId, subscriptionId));
		} catch (ApiSubscriptionException | ApiEventException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}s
	}

	@ApiOperation(value = "Atualizar inscrição por id ")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(Principal principal, @PathVariable Long id,
			@RequestBody @Valid Subscription subscription) {
		try {
			return ResponseEntity.ok(subscriptionService.update(principal, id, subscription));
		} catch (ApiSubscriptionException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@ApiOperation(value = "Deletar inscrição por id ")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(Principal principal, @PathVariable Long id){
		try {
			subscriptionService.delete(principal, id);
			return ResponseEntity.accepted().build();
		} catch (ApiSubscriptionException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
