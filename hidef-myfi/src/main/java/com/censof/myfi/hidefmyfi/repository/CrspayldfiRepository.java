package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Crspayldfi;

@Repository("crspayldfiRepository")
public interface CrspayldfiRepository extends JpaRepository<Crspayldfi, BigInteger>{
	public static String FIND_BY_BODY_ID = "select e from Crspayldfi e where e.bodyID=:bodyID";
	
	@Query(FIND_BY_BODY_ID)
	public Crspayldfi getAllCrspayldfiByBodyID(@Param("bodyID") BigInteger bodyID);
	
}
