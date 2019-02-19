package com.wu.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wu.dao.InfoDao;
//
//帮组信息相关的servlet
public class InfoServlet extends HttpServlet {

	
	
//	private static final long sgetCommentsByProducterialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
	
		if(action.equals("add")){
			add(request,response);
		}else if(action.equals("show")){
			show(request,response);
		}
	}
	//查询单个信息
	private void show(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		int infoid = Integer.parseInt(request.getParameter("infoid"));
		
		InfoDao infoDao=new InfoDao();
		Map<String, Object> record = infoDao.get(infoid);
		
		request.setAttribute("record", record);
		request.getRequestDispatcher("showInfo.jsp").forward(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		//获取数据
		String title=request.getParameter("title");
		String time=request.getParameter("time");
		String publisher=request.getParameter("publisher");
		String lanmu=request.getParameter("lanmu");
		String content=request.getParameter("content");
	
		Map<String ,Object> record= new HashMap<String, Object>();
		
		record.put("title", title);
		record.put("time", time);
		record.put("publisher", publisher);
		record.put("lanmu", lanmu);
		record.put("content", content);
	
		String message="";
		InfoDao infoDao=new InfoDao();
		int flag=infoDao.add(record);
		if(flag>0){
			message="<script>alert('操作成功');window.location.href='infoServlet?action=list';</script>";
		}else{
			message="<script>alert('添加失败');window.location.href='addInfo.jsp';</script>";
		}
		
		request.setAttribute("message",message);
		
		request.getRequestDispatcher("message.jsp").forward(request, response);
	}	
	

}
