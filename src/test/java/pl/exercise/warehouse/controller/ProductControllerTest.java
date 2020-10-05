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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.exercise.warehouse.controller.config.REST_TestConfig;
import pl.exercise.warehouse.dto.CategoryDto;
import pl.exercise.warehouse.dto.ProducerDto;
import pl.exercise.warehouse.dto.ProductDto;
import pl.exercise.warehouse.mapper.ProductMapper;
import pl.exercise.warehouse.model.Producer;
import pl.exercise.warehouse.model.Product;
import pl.exercise.warehouse.service.ProductService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Product Controller")
@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = ProductController.class)
@ComponentScan(basePackageClasses = {REST_TestConfig.class})
class ProductControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductMapper mapper;

    @MockBean
    private ProductService service;

    @Test
    @DisplayName("Find product by id - return product")
    void shouldFindProductById() throws Exception {
        var productFromWeb = prepareProductDto();
        productFromWeb.setId(1L);
        var expected = mapper.toProduct(productFromWeb);

        when(service.getById(productFromWeb.getId()))
                .thenReturn(expected);

        var mvcResult = mockMvc.perform(
                get("/api/product/product/{id}", productFromWeb.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isOk()
                ).andReturn();

        var jsonString = mvcResult.getResponse().getContentAsString();
        var result = objectMapper.readValue(jsonString, Product.class);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Find product by id - return Error 404")
    void shouldReturnError404ProductById() throws Exception {

    }

    private ProductDto prepareProductDto() {
        var product = new ProductDto();
        product.setName("Rower");
        product.setQuantity(3);
        product.setPrice(699.99);
        product.setProducer(new ProducerDto());
        product.setCategory(new CategoryDto());
        product.setPhotoUri("/category/photo/1");
        product.setOpinion(9);
        return product;
    }

}
