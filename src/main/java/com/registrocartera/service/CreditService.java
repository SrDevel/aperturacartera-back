package com.registrocartera.service;

import com.registrocartera.dto.NewCredit;
import com.registrocartera.dto.ResponseCreditData;
import com.registrocartera.model.Credit;
import com.registrocartera.model.CreditStatus;
import com.registrocartera.repository.ClientRepository;
import com.registrocartera.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditService {
    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private ClientRepository clientRepository;

    public ResponseCreditData createCredit(NewCredit newCredit) {
        var client = clientRepository.getReferenceById(newCredit.clientId());

        var credit = new Credit();
        credit.setAmount(newCredit.amount());
        credit.setInterestRate(newCredit.interestRate());
        credit.setTerm(newCredit.term());
        credit.setFees(newCredit.fees());
        credit.setClient(client);
        credit.setStatus(CreditStatus.PENDING);

        var savedCredit = creditRepository.save(credit);

        return new ResponseCreditData(savedCredit);
    }

    public ResponseCreditData updateCreditStatus(Long creditId, String status) {
        Credit credit = creditRepository.findById(creditId)
                .orElseThrow(() -> new IllegalArgumentException("Crédito no encontrado"));

        validateCreditStatus(credit, status);

        credit.setStatus(CreditStatus.valueOf(status));
        Credit updatedCredit = creditRepository.save(credit);

        return new ResponseCreditData(updatedCredit);
    }

    private void validateCreditStatus(Credit credit, String status) {
        try {
            CreditStatus newStatus = CreditStatus.valueOf(status);

            // Validaciones
            if (credit.getStatus() == CreditStatus.PAID) {
                throw new IllegalArgumentException("El crédito ya está pagado.");
            } else if (credit.getStatus() == CreditStatus.CANCELLED) {
                throw new IllegalArgumentException("El crédito ya ha sido cancelado.");
            } else if (credit.getStatus() == CreditStatus.REJECTED) {
                throw new IllegalArgumentException("El crédito ya ha sido rechazado.");
            } else if (credit.getStatus() == CreditStatus.APPROVED) {
                throw new IllegalArgumentException("El crédito ya ha sido aprobado.");
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Estado inválido: " + status);
        }

    }
}
