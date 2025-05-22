// com.repdis.driving.adapters.AdminRestController.java
package com.repdis.driving.adapters;

import com.repdis.application.ports.driving.AdminServicePort;
import com.repdis.driving.dto.AdminDTO;
import com.repdis.driving.mappers.AdminDtoMapper;
import domain.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminRestController {

    private final AdminServicePort adminService;

    @GetMapping
    public List<AdminDTO> getAllAdmins() {
        return adminService.getAllAdmins()
                .stream()
                .map(AdminDtoMapper::toDto)
                .toList();
    }

    @GetMapping("/debug")
    public List<Admin> getAdminsRaw() {
        List<Admin> admins = adminService.getAllAdmins();
        admins.forEach(a -> System.out.println("Admin: " + a.getId() + ", Name: " + a.getName()));
        return admins;
    }
}
