package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Cbcpayldaddinfo;

@Repository("cbcpayldaddinfoRepository")
public interface CbcpayldaddinfoRepository extends JpaRepository<Cbcpayldaddinfo, BigInteger>{
	
	public static String FIND_BY_HDRID_ID = "select e from Cbcpayldaddinfo e where e.hdrID=:hdrID";
	@Query(FIND_BY_HDRID_ID)
	public List<Cbcpayldaddinfo> getAllAdditionalInfoByHdrIDId(@Param("hdrID") BigInteger hdrID);
	
	public static String FIND_BY_ID = "select e from Cbcpayldaddinfo e where e.id=:id";
	@Query(FIND_BY_ID)
	public Cbcpayldaddinfo getAllAdditionalInfoById(@Param("id") BigInteger id);

}
