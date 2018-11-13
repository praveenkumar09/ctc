package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Cbcsummaryreference;

@Repository("cbcsummaryreferenceRepository")
public interface CbcsummaryreferenceRepository extends JpaRepository<Cbcsummaryreference, BigInteger>{
	
	@Query("SELECT p FROM Cbcsummaryreference p")
    public List<Cbcsummaryreference> findAllSummaryReference();
	
	
}
