<%@page import="com.wu.dao.InfoDao"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	InfoDao infoDao=new InfoDao();
	
    List<Map<String,Object>> infolist1=infoDao.listByLanmu("新手入门");
    request.setAttribute("infolist1",infolist1);
    List<Map<String,Object>> infolist2=infoDao.listByLanmu("配送方式");
    request.setAttribute("infolist2",infolist2);
    List<Map<String,Object>> infolist3=infoDao.listByLanmu("支付方式");
    request.setAttribute("infolist3",infolist3);
    List<Map<String,Object>> infolist4=infoDao.listByLanmu("售后服务");
    request.setAttribute("infolist4",infolist4);
    List<Map<String,Object>> infolist5=infoDao.listByLanmu("特色服务");     
    request.setAttribute("infolist5",infolist5);  
    List<Map<String,Object>> infolist6=infoDao.listByLanmu("帮助信息");
    request.setAttribute("infolist6",infolist6);

 %>


<table width="100%">
	<tr>
		<td valign="top" >
			<b>新手入门</b><br/>
			<c:forEach items="${infolist1}" var="record">
				<A href="infoServlet?action=show&infoid=${record.infoid}" target="_blank">${record.title}</A>
			</c:forEach>
		</td>
		

		<td valign="top">
			<b>配送方式</b><br/>
			<c:forEach items="${infolist2}" var="record">
				<A href="infoServlet?action=show&infoid=${record.infoid}" target="_blank">${record.title}</A>
			</c:forEach>
		</td>
		
	
	<td valign="top" >
			<b>支付方式</b><br/>
			<c:forEach items="${infolist3}" var="record">
				<A href="infoServlet?action=show&infoid=${record.infoid}" target="_blank">${record.title}</A>
			</c:forEach>
		</td>
		
	<td valign="top" >
			<b>售后服务</b><br/>
			<c:forEach items="${infolist4}" var="record">
				<A href="infoServlet?action=show&infoid=${record.infoid}" target="_blank">${record.title}</A>
			</c:forEach>
		</td>

		<td valign="top" >
			<b>特色服务</b><br/>
			<c:forEach items="${infolist5}" var="record">
				<A href="infoServlet?action=show&infoid=${record.infoid}" target="_blank">${record.title}</A>
			</c:forEach>
		</td>
	
	
	<td valign="top" >
			<b>帮助信息</b><br/>
			<c:forEach items="${infolist6}" var="record">
				<A  href="infoServlet?action=show&infoid=${record.infoid}" target="_blank">${record.title}</A>
			</c:forEach>
		</td>
	
	</tr>
</table>
