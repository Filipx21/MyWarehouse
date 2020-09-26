package pl.exercise.warehouse.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import pl.exercise.warehouse.controller.config.REST_TestConfig;
import pl.exercise.warehouse.model.Producer;
import pl.exercise.warehouse.model.Product;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = ProducerController.class)
@ComponentScan(basePackageClasses = {REST_TestConfig.class})
public class ProducerControllerTest {



    private Producer prepaereProducer() {
        var producer = new Producer();
        producer.setCompanyName("TeleFront");
        producer.setOwner("Marek Jakowski");
        producer.setAddress("Test 44-231 Testowo");
        producer.setOpinion(6);
        producer.setProducts(new ArrayList<>());
        return producer;
    }

}
