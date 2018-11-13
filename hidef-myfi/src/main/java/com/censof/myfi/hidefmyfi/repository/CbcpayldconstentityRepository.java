package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Cbcpayldconstentity;

@Repository("cbcpayldconstentityRepository")
public interface CbcpayldconstentityRepository extends JpaRepository<Cbcpayldconstentity, BigInteger>{
public static String FIND_CONSTENTITY_BY_REPORTS = "select e from Cbcpayldconstentity e where e.reportID=:reportID";
	
	@Query(FIND_CONSTENTITY_BY_REPORTS)
	public Cbcpayldconstentity getAllconstentityByReportsId(@Param("reportID") BigInteger reportID);
	
	public static String FIND_CONSTENTITY_BY_ID = "select e from Cbcpayldconstentity e where e.id=:id";
	
	@Query(FIND_CONSTENTITY_BY_ID)
	public Cbcpayldconstentity getAllconstentityById(@Param("id") BigInteger id);

}
