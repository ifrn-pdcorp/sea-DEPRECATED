package br.edu.ifrn.laj.pdcorp.apisea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.edu.ifrn.laj.pdcorp.apisea.configs.FileStorageConfig;

@EnableJpaRepositories("br.edu.ifrn.laj.pdcorp.apisea")
@EntityScan("br.edu.ifrn.laj.pdcorp.apisea")
@EnableConfigurationProperties({
	FileStorageConfig.class
})
@ComponentScan("br.edu.ifrn.laj.pdcorp.apisea")
@SpringBootApplication
public class ApiSeaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiSeaApplication.class, args);
	}

}