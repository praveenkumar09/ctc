package com.censof.myfi.hidefmyfi.serviceImpl;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.censof.myfi.hidefmyfi.entity.User;
import com.censof.myfi.hidefmyfi.repository.UserRepository;
import com.censof.myfi.hidefmyfi.service.UserService;
import com.censof.myfi.hidefmyfi.vo.UserVo;



@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    
	@Override
	public UserVo findUserByEmail(String email) {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(email); 
		UserVo userVo = new UserVo();
		userVo.setId(user.getId());
		userVo.setMyCBCId(user.getMyCbcId());
		userVo.setEmail(user.getEmail());
		userVo.setPassword(user.getPassword());
		userVo.setStatus(user.getStatus());
		userVo.setToken(user.getToken());
		userVo.setUsername(user.getName());
		return userVo;
	}
	
	
	@Override
	public void saveUser(UserVo userVo) {
		// TODO Auto-generated method stub
		User user = new User();
		
		if(userVo.getId() != null) {
			user.setId(userVo.getId());
		}
		
		user.setMyCbcId(userVo.getMyCBCId());
		user.setEmail(userVo.getEmail());
		
		user.setPassword(bCryptPasswordEncoder.encode(userVo.getPassword()));
		user.setStatus(userVo.getStatus());
		user.setToken(userVo.getToken());
		user.setName(userVo.getUsername());
		userRepository.save(user);
	}
	
	
	@Override
	public UserVo findByEmailAndMyCbcId(String email, String mycbcId) {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmailAndMyCbcId(email, mycbcId);
		UserVo userVo = new UserVo();
		userVo.setId(user.getId());
		userVo.setMyCBCId(user.getMyCbcId());
		userVo.setEmail(user.getEmail());
		userVo.setPassword(user.getPassword());
		userVo.setStatus(user.getStatus());
		userVo.setToken(user.getToken());
		userVo.setUsername(user.getName());
		return userVo;
	}


	@Override
	public UserVo findByToken(String token) {
		// TODO Auto-generated method stub
		User user = userRepository.findByToken(token);
		UserVo userVo = new UserVo();
		userVo.setId(user.getId());
		userVo.setMyCBCId(user.getMyCbcId());
		userVo.setEmail(user.getEmail());
		userVo.setPassword(user.getPassword());
		userVo.setStatus(user.getStatus());
		userVo.setToken(user.getToken());
		userVo.setUsername(user.getName());
		return userVo;
	}


	@Override
	public void updateUser(UserVo userVo) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setId(userVo.getId());
		user.setMyCbcId(userVo.getMyCBCId());
		user.setEmail(userVo.getEmail());
		
		user.setPassword(userVo.getPassword());
		user.setStatus(userVo.getStatus());
		user.setToken(userVo.getToken());
		user.setName(userVo.getUsername());
		userRepository.save(user);
	}


	@Override
	public UserVo findByUsername(String username) {
		// TODO Auto-generated method stub
		User user = userRepository.findByName(username);
		UserVo userVo = new UserVo();
		if(user != null) {
		userVo.setId(user.getId());
		userVo.setMyCBCId(user.getMyCbcId());
		userVo.setEmail(user.getEmail());
		userVo.setPassword(user.getPassword());
		userVo.setStatus(user.getStatus());
		userVo.setToken(user.getToken());
		userVo.setUsername(user.getName());
		return userVo;
		}else {
			return null;
		}
	}
	
	

}
