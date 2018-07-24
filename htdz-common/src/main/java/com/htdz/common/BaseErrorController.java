package com.htdz.common;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller 
@RequestMapping(value = "error")
public class BaseErrorController implements ErrorController {
	@Override 
	public String getErrorPath() { 
		return "/baseerror"; 
	} 
	
	@RequestMapping()
	public String error() { 
		return getErrorPath(); 
	}

}
