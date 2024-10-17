package com.registrocartera.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RegisterClientData(
        @NotBlank
        String name,
        @NotBlank
        String lastName,
        @NotBlank
        String phone,
        @NotBlank @Email
        String email,
        @NotBlank @Pattern(regexp = "\\d{8,10}")
        String dni
) {
}
