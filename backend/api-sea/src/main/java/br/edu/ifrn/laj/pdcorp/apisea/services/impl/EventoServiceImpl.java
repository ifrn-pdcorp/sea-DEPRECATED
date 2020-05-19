package br.edu.ifrn.laj.pdcorp.apisea.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.laj.pdcorp.apisea.models.Evento;
import br.edu.ifrn.laj.pdcorp.apisea.repositories.EventoRepository;
import br.edu.ifrn.laj.pdcorp.apisea.services.EventoService;

@Service
public class EventoServiceImpl implements EventoService {

	@Autowired
	private EventoRepository eventoRepository;

	@Override
	public Evento add(Evento evento) {
		return eventoRepository.save(evento);
	}

	@Override
	public Evento findById(Long id) {
		Optional<Evento> optional = eventoRepository.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	public List<Evento> findAll() {
		return eventoRepository.findAll();
	}

	@Override
	public Evento update(Evento evento) {
		return eventoRepository.save(evento);
	}

	@Override
	public void remove(Evento evento) {
		eventoRepository.delete(evento);
	}

}
