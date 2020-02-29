package com.dj.ssm.comtroller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/index/")
public class IndexController {
	

	@RequestMapping("exit")
	public String exit(HttpSession session) {
		session.removeAttribute("user");
		return "user/login";
	}
	
	
	@RequestMapping("toIndex")
	public String toIndex() {
		return "index/index";
	}

	@RequestMapping("toTop")
	public String toTop() {
		return "index/top";
	}
	
	@RequestMapping("toLeft")
	public String toLeft() {
		return "index/left";
	}
	
	@RequestMapping("toRight")
	public String toRight() {
		return "index/right";
	}
}
