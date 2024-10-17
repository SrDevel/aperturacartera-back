package com.registrocartera.controller;

import com.registrocartera.dto.ListCreditData;
import com.registrocartera.dto.ListDataClient;
import com.registrocartera.dto.NewCredit;
import com.registrocartera.dto.ResponseCreditData;
import com.registrocartera.model.CreditStatus;
import com.registrocartera.repository.CreditRepository;
import com.registrocartera.service.CreditService;
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

@RestController
@RequestMapping("/credits")
public class CreditController {
    @Autowired
    private CreditService creditService;

    @Autowired
    private CreditRepository creditRepository;

    @PostMapping
    public ResponseEntity<ResponseCreditData> createCredit(@Valid @RequestBody NewCredit newCredit, UriComponentsBuilder uriComponentsBuilder) {
        ResponseCreditData response = creditService.createCredit(newCredit);

        URI uri = uriComponentsBuilder.path("/credits/{id}").buildAndExpand(response.id()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @PatchMapping("/{creditId}/status")
    @Transactional
    public ResponseEntity<ResponseCreditData> updateCreditStatus(
            @PathVariable Long creditId,
            @RequestParam String status) {
        String statusUpperCase = CreditStatus.fromSpanishName(status).name();
        ResponseCreditData response = creditService.updateCreditStatus(creditId, statusUpperCase);
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<Page<ListCreditData>> getCredits(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(creditRepository.findAll(pageable).map(ListCreditData::new));
    }
}
