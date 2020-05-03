package pl.exercise.warehouse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.exercise.warehouse.dto.ProductDto;
import pl.exercise.warehouse.model.Product;

@Mapper
public interface ProductMapper {

    @Mappings({
            @Mapping(target = "id", source = "productDto.id"),
            @Mapping(target = "name", source = "productDto.name"),
            @Mapping(target = "quantity", source = "productDto.quantity"),
            @Mapping(target = "price", source = "productDto.price"),
            @Mapping(target = "producer", source = "productDto.producer"),
            @Mapping(target = "category", source = "productDto.category"),
            @Mapping(target = "photoUri", source = "productDto.photoUri"),
            @Mapping(target = "opinion", source = "productDto.opinion")})
    Product toProduct(ProductDto productDto);

    @Mappings({
            @Mapping(target = "id", source = "product.id"),
            @Mapping(target = "name", source = "product.name"),
            @Mapping(target = "quantity", source = "product.quantity"),
            @Mapping(target = "price", source = "product.price"),
            @Mapping(target = "producer", source = "product.producer"),
            @Mapping(target = "category", source = "product.category"),
            @Mapping(target = "photoUri", source = "product.photoUri"),
            @Mapping(target = "opinion", source = "product.opinion")})
    ProductDto toProductDto(Product product);

}
