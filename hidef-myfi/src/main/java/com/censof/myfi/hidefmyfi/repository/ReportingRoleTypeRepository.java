package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Cbcreportingrole;

@Repository("reportingRoleTypeRepository")
public interface ReportingRoleTypeRepository extends JpaRepository<Cbcreportingrole, BigInteger>{
	
	@Query("SELECT p FROM Cbcreportingrole p")
    public List<Cbcreportingrole> findAllReportingTypes();
}
