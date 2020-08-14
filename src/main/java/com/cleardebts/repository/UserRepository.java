package com.cleardebts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cleardebts.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
