package com.project.SWP391.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_code")
    private String orderCode;
    @CreationTimestamp
    @Column(name = "order_date")
    private Date orderDate;
    @Column(name = "note_text")
    private String noteText;
    @Column(name = "total_price")
    private String total;
    @Column(name = "status")
    private int status;
    @Column(name = "isReport")
    private int isReport;
    @Column(name = "report_content")
    private String reportContent;

    @ManyToOne
    @JoinColumn (name = "customer_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn (name = "store_id", nullable = false)
    private Store store;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private Set<Item> items;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private Set<Shipment> shipments;

}