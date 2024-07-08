package com.thanhtuyen.webbook.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Column(name = "is_active", columnDefinition = "boolean default true")
    private boolean is_active;

    public Category(String name, String descreiption, boolean is_active) {
        this.name = name;
        this.description = descreiption;
        this.is_active = is_active;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", descreiption='" + description + '\'' +
                ", is_active=" + is_active +
                '}';
    }
}
