package pl.exercise.warehouse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import pl.exercise.warehouse.controller.config.REST_TestConfig;
import pl.exercise.warehouse.dto.ProducerDto;
import pl.exercise.warehouse.mapper.ProducerMapper;
import pl.exercise.warehouse.model.Producer;
import pl.exercise.warehouse.model.Product;
import pl.exercise.warehouse.service.ProducerService;

import java.util.ArrayList;

@DisplayName("Producer Controller")
@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = ProducerController.class)
@ComponentScan(basePackageClasses = {REST_TestConfig.class})
class ProducerControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProducerMapper mapper;

    @MockBean
    private ProducerService service;

    @Test
    void test() throws Exception {
        System.out.println(mapper.toProducer(prepaereProducerDto()));
    }


    private ProducerDto prepaereProducerDto() {
        var producer = new ProducerDto();
        producer.setCompanyName("TeleFront");
        producer.setOwner("Marek Jakowski");
        producer.setAddress("Test 44-231 Testowo");
        producer.setOpinion(6);
        producer.setProducts(new ArrayList<>());
        return producer;
    }

}
