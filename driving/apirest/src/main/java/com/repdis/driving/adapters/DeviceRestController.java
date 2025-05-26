package com.repdis.driving.adapters;

import com.repdis.application.ports.driving.DeviceServicePort;
import com.repdis.driving.dto.DeviceDTO;
import com.repdis.driving.mappers.DeviceDtoMapper;
import domain.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/devices")
public class DeviceRestController {

    private final DeviceServicePort deviceServicePort;

    @Autowired
    public DeviceRestController(@Qualifier("deviceUseCaseImpl") DeviceServicePort deviceServicePort) {
        this.deviceServicePort = deviceServicePort;
    }

    @GetMapping
    public ResponseEntity<List<DeviceDTO>> getAllDevices() {
        List<Device> devices = deviceServicePort.getAllDevices();
        List<DeviceDTO> deviceDTOs = devices.stream()
                .map(DeviceDtoMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(deviceDTOs);
    }

    @GetMapping("/by-client/{clientId}")
    public ResponseEntity<List<DeviceDTO>> getDevicesByClient(@PathVariable Long clientId) {
        List<Device> devices = deviceServicePort.findByClientId(clientId);
        List<DeviceDTO> deviceDTOs = devices.stream()
                .map(DeviceDtoMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(deviceDTOs);
    }

    @GetMapping("/by-serial")
    public ResponseEntity<Device> getDeviceBySerial(@RequestParam String serial) {
        Optional<Device> device = deviceServicePort.findBySerial(serial);
        return device.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Void> updateDevice(@RequestBody DeviceDTO dto) {
        deviceServicePort.updateDevice(DeviceDtoMapper.toDomain(dto));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        deviceServicePort.deleteDeviceById(id);
        return ResponseEntity.noContent().build();
    }

}