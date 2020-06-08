package pl.exercise.warehouse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.when;

import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import pl.exercise.warehouse.config.AppConfig;
import pl.exercise.warehouse.dto.CategoryDto;
import pl.exercise.warehouse.mapper.CategoryMapper;
import pl.exercise.warehouse.model.Category;
import pl.exercise.warehouse.service.CategoryService;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = CategoryController.class)


class CategoryControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CategoryMapper _categoryMapper;

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


        when(categoryService.add(categoryMapper.toCategory(category))).thenReturn(categoryMapper.toCategory(category));

        MvcResult mvcResult = mockMvc.perform(post("/api/category/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(category))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status()
                    .isCreated()
                ).andReturn();


        String result = mvcResult.getResponse().getContentAsString();
        System.out.println(result);
        //assertEquals(expected, result);
    }

    @Test
    void deleteCategory() {
    }

    private CategoryDto prepareCategory() {
        CategoryDto category = new CategoryDto();
        category.setName("PC");
        return category;
    }
}