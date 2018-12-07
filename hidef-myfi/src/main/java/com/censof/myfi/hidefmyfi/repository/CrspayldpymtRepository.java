package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Crspayldpymt;

@Repository("crspayldpymtRepository")
public interface CrspayldpymtRepository extends JpaRepository<Crspayldpymt, BigInteger>{
	
public static String FIND_BY_BODY_ID = "select e from Crspayldpymt e where e.acctRepID=:acctRepID";
	
	@Query(FIND_BY_BODY_ID)
	public List<Crspayldpymt> getAllCrspayldpymtByacctRepID(@Param("acctRepID") BigInteger acctRepID);

}
