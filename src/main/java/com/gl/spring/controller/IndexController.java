package com.gl.spring.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	@RequestMapping(value = "index",name = "首頁...",method = RequestMethod.GET)
	public String indexPage(Locale locale , Model model) {
		  
	return "index";
	}
	@RequestMapping(value = "index",name = "會員首頁...",method = RequestMethod.POST)
	public String indexOk(Locale locale , Model model) {
		
	return "index";	
	}
}
