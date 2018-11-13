package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Cbcfileformatcd;

@Repository("fileFormatCdRepository")
public interface FileFormatCdRepository extends JpaRepository<Cbcfileformatcd, BigInteger>{
	
	@Query("SELECT p FROM Cbcfileformatcd p")
    public List<Cbcfileformatcd> findAllFileFormatCdTypes();
}
