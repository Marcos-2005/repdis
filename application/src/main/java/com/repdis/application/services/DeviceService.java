package com.repdis.application.services;

import com.repdis.application.ports.driven.DeviceRepositoryPort;
import com.repdis.application.ports.driving.DeviceServicePort;
import domain.Device;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService implements DeviceServicePort {

    private final DeviceRepositoryPort deviceRepositoryPort;

    public DeviceService(DeviceRepositoryPort deviceRepositoryPort) {
        this.deviceRepositoryPort = deviceRepositoryPort;
    }

    @Override
    public Optional<Device> findBySerial(String serial) {
        return deviceRepositoryPort.findBySerial(serial);
    }

    @Override
    public List<Device> getAllDevices() {
        return deviceRepositoryPort.findAll();
    }

    @Override
    public void deleteDeviceById(Long id) {
        deviceRepositoryPort.deleteById(id);
    }

    @Override
    public void updateDevice(Device device) {
        deviceRepositoryPort.update(device);
    }

    @Override
    public List<Device> findByClientId(Long clientId) {
        return deviceRepositoryPort.findByClientId(clientId);
    }
}
