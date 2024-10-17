package com.registrocartera.controller;

import com.registrocartera.dto.RegisterClientData;
import com.registrocartera.dto.ListDataClient;
import com.registrocartera.dto.ResponseDataClient;
import com.registrocartera.model.Client;
import com.registrocartera.repository.ClientRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController()
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientRepository repository;

    @PostMapping
    public ResponseEntity<ResponseDataClient> createClient(@RequestBody @Valid RegisterClientData dataClient, UriComponentsBuilder uriComponentsBuilder) {
        Client client = repository.save(new Client(dataClient));
        ResponseDataClient clientResponse = new ResponseDataClient(
                client.getId(),
                client.getName(),
                client.getLastName(),
                client.getPhone(),
                client.getEmail(),
                client.getDni()
        );
        URI uri = uriComponentsBuilder.path("/clients/{id}").buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(uri).body(clientResponse);
    }

    @GetMapping()
    public ResponseEntity<Page<ListDataClient>> getClients(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(repository.findByActiveTrue(pageable).map(ListDataClient::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDataClient> getClient(@PathVariable Long id) {
        Client client = repository.getReferenceById(id);
        return ResponseEntity.ok(new ResponseDataClient(client.getId(),
                client.getName(),
                client.getLastName(),
                client.getPhone(),
                client.getEmail(),
                client.getDni()
        ));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseDataClient> updateClient(@PathVariable Long id, @RequestBody @Valid RegisterClientData dataClient) {
        Client client = repository.getReferenceById(id);
        client.update(dataClient);
        return ResponseEntity.ok(new ResponseDataClient(client.getId(),
                client.getName(),
                client.getLastName(),
                client.getPhone(),
                client.getEmail(),
                client.getDni()
        ));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        Client client = repository.getReferenceById(id);
        client.setActive(false);
        return ResponseEntity.noContent().build();
    }

}
