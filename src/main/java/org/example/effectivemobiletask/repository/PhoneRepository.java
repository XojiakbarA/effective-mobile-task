package org.example.effectivemobiletask.repository;

import org.example.effectivemobiletask.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    boolean existsByNumber(String number);

    boolean existsByNumberAndIdNot(String number, Long id);

    List<Phone> getAllByUserUsername(String username);

    Optional<Phone> findByIdAndUserUsername(Long id, String username);

    @Transactional
    void deleteByIdAndUserUsername(Long id, String username);
}
