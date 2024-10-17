package com.registrocartera.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UpdatePasswordData(
        @NotBlank
        String username,
        @NotBlank
        String oldPassword,
        @NotBlank @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$") // Minimo 8 caracteres, al menos una letra mayuscula, una letra minuscula y un numero
        String newPassword
) {
}