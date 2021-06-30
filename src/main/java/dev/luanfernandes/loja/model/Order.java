package dev.luanfernandes.loja.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;

    @Column(name = "total_price")
    private BigDecimal totalPrice = BigDecimal.ZERO;
    private final LocalDate date = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();

    public Order(Customer customer) {
        this.customer = customer;
    }

    public void addItem(OrderItem orderItem) {
        orderItem.setOrder(this);
        this.items.add(orderItem);
        this.totalPrice = this.totalPrice.add(orderItem.getValue());

    }

}
