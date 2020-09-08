package com.cleardebts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cleardebts.model.TransactionDetailRow;

@Repository
public interface TransactionRowDetailRepository extends JpaRepository<TransactionDetailRow, Long> {

//	@Query(nativeQuery = false, value = "select t from Transaction t where t.status = :status and ( t.borrowerContact = :contactNo or t.lenderContact = :contactNo)")
//	List<Transaction> getOpenTransactionForUser(@Param("contactNo") final String contactNumber,
//			@Param("status") final String status);

}
