package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Cbcpayldaddress;

@Repository("cbcpayldaddressRepository")
public interface CbcpayldaddressRepository extends JpaRepository<Cbcpayldaddress, BigInteger>{

	public static String FIND_BY_ID = "select e from Cbcpayldaddress e where e.id=:id";
	@Query(FIND_BY_ID)
	public Cbcpayldaddress getAllPayldAddressById(@Param("id") BigInteger id);
	
	public static String FIND_BY_OBJECT_ID = "select e from Cbcpayldaddress e where e.objectID=:objectID";
	@Query(FIND_BY_OBJECT_ID)
	public List<Cbcpayldaddress> getAllPayldAddressByObjectId(@Param("objectID") BigInteger objectID);
}
