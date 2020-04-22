package br.com.tarefas.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import br.com.tarefas.modelo.Tarefa;

public interface TarefasRepository  extends ReactiveMongoRepository <Tarefa, String> {

}
