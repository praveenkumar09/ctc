package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Crspayldname;

@Repository("crspayldnameRepository")
public interface CrspayldnameRepository extends JpaRepository<Crspayldname, BigInteger>{

}
