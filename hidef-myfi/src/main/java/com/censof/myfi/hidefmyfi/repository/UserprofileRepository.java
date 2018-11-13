package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Userprofile;

@Repository("userprofileRepository")
public interface UserprofileRepository extends JpaRepository<Userprofile, BigInteger>{
	
	public static String FIND_BY_MYCBC_ID = "select e from Userprofile e where e.mycbcid=:mycbcid";
	@Query(FIND_BY_MYCBC_ID)
	public List<Userprofile> getUserprofileDetailsByMycbcId(@Param("mycbcid") String mycbcid);
	
	public static String FIND_BY_ID = "select e from Userprofile e where e.id=:id";
	@Query(FIND_BY_ID)
	public Userprofile getUserprofileDetailsById(@Param("id") BigInteger id);

}
