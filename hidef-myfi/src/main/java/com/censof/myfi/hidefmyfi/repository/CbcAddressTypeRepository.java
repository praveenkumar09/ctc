package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Cbcaddresstype;

@Repository("cbcAddressTypeRepository")
public interface CbcAddressTypeRepository extends JpaRepository<Cbcaddresstype, BigInteger>{
	
	@Query("SELECT p FROM Cbcaddresstype p")
    public List<Cbcaddresstype> findAllAddressTypes();
}
