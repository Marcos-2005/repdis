package com.repdis.application.ports.driving;

import domain.Device;

import java.util.List;

public interface DeviceServicePort {
    List<Device> findByClientId(Long clientId);
}
