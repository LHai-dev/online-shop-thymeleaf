package com.example.sa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    // Getter for 'id'
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Getter and Setter for 'name'
    private String name;

    // Getter and Setter for 'category'
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;


    // Getter and Setter for 'price'
    private Double price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    // Add a method to add images to the product
    public void addImage(Image image) {
        images.add(image);
        image.setProduct(this);
    }

    // Add a method to remove images from the product
    public void removeImage(Image image) {
        images.remove(image);
        image.setProduct(null);
    }
}
