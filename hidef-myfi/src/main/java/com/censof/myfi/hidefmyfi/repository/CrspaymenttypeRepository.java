package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Crspaymenttype;

@Repository("crspaymenttypeRepository")
public interface CrspaymenttypeRepository extends JpaRepository<Crspaymenttype, BigInteger>{
	
	@Query("SELECT p FROM Crspaymenttype p")
    public List<Crspaymenttype> findAllPaymentTypes();

}
