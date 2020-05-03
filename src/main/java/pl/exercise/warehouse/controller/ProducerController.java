package pl.exercise.warehouse.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.exercise.warehouse.service.ProducerService;

@RestController
@RequestMapping("/api/producer")
public class ProducerController {

    private ProducerService producerService;


}
