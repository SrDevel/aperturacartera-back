package com.registrocartera.dto;

import com.registrocartera.model.Credit;

public record ResponseCreditData(
    Long id,
    Long clientId,
    Double amount,
    Double interestRate,
    Integer term,
    Integer fees,
    String status
) {
    public ResponseCreditData(Credit credit) {
        this(
            credit.getId(),
            credit.getClient().getId(),
            credit.getAmount(),
            credit.getInterestRate(),
            credit.getTerm(),
            credit.getFees(),
            credit.getStatus().getSpanishName()
        );
    }
}
