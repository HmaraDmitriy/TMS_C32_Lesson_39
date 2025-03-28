package com.tms.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Scope("prototype")
@Component
public class Product {
    private Long id;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "The name must contain only letters.")
    @NotBlank(message = "The product name cannot be empty.")
    @Size(min = 2, max = 100, message = "The name must contain from 2 to 100 characters.")
    private String name;

    @NotNull(message = "The price cannot be empty")
    @Positive(message = "The price should be positive")
    private Double price;

    private Timestamp created;
    private Timestamp updated;

    public Product() {
    }

    public Product(Long id, String name, Double price, Timestamp created, Timestamp updated) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.created = created;
        this.updated = updated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}