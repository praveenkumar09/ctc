package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Crspayldaddress;

@Repository("crspayldaddressRepository")
public interface CrspayldaddressRepository extends JpaRepository<Crspayldaddress, BigInteger>{
	
public static String FIND_BY_OBJECT_ID = "select e from Crspayldaddress e where e.objectID=:objectID";
	
	@Query(FIND_BY_OBJECT_ID)
	public List<Crspayldaddress> getAllCrspayldaddressByObjectID(@Param("objectID") BigInteger objectID);

}
