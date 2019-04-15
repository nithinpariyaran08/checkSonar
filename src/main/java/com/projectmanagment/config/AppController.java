package com.projectmanagment.config;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

	/*@RequestMapping("/")
	String home(ModelMap modal) {
		modal.addAttribute("title", "Task CRUD");
		return "login";
	}*/
	
	
	/* @GetMapping({"/", "/hello"})
	    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
	        model.addAttribute("name", name);
	        return "hello";
	    }*/
	
	
	@GetMapping("/")
	    public String hello(Model model) {
		 model.addAttribute("title", "Task CRUD");
	        return "index";
	    }
	
	
	
	@RequestMapping("/userpage/")
	String user(ModelMap modal) {
		modal.addAttribute("title", "User Reg");
		return "userpage";
	}
	
	@RequestMapping("/projectpage/")
	String project(ModelMap modal) {
		modal.addAttribute("title", "Project CRUD");
		return "projectPage";
	}
	
	
	@RequestMapping("/taskpage/")
	String task(ModelMap modal) {
		modal.addAttribute("title", "Project CRUD");
		return "taskpage";
	}
	@RequestMapping("/partials/{page}")
	String partialHandler(@PathVariable("page") final String page) {
		return page;
	}

}
