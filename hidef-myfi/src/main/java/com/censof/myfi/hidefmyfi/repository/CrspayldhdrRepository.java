package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Crspayldhdr;

@Repository("crspayldhdrRepository")
public interface CrspayldhdrRepository extends JpaRepository<Crspayldhdr, BigInteger>{
public static String FIND_ALL_CRS_DETAILS = "select e from Crspayldhdr e where e.crsid=:crsid";
	
	@Query(FIND_ALL_CRS_DETAILS)
	public List<Crspayldhdr> getAllCrsDetails(@Param("crsid") BigInteger crsid);
	
	public static String FIND_BY_ID = "select e from Crspayldhdr e where e.id=:id";
	
	@Query(FIND_BY_ID)
	public Crspayldhdr getAllById(@Param("id") BigInteger id);

}
