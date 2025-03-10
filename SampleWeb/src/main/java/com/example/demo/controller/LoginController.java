package com.example.demo.controller;

import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.MessageConst;
import com.example.demo.constant.UrlConst;
import com.example.demo.form.LoginForm;
import com.example.demo.service.LoginService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

/**
 * ログイン画面コントローラー
 * 
 * 
 */

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	/**ログイン画面　service	 */
	private final LoginService service;
	
	/**PasswordEncoder	 */
	private final PasswordEncoder passwordEncoder =new BCryptPasswordEncoder();

	/**メッセージソース	 */
	private final MessageSource messageSource;
	
	/**
	 * ログイン
	 * 
	 *  @param model モデル
	 *  @param form 入力情報
	 *  @return 表示画面
	 *  
	 *  */
	@GetMapping(UrlConst.LOGIN)
	public String view(Model model,LoginForm form) {
		return "login";
	}
	
	@PostMapping(UrlConst.LOGIN)
	public String login(Model model,LoginForm form) {
		var userInfo = service.searchUserById(form.getLoginId());
		var isCorrectUserAuth = userInfo.isPresent() 
				&& passwordEncoder.matches(form.getPassword(), userInfo.get().getPassword());
 		if(isCorrectUserAuth) {
		return "redirect:/menu";
		} else {
			var errorMsg = AppUtil.getMessage(messageSource, MessageConst.LOGIN_WRONG_INPUT);
		model.addAttribute("errorMsg",errorMsg);
		return "login";
		}
	}
}
