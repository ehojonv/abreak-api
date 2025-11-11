package com.fiap.abreak_api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fiap.abreak_api.model.Break;

public interface BreakRepo extends JpaRepository<Break, Long> {

    Page<Break> findByUserIdOrderByDateTimeDesc(Long userId, Pageable pageable);

    @Query("SELECT p FROM ab_break p WHERE p.id_user = :userId " +
            "AND FUNCTION('TRUNC', p.date_time) = FUNCTION('TRUNC', CURRENT_TIMESTAMP)")
    List<Break> findPausasHoje(@Param("userId") Long userId);

    // Contar pausas de hoje
    @Query("SELECT COUNT(p) FROM ab_break p WHERE p.id_user = :userId " +
            "AND FUNCTION('TRUNC', p.date_time) = FUNCTION('TRUNC', CURRENT_TIMESTAMP)")
    Long countPausasHoje(@Param("userId") Long userId);

}
