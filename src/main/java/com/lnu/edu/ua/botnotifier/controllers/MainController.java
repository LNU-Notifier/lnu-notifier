package com.lnu.edu.ua.botnotifier.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lnu.edu.ua.botnotifier.api.services.IUserService;

@Controller
public class MainController {

	private IUserService userService;

	@GetMapping("/")
	@ResponseBody
	public String index() {
		return "";
	}

	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
}
