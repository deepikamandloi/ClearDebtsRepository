package com.cleardebts.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_det")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "descr")
	private String descr;

	@Column(name = "qty")
	private Long qty;

	@Column(name = "is_active")
	private Boolean isActive;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "formula_Id", nullable = false)
	private ChemicalFormula formula;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Long getQty() {
		return qty;
	}

	public void setQty(Long qty) {
		this.qty = qty;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public ChemicalFormula getFormula() {
		return formula;
	}

	public void setFormula(ChemicalFormula formula) {
		this.formula = formula;
	}



}
