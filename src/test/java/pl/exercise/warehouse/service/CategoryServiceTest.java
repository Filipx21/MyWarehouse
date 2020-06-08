package pl.exercise.warehouse.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.exercise.warehouse.dto.CategoryDto;
import pl.exercise.warehouse.mapper.CategoryMapper;
import pl.exercise.warehouse.model.Category;
import pl.exercise.warehouse.repository.CategoryRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Test Category")
@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryService categoryService;

    @Autowired
    private CategoryMapper _categoryMapper;

    @Test
    @DisplayName("Add category without id - return Category")
    void shouldAddNewCategoryWithoutId() throws NullPointerException {
        Category expected = prepareCategory();

        when(categoryRepository.save(expected)).thenReturn(expected);

        CategoryDto categoryDto = _categoryMapper.toCategoryDto(expected);

        //CategoryDto result = categoryService.add(categoryDto);

        //assertEquals(expected, result);
    }

//    @Test
//    @DisplayName("Add category without id and data - throw NullPointerException")
//    void shouldThrowExceptionNewCategoryWithoutIdAndData() throws NullPointerException {
//        Category expected = new Category();
//
//        when(categoryRepository.save(expected)).thenThrow(NullPointerException.class);
//
//        assertThrows(NullPointerException.class, () ->
//                categoryService.add(expected)
//        );
//    }

    private Category prepareCategory() {
        Category category = new Category();
        category.setName("PC");
        return category;
    }

}
