package pl.exercise.warehouse.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.exercise.warehouse.model.Producer;
import pl.exercise.warehouse.repository.ProducerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@DisplayName("Producer Service")
@ExtendWith(MockitoExtension.class)
class ProducerServiceTest {

    @Mock
    private ProducerRepository producerRepository;

    @InjectMocks
    private ProducerService producerService;

    @Test
    @DisplayName("Get all products - return List<Product>")
    void shouldGetAll() throws NullPointerException {
        Producer producer1 = prepareProducer();
        Producer producer2 = prepareProducer();
        Producer producer3 = prepareProducer();
        List<Producer> expected = List.of(producer1, producer2, producer3);

        when(producerRepository.findAll()).thenReturn(expected);

        List<Producer> result = producerService.getAll();

        assertEquals(expected.size(), result.size());
        assertEquals(expected, result);
        assertEquals(expected.get(1), result.get(1));
    }

    @Test
    @DisplayName("Get all products - throw NullPointerException")
    void shouldThrowExceptionGetAll() throws NullPointerException {
        List<Producer> expected = new ArrayList<>();

        when(producerRepository.findAll()).thenReturn(expected);

        assertThrows(NullPointerException.class, () ->
                producerService.getAll()
        );
    }

    @Test
    @DisplayName("Get by id - return Product")
    void shouldGetById() throws NullPointerException {
        Producer expected = prepareProducer();
        expected.setId(1L);

        when(producerRepository.findById(expected.getId())).thenReturn(Optional.of(expected));

        Producer result = producerService.getById(expected.getId());

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get by id - throw NullPointerException")
    void shouldThrowExceptionGetById() throws NullPointerException {
        Producer expected = prepareProducer();
        expected.setId(1L);

        when(producerRepository.findById(expected.getId())).thenReturn(Optional.empty());

        assertThrows(NullPointerException.class, () ->
                producerService.getById(expected.getId())
        );
    }

    @Test
    @DisplayName("Add new product - return Product")
    void shouldAdd() throws NullPointerException {
        Producer expected = prepareProducer();

        when(producerRepository.save(expected)).thenReturn(expected);

        Producer result = producerService.add(expected);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Add new product - throw NullPointerException")
    void shouldThrowExceptionAdd() throws NullPointerException {
        Producer expected = null;

        assertThrows(NullPointerException.class, () ->
                producerService.add(expected)
        );
    }

    @Test
    @DisplayName("Delete by id - throw NullPointerException")
    void shouldThrowExceptionDeleteById() throws NullPointerException {
        Producer expected = prepareProducer();

        when(producerRepository.findById(expected.getId())).thenReturn(Optional.empty());

        assertThrows(NullPointerException.class, () ->
                producerService.deleteById(expected.getId())
        );
    }

    private Producer prepareProducer() {
        Producer producer = new Producer();
        producer.setCompanyName("Waldemar");
        producer.setOwner("Edward Kosa");
        producer.setAddress("Warszawa ul. zmyslona 33/1");
        producer.setOpinion(9);
        producer.setProducts(new ArrayList<>());
        return producer;
    }

}
