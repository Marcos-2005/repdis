package com.repdis.application.ports.driven;

import domain.Admin;
import java.util.List;

public interface AdminRepositoryPort {
    List<Admin> findAll();
}