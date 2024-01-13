package com.scarlxrd.challengePicpay.repositories;

import com.scarlxrd.challengePicpay.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransctionRepository extends JpaRepository<Transaction,Long> {
}
