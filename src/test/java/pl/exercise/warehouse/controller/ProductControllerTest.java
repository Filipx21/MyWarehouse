package pl.exercise.warehouse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import pl.exercise.warehouse.controller.config.REST_TestConfig;
import pl.exercise.warehouse.mapper.ProductMapper;
import pl.exercise.warehouse.service.ProductService;

@DisplayName("Producer Controller")
@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = ProducerController.class)
@ComponentScan(basePackageClasses = {REST_TestConfig.class})
public class ProductControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductMapper mapper;

    @MockBean
    private ProductService service;



}
