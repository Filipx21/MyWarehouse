package pl.exercise.warehouse.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.exercise.warehouse.model.Category;
import pl.exercise.warehouse.repository.CategoryRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void shouldAddNewProductWithoutId() throws NullPointerException {
        Category expected = prepareCategory();

        when(categoryRepository.save(expected)).thenReturn(expected);

        Category result = categoryService.add(expected);

        assertEquals(expected, result);
    }

    @Test
    void shouldThrowExceptionNewProductWithoutIdAndData() throws NullPointerException {
        Category expected = new Category();

        when(categoryRepository.save(expected)).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () ->
                categoryService.add(expected)
        );
    }

    private Category prepareCategory() {
        Category category = new Category();
        category.setName("PC");
        return category;
    }

}
