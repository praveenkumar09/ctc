package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Cbcpayldbody;

@Repository("cbcpayldbodyRepository")
public interface CbcpayldbodyRepository extends JpaRepository<Cbcpayldbody, BigInteger>{
	
	public static String FIND_CONSTENTITY_BY_REPORTS = "select e from Cbcpayldbody e where e.hdrID=:hdrID";
	
	@Query(FIND_CONSTENTITY_BY_REPORTS)
	public Cbcpayldbody getAllBodyDetailsById(@Param("hdrID") BigInteger hdrID);

}
