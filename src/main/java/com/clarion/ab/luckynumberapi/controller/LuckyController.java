package com.clarion.ab.luckynumberapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.clarion.ab.luckynumberapi.model.User;
import com.clarion.ab.luckynumberapi.service.LuckyNumberService;

@Controller
public class LuckyController {
	
	@Autowired
	LuckyNumberService luckyNumber;
	
	@GetMapping(value = "/")
	public String getLandingPage(User user) {
		return "checkLuckyNumber";
	}
	
	@PostMapping(value = "/checkLuckyNumber")
	public String checkLuckyNumber(User user) {
		luckyNumber.checkLuckyNumber(user);
		return "result";
	}
	
}
