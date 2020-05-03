package pl.exercise.warehouse.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import pl.exercise.warehouse.model.Producer;
import pl.exercise.warehouse.repository.ProducerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProducerService {

    private final Logger logger = LoggerFactory.getLogger(ProducerService.class);
    private ProducerRepository producerRepository;

    public ProducerService(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    public List<Producer> getAll() {
        List<Producer> products = producerRepository.findAll();
        if (products.size() > 0) {
            logger.info("Producers found");
            return products;
        }
        logger.warn("Problem finding producers");
        throw new NullPointerException("There isn't any producers");
    }

    public Producer getById(Long id) {
        Optional<Producer> optionalProducer = producerRepository.findById(id);
        if (optionalProducer.isPresent()) {
            logger.info("Producer found");
            return optionalProducer.get();
        }
        logger.warn("Problem finding producer");
        throw new NullPointerException("There isn't producer with id = " + id);
    }

    public Producer add(Producer producer) {
        Producer copy = producer;
        if (copy != null) {
            logger.info("Producer added");
            return producerRepository.save(copy);
        }
        logger.warn("Problem while adding producer");
        throw new NullPointerException("Problem while adding producer");
    }

    public void deleteById(Long id) {
        Optional<Producer> optionalProduct = producerRepository.findById(id);
        if (optionalProduct.isPresent()) {
            logger.info("Producer deleted");
            producerRepository.deleteById(id);
        } else {
            logger.warn("Problem while deleting producer");
            throw new NullPointerException("Problem while deleting producer");
        }
    }

}
