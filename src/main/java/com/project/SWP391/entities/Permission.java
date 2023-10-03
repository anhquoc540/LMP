package com.project.SWP391.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    STORE_READ("management:read"),
    STORE_UPDATE("management:update"),
    STORE_CREATE("management:create"),
    STORE_DELETE("management:delete")

    ;

    @Getter
    private final String permission;
}