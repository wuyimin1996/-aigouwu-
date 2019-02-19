package com.wu.control;

import java.io.IOException;
import java.util.Map;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wu.dao.AdminDao;

public class AdminServlet extends HttpServlet {
	
	//管理员相关的servlet
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		//设置传入格式
			request.setCharacterEncoding("utf-8");
		//方法的分离
			String action = request.getParameter("action");
		if(action.equals("login")){
			login(request,response);
		}else if(action.equals("logout")){
			logout(request,response);
		}
	}
	//退出方法
	private void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		HttpSession session = request.getSession();
		session.invalidate();	
		//传入消息
		String message="<script>alert('注销成功');window.location.href='adminlogin.jsp';</script>";
		request.setAttribute("message",message);
		
		request.getRequestDispatcher("message.jsp").forward(request, response);
		
			
	}
	//登陆方法
	private void login(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//调用dao
		AdminDao adminDao=new AdminDao();
		Map<String, Object> admin = adminDao.validateVip(username, password);
		
		HttpSession session = request.getSession();
		session.setAttribute("admin", admin);
		//判断后台人员登陆的信息
		String message="";
		if(admin!=null){
			message="<script>alert('登录成功');window.location.href='adminIndex.jsp';</script>";			
		}else{
			message="<script>alert('登录失败');window.location.href='adminlogin.jsp';</script>";
		}
		request.setAttribute("message", message);
		
		request.getRequestDispatcher("message.jsp").forward(request,response);
	}


}
