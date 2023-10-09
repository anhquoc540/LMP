package com.project.SWP391.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter

@Entity
@Table(name = "items")
public class Item implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "weight")
    private float weight;

    @Column(name = "quantity")
    private int quantity;
    @Column(name = "total_price")
    private float total;

    @ManyToOne
    @JoinColumn (name = "special_id")
    private SpecialLaundry specialLaundry;
    @ManyToOne
    @JoinColumn (name = "standard_id")
    private StandardLaundry standardLaundry;

    @ManyToOne
    @JoinColumn (name = "order_id", nullable = false)
    private Order order;

}
