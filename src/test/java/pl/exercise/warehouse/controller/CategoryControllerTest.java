package pl.exercise.warehouse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import pl.exercise.warehouse.controller.config.REST_TestConfig;
import pl.exercise.warehouse.dto.CategoryDto;
import pl.exercise.warehouse.mapper.CategoryMapper;
import pl.exercise.warehouse.service.CategoryService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = CategoryController.class)
@ComponentScan(basePackageClasses = {REST_TestConfig.class})
class CategoryControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryMapper categoryMapper;

    @MockBean
    private CategoryService categoryService;

    @Test
    @DisplayName("Save category - return URI")
    void shouldSaveCategory() throws Exception {
        CategoryDto category = prepareCategory();
        Object expected = objectMapper.writeValueAsString(
                categoryMapper.toCategory(category));

        when(categoryService.add(categoryMapper.toCategory(category)))
                .thenReturn(categoryMapper.toCategory(category));

        MvcResult mvcResult = mockMvc.perform(post("/api/category/category")
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
        CategoryDto categoryFromUser = prepareCategory();

        when(categoryService.add(categoryMapper.toCategory(categoryFromUser)))
                .thenThrow(NullPointerException.class);

        mockMvc.perform(post("/api/category/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryFromUser))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isInternalServerError());

    }

    @Test
    @DisplayName("Delete Category - delete category from db")
    void shouldDeleteCategory() throws Exception {
        var idCategoryToDelete = 1L;

        when(categoryService.deleteById(idCategoryToDelete)).thenReturn(true);

        mockMvc.perform(delete("/api/category/delete_category/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Delete Category - return error 500")
    void shouldReturnError500ForError() throws Exception {
        var idCategoryToDelete = 1L;

        when(categoryService.deleteById(idCategoryToDelete))
                .thenThrow(NullPointerException.class);

        mockMvc.perform(delete("/api/category/delete_category/{id}", idCategoryToDelete)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isInternalServerError()
                );
    }

    private CategoryDto prepareCategory() {
        CategoryDto category = new CategoryDto();
        category.setName("PC");
        return category;
    }
}