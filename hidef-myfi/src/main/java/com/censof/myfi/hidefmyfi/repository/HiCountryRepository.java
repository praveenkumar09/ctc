package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Hicountry;

@Repository("hiCountryRepository")
public interface HiCountryRepository extends JpaRepository<Hicountry, BigInteger>{
	
	@Query("SELECT p FROM Hicountry p")
    public List<Hicountry> findAllCountry();
	
	public static String FIND_BY_COUNTRY_CODE = "select e from Hicountry e where e.countryCode=:countryCode";
	
	@Query(FIND_BY_COUNTRY_CODE)
	public Hicountry getAllHicountryByCountryCode(@Param("countryCode") String countryCode);
	
	
}
