<%@page import="com.wu.dao.CategoryDao"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%

   //调用DAO
   CategoryDao dao=new CategoryDao();
   //查询所有的分类
   List<Map<String,Object>> categroies=dao.list();
   
   request.setAttribute("categroies",categroies);

 %>
<A href="index.jsp">首页</A>
<c:forEach items="${categroies}" var="record">
   <a href="productServlet?action=displayProducts&categoryid=${record.categoryid}">${record.name}</a>
</c:forEach>
<a href="productServlet?action=showCart">查看购物车</a>
<a href="productServlet?action=displayProducts">返回首页</a>