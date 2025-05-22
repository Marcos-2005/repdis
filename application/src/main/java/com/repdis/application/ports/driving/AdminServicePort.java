package com.repdis.application.ports.driving;

import domain.Admin;
import java.util.List;

public interface AdminServicePort {
    List<Admin> getAllAdmins();
}