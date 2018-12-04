package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Crspayldbody;
@Repository("crspayldbodyRepository")
public interface CrspayldbodyRepository extends JpaRepository<Crspayldbody, BigInteger>{
	
	public static String FIND_BY_HRD_ID = "select e from Crspayldbody e where e.hdrID=:hdrID";
	
	@Query(FIND_BY_HRD_ID)
	public Crspayldbody getAllCrsBodyDetailsByHrdid(@Param("hdrID") BigInteger hdrID);
	
	

}
