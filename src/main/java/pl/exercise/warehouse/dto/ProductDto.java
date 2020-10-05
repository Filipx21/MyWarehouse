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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProducerDto getProducer() {
        return producer;
    }

    public void setProducer(ProducerDto producer) {
        this.producer = producer;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    public int getOpinion() {
        return opinion;
    }

    public void setOpinion(int opinion) {
        this.opinion = opinion;
    }
}
