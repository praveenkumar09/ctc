package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Crspayldin;

@Repository("crspayldinRepository")
public interface CrspayldinRepository extends JpaRepository<Crspayldin, BigInteger>{
	
public static String FIND_BY_OBJECT_ID = "select e from Crspayldin e where e.objectID=:objectID";
	
	@Query(FIND_BY_OBJECT_ID)
	public List<Crspayldin> getAllCrspayldinByObjectID(@Param("objectID") BigInteger objectID);

}
