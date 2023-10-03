package com.project.SWP391.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


    @Getter
    @Setter

    @Entity
    @Table(name = "roles")
    public class Role implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private Long id;

        @Enumerated(EnumType.STRING)
        @Column(length = 20)
        private ERole name;
    }

