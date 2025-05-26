package com.repdis.application.ports.driving;

import domain.Device;

import java.util.List;
import java.util.Optional;

public interface DeviceServicePort {
    List<Device> findByClientId(Long clientId);
    Optional<Device> findBySerial(String serial);
    List<Device> getAllDevices();
    void updateDevice(Device device);
    void deleteDeviceById(Long id);
}

