package com.censof.myfi.hidefmyfi.entity;


import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the cbc_currency database table.
 * 
 */
@Entity
@Table(name="cbc_currency")
@NamedQuery(name="CbcCurrency.findAll", query="SELECT c FROM CbcCurrency c")
public class CbcCurrency implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name="code")
	private String code;

	@Column(name="name")
	private String name;

	@Column(name="symbol")
	private String symbol;

	public CbcCurrency() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}