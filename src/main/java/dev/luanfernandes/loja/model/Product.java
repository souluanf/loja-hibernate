package dev.luanfernandes.loja.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@NamedQuery(
        name = "productsByCategoryName",
        query = "SELECT p from Product p where p.category.name = :name"
)
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDate registrationDate = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Category category;

    public Product(String name, String description, BigDecimal price, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }
}
