package br.edu.ifrn.laj.pdcorp.apisea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableJpaRepositories("br.edu.ifrn.laj.pdcorp.apisea")
@EntityScan("br.edu.ifrn.laj.pdcorp.apisea")
@ComponentScan("br.edu.ifrn.laj.pdcorp.apisea")
@SpringBootApplication
public class ApiSeaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiSeaApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("*").allowedOrigins("*");
			}
		};
	}
}