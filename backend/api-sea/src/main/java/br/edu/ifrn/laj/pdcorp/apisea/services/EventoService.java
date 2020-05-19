package br.edu.ifrn.laj.pdcorp.apisea.services;

import java.util.List;

import br.edu.ifrn.laj.pdcorp.apisea.models.Evento;

public interface EventoService {

	Evento add(Evento evento);

	Evento findById(Long id);

	List<Evento> findAll();

	Evento update(Evento evento);

	void remove(Evento evento);

}
