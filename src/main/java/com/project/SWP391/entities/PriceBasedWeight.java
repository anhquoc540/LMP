package com.project.SWP391.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter

@Entity
@Table(name = "prices")
public class PriceBasedWeight implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "weight_from")
    private int from;

    @Column(name = "weight_to")
    private int to;

    @Column(name = "price")
    private float price;
    @ManyToOne
    @JoinColumn (name = "service_id", nullable = false)
    private Service service;


}
