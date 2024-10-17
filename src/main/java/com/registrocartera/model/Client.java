package com.registrocartera.model;

import com.registrocartera.dto.RegisterClientData;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private String dni;
    @Column(columnDefinition = "boolean default true")
    private boolean active = true;

    public Client(RegisterClientData dataClient) {
        this.name = dataClient.name();
        this.lastName = dataClient.lastName();
        this.phone = dataClient.phone();
        this.email = dataClient.email();
        this.dni = dataClient.dni();
    }

    public void deactivateClient() {
        this.active = false;
    }

    public void update(RegisterClientData dataClient) {
        this.name = dataClient.name();
        this.lastName = dataClient.lastName();
        this.phone = dataClient.phone();
        this.email = dataClient.email();
        this.dni = dataClient.dni();
    }
}
