package com.registrocartera.repository;

import com.registrocartera.model.Credit;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<Credit, Long> {
    @NonNull
    Page<Credit> findAll(Pageable pageable);
}
