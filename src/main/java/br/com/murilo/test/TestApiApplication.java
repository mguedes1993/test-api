package br.com.murilo.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({ "br.com.murilo.test.*" })
@EntityScan("br.com.murilo.test.*")
@EnableJpaRepositories("br.com.murilo.test.*")
public class TestApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(TestApiApplication.class);
	}
}
