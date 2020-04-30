package pl.exercise.warehouse.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import pl.exercise.warehouse.model.Category;
import pl.exercise.warehouse.repository.CategoryRepository;

import java.util.Optional;

@Service
public class CategoryService {

    private final Logger logger = LoggerFactory.getLogger(CategoryService.class);
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category add(Category category) {
        Category copy = category;
        if (copy != null) {
            logger.info("Category added");
            return categoryRepository.save(copy);
        }
        logger.warn("Problem while adding category");
        throw new NullPointerException("Problem while adding category");
    }

    public void deleteById(Long id){
        Optional<Category> optionalProduct = categoryRepository.findById(id);
        if (optionalProduct.isPresent()) {
            logger.info("Category deleted");
            categoryRepository.deleteById(id);
        }
        logger.warn("Problem while deleting category");
        throw new NullPointerException("Problem while deleting category");
    }
}
