package br.edu.ifrn.laj.pdcorp.apisea.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ifrn.laj.pdcorp.apisea.dtos.EventDTO;
import br.edu.ifrn.laj.pdcorp.apisea.dtos.UploadFileDTO;
import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiEventException;
import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiSubscriptionException;
import br.edu.ifrn.laj.pdcorp.apisea.models.Activity;
import br.edu.ifrn.laj.pdcorp.apisea.models.File;
import br.edu.ifrn.laj.pdcorp.apisea.repositories.FileRepository;
import br.edu.ifrn.laj.pdcorp.apisea.services.ActivityService;
import br.edu.ifrn.laj.pdcorp.apisea.services.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Event Endpoint", description = "Control of events", tags = "Event Endpoint")
@RestController
@RequestMapping("/events")
public class EventController {

	@Autowired
	private EventService eventService;
	@Autowired
	private ActivityService actvityService;
	@Autowired
	private FileRepository fileRepository;

	@ApiOperation(value = "Listar todos os eventos")
	@GetMapping
	public List<EventDTO> findAll() {
		return eventService.findAllIsActive();
	}

	@ApiOperation(value = "Listar evento por id")
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(eventService.findById(id));
		} catch (ApiEventException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@ApiOperation(value = "Adicionar evento")
	@PostMapping
	public ResponseEntity<?> save(Principal principal, @RequestBody EventDTO event) {

		try {
			return ResponseEntity.ok(eventService.save(principal, event.convertToModel()));
		} catch (ApiEventException | ApiException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
		}
	}

	@ApiOperation(value = "Atualizar evento por id")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(Principal principal, @PathVariable Long id, @RequestBody EventDTO event) {
		try {
			return ResponseEntity.ok(eventService.update(principal, id, event.convertToModel()));
		} catch (ApiEventException | ApiException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@ApiOperation(value = "Ativar atividade por id do evento")
	@PutMapping("/{idEvent}/activities")
	public ResponseEntity<?> addActivity(@PathVariable Long idEvent, @RequestBody Activity activity) {
		try {
			return ResponseEntity.ok(eventService.addActivity(activity, idEvent));
		} catch (ApiEventException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@ApiOperation(value = "Desativar evento por id")
	@PutMapping("/{id}/deactivate")
	public ResponseEntity<?> deactivate(Principal principal, @PathVariable Long id) {
		try {
			return ResponseEntity.ok(eventService.deactivate(principal, id));
		} catch (ApiEventException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@ApiOperation(value = "Fazer upload de arquivos para atividade especifica por id do evento e id da atividade")
	@PostMapping("/event/{idEvent}/activity/{idActivity}/uploadFile")
	public UploadFileDTO uploadFile(@PathVariable Long idEvent, @PathVariable Long idActivity, Principal principal,
			@RequestParam("file") MultipartFile file) throws ApiEventException, ApiSubscriptionException {
		String fileName = actvityService.storeFile(idEvent, idActivity, principal, file);
		String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("events" + "/" + "event" + "/" + idEvent + "/" + "activity" + "/" + idActivity + "/downloadFile/")
				.path(fileName).toUriString();

		File fileUpload = new File(fileName.toString(), file.getContentType(), file.getSize(), idActivity);

		fileRepository.save(fileUpload);

		return new UploadFileDTO(fileName.toString(), fileDownloadUrl, file.getContentType(), file.getSize());
	}

	@ApiOperation(value = "Fazer download de arquivos por id do evento e id da inscricao")
	@GetMapping("/event/{idEvent}/activity/{idActivity}/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable Long idEvent, @PathVariable Long idActivity,
			Principal principal, @PathVariable String fileName, HttpServletRequest request)
			throws ApiEventException, ApiSubscriptionException {
		Resource resource = (Resource) actvityService.loadFileAsResource(idEvent, idActivity, principal, fileName);
		String contentType = null;
		try {
			contentType = request.getServletContext()
					.getMimeType(((org.springframework.core.io.Resource) resource).getFile().getAbsolutePath());
		} catch (Exception e) {
			ResponseEntity.badRequest().body(e.getMessage());
		}
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(
				HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + ((org.springframework.core.io.Resource) resource).getFilename() + "\"")
				.body(resource);

	}
}
