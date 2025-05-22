package com.repdis.driving.mappers;

import com.repdis.driving.dto.AdminDTO;
import domain.Admin;

public class AdminDtoMapper {

    public static AdminDTO toDto(Admin admin) {
        return new AdminDTO(
                admin.getId(),
                admin.getName()
        );
    }

    public static Admin toDomain(AdminDTO dto) {
        return new Admin(
                dto.getId(),
                dto.getName(),
                null,
                null
        );
    }
}
