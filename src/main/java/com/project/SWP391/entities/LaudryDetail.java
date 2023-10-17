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
@Table(name = "special_detail")
public class LaudryDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "from")
    private float from;
    @Column(name = "to")
    private float to;
    @Column(name = "unit")
    private String unit;

    @Column(name = "price")
    private float price;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "service_id")
    private Laundry laundryService ;






}
