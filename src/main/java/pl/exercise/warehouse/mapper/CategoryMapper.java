package pl.exercise.warehouse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.exercise.warehouse.dto.CategoryDto;
import pl.exercise.warehouse.model.Category;

@Mapper
public interface CategoryMapper {

    @Mappings({
            @Mapping(target = "id", source = "categoryDto.id"),
            @Mapping(target = "name", source = "categoryDto.name")})
    Category toCategory(CategoryDto categoryDto);

    @Mappings({
            @Mapping(target = "id", source = "category.id"),
            @Mapping(target = "name", source = "category.name")})
    CategoryDto toCategoryDto(Category category);
}
