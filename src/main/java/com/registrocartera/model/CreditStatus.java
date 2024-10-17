package com.registrocartera.model;

public enum CreditStatus {
    PENDING("Pendiente"),
    APPROVED("Aprobado"),
    REJECTED("Rechazado"),
    CANCELLED("Cancelado"),
    PAID("Pagado");

    private final String spanishName;

    CreditStatus(String spanishName) {
        this.spanishName = spanishName;
    }

    public static CreditStatus fromSpanishName(String spanishName) {
        for (CreditStatus status : CreditStatus.values()) {
            if (status.spanishName.equalsIgnoreCase(spanishName)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid spanish name");
    }

    public String getSpanishName() {
        return spanishName;
    }

}
