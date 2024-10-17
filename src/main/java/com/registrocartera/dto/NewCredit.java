package com.registrocartera.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record NewCredit(
        @Positive
        Double amount,
        @Positive
        Double interestRate,
        @Positive
        Integer term,
        @Positive
        Integer fees,
        @Positive @NotNull
        Long clientId
) {
}
