package com.censof.myfi.hidefmyfi.service;

import com.censof.myfi.hidefmyfi.vo.UserVo;

public interface UserService {
	public UserVo findUserByEmail(String email);
	public void saveUser(UserVo user);
	public UserVo findByEmailAndMyCbcId(String email, String mycbcId);
	public UserVo findByToken(String token);
	public void updateUser(UserVo user);
	public UserVo findByUsername(String username);
	public UserVo findByMyCbcIdAndStatus(String mycbcId, int status);
	
}
