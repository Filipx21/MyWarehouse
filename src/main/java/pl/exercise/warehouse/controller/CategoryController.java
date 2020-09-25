package pl.exercise.warehouse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.exercise.warehouse.dto.CategoryDto;
import pl.exercise.warehouse.mapper.CategoryMapper;
import pl.exercise.warehouse.model.Category;
import pl.exercise.warehouse.service.CategoryService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService categoryService;
    private CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @PostMapping("/category")
    public ResponseEntity<CategoryDto> saveCategory(@Valid @RequestBody CategoryDto category) {
        try {
            Category _category = categoryMapper.toCategory(category);
            Category addedCategory = categoryService.add(_category);
            CategoryDto categoryDto = categoryMapper.toCategoryDto(addedCategory);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(addedCategory.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(categoryDto);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete_category/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable("id") Long id) {
        try {
            if(categoryService.deleteById(id)){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
