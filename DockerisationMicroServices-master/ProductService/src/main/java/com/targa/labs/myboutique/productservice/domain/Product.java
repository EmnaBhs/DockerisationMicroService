package com.targa.labs.myboutique.productservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.targa.labs.myboutique.commons.domain.AbstractEntity;
import com.targa.labs.myboutique.productservice.domain.enumeration.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A Product.
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "product")
public class Product extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "price", precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "quantity")
    private Integer quantity;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ProductStatus status;

    @Column(name = "sales_counter")
    private Integer salesCounter;

    @OneToMany
    @JsonIgnore
    private Set<Review> reviews = new HashSet<>();

    @ManyToOne
    private Category category;

    public Product() {
        // Empty Constructor for JPA
    }

    public Product(String name, String description, BigDecimal price,
            Integer quantity, ProductStatus status,
            Integer salesCounter, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.salesCounter = salesCounter;
        this.category = category;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public Integer getSalesCounter() {
        return salesCounter;
    }

    public void setSalesCounter(Integer salesCounter) {
        this.salesCounter = salesCounter;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
