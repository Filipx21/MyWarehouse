package pl.exercise.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.exercise.warehouse.model.Category;
import pl.exercise.warehouse.model.Product;

import java.util.Collection;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Collection<Product> findAllByCategory(Category category);

}
