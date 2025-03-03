package com.example.cuisine_crud_repository.model;

import javax.persistence.*;

@Entity
@Table(name = "cuisine")
public class Cuisine  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String image;
    private String category;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;

    public Cuisine() {
    }

    public Cuisine(String description, String image, String category, String name) {
        this.description = description;
        this.image = image;
        this.category = category;
        this.name = name;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
