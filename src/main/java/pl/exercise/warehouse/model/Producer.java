package pl.exercise.warehouse.model;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "producer")
public class Producer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String companyName;
    private String owner;
    private String address;
    private int opinion;
    @OneToMany(mappedBy = "producer")
    private List<Product> products;

    public Producer() {
    }

    public Producer(String companyName, String owner, String address, int opinion, List<Product> products) {
        this.companyName = companyName;
        this.owner = owner;
        this.address = address;
        this.opinion = opinion;
        this.products = products;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getOpinion() {
        return opinion;
    }

    public void setOpinion(int opinion) {
        this.opinion = opinion;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producer producer = (Producer) o;
        return id == producer.id &&
                opinion == producer.opinion &&
                Objects.equals(companyName, producer.companyName) &&
                Objects.equals(owner, producer.owner) &&
                Objects.equals(address, producer.address) &&
                Objects.equals(products, producer.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, owner, address, opinion, products);
    }

    @Override
    public String toString() {
        return "Producer{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", owner='" + owner + '\'' +
                ", address='" + address + '\'' +
                ", opinion=" + opinion +
                ", products=" + products +
                '}';
    }
}
