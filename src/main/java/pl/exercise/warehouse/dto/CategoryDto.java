package pl.exercise.warehouse.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryDto {

    @Getter @Setter private long id;
    @NotNull @Size(max = 50)
    @Getter @Setter private String name;

}
