package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Crspayldname;

@Repository("crspayldnameRepository")
public interface CrspayldnameRepository extends JpaRepository<Crspayldname, BigInteger>{
	
public static String FIND_BY_OBJECT_ID = "select e from Crspayldname e where e.objectID=:objectID";
	
	@Query(FIND_BY_OBJECT_ID)
	public List<Crspayldname> getAllCrspayldfiByObjectID(@Param("objectID") BigInteger objectID);

}
