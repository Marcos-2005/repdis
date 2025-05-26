package com.repdis.driven.adapters;

import com.repdis.application.ports.driven.DeviceRepositoryPort;
import com.repdis.driven.mappers.DeviceEntityMapper;
import com.repdis.driven.repositories.DeviceJpaRepository;
import com.repdis.driven.entities.DeviceEntity;
import domain.Device;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DeviceRepositoryAdapter implements DeviceRepositoryPort {

    private final DeviceJpaRepository deviceJpaRepository;
    private final DeviceEntityMapper deviceEntityMapper;

    @Override
    public List<Device> findByClientId(Long clientId) {
        return deviceJpaRepository.findByClientId(clientId)
                .stream()
                .map(deviceEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Device> findAll() {
        return deviceJpaRepository.findAll()
                .stream()
                .map(deviceEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void update(Device device) {
        DeviceEntity entity = deviceEntityMapper.toEntity(device);
        deviceJpaRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        deviceJpaRepository.deleteById(id);
    }

    @Override
    public Optional<Device> findBySerial(String serial) {
        return deviceJpaRepository.findBySerialNumber(serial)
                .map(deviceEntityMapper::toDomain);
    }
}
