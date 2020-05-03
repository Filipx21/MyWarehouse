package pl.exercise.warehouse.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductDto {

    @Getter @Setter private long id;
    @Size(min = 2, max = 50)
    @Getter @Setter private String name;
    @Min(1)
    @Getter @Setter private int quantity;
    @DecimalMin(value = "0.0", inclusive = false)
    @Getter @Setter private double price;
    @Getter @Setter private ProducerDto producer;
    @Getter @Setter private CategoryDto category;
    @NotNull
    @Getter @Setter private String photoUri;
    @Min(0) @Max(10)
    @Getter @Setter private int opinion;

}
