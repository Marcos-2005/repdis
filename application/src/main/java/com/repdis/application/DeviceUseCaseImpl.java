package com.repdis.application;

import com.repdis.application.ports.driven.DeviceRepositoryPort;
import com.repdis.application.ports.driving.DeviceServicePort;
import domain.Device;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeviceUseCaseImpl implements DeviceServicePort {

    private final DeviceRepositoryPort deviceRepositoryPort;

    @Override
    public List<Device> findByClientId(Long clientId) {
        return deviceRepositoryPort.findByClientId(clientId);
    }

    @Override
    public List<Device> getAllDevices() {
        return deviceRepositoryPort.findAll();
    }

    @Override
    public void updateDevice(Device device) {
        deviceRepositoryPort.update(device);
    }

    @Override
    public void deleteDeviceById(Long id) {
        deviceRepositoryPort.deleteById(id);
    }

    @Override
    public Optional<Device> findBySerial(String serial) {
        return deviceRepositoryPort.findBySerial(serial);
    }
}
