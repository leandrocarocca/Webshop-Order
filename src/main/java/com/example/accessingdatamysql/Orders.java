package com.example.accessingdatamysql;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime date;
    private long customerId;
    @ElementCollection
    @CollectionTable(name="orders_products", joinColumns = @JoinColumn(name="order_id"))
    @Column(name="product_id")
    private List<Long> product;

    public Orders(LocalDateTime date, List<Long>product, long customerId) {
        this.date = date;
        this.product = product;
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public List<Long> getProduct() {
        return product;
    }

    public void setProduct(List<Long> product) {
        this.product = product;
    }
}
