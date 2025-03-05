package com.example.demo.service;

import java.util.Optional;

import org.dozer.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.constant.AuthorityKind;
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
	private final PasswordEncoder passwordEncoder;
	
	/**
	 * 画面の入力情報をもとにユーザー情報テーブルの新規登録を行います。
	 * 
	 * <P>ただし、以下のテーブル項目はこの限りではありません。
	 * <ul>
	 * <li>パスワード：画面で入力したパスワードがハッシュ化され登録されます。</li>
	 * <li>権限：常に「商品情報の確認が可能」のコード値が登録されます。</li>
	 * </ul>
	 * 
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
		userInfo.setAuthority(AuthorityKind.ITEM_WATCHER.getAuthorityKind());
		
		return  Optional.of(repository.save(userInfo));
	}
}
