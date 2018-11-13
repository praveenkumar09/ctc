package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Cbcpayldreport;

@Repository("cbcpayldreportRepository")
public interface CbcpayldreportRepository extends JpaRepository<Cbcpayldreport, BigInteger>{
	
	public static String FIND_BY_ID = "select e from Cbcpayldreport e where e.id=:id";
	@Query(FIND_BY_ID)
	public Cbcpayldreport getAllPayldCBCReportsById(@Param("id") BigInteger id);
	
	public static String FIND_BY_OBJECT_ID = "select e from Cbcpayldreport e where e.hdrID=:hdrID";
	@Query(FIND_BY_OBJECT_ID)
	public List<Cbcpayldreport> getAllPayldCBCReportsByObjectId(@Param("hdrID") BigInteger hdrID);

}
