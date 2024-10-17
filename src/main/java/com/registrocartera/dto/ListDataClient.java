package com.registrocartera.dto;


public record ListDataClient(
        Long id,
        String name,
        String lastName,
        String phone,
        String email,
        String dni
) {
    public ListDataClient(ListDataClient client) {
        this(client.id(), client.name(), client.lastName(), client.phone(), client.email(), client.dni());
    }
}
