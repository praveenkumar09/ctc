package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Docrefid;

@Repository("docrefidRepository")
public interface DocrefidRepository extends JpaRepository<Docrefid, BigInteger>{
	
public static String FIND_ALL_DOC_REFID_DETAILS = "select e from Docrefid e where e.date=:date";
	
	@Query(FIND_ALL_DOC_REFID_DETAILS)
	public Docrefid getAllDocrefIdDetailsByDate(@Param("date") String date);

}
