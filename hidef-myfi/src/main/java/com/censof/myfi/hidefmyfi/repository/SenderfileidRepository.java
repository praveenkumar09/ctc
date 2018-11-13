package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Senderfileid;


@Repository
public interface SenderfileidRepository extends JpaRepository<Senderfileid, BigInteger> {
	
	public Senderfileid findByDate(String date);

}
