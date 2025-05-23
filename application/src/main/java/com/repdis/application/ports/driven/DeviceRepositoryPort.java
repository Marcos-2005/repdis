package com.repdis.application.ports.driven;

import domain.Device;

import java.util.List;

public interface DeviceRepositoryPort {
    List<Device> findByClientId(Long clientId);
}