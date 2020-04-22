package br.com.tarefas;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;

import br.com.tarefas.modelo.Tarefa;
import br.com.tarefas.repository.TarefasRepository;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class TarefasApplication {

	public static void main(String[] args) {
		SpringApplication.run(TarefasApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init (ReactiveMongoOperations operations, TarefasRepository repository) {
		return args -> {
			Flux<Tarefa> tarefaFlux = Flux.just(
					
					new Tarefa(null, "Concluir projeto tarefas", new Date(), false),
					new Tarefa(null, "Fazer compras", new Date(), true),
					new Tarefa(null, "Marcar Reunião", new Date(), false),
					new Tarefa(null, "Fazer call", new Date(), true))
					.flatMap(repository::save);

			tarefaFlux
					.thenMany(repository.findAll())
					.subscribe(System.out::println);
		};
	}
}
