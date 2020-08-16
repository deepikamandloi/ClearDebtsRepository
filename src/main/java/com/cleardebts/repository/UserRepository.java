package com.cleardebts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cleardebts.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(nativeQuery = false, value = "select u from User u where u.contactNumber = :contactNumber or u.email = :email")
	User getUserByContactAndEmail(@Param("contactNumber") final String contactNumber, @Param("email") final String email);
}
