package com.repdis.driven.mappers;

import com.repdis.driven.entities.AdminEntity;
import domain.Admin;

public class AdminEntityMapper {

    private AdminEntityMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static Admin toDomain(AdminEntity entity) {
        if (entity == null) return null;
        return Admin.builder()
                .id(entity.getId())
                .name(entity.getName())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .build();
    }

    public static AdminEntity toEntity(Admin admin) {
        if (admin == null) return null;
        return AdminEntity.builder()
                .id(admin.getId())
                .name(admin.getName())
                .username(admin.getUsername())
                .password(admin.getPassword())
                .build();
    }
}