package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Cbcmessagetypeindic;
import com.censof.myfi.hidefmyfi.entity.Crspayldpymt;

@Repository("cbcmessagetypeindicRepository")
public interface CbcmessagetypeindicRepository extends JpaRepository<Cbcmessagetypeindic, BigInteger>{

	@Query("SELECT p FROM Cbcmessagetypeindic p")
    public List<Cbcmessagetypeindic> findAllMessageTypeIndicator();
	
	public static String FIND_BY_ID = "select e from Cbcmessagetypeindic e where e.id=:id";
	
	@Query(FIND_BY_ID)
	public Cbcmessagetypeindic getAllbyCbcmessagetypeindicID(@Param("id") BigInteger acctRepID);
}
