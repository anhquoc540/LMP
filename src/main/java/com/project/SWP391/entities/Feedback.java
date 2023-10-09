package com.project.SWP391.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;

@Getter
@Setter

@Entity
@Table(name = "feedbacks")
public class Feedback implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "customer_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn (name = "special_id")
    private SpecialLaundry specialLaundry;

    @ManyToOne
    @JoinColumn (name = "standard_id")
    private StandardLaundry standardLaundry;

    @Column(name = "star")
    private int star;

    @Column(name = "contents")
    @Nationalized
    private String content;

}
