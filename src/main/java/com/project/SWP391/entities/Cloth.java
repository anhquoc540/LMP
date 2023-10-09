package com.project.SWP391.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter

@Entity
@Table(name = "clothes", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)

public class Cloth implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    @Nationalized
    private String name;

    @OneToMany(mappedBy = "cloth")
    private Set<SpecialLaundry> laundries;
}
