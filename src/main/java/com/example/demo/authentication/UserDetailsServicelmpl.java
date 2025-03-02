package com.example.demo.authentication;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー情報生成
 * 
 */
@Component
@RequiredArgsConstructor
public class UserDetailsServicelmpl implements UserDetailsService{
	
	/**ユーザー情報テーブルRepository	 */
	private final UserInfoRepository repository;
	
	/**
	 * ユーザー情報生成
	 * 
	 * @param username
	 * @throws UsernameNotFoundException */
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		var userInfo = repository.findById(username)
				.orElseThrow(() -> new UsernameNotFoundException(username));
		
		return User.withUsername(userInfo.getLoginId())
				.password(userInfo.getPassword())
				.roles("USER")
				.build();
	}
	
}
