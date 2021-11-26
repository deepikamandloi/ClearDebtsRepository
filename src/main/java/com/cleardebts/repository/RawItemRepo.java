package com.cleardebts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cleardebts.model.RawItem;

@Repository
public interface RawItemRepo extends JpaRepository<RawItem, Long> {

}
