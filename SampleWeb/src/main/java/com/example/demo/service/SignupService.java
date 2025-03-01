package com.example.demo.service;

import java.util.Optional;

import org.dozer.Mapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserInfo;
import com.example.demo.form.SignupForm;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー登録画面
 */
@Service
@RequiredArgsConstructor
public class SignupService {

	/**ユーザー情報テーブル DAO	 */
	private final UserInfoRepository repository;
	
	/**DozerMapper	 */
	private final Mapper mapper;
	
	/**PasswordEncoder	 */
	private final PasswordEncoder passwordEncoder =new BCryptPasswordEncoder();
	
	/**
	 * ユーザー情報テーブル 新規登録
	 * @param loginId 入力情報
	 * @return 登録情報(ユーザー情報Entity)、既に同じユーザーIDで登録がある場合はempty
	 */
	public Optional<UserInfo> resistUserInfo(SignupForm  form){
		var userInfoExistedOpt = repository.findById(form.getLoginId());
		if(userInfoExistedOpt.isPresent()) {
			return Optional.empty();
		}
		var userInfo = mapper.map(form, UserInfo.class);
		
		var encodedPassword = passwordEncoder.encode(form.getPassword());
		userInfo.setPassword(encodedPassword);
		
		return  Optional.of(repository.save(userInfo));
	}
}
