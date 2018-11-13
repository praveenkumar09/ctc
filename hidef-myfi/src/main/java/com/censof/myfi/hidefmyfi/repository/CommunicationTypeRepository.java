package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Ctscommunicationtypecd;

@Repository("communicationTypeRepository")
public interface CommunicationTypeRepository extends JpaRepository<Ctscommunicationtypecd, BigInteger>{
	
	@Query("SELECT p FROM Ctscommunicationtypecd p")
    public List<Ctscommunicationtypecd> findAllCommunicationTypes();
}
