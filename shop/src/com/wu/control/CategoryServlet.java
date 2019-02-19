package com.wu.control;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wu.dao.CategoryDao;


//商品分类
public class CategoryServlet extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			//转码
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		if(action.equals("add")){
			add(request,response);
		}
	}
	//添加分类
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//1.添加分类
		String name=request.getParameter("name");
		String sort=request.getParameter("sort");
		
		//封装数据
		Map<String,Object> record=new HashMap<String,Object>();
		record.put("name",name);
		record.put("sort",sort);
		
		//调用dao
		CategoryDao categoryDao=new CategoryDao();
		
		int flag = categoryDao.add(record);
		
		String message="";
		if(flag>0){
			message="<script>alert('操作成功')</script>";
		}else{
			
			message="<script>alert('操作失败')</script>";
		}
		//把信息放在页面
		request.setAttribute("message", message);
		//跳转页面
		request.getRequestDispatcher("addCategory.jsp").forward(request, response);
		
	}
}
