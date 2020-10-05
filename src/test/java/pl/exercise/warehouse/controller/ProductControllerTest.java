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
import pl.exercise.warehouse.model.Product;
import pl.exercise.warehouse.service.ProductService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status()
                .isOk()
        ).andReturn();

        var jsonString = mvcResult.getResponse().getContentAsString();
        var result = objectMapper.readValue(jsonString, Product.class);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Find product by id - return Error 404")
    void shouldReturnError404ProductById() throws Exception {
        var productFromWeb = prepareProductDto();
        productFromWeb.setId(1L);

        when(service.getById(productFromWeb.getId()))
                .thenThrow(NullPointerException.class);

        var mvcResult = mockMvc.perform(
                get("/api/product/product/{id}", productFromWeb.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status()
                .isNotFound()
        ).andReturn();

        var result = mvcResult.getResponse().getContentAsString();

        assertEquals("", result);
    }

    @Test
    @DisplayName("Find all products - return products list")
    void shouldFindAllProducts() throws Exception {
        var product1 = prepareProductDto();
        var product2 = prepareProductDto();
        var product3 = prepareProductDto();
        List<Product> expected = new ArrayList<>();
        expected.add(mapper.toProduct(product1));
        expected.add(mapper.toProduct(product2));
        expected.add(mapper.toProduct(product3));

        when(service.getAll()).thenReturn(expected);

        var mvcResult = mockMvc.perform(
                get("/api/product/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status()
                .isOk()
        ).andReturn();

        var jsonString = mvcResult.getResponse().getContentAsString();
        Product[] products = objectMapper.readValue(jsonString, Product[].class);
        List<Product> result = Arrays.asList(products);

        assertEquals(expected, result);
        assertEquals(3, result.size());
    }

    @Test
    @DisplayName("Find all products - return Error 500")
    void shouldReturnError500FindAllProducts() throws Exception {

        when(service.getAll()).thenThrow(NullPointerException.class);

        var mvcResult = mockMvc.perform(
                get("/api/product/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status()
                .isInternalServerError()
        ).andReturn();

        var result = mvcResult.getResponse().getContentAsString();

        assertEquals("", result);
    }

    @Test
    @DisplayName("Save product - return URI")
    void shouldSaveProduct() throws Exception {
        var productFromWeb = prepareProductDto();
        var expected = mapper.toProduct(productFromWeb);

        when(service.add(expected)).thenReturn(expected);

        var mvcResult = mockMvc.perform(
                post("/api/product/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productFromWeb))
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status()
                .isCreated()
        ).andReturn();

        var jsonString = mvcResult.getResponse().getContentAsString();
        var result = objectMapper.readValue(jsonString, Product.class);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Save product - return Error 500")
    void shouldReturnError500SaveProduct() throws Exception {
        var productFromWeb = prepareProductDto();

        when(service.add(null)).thenThrow(NullPointerException.class);

        var mvcResult = mockMvc.perform(
                post("/api/product/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productFromWeb))
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status()
                .isInternalServerError()
        ).andReturn();

        var result = mvcResult.getResponse().getContentAsString();

        assertEquals("", result);
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
