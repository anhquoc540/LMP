package com.project.SWP391.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter

@Entity
@Table(name = "services")
public class Service implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "unit")
    private String unit;

    @Column(name = "price")
    private float price;

    @Column(name = "isDeleted")
    private int isDeleted;

    @ManyToOne
    @JoinColumn (name = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn (name = "type_id")
    private Type type;

    @ManyToOne
    @JoinColumn (name = "clothe_id")
    private Clothe clothe;

    @ManyToOne
    @JoinColumn (name = "material_id")
    private Material material;

    @ManyToOne
    @JoinColumn (name = "time_id")
    private Time time;

    @OneToMany (mappedBy = "service")
    private Set<PriceBasedWeight> prices_weight ;

    @OneToMany (mappedBy = "service")
    private Set<Feedback> feedbacks ;


}
