package com.iranthecreator.picpaychallenger.repositories;

import com.iranthecreator.picpaychallenger.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
