package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Cbcpayldreceivingcountry;


@Repository("cbcpayldreceivingcountryRepository")
public interface CbcpayldreceivingcountryRepository extends JpaRepository<Cbcpayldreceivingcountry, BigInteger>{
	
	public static String FIND_RECEIVING_COUNTRY_BY_ID = "select e from Cbcpayldreceivingcountry e where e.hdrID=:hdrID";
	
	@Query(FIND_RECEIVING_COUNTRY_BY_ID)
	public List<Cbcpayldreceivingcountry> getAllReceivingCountry(@Param("hdrID") BigInteger hdrID);
	
	public static String FIND_BY_ID = "select e from Cbcpayldreceivingcountry e where e.id=:id";
	
	@Query(FIND_BY_ID)
	public Cbcpayldreceivingcountry getAllReceivingCountryByid(@Param("id") BigInteger id);

}
