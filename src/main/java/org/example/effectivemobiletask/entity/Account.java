package org.example.effectivemobiletask.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double initAmount;

    @Column(nullable = false)
    private Double amount;

    @OneToOne(optional = false)
    private User user;
}
