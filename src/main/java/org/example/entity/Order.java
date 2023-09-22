package org.example.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class Order {
    private int id;
    private Timestamp date;
    private int cost;
    private Set<Product> productSet = new HashSet<>();

    public void addProduct(Product product) {
        productSet.add(product);
    }

    public Set<Integer> getProductIds() {
        return productSet.stream().map(Product::getId).collect(Collectors.toSet());
    }
}
