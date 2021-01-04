package pl.exercise.warehouse.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.exercise.warehouse.dto.ProductDto;
import pl.exercise.warehouse.mapper.ProductMapper;
import pl.exercise.warehouse.model.Product;
import pl.exercise.warehouse.service.ProductService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    private ProductService productService;
    private ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id) {
        try {
            Product product = productService.getById(id);
            ProductDto productDto = productMapper.toProductDto(product);
            return ResponseEntity.ok().body(productDto);
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/products")
    public ResponseEntity getAllProducts() {
        try {
            List<Product> products = productService.getAll();
            products.stream().forEach(x -> System.out.println(x));
            List<ProductDto> productsDto = products.stream()
                    .map(productMapper::toProductDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(productsDto);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDto> saveProduct(@Valid @RequestBody ProductDto product) {
        try {
            System.out.println("1 ->" + product.toString());
            Product _product = productMapper.toProduct(product);
            System.out.println("2 ->" + _product.toString());
            Product addedProduct = productService.add(_product);
            System.out.println("3 ->" + addedProduct.toString());
            ProductDto productDto = productMapper.toProductDto(addedProduct);
            System.out.println("4 ->" + productDto.toString());
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(addedProduct.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(productDto);
        } catch (Exception ex) {
            System.out.println("Tutaj cos sie stało");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/product")
    public ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody ProductDto product) {
        try {
            Product _product = productMapper.toProduct(product);
            Product updatedProduct = productService.update(_product);
            ProductDto productDto = productMapper.toProductDto(updatedProduct);
            return ResponseEntity.ok().body(productDto);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") Long id) {
        try {
            productService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
