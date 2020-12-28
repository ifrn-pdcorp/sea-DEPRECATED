package br.edu.ifrn.laj.pdcorp.apisea.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import br.edu.ifrn.laj.pdcorp.apisea.configs.UploadConfig;
import br.edu.ifrn.laj.pdcorp.apisea.enums.TypeImage;

@Service
public class UploadService {

	@Autowired
	private UploadConfig uploadConfig;

	public String uploadThumbnail(MultipartFile file, TypeImage type) throws IOException {
		UUID idImage = UUID.randomUUID();
		StringBuilder nameWithPrefix = new StringBuilder(type.getPrefix()).append(file.getOriginalFilename()).append(idImage.toString());
		StringBuilder path = new StringBuilder(uploadConfig.getSource());
		path.append(File.separator).append(nameWithPrefix);
		Path source = Paths.get(path.toString());

		Files.copy(file.getInputStream(), source, StandardCopyOption.REPLACE_EXISTING);
		return nameWithPrefix.toString();
	}
	
	public byte[] downloadThumbnail(String imageName) throws IOException, NoSuchFileException {
		
		StringBuilder pathBuilder = new StringBuilder(uploadConfig.getSource());
		pathBuilder.append(File.separator).append(imageName);
		return Files.readAllBytes(Paths.get(pathBuilder.toString()));
	}
}
