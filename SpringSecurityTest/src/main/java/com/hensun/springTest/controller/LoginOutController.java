package com.hensun.springTest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginOutController extends BaseController{
	
	public LoginOutController() {
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/login.do")
	public void home() {
		
	}

}
