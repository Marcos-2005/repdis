package com.repdis.driven.repositories;

import com.repdis.driven.entities.DiagnosticoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiagnosticoJpaRepository extends JpaRepository<DiagnosticoEntity, Long> {
    void deleteByServiceOrderId(Long serviceOrderId);
    List<DiagnosticoEntity> findByServiceOrderId(Long serviceOrderId);
}
