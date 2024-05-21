package br.com.alura.literalura.Literalura.One;

import br.com.alura.literalura.Literalura.One.principal.Principal;
import br.com.alura.literalura.Literalura.One.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraOneApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraOneApplication.class, args);
	}
	@Autowired
	private LivroRepository repositorio;

	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal(repositorio);
		principal.exibeMenu();
	}
}
