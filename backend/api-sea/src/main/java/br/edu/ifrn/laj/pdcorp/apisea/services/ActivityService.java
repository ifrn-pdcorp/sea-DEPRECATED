package br.edu.ifrn.laj.pdcorp.apisea.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.edu.ifrn.laj.pdcorp.apisea.configs.UploadConfig;
import br.edu.ifrn.laj.pdcorp.apisea.enums.ExceptionMessages;
import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiEventException;
import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiSubscriptionException;
import br.edu.ifrn.laj.pdcorp.apisea.exceptions.FileNotFoundException;
import br.edu.ifrn.laj.pdcorp.apisea.exceptions.FileStorageException;
import br.edu.ifrn.laj.pdcorp.apisea.models.Activity;
import br.edu.ifrn.laj.pdcorp.apisea.models.Event;
import br.edu.ifrn.laj.pdcorp.apisea.models.File;
import br.edu.ifrn.laj.pdcorp.apisea.models.User;
import br.edu.ifrn.laj.pdcorp.apisea.models.enums.UserType;
import br.edu.ifrn.laj.pdcorp.apisea.repositories.ActivityRepository;
import br.edu.ifrn.laj.pdcorp.apisea.repositories.EventRepository;
import br.edu.ifrn.laj.pdcorp.apisea.repositories.FileRepository;
import br.edu.ifrn.laj.pdcorp.apisea.repositories.UserRepository;

@Service
public class ActivityService {

	private final Path fileStorageLocation;

	@Autowired
	private ActivityRepository repository;
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private FileRepository fileRepository;

	public ActivityService(UploadConfig uploadConfig) {
		this.fileStorageLocation = Paths.get(uploadConfig.getSource()).toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception e) {
			throw new FileStorageException("Não foi possível criar o diretório onde os arquivos serão armazenados", e);
		}
	}

	public File saveFile(File file) {
		return this.fileRepository.save(file);
	}

	public Optional<File> loadFile(Long id) {
		return this.fileRepository.findById(id);
	}

	public void update(Activity activity) throws ApiEventException {
		Optional<Activity> opt = this.repository.findById(activity.getId());
		if (!opt.isPresent()) {
			throw new ApiEventException(ExceptionMessages.ACTIVITY_IS_NOT_VALID);
		}

		Activity existent = opt.get();
		BeanUtils.copyProperties(activity, existent, "id");
		this.repository.save(existent);
	}

	public File findByIdFile(Long id) throws ApiEventException {
		Optional<File> optional = fileRepository.findById(id);
		if (optional.isEmpty())
			throw new ApiEventException(ExceptionMessages.EVENT_DOESNT_EXISTS_DB);
		return optional.get();
	}

	public Object saveFileDb(String fileName, String fileType, long fileSize, long idActivity) {
		File file = new File(fileName, fileType, fileSize, idActivity);
		return fileRepository.save(file);
	}

	public String storeFile(Long idEvent, Long idActivity, Principal principal, MultipartFile file)
			throws ApiEventException, ApiSubscriptionException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		Optional<Event> optional = eventRepository.findById(idEvent);
		Optional<Activity> opt = this.repository.findById(idActivity);
		if (optional.isEmpty()) {
			throw new ApiEventException(ExceptionMessages.EVENT_DOESNT_EXISTS_DB);
		}

		if (!opt.isPresent()) {
			throw new ApiEventException(ExceptionMessages.ACTIVITY_DOESNT_EXIST_IN_EVENT);
		}

		User user = this.findUserAuthenticated(principal);

		if (UserType.STUDENT.equals(user.getType())) {
			throw new ApiEventException(ExceptionMessages.USER_REQUEST_FORBBIDEN);
		}

		Path targetLocation = this.fileStorageLocation.resolve(fileName);
		try {
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return fileName;

	}

	public Resource loadFileAsResource(Long idEvent, Long idActivity, Principal principal, String fileName)
			throws ApiEventException, ApiSubscriptionException {
		User user = this.findUserAuthenticated(principal);

		Optional<Activity> opt = this.repository.findById(idActivity);

		if (!opt.isPresent()) {
			throw new ApiEventException(ExceptionMessages.ACTIVITY_DOESNT_EXIST_IN_EVENT);
		}

		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {

				return resource;

			} else {
				throw new FileNotFoundException("File not found " + fileName);
			}
		} catch (Exception e) {
			throw new FileNotFoundException("File not found " + fileName, e);
		}
	}

	public Activity findById(Long id) throws ApiEventException {
		Optional<Activity> opt = this.repository.findById(id);
		if (!opt.isPresent()) {
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
