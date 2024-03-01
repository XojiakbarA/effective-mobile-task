package org.example.effectivemobiletask.repository;

import org.example.effectivemobiletask.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
    boolean existsByValue(String value);
}
