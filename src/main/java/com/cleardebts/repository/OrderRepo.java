package com.cleardebts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cleardebts.model.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

}
