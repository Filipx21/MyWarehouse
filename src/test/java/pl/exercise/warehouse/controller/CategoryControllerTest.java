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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import pl.exercise.warehouse.controller.config.REST_TestConfig;
import pl.exercise.warehouse.dto.CategoryDto;
import pl.exercise.warehouse.mapper.CategoryMapper;
import pl.exercise.warehouse.service.CategoryService;

@DisplayName("Category Controller")
@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = CategoryController.class)
@ComponentScan(basePackageClasses = {REST_TestConfig.class})
class CategoryControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryMapper mapper;

    @MockBean
    private CategoryService service;

    @Test
    @DisplayName("Save category - return URI")
    void shouldSaveCategory() throws Exception {
        var category = prepareCategory();
        var expected = objectMapper.writeValueAsString(
                mapper.toCategory(category));

        when(service.add(mapper.toCategory(category)))
                .thenReturn(mapper.toCategory(category));

        var mvcResult = mockMvc.perform(post("/api/category/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(category))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isCreated()
                ).andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Save category - return error 500")
    void shouldThrowExceptionSaveCategory() throws Exception {
        var categoryFromUser = prepareCategory();

        when(service.add(mapper.toCategory(categoryFromUser)))
                .thenThrow(NullPointerException.class);

        mockMvc.perform(post("/api/category/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryFromUser))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isInternalServerError()
                );
    }

    @Test
    @DisplayName("Delete Category - delete category from db")
    void shouldDeleteCategory() throws Exception {
        var idCategoryToDelete = 1L;

        when(service.deleteById(idCategoryToDelete)).thenReturn(true);

        mockMvc.perform(delete("/api/category/delete_category/{id}", idCategoryToDelete)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status()
                    .isNoContent()
            );
    }

    @Test
    @DisplayName("Delete Category - return error 500")
    void shouldReturnError500ForError() throws Exception {
        var idCategoryToDelete = 1L;

        when(service.deleteById(idCategoryToDelete))
                .thenThrow(NullPointerException.class);

        mockMvc.perform(delete("/api/category/delete_category/{id}", idCategoryToDelete)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isInternalServerError()
                );
    }

    private CategoryDto prepareCategory() {
        var category = new CategoryDto();
        category.setName("PC");
        return category;
    }

}
