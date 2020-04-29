package pl.exercise.warehouse.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.exercise.warehouse.model.Producer;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class ProducerServiceTest {

    @Test
    void getAll() {
    }

    @Test
    void getById() {
    }

    @Test
    void add() {
    }

    @Test
    void deleteById() {
    }

    private Producer prepareProducer(){
        Producer producer = new Producer();
        producer.setCompanyName("Waldemar");
        producer.setOwner("Edward Kosa");
        producer.setAddress("Warszawa ul. zmyslona 33/1");
        producer.setOpinion(9);
        producer.setProducts(new ArrayList<>());
        return producer;
    }
}