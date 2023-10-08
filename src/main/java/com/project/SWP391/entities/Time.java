package com.project.SWP391.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Setter

@Entity
@Table(name = "store_times")
public class Time implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "price")
    private float price;

    @ManyToOne
    @JoinColumn (name = "store_id")
    private Store store;
    @ManyToOne
    @JoinColumn (name = "category_id")
    private TimeCategory timeCategory;
    @OneToMany(mappedBy = "time")
    private Set<Order> orders;



}

