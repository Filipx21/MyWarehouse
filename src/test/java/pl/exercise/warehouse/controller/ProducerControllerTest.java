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
import pl.exercise.warehouse.config.AppConfig;
import pl.exercise.warehouse.dto.ProducerDto;
import pl.exercise.warehouse.mapper.ProducerMapper;
import pl.exercise.warehouse.model.Producer;
import pl.exercise.warehouse.service.ProducerService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Producer Controller")
@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = ProducerController.class)
@ComponentScan(basePackageClasses = {AppConfig.class})
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
    @DisplayName("Find producer by id - return producer")
    void shouldFindProducerById() throws Exception {
        var producerFromWeb = prepareProducerDto();
        producerFromWeb.setId(1L);
        var expected = mapper.toProducer(producerFromWeb);

        when(service.getById(producerFromWeb.getId()))
                .thenReturn(expected);

        var mvcResult = mockMvc.perform(
                get("/api/producer/producer/{id}", producerFromWeb.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isOk()
                ).andReturn();

        var jsonString = mvcResult.getResponse().getContentAsString();
        var result = objectMapper.readValue(jsonString, Producer.class);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Find producer by id - return Error 404")
    void shouldReturnError404ProducerById() throws Exception {
        var producerFromWeb = prepareProducerDto();
        producerFromWeb.setId(1L);

        when(service.getById(producerFromWeb.getId()))
                .thenThrow(NullPointerException.class);

        var mvcResult = mockMvc.perform(
                get("/api/producer/producer/{id}", producerFromWeb.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status()
            .isNotFound()
        ).andReturn();

        var result = mvcResult.getResponse().getContentAsString();

        assertEquals("", result);
    }

    @Test
    @DisplayName("Find all producers - return producer list")
    void shouldFindAllProducers() throws Exception {
        var producer1 = prepareProducerDto();
        var producer2 = prepareProducerDto();
        var producer3 = prepareProducerDto();
        List<Producer> expected = new ArrayList<>();
        expected.add(mapper.toProducer(producer1));
        expected.add(mapper.toProducer(producer2));
        expected.add(mapper.toProducer(producer3));

        when(service.getAll()).thenReturn(expected);

        var mvcResult = mockMvc.perform(
                get("/api/producer/producers")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status()
                .isOk()
        ).andReturn();

        var jsonString = mvcResult.getResponse().getContentAsString();
        Producer[] producers = objectMapper.readValue(jsonString, Producer[].class);
        List<Producer> result = Arrays.asList(producers);

        assertEquals(expected, result);
        assertEquals(3, result.size());
    }

    @Test
    @DisplayName("Find all producers - return Error 500")
    void shouldReturnError500FindAllProducers() throws Exception {

        when(service.getAll()).thenThrow(NullPointerException.class);

        var mvcResult = mockMvc.perform(
                get("/api/producer/producers")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status()
                .isInternalServerError()
        ).andReturn();

        var result = mvcResult.getResponse().getContentAsString();

        assertEquals("", result);
    }

    @Test
    @DisplayName("Save producer - return URI")
    void shouldSaveProducer() throws Exception {
        var producerFromWeb = prepareProducerDto();
        var expected = mapper.toProducer(producerFromWeb);

        when(service.add(expected)).thenReturn(expected);

        var mvcResult = mockMvc.perform(
                post("/api/producer/producer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(producerFromWeb))
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status()
                .isCreated()
        ).andReturn();

        var jsonString = mvcResult.getResponse().getContentAsString();
        var result = objectMapper.readValue(jsonString, Producer.class);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Save producer - return Error 500")
    void shouldReturnError500SaveProducer() throws Exception {
        var producerFromWeb = prepareProducerDto();
        Producer producer = null;

        when(service.add(null)).thenThrow(NullPointerException.class);

        var mvcResult = mockMvc.perform(
                post("/api/producer/producer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(producerFromWeb))
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status()
                .isInternalServerError()
        ).andReturn();

        var result = mvcResult.getResponse().getContentAsString();

        assertEquals("", result);
    }

    @Test
    @DisplayName("Delete producer - delete from db")
    void shouldDeleteProducerFromDb() throws Exception {
        var idProducerToDelete = 1L;

        when(service.deleteById(idProducerToDelete)).thenReturn(true);

        mockMvc.perform(delete("/api/producer/producer/{id}", idProducerToDelete)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isNoContent()
                );
    }

    @Test
    @DisplayName("Delete producer - return error 500")
    void shouldReturnError500ForError() throws Exception {
        var idProducerToDelete = 1L;

        when(service.deleteById(idProducerToDelete))
                .thenThrow(NullPointerException.class);

        mockMvc.perform(delete("/api/producer/producer/{id}", idProducerToDelete)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isInternalServerError()
                );
    }

    private ProducerDto prepareProducerDto() {
        var producer = new ProducerDto();
        producer.setCompanyName("TeleFront");
        producer.setOwner("Marek Jakowski");
        producer.setAddress("Test 44-231 Testowo");
        producer.setOpinion(6);
        producer.setProducts(new ArrayList<>());
        return producer;
    }

}
