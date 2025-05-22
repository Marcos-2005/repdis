package com.repdis.driven.repositories;


import com.repdis.driven.entities.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceJpaRepository extends JpaRepository<DeviceEntity, Long> {
}
