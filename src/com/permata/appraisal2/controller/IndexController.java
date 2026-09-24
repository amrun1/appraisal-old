package com.permata.appraisal2.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping({ "index", "" })
	public String index(Model model) {
		model.addAttribute("date_time", new Date());
		return "index";
	}

}