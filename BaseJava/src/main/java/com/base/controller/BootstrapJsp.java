package com.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bootstrap")
public class BootstrapJsp {
	@RequestMapping("/index")
	public String index(){
		return "views/bootstrap/index";
	}
}
