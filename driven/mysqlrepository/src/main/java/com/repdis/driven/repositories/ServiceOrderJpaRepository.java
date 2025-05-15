package com.repdis.driven.repositories;

import com.repdis.driven.entities.ServiceOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceOrderJpaRepository extends JpaRepository<ServiceOrderEntity, Long> {
}
