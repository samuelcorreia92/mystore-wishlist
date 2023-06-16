package com.mystore.shared.core.auth;

public record PrincipalUser(
        String clientId,
        String name,
        String email,
        boolean premium
) {
}
