package pl.exercise.warehouse.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductDto {

    private long id;
    @Size(min = 2, max = 50)
    private String name;
    @Min(1)
    private int quantity;
    @DecimalMin(value = "0.0", inclusive = false)
    private double price;
    private ProducerDto producer;
    private CategoryDto category;
    @NotNull
    private String photoUri;
    @Min(0) @Max(10)
    private int opinion;

}
