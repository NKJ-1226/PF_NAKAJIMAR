package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.constant.UrlConst;
/**
 * 
 */
@Controller
public class MenuController {

	@GetMapping(UrlConst.MENU)
	public String view(@AuthenticationPrincipal User user, Model model) {
		var hasUserManageAuth = user.getAuthorities().stream()
				.allMatch(authority -> authority.getAuthority()
						.equals(AuthorityKind.ITEM_AND_USER_MANAGER.getAuthorityKind()));
		model.addAttribute("hasUserManageAuth" , hasUserManageAuth);

	return "menu";
	}

}