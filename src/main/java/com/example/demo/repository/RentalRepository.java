package com.example.demo.repository;

import com.example.demo.model.Rental;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByUserId(Long userId, Pageable pageable);

    List<Rental> findByUserIdAndActualReturnDateIsNull(Long userId, Pageable pageable);

    List<Rental> findByUserIdAndActualReturnDateIsNotNull(Long userId, Pageable pageable);
}
