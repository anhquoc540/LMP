package com.project.SWP391.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;
@Getter
@Setter

@Entity
@Table(name = "time_categories", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class TimeCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_range")
    private String dateRange;


    @OneToMany(mappedBy = "timeCategory")
    private Set<Time> times ;
}
