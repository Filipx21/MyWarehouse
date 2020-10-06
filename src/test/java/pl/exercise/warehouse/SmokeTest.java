package pl.exercise.warehouse;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.exercise.warehouse.controller.CategoryController;
import pl.exercise.warehouse.controller.ProducerController;
import pl.exercise.warehouse.controller.ProductController;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class SmokeTest {

    @Autowired
    private CategoryController categoryController;

    @Autowired
    private ProducerController producerController;

    @Autowired
    private ProductController productController;

    @Test
    void contextLoads() {
        assertThat(categoryController).isNotNull();
        assertThat(producerController).isNotNull();
        assertThat(productController).isNotNull();
    }

}
