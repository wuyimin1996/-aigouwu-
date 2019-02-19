package com.wu.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//验证用户是否登陆类
public class ValidateLogin {
	
	//验证用户是否登陆
	public static boolean validateVip(HttpServletRequest request){
		//获取session
		HttpSession session = request.getSession();
		//获取用户
		Map<String,Object> vip = (Map<String, Object>) session.getAttribute("vip");
		if(vip!=null){
			//登陆
			return true;
		}else{
			return false;
		}
	}
}
