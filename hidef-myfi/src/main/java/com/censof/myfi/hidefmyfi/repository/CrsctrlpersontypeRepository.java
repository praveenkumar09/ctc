package com.censof.myfi.hidefmyfi.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.Crsctrlpersontype;

@Repository("crsctrlpersontypeRepository")
public interface CrsctrlpersontypeRepository extends JpaRepository<Crsctrlpersontype, BigInteger>{
	@Query("SELECT p FROM Crsctrlpersontype p")
    public List<Crsctrlpersontype> findAllControllingPersonType();

}
