package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Cbcpayldname;
@Repository("cbcpayldnameRepository")
public interface CbcpayldnameRepository extends JpaRepository<Cbcpayldname, BigInteger>{
	
	public static String FIND_BY_ID = "select e from Cbcpayldname e where e.id=:id";
	@Query(FIND_BY_ID)
	public Cbcpayldname getAllPayldNameDetailsById(@Param("id") BigInteger id);

	
	public static String FIND_BY_OBJECT_ID = "select e from Cbcpayldname e where e.objectID=:objectID";
	@Query(FIND_BY_OBJECT_ID)
	public List<Cbcpayldname> getAllPayldNameDetailsByObjectId(@Param("objectID") BigInteger objectID);

}
