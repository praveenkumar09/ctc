package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Messagetype;

@Repository("messagetypeRepository")
public interface MessagetypeRepository extends JpaRepository<Messagetype, BigInteger>{
	
	@Query("SELECT p FROM Messagetype p")
    public List<Messagetype> findAllMessageTypes();

}
