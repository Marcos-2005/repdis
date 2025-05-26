package com.repdis.application.ports.driven;

import domain.Device;

import java.util.List;
import java.util.Optional;

public interface DeviceRepositoryPort {
    List<Device> findByClientId(Long clientId);
    List<Device> findAll();
    void update(Device device);
    void deleteById(Long id);
    Optional<Device> findBySerial(String serial);
}