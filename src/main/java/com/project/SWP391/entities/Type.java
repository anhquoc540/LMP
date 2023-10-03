package com.project.SWP391.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter

@Entity
@Table(name = "types", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)

public class Type implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn (name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "type")
    private Set<Service> services ;
}
