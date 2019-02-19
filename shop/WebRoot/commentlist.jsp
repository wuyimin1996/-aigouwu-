<%@page import="com.wu.dao.CommentDao"%>
<%@page import="com.wu.dao.CategoryDao"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<b>商品评论</b><br/>
<c:if test="${vip!=null}">
<form action="productServlet?action=addComment" method="post">

	标题：<input type="text" name="title"/><br/>
	评分:<select name="score">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
		</select>分
		<br/>
	内容:
		<textarea name="content" rows="6" cols="30">
		
		</textarea>	
		<input type="hidden" name="productid" value="${record.productid}">
		<input type="button" value="评论" onclick="form.submit();" />
		<br>
</form>
</c:if>
<br/>
<br/>
<%
	CommentDao commentDao=new CommentDao();
	
	//查询某一个产品的所有的列表
   Map<String,Object> record=(Map<String,Object>)request.getAttribute("record");
	int productid=Integer.parseInt(record.get("productid").toString());
 	//评论 
 	List<Map<String,Object>> comments=commentDao.getCommentsByProduct(productid);
 	
 	request.setAttribute("comments",comments );
 	
 %>

<c:forEach items="${comments}" var="record">
	标题：${record.title} <br>
	
	评分：${record.score} 评论时间：${record.time} 评论客户:${record.username}<br>
	
	内容：${record.content}

	

</c:forEach>
