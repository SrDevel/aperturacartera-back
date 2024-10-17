package com.registrocartera.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationUserData(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
