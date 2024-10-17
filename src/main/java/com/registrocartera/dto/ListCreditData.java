package com.registrocartera.dto;

import com.registrocartera.model.Credit;

public record ListCreditData(
        Long id,
        Long clientId,
        String clientName,
        String clientLastName,
        Double amount,
        Double interestRate,
        Integer term,
        Integer fees,
        String status
) {
    public ListCreditData(Credit credit) {
        this(credit.getId(),
                credit.getClient().getId(),
                credit.getClient().getName(),
                credit.getClient().getLastName(),
                credit.getAmount(),
                credit.getInterestRate(),
                credit.getTerm(),
                credit.getFees(),
                credit.getStatus().getSpanishName());
    }
}
