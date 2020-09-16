package br.edu.ifrn.laj.pdcorp.apisea.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.laj.pdcorp.apisea.enums.ExceptionMessages;
import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiEventException;
import br.edu.ifrn.laj.pdcorp.apisea.models.Activity;
import br.edu.ifrn.laj.pdcorp.apisea.repositories.ActivityRepository;

@Service
public class ActivityService {

	@Autowired
	private ActivityRepository repository;

	public void update(Activity activity) throws ApiEventException {
		Optional<Activity> opt = this.repository.findById(activity.getId());
		if(!opt.isPresent()) {
			throw new ApiEventException(ExceptionMessages.ACTIVITY_IS_NOT_VALID);
		}
		
		Activity existent = opt.get();
		BeanUtils.copyProperties(activity, existent, "id");
		this.repository.save(existent);
	}
	
	
	public Activity findById(Long id) throws ApiEventException {
		Optional<Activity> opt = this.repository.findById(id);
		if(!opt.isPresent()) {
			throw new ApiEventException(ExceptionMessages.ACTIVITY_IS_NOT_VALID);
		}
		
		return opt.get();
	}
}
