package com.registrocartera.dto;

public record ResponseDataClient(
        Long id,
        String name,
        String lastName,
        String phone,
        String email,
        String dni
) {
}
