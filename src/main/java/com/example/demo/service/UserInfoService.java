package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserInfoRepository;

public class UserInfoService implements UserDetailsService {

	@Autowired
	private UserInfoRepository userInfoRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<UserInfo> userInfo=userInfoRepository.findByName(username);
		return userInfo.map(UserInfoDetails::new)
				.orElseThrow(()-> new UsernameNotFoundException("User not found "+username));
	}
public UserInfo getOneUser(String name)
{
return userInfoRepository.findByName(name).orElse(null);	
}
public UserInfo addOneUser(UserInfo userInfo)
{
	userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
return userInfoRepository.save(userInfo);	
}
public List<UserInfo> getAll()
{
return userInfoRepository.findAll();	
}


}
