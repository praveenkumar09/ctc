package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Crspayldnamemiddle;

@Repository("crspayldnamemiddleRepository")
public interface CrspayldnamemiddleRepository extends JpaRepository<Crspayldnamemiddle, BigInteger>{
	
public static String FIND_BY_NAME_ID = "select e from Crspayldnamemiddle e where e.nameID=:nameID";
	
	@Query(FIND_BY_NAME_ID)
	public List<Crspayldnamemiddle> getAllCrspayldnamemiddleNameID(@Param("nameID") BigInteger nameID);

}
