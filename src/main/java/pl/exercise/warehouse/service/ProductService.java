package pl.exercise.warehouse.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import pl.exercise.warehouse.model.Category;
import pl.exercise.warehouse.model.Product;
import pl.exercise.warehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product add(Product product) {
        Product copy = product;
        if (copy != null) {
            logger.info("Product added");
            return productRepository.save(copy);
        }
        logger.warn("Problem while adding product");
        throw new NullPointerException("Problem while adding product");
    }

    public Product update(Product product) {
        Product copy = product;
        if (copy.getId() == 0) {
            logger.warn("Problem with id");
            throw new NullPointerException("Problem with id");
        }
        if(getById(copy.getId()) != null){
            logger.info("Product updated");
            return productRepository.save(copy);
        }
        logger.warn("Problem while adding product");
        throw new NullPointerException("Problem while adding product");
    }

    public boolean deleteById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(id);
            logger.info("Product deleted");
            return true;
        } else {
            logger.warn("Problem while deleting product");
            throw new NullPointerException("Problem while deleting product");
        }
    }

    public Product getById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            logger.info("Product found");
            return optionalProduct.get();
        }
        logger.warn("Problem finding product");
        throw new NullPointerException("There isn't product with id = " + id);
    }

    public List<Product> getAll() {
        List<Product> products = productRepository.findAll();
        if (products.size() > 0) {
            logger.info("Products found");
            return products;
        }
        logger.warn("Problem finding products");
        throw new NullPointerException("There isn't any product");
    }

    public void increaseOrDecreaseQuantityForId(Long id, int value) {
        try {
            Product product = this.getById(id);
            product.setQuantity(product.getQuantity() + value);
            this.add(product);
            logger.info("Operation done");
        } catch (NullPointerException ex) {
            logger.warn("Operation failure");
            throw new NullPointerException("There is some problem with function");
        }
    }

    public List<Product> getAllByCategory(Category category) {
        List<Product> products = productRepository.findAllByCategory(category);
        if (products.size() > 0) {
            logger.info("Products by category found");
            return products;
        }
        logger.warn("Problem finding products by category");
        throw new NullPointerException("There isn't any product for selected category = " + category);
    }
}
