package org.example.effectivemobiletask.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "phones")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String number;

    @ManyToOne(optional = false)
    private User user;
}
