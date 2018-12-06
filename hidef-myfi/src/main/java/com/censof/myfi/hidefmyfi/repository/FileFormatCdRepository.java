package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Cbcfileformatcd;
import com.censof.myfi.hidefmyfi.entity.Hicountry;

@Repository("fileFormatCdRepository")
public interface FileFormatCdRepository extends JpaRepository<Cbcfileformatcd, BigInteger>{
	
	public static String FIND_BY_FILE_FORMAT_TYPE = "select e from Cbcfileformatcd e where e.CBCFileFormatType=:CBCFileFormatType";
	
	@Query("SELECT p FROM Cbcfileformatcd p")
    public List<Cbcfileformatcd> findAllFileFormatCdTypes();
	
	@Query(FIND_BY_FILE_FORMAT_TYPE)
	public Cbcfileformatcd findByFileFormatCode(@Param("CBCFileFormatType") String fileFormatCode);
	
	
}
