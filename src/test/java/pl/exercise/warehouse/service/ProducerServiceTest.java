package pl.exercise.warehouse.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.exercise.warehouse.model.Producer;
import pl.exercise.warehouse.repository.ProducerRepository;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProducerServiceTest {

    @Mock
    private ProducerRepository producerRepository;

    @InjectMocks
    private ProducerService producerService;

    @Test
    void shouldGetAll() throws NullPointerException{
    }

    @Test
    void shouldThrowExceptionGetAll() throws NullPointerException{
    }

    @Test
    void shouldGetById() throws NullPointerException{
    }

    @Test
    void shouldThrowExceptionGetById() throws NullPointerException{
    }

    @Test
    void shouldAdd() throws NullPointerException {
        Producer expected = prepareProducer();

        when(producerRepository.save(expected)).thenReturn(expected);

        Producer result = producerService.add(expected);

        assertEquals(expected, result);
    }

    @Test
    void shouldThrowExceptionAdd() throws NullPointerException{
    }

    @Test
    void shouldThrowExceptionDeleteById() throws NullPointerException{
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