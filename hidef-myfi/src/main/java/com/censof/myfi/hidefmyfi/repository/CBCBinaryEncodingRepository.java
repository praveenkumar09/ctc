package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Cbcbinaryencodingschemecd;

@Repository("cBCBinaryEncodingRepository")
public interface CBCBinaryEncodingRepository extends JpaRepository<Cbcbinaryencodingschemecd, BigInteger>{
	
	@Query("SELECT p FROM Cbcbinaryencodingschemecd p")
    public List<Cbcbinaryencodingschemecd> findAllBinaryencodingSchemeTypes();
}
