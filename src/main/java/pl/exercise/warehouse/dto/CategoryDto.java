package pl.exercise.warehouse.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryDto {

    private long id;
    @NotNull @Size(max = 50)
    private String name;

    public CategoryDto() {
    }

    public CategoryDto(long id, @NotNull @Size(max = 50) String name) {
        this.id = id;
        this.name = name;
    }

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

    @Override
    public String toString() {
        return "CategoryDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
