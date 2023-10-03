package com.project.SWP391.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;
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
    private String total;

    @ManyToOne
    @JoinColumn (name = "service_id", nullable = false)
    private Service service;

    @ManyToOne
    @JoinColumn (name = "order_id", nullable = false)
    private Order order;

}
