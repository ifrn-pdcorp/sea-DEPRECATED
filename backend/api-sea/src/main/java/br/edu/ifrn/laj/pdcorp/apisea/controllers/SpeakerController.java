package br.edu.ifrn.laj.pdcorp.apisea.controllers;

import java.util.List;

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

import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiEventException;
import br.edu.ifrn.laj.pdcorp.apisea.models.Speaker;
import br.edu.ifrn.laj.pdcorp.apisea.services.SpeakerService;

@RestController
@RequestMapping("/speakers")
public class SpeakerController {
	
	@Autowired
	private SpeakerService service;
	
	@PostMapping
	public ResponseEntity<Speaker> save(@RequestBody Speaker speaker){
		return ResponseEntity.ok(this.service.save(speaker));
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Speaker speaker){
		try {
			return ResponseEntity.ok(this.service.update(speaker));
		} catch (ApiEventException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Speaker>> listAll(){
		return ResponseEntity.ok(service.findAll());
	}
	
	public ResponseEntity<?> findById(@PathVariable Long id){
		try {
			return ResponseEntity.ok(service.findById(id));
		} catch (ApiEventException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}

}
