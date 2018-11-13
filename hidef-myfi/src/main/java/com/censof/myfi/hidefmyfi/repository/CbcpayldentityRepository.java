package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Cbcpayldentity;

@Repository("cbcpayldentityRepository")
public interface CbcpayldentityRepository extends JpaRepository<Cbcpayldentity, BigInteger>{

	public static String FIND_PAYLD_DETAILS_BY_PAYLDID = "select e from Cbcpayldentity e where e.hdrID=:hdrID";
	
	@Query(FIND_PAYLD_DETAILS_BY_PAYLDID)
	public List<Cbcpayldentity> getAllReportingEntityDetails(@Param("hdrID") BigInteger hdrID);
	
	public static String FIND_BY_ID = "select e from Cbcpayldentity e where e.id=:id";
	@Query(FIND_BY_ID)
	public Cbcpayldentity getAllReportingEntityDetailsById(@Param("id") BigInteger id);
}
