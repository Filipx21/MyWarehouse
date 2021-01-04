package pl.exercise.warehouse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.exercise.warehouse.dto.ProducerDto;
import pl.exercise.warehouse.mapper.ProducerMapper;
import pl.exercise.warehouse.model.Producer;
import pl.exercise.warehouse.service.ProducerService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/producer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProducerController {

    private ProducerService producerService;
    private ProducerMapper producerMapper;

    public ProducerController(ProducerService producerService, ProducerMapper producerMapper) {
        this.producerService = producerService;
        this.producerMapper = producerMapper;
    }

    @GetMapping("/producer/{id}")
    public ResponseEntity<ProducerDto> getProducerById(@PathVariable("id") Long id){
        try{
            Producer producer = producerService.getById(id);
            ProducerDto producerDto = producerMapper.toProducerDto(producer);
            return ResponseEntity.ok().body(producerDto);
        }catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/producers")
    public ResponseEntity getAllProducers() {
        try {
            List<Producer> producers = producerService.getAll();
            List<ProducerDto> producersDto = producers.stream()
                    .map(producerMapper::toProducerDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(producersDto);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/producer")
    public ResponseEntity<ProducerDto> saveProducer(@Valid @RequestBody ProducerDto producer) {
        try {
            Producer _producer = producerMapper.toProducer(producer);
            Producer addedProducer = producerService.add(_producer);
            ProducerDto producerDto = producerMapper.toProducerDto(addedProducer);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(addedProducer.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(producerDto);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/producer/{id}")
    public ResponseEntity<Object> deleteProducer(@PathVariable("id") Long id) {
        try {
            if(producerService.deleteById(id)){
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
