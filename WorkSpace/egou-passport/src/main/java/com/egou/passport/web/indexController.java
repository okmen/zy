package com.egou.passport.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/")
public class indexController {

	@RequestMapping(value = "/login")
	public String login(Model model) throws Exception {
		return "login";
	}
}
