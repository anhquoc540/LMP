package com.project.SWP391.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.Set;

@Getter
@Setter

@Entity
@Table(name = "standard_services")
public class StandardLaundry {

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
    @OneToMany(mappedBy = "standardLaundry")
    private Set<PriceBasedWeight> prices_weight ;

    @Column(name = "isDeleted")
    private int isDeleted;

    @OneToOne
    @JoinColumn (name = "store_id")
    private Store store;

    @OneToMany (mappedBy = "standardLaundry")
    private Set<Feedback> feedbacks ;

    @OneToMany (mappedBy = "standardLaundry")
    private Set<Item> items ;
}
