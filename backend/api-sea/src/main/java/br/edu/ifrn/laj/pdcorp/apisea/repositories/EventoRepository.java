package br.edu.ifrn.laj.pdcorp.apisea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrn.laj.pdcorp.apisea.models.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {

}
