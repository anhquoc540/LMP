package com.project.SWP391.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "special_services")
public class SpecialLaundry implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    @Nationalized
    private String name;
    @Column(name = "description")
    @Nationalized
    private String description;

    @Column(name = "image")
    private String imageBanner;

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
    @JoinColumn (name = "cloth_id")
    private Cloth cloth;

    @ManyToMany
    Set<Material> materials;



    @OneToMany (mappedBy = "specialLaundry")
    private Set<Feedback> feedbacks ;

    @OneToMany (mappedBy = "specialLaundry")
    private Set<Item> items ;




}
