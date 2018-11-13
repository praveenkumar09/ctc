package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Crsmessagetypeindic;

@Repository("crsmessagetypeindicRepository")
public interface CrsmessagetypeindicRepository extends JpaRepository<Crsmessagetypeindic, BigInteger>{
	@Query("SELECT p FROM Crsmessagetypeindic p")
    public List<Crsmessagetypeindic> findAllCrsMessageTypeIndic();
}
