package org.example.effectivemobiletask.repository;

import org.example.effectivemobiletask.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
    boolean existsByValue(String value);

    boolean existsByValueAndIdNot(String email, Long id);

    List<Email> findAllByUserUsername(String username);

    Optional<Email> findByIdAndUserUsername(Long id, String username);

    void deleteByIdAndUserUsername(Long id, String username);
}
