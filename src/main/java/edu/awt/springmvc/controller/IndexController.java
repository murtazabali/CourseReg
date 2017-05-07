package edu.awt.springmvc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.awt.springmvc.service.CRService;

@Controller
public class IndexController {
	
	@Autowired
	CRService cr;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String Index(){
		return "index";
	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String Details(
							@RequestParam(required = false) Integer regno,
							@RequestParam String code,
							@RequestParam String cmd,
							ModelMap map
			){
		if(cmd.equals("Withdraw")){
			cr.Withdraw(regno, code);
		}
		if(cmd.equals("Register")){
			cr.Register(regno, code);
		}
		if(regno != null){
			map.addAttribute("student", cr.getStudent(regno));
			map.addAttribute("regs", cr.getReg(regno));
			map.addAttribute("offcrs", cr.offeredCourses(regno));
		}
		return "index";
	}
}
