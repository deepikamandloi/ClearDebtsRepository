package com.cleardebts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cleardebts.model.ChemicalFormula;

@Repository
public interface ChemicalFormulaRepo extends JpaRepository<ChemicalFormula, Long> {

}
