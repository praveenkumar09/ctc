package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Crspayldctrlperson;

@Repository("crspayldctrlpersonRepository")
public interface CrspayldctrlpersonRepository extends JpaRepository<Crspayldctrlperson, BigInteger>{

	public static String FIND_BY_ACCTREPID_ID = "select e from Crspayldctrlperson e where e.acctRepID=:acctRepID";
	
	@Query(FIND_BY_ACCTREPID_ID)
	public List<Crspayldctrlperson> getAllCrspayldctrlpersonAcctRepID(@Param("acctRepID") BigInteger acctRepID);
}
