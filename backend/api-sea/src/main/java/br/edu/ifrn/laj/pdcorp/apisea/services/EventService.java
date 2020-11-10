package br.edu.ifrn.laj.pdcorp.apisea.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.edu.ifrn.laj.pdcorp.apisea.configs.FileStorageConfig;
import br.edu.ifrn.laj.pdcorp.apisea.dtos.EventDTO;
import br.edu.ifrn.laj.pdcorp.apisea.enums.ExceptionMessages;
import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiEventException;
import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiSubscriptionException;
import br.edu.ifrn.laj.pdcorp.apisea.exceptions.FileNotFoundException;
import br.edu.ifrn.laj.pdcorp.apisea.exceptions.FileStorageException;
import br.edu.ifrn.laj.pdcorp.apisea.models.Activity;
import br.edu.ifrn.laj.pdcorp.apisea.models.Event;
import br.edu.ifrn.laj.pdcorp.apisea.models.Subscription;
import br.edu.ifrn.laj.pdcorp.apisea.models.User;
import br.edu.ifrn.laj.pdcorp.apisea.models.enums.UserType;
import br.edu.ifrn.laj.pdcorp.apisea.repositories.ActivityRepository;
import br.edu.ifrn.laj.pdcorp.apisea.repositories.EventRepository;
import br.edu.ifrn.laj.pdcorp.apisea.repositories.SubscriptionRepository;
import br.edu.ifrn.laj.pdcorp.apisea.repositories.UserRepository;

@Service
public class EventService {
	
	private final Path fileStorageLocation;

	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ActivityRepository repository;
	@Autowired
	private SubscriptionRepository subscriptionRepository;

	public EventService(FileStorageConfig fileStorageConfig) {
		this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir())
				.toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception e) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored", e);
		}
	}

	public EventDTO add(Principal principal, Event event) throws ApiEventException {
		User user = this.findUserAuthenticated(principal);
		event.setActive(true);
		event.setOwner(user);
		if (UserType.STUDENT.equals(user.getType()))
			throw new ApiEventException(ExceptionMessages.USER_REQUEST_FORBBIDEN);
		return EventDTO.convertFromModel(eventRepository.save(event));
	}
	
	public EventDTO update(Event event) {
		return EventDTO.convertFromModel(eventRepository.save(event));
	}

	public EventDTO findById(Long id) throws ApiEventException {
		Optional<Event> optional = eventRepository.findById(id);
		if (optional.isEmpty())
			throw new ApiEventException(ExceptionMessages.EVENT_DOESNT_EXISTS_DB);
		return EventDTO.convertFromModel(optional.get());
	}
	
	private Event findModelById(Long id) throws ApiEventException {
		Optional<Event> optional = eventRepository.findById(id);
		if (optional.isEmpty())
			throw new ApiEventException(ExceptionMessages.EVENT_DOESNT_EXISTS_DB);
		return optional.get();
	}
	
	
	public List<EventDTO> findAll() {
		List<Event> events = eventRepository.findAll();
		return EventDTO.convertFromModel(events);
	}

	public List<EventDTO> findAllIsActive() {
		List<Event> events = eventRepository.findAllByActiveIsTrue();
		return EventDTO.convertFromModel(events);
	}
	
	public EventDTO addActivity(Activity activity, Long idEvent) throws ApiEventException {
		Event event = this.findModelById(idEvent);
	    event.addActivity(activity);
	    return this.update(event);
	}

	public EventDTO update(Principal principal, Long id, Event event) throws ApiEventException {
		Optional<Event> optional = eventRepository.findById(id);
		if (optional.isEmpty())
			throw new ApiEventException(ExceptionMessages.EVENT_DOESNT_EXISTS_DB);

		Event existent = optional.get();
		
		User user = this.findUserAuthenticated(principal);

		if (!existent.getOwner().getId().equals(user.getId()))
			throw new ApiEventException(ExceptionMessages.USER_REQUEST_FORBBIDEN);

		BeanUtils.copyProperties(event, existent, "id", "active", "owner");
		return EventDTO.convertFromModel(eventRepository.save(existent));
	}

	public EventDTO deactivate(Principal principal, Long id) throws ApiEventException {
		Optional<Event> optional = eventRepository.findById(id);
		if (optional.isEmpty())
			throw new ApiEventException(ExceptionMessages.EVENT_DOESNT_EXISTS_DB);
		
		Event event = optional.get();
		User user = this.findUserAuthenticated(principal);

		if (!event.getOwner().getId().equals(user.getId()))
			throw new ApiEventException(ExceptionMessages.USER_REQUEST_FORBBIDEN);
		
		event.setActive(false);
		return EventDTO.convertFromModel(eventRepository.save(event));
	}

	public String storeFile(Long idEvent, Long idActivity, Principal principal, MultipartFile file) throws ApiEventException, ApiSubscriptionException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		Optional<Event> optional = eventRepository.findById(idEvent);
		Optional<Activity> opt = this.repository.findById(idActivity);
		if(optional.isEmpty()) {
			throw new ApiEventException(ExceptionMessages.EVENT_DOESNT_EXISTS_DB);
		}
			
		if(!opt.isPresent()) {
			throw new ApiEventException(ExceptionMessages.ACTIVITY_DOESNT_EXIST_IN_EVENT);
		}
		
		User user = this.findUserAuthenticated(principal);
		
		if (UserType.STUDENT.equals(user.getType())) {
			throw new ApiEventException(ExceptionMessages.USER_REQUEST_FORBBIDEN);
		}
		
		try {
			if(fileName.contains("..")) {
				throw new FileStorageException("File name contains invalid path sequence "
			+ fileName);
			}
			
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			
			return fileName;
		} catch (Exception e) {
			throw new FileStorageException("Could not store file " 
		+ fileName 
		+ "Please try again", e);
		}
	}
	
	public Resource loadFileAsResource(Long idEvent, Long idSubscription, Principal principal, String fileName) throws ApiEventException, ApiSubscriptionException {
		User user = this.findUserAuthenticated(principal);
		Subscription subscription = this.findSubscriptionById(idSubscription);
		
		if (!(user.getId().equals(subscription.getUser().getId())
				|| user.getId().equals(subscription.getEvent().getOwner().getId())))
			throw new ApiSubscriptionException(ExceptionMessages.USER_REQUEST_FORBBIDEN);
		
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if(resource.exists()) {
				return resource;
			}else {
				throw new FileNotFoundException("File not found " 
						+ fileName);
			}
		} catch (Exception e) {
			throw new FileNotFoundException("File not found " 
					+ fileName , e);
		}
	}
	
	private Subscription findSubscriptionById(Long id) throws ApiSubscriptionException {
		Optional<Subscription> optional = subscriptionRepository.findById(id);
		if (optional.isEmpty())
			throw new ApiSubscriptionException(ExceptionMessages.SUBSCRIPTION_DOESNT_EXISTS_DB);
		return optional.get();
	}

	public Activity findByIdActivity(Long id) throws ApiEventException {
		Optional<Activity> opt = this.repository.findById(id);
		if(!opt.isPresent()) {
			throw new ApiEventException(ExceptionMessages.ACTIVITY_IS_NOT_VALID);
		}
		
		return opt.get();
	}
	
	private User findUserAuthenticated(Principal principal) throws ApiEventException {
		User user = userRepository.findByEmail(principal.getName());
		if (user == null)
			throw new ApiEventException(ExceptionMessages.USER_DOESNT_EXISTS_DB);
		return user;
	}

}
