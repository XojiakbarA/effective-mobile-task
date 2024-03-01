package org.example.effectivemobiletask.repository;

import org.example.effectivemobiletask.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    boolean existsByNumber(String number);
}
