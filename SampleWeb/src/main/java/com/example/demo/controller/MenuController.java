package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.constant.UrlConst;
/**
 * 
 */
@Controller
public class MenuController {

	@GetMapping(UrlConst.MENU)
	public String view() {

	return "menu";
}
}