package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Cbcdocumenttypeindicator;

@Repository("documentTypeIndicatorRepository")
public interface DocumentTypeIndicatorRepository extends JpaRepository<Cbcdocumenttypeindicator, BigInteger>{
	
	@Query("SELECT p FROM Cbcdocumenttypeindicator p")
    public List<Cbcdocumenttypeindicator> findAllReportingTypeIndicator();
}
