package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Usereceivingcountry;
import com.censof.myfi.hidefmyfi.entity.Userprofile;

@Repository("usereceivingcountryRepository")
public interface UsereceivingcountryRepository extends JpaRepository<Usereceivingcountry, BigInteger>{
	
	public static String FIND_BY_ID = "select e from Usereceivingcountry e where e.id=:id";
	@Query(FIND_BY_ID)
	public Usereceivingcountry getAllUserReceivingCountryById(@Param("id") BigInteger id);
	
	public static String FIND_BY_USER = "select e from Usereceivingcountry e where e.userprofile=:userprofile";
	@Query(FIND_BY_USER)
	public List<Usereceivingcountry> getAllUserReceivingCountryById(@Param("userprofile") Userprofile userprofile);

}
