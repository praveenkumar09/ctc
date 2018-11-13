package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Cbcpayldsumref;

@Repository("cbcpayldsumrefRepository")
public interface CbcpayldsumrefRepository extends JpaRepository<Cbcpayldsumref, BigInteger>{
	public static String FIND_BY_HDRID_ID = "select e from Cbcpayldsumref e where e.addinfoID=:addinfoID";
	@Query(FIND_BY_HDRID_ID)
	public List<Cbcpayldsumref> getAllSummaryByAddinfoIDId(@Param("addinfoID") BigInteger addinfoID);
	
	public static String FIND_BY_ID = "select e from Cbcpayldsumref e where e.id=:id";
	@Query(FIND_BY_ID)
	public Cbcpayldsumref getAllSummaryById(@Param("id") BigInteger id);

}
