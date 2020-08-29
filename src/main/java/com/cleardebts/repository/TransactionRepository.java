package com.cleardebts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cleardebts.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	@Query(nativeQuery = false, value = "select t from Transaction t where t.status = :status and ( t.borrowerContact = :contactNo or t.lenderContact = :contactNo)")
	List<Transaction> getOpenTransactionForUser(@Param("contactNo") final String contactNumber,
			@Param("status") final String status);

}
