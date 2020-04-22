package br.com.tarefas.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.tarefas.modelo.Tarefa;
import br.com.tarefas.repository.TarefasRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

	private TarefasRepository tarefasRepository;
	
    public TarefaController(TarefasRepository tarefasRepository) { 
    	this.tarefasRepository = tarefasRepository;
    }

    @GetMapping
    public Flux<Tarefa> getAllTarefas() {
    	return tarefasRepository.findAll();
    }
	
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Tarefa>> getTarefa(@PathVariable String id) {
        return tarefasRepository.findById(id)
                .map(Tarefa -> ResponseEntity.ok(Tarefa))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Tarefa> saveTarefa(@RequestBody Tarefa Tarefa) {
        return tarefasRepository.save(Tarefa);
    }
    
    @PutMapping("{id}")
    public Mono<ResponseEntity<Tarefa>> updateTarefa(@PathVariable(value = "id") String id,
                                                       @RequestBody Tarefa tarefa) {
        return tarefasRepository.findById(id)
                .flatMap(existingTarefa -> {
                    existingTarefa.setDescricao(tarefa.getDescricao());
                    existingTarefa.setData(new Date());
                    existingTarefa.setStatus(tarefa.getStatus());
                    return tarefasRepository.save(existingTarefa);
                })
                .map(updateTarefa -> ResponseEntity.ok(updateTarefa))
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }
    
    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteTarefa(@PathVariable(value = "id") String id) {
        return tarefasRepository.findById(id)
                .flatMap(existingTarefa ->
                tarefasRepository.delete(existingTarefa)
                                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping
    public Mono<Void> deleteAllTarefas() {
        return tarefasRepository.deleteAll();
    }
    
	/*
	 * @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	 * public Flux<TarefaEvent> getTarefaEvents() { return
	 * Flux.interval(Duration.ofSeconds(5)) .map(val -> new TarefaEvent(val,
	 * "Tarefa Event") ); }
	 */
    
}
