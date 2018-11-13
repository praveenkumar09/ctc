package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Cbcbizactivitiesreference;

@Repository("cbcbizactivitiesreferenceRepository")
public interface CbcbizactivitiesreferenceRepository extends JpaRepository<Cbcbizactivitiesreference, BigInteger>{
	
	@Query("SELECT p FROM Cbcbizactivitiesreference p")
    public List<Cbcbizactivitiesreference> findAllBizActivitiesTypes();

}
