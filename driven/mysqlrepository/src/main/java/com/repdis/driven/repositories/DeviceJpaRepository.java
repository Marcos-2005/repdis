package com.repdis.driven.repositories;


import com.repdis.driven.entities.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceJpaRepository extends JpaRepository<DeviceEntity, Long> {
    List<DeviceEntity> findByClientId(Long clientId);
    Optional<DeviceEntity> findBySerialNumber(String serialNumber);
}
