package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Cbcmessagetypeindic;

@Repository("cbcmessagetypeindicRepository")
public interface CbcmessagetypeindicRepository extends JpaRepository<Cbcmessagetypeindic, BigInteger>{

	@Query("SELECT p FROM Cbcmessagetypeindic p")
    public List<Cbcmessagetypeindic> findAllMessageTypeIndicator();
}
