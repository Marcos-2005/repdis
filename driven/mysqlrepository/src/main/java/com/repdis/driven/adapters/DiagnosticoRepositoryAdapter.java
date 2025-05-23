package com.repdis.driven.adapters;

import com.repdis.application.ports.driven.DiagnosticoRepositoryPort;
import com.repdis.driven.repositories.DiagnosticoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DiagnosticoRepositoryAdapter implements DiagnosticoRepositoryPort {

    private final DiagnosticoJpaRepository diagnosticoJpaRepository;

    @Override
    public void deleteByServiceOrderId(Long serviceOrderId) {
        diagnosticoJpaRepository.deleteByServiceOrderId(serviceOrderId);
    }
}
