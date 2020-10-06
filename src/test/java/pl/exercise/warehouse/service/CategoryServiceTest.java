package pl.exercise.warehouse.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.exercise.warehouse.model.Category;
import pl.exercise.warehouse.repository.CategoryRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@DisplayName("Category Service")
@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    @DisplayName("Add category without id - return Category")
    void shouldAddNewCategoryWithoutId() throws NullPointerException {
        var expected = prepareCategory();

        when(categoryRepository.save(expected)).thenReturn(expected);

        Category result = categoryService.add(expected);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Add category without id and data - throw NullPointerException")
    void shouldThrowExceptionNewCategoryWithoutIdAndData() throws NullPointerException {
        var expected = new Category();

        when(categoryRepository.save(expected)).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () ->
                categoryService.add(expected)
        );
    }

    @Test
    @DisplayName("Delete category by id - return true")
    void shouldDeleteCategoryById() throws NullPointerException {
        var idCategoryToDelete = 1L;
        var category = new Category();

        when(categoryRepository.findById(idCategoryToDelete))
                .thenReturn(Optional.of(category));

        var result = categoryService.deleteById(idCategoryToDelete);

        assertTrue(result);
    }

    @Test
    @DisplayName("Delete category by id - throw NullPointerException")
    void shouldThrowNullPointerExceptionDeleteById() throws NullPointerException {
        var idCategoryToDelete = 1L;

        when(categoryRepository.findById(idCategoryToDelete))
                .thenReturn(Optional.empty());

        assertThrows(NullPointerException.class, () ->
            categoryService.deleteById(idCategoryToDelete)
        );
    }

    private Category prepareCategory() {
        Category category = new Category();
        category.setName("PC");
        return category;
    }

}
