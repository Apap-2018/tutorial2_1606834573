package com.example.demo.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {
	@RequestMapping ("/viral")
	public String index() {
		return "viral";
	}
	
	@RequestMapping ("/challenge")
	public String challenge(@RequestParam(value="name") String name, Model model) {
		model.addAttribute("name", name);
		return "challenge";
	}
	
	@RequestMapping(value= {"/challenge", "/challenge/{name}"})
	public String challengePath(@PathVariable Optional<String> name, Model model) {
		if(name.isPresent()) {
			model.addAttribute("name", name.get());
		} else {
			model.addAttribute("name", "KB");
		} return "challenge";
	}
	
	@RequestMapping("/generator")
	public String generator(@RequestParam(value= "a") Integer param1, @RequestParam(value= "b") Integer param2, Model model) {
		String result = "";
		String resultPart = "hm";
		
		if(param1 == null || param2 == null || (param1 <= 1 && param2 <= 1)) {
			result = resultPart;
		}  else {
			resultPart="h";
			
			if (param1 == 0) {
				for (int i = 0; i < 1; i++) {
					resultPart += "m";
				}
			} else {
				for (int i = 0; i < param1; i++) {
					resultPart += "m";
				}
			}
			
			for (int i = 0; i < param2; i++) {
				if (i == (param2-1)) {
					result += resultPart;
				} else {
					result += (resultPart + " ");
				}
			}
		}
		
		model.addAttribute("param1", param1);
		model.addAttribute("param2", param2);
		model.addAttribute("result",result);
		
		return "generator";
	}
}
