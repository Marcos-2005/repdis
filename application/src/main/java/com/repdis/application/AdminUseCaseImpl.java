package com.repdis.application;

import com.repdis.application.ports.driven.AdminRepositoryPort;
import com.repdis.application.ports.driving.AdminServicePort;
import domain.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUseCaseImpl implements AdminServicePort {

    private final AdminRepositoryPort adminRepository;

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
}
