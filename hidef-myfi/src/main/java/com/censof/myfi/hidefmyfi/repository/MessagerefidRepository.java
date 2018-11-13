package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Messagerefid;

@Repository
public interface MessagerefidRepository  extends JpaRepository<Messagerefid, BigInteger>{
	
	public Messagerefid findByDate(String date);

}
