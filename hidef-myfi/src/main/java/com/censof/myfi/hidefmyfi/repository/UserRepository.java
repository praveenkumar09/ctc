package com.censof.myfi.hidefmyfi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.censof.myfi.hidefmyfi.entity.User;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	public User findByEmail(String email);
	public User findByEmailAndMyCbcId(String email,String mycbcId);
	public User findByToken(String token);
	public User findByName(String name);
	public User findByNameAndStatus(String name,int status);
	public User findByMyCbcIdAndStatus(String myCBCId,int status);
	public static String GET_USER = "select e from User e where e.myCbcId=:myCbcId";
	
	@Query(GET_USER)
	public List<User> getUser(@Param("myCbcId") String mycbcid);
}
