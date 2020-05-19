package br.edu.ifrn.laj.pdcorp.apisea.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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

import br.edu.ifrn.laj.pdcorp.apisea.models.Evento;
import br.edu.ifrn.laj.pdcorp.apisea.services.EventoService;

@RestController
@RequestMapping("/eventos")
public class EventoResource {
	
	@Autowired
	private EventoService eventoService;
	
	@GetMapping
	public List<Evento> findAll() {
		return eventoService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Evento> findById(@PathVariable Long id) {
		Evento evento = eventoService.findById(id);
		if (evento == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(evento);
	}

	@PostMapping
	public Evento addProduto(@RequestBody @Valid Evento evento) {
		return eventoService.add(evento);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Evento> atualizar(@PathVariable Long id, @RequestBody @Valid Evento evento) {
		Evento existente = eventoService.findById(id);
		if (existente == null)
			return ResponseEntity.notFound().build();
		BeanUtils.copyProperties(evento, existente, "id");
		existente = eventoService.update(existente);
		return ResponseEntity.ok(existente);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Evento evento = eventoService.findById(id);
		if (evento == null)
			return ResponseEntity.notFound().build();
		eventoService.remove(evento);
		return ResponseEntity.noContent().build();
	}

}
