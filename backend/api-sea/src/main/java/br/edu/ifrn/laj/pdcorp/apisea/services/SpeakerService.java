package br.edu.ifrn.laj.pdcorp.apisea.services;

import java.util.List;
import java.util.Optional;

import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.edu.ifrn.laj.pdcorp.apisea.enums.ExceptionMessages;
import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiEventException;
import br.edu.ifrn.laj.pdcorp.apisea.models.Speaker;
import br.edu.ifrn.laj.pdcorp.apisea.repositories.SpeakerRepository;

@Service
public class SpeakerService {

	@Autowired
	private SpeakerRepository repository;

	public Speaker save(Speaker speaker) throws ApiException {
		try {
			return this.repository.save(speaker);
		} catch (DataIntegrityViolationException ex) {
			throw new ApiException(
					ExceptionMessages.DATA_VALIDATION.getDescription().concat(ex.getMostSpecificCause().getMessage()));
		}
	}

	public Speaker update(Speaker speaker) throws ApiEventException, ApiException {
		Speaker existent = findById(speaker.getId());
		BeanUtils.copyProperties(speaker, existent, "id");
		return save(existent);
	}

	public List<Speaker> findAll() {
		return this.repository.findAll();
	}

	public Speaker findById(Long id) throws ApiEventException {
		Optional<Speaker> opt = this.repository.findById(id);
		if (!opt.isPresent()) {
			throw new ApiEventException(ExceptionMessages.SPEAKER_DOESNT_EXIST_DB);
		}
		return opt.get();
	}

}
