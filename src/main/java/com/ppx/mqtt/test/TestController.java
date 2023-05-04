package com.ppx.mqtt.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/test")
public class TestController {
	
	// https://blog.csdn.net/leveretz/article/details/125816695
	// https://blog.csdn.net/weixin_42230797/article/details/126507310
	
	@GetMapping("/test") @ResponseBody
    public String test(HttpServletRequest request) {
    	return "begin test002"; 
    }
}
