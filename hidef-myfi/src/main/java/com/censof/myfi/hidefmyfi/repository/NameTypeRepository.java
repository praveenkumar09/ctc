package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Cbcnametype;

@Repository("nameTypeRepository")
public interface NameTypeRepository extends JpaRepository<Cbcnametype, BigInteger>{
	
	@Query("SELECT p FROM Cbcnametype p")
    public List<Cbcnametype> findAllNameTypes();
}
