package com.censof.myfi.hidefmyfi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.CbcCurrency;

@Repository("cbcCurrencyRepository")
public interface CbcCurrencyRepository extends JpaRepository<CbcCurrency, Integer>{
	
	@Query("SELECT p FROM CbcCurrency p")
    public List<CbcCurrency> findAllCurrency();

}
