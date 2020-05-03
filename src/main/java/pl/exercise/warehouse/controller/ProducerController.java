package pl.exercise.warehouse.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.exercise.warehouse.dto.ProducerDto;
import pl.exercise.warehouse.mapper.ProducerMapper;
import pl.exercise.warehouse.model.Producer;
import pl.exercise.warehouse.service.ProducerService;

@RestController
@RequestMapping("/api/producer")
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


}
