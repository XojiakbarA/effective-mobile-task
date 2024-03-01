package org.example.effectivemobiletask.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "emails")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String value;

    @ManyToOne(optional = false)
    private User user;
}
