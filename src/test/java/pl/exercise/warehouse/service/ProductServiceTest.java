package pl.exercise.warehouse.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.exercise.warehouse.model.Category;
import pl.exercise.warehouse.model.Producer;
import pl.exercise.warehouse.model.Product;
import pl.exercise.warehouse.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@DisplayName("Test Product")
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldAddNewProductWithoutId() throws NullPointerException {
        Product expected = prepareProduct();

        when(productRepository.save(expected)).thenReturn(expected);

        Product result = productService.add(expected);

        assertEquals(expected, result);
    }

    @Test
    void shouldThrowExceptionNewProductWithoutIdAndData() throws NullPointerException {
        Product expected = new Product();

        when(productRepository.save(expected)).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () ->
                productService.add(expected)
        );
    }

    @Test
    void shouldUpdateOldProductWithId() throws NullPointerException {
        Product expected = prepareProduct();
        expected.setId(1L);

        when(productRepository.findById(expected.getId())).thenReturn(Optional.of(expected));
        when(productRepository.save(expected)).thenReturn(expected);

        Product result = productService.update(expected);

        assertEquals(expected, result);
    }

    @Test
    void shouldThrowExceptionOldProductWithoutId() throws NullPointerException {
        Product expected = prepareProduct();

        assertThrows(NullPointerException.class, () ->
                productService.update(expected)
        );
    }

    @Test
    void shouldThrowExceptionNewProductWithIdTryUpdate() throws NullPointerException {
        Product expected = prepareProduct();
        expected.setId(1L);

        when(productRepository.findById(expected.getId())).thenReturn(Optional.empty());

        assertThrows(NullPointerException.class, () ->
                productService.update(expected)
        );
    }

    @Test
    void shouldThrowExceptionDeleteById() throws NullPointerException {
        Product expected = prepareProduct();

        when(productRepository.findById(expected.getId())).thenReturn(Optional.empty());

        assertThrows(NullPointerException.class, () ->
                productService.deleteById(expected.getId())
        );
    }

    @Test
    void shouldGetById() throws NullPointerException {
        Product expected = prepareProduct();
        expected.setId(1L);

        when(productRepository.findById(expected.getId())).thenReturn(Optional.of(expected));

        Product result = productService.getById(expected.getId());

        assertEquals(expected, result);
    }

    @Test
    void shouldThrowExceptionGetById() throws NullPointerException {
        Product expected = prepareProduct();
        expected.setId(1L);

        when(productRepository.findById(expected.getId())).thenReturn(Optional.empty());

        assertThrows(NullPointerException.class, () ->
                productService.getById(expected.getId())
        );
    }

    @Test
    void shouldGetAll() throws NullPointerException {
        Product product1 = prepareProduct();
        Product product2 = prepareProduct();
        Product product3 = prepareProduct();
        List<Product> expected = List.of(product1, product2, product3);

        when(productRepository.findAll()).thenReturn(expected);

        List<Product> result = productService.getAll();

        assertEquals(expected.size(), result.size());
        assertEquals(expected, result);
        assertEquals(expected.get(1), result.get(1));
    }

    @Test
    void shouldThrowExceptionGetAll() throws NullPointerException {
        List<Product> expected = new ArrayList<>();

        when(productRepository.findAll()).thenReturn(expected);

        assertThrows(NullPointerException.class, () ->
                productService.getAll()
        );
    }

    @Test
    void getAllByCategory() throws NullPointerException {
    }

    private Product prepareProduct() {
        Product product = new Product();
        product.setName("Myszka komputerowa");
        product.setQuantity(99);
        product.setPrice(45.99);
        product.setProducer(new Producer());
        product.setCategory(new Category());
        product.setPhotoUri("http://usmiechnij-sie.com/upload/rozrywka/0/1543598865161.jpg");
        product.setOpinion(6);
        return product;
    }
}