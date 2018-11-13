package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Cbcpayldbizactiv;

@Repository("cbcpayldbizactivRepository")
public interface CbcpayldbizactivRepository extends JpaRepository<Cbcpayldbizactiv, BigInteger>{
	public static String FIND_BY_CONSENTID_ID = "select e from Cbcpayldbizactiv e where e.consentID=:consentID";
	@Query(FIND_BY_CONSENTID_ID)
	public List<Cbcpayldbizactiv> getAllBizActivitiesByConsentID(@Param("consentID") BigInteger consentID);
	
	public static String FIND_BY_ID = "select e from Cbcpayldbizactiv e where e.id=:id";
	@Query(FIND_BY_ID)
	public Cbcpayldbizactiv getAllBizActivitiesByID(@Param("id") BigInteger id);

}
