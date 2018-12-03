package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Crspayldin;

@Repository("crspayldinRepository")
public interface CrspayldinRepository extends JpaRepository<Crspayldin, BigInteger>{

}
