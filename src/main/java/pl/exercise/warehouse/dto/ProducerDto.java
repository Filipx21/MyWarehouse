package pl.exercise.warehouse.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import java.util.List;

public class ProducerDto {

    @Getter @Setter private long id;
    @Size(min = 2, max = 50)
    @Getter @Setter private String companyName;
    @Size(min = 2, max = 50)
    @Getter @Setter private String owner;
    @Size(min = 2, max = 50)
    @Getter @Setter private String address;
    @Min(0) @Max(10)
    @Getter @Setter private int opinion;
    @Getter @Setter private List<ProductDto> products;

}
