package br.edu.ifrn.laj.pdcorp.apisea.controllers;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.ifrn.laj.pdcorp.apisea.configs.UploadConfig;
import br.edu.ifrn.laj.pdcorp.apisea.enums.TypeImage;
import br.edu.ifrn.laj.pdcorp.apisea.services.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Image Endpoint", description = "Control of images", tags = "Image Endpoint")
@RestController
@RequestMapping("/upload")
public class ImageController {

	@Autowired
	private UploadService uploadService;
	@Autowired
	private UploadConfig config;

	private Logger logger = LoggerFactory.getLogger(ImageController.class);

	@ApiOperation(value = "Visualizar imagem por nome")
	@GetMapping("/{originalName}")
	public ResponseEntity<?> getImage(@PathVariable String originalName) throws IOException {
		try {
			byte[] thumbnail = this.uploadService.downloadThumbnail(originalName);
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(thumbnail);
		} catch (NoSuchFileException ex) {
			return ResponseEntity.badRequest()
					.body("A imagem ".concat(ex.getLocalizedMessage()).concat(" não foi encontrada."));
		}
	}

	@ApiOperation(value = "Adicionar imagem")
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> upload(@RequestParam("image") MultipartFile image,
			@RequestParam("type_image") TypeImage type) {
		try {
			String imageName = this.uploadService.uploadThumbnail(image, type);
			String virtualAccess = config.getRootVirtualAddress().concat(imageName);
			return new ResponseEntity<String>(virtualAccess, HttpStatus.OK);
		} catch (IOException e) {
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
