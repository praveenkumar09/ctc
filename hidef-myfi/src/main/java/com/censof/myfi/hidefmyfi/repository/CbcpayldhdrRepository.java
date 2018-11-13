package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.censof.myfi.hidefmyfi.entity.Cbcpayldhdr;

@Repository("cbcpayldhdrRepository")
public interface CbcpayldhdrRepository extends JpaRepository<Cbcpayldhdr, BigInteger>{
	
public static String FIND_ALL_CBC_DETAILS = "select e from Cbcpayldhdr e where e.myCBCID=:myCBCID and e.isdeleted=:isdeleted";
	
	@Query(FIND_ALL_CBC_DETAILS)
	public List<Cbcpayldhdr> getAllCbcDetails(@Param("myCBCID") String myCBCID,@Param("isdeleted") Integer isdeleted);
	
	
	public static String FIND_BY_PAYLD_HRDID = "select e from Cbcpayldhdr e where e.id=:id";
	
	@Query(FIND_BY_PAYLD_HRDID)
	public Cbcpayldhdr getCbcDetailsById(@Param("id") BigInteger id);
	
	public static String UPDATE_IS_DELETED = "update Cbcpayldhdr e set e.isdeleted=:isdeleted where e.id=:id";
	
	@Transactional
	@Modifying
	@Query(UPDATE_IS_DELETED)
	public int setIsdeleted(@Param("isdeleted") Integer isdeleted,@Param("id") BigInteger id);

}
