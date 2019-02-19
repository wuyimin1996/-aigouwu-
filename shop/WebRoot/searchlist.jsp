<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>搜索结果列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <table width="900" border="1" cellspacing="0" cellpadding="0" bordercolor="#006699" style="border-collapse:collapse">
     <tr>
      <td ><%@ include file="top.jsp" %></td>
    </tr>
    <tr>
      <td><%@ include file="banner.jsp" %></td>
    </tr>
    <tr>
      <td><%@ include file="nav.jsp" %>
	  </td>
    </tr>
    <tr>
    	<td><%@ include file="position.jsp" %></td>
    </tr>
    
    <tr>
      <td height="96">
				  <!--产品列表 start-->
				  <table width="100%" border="0" cellspacing="0" cellpadding="0" style="font-size:13px;">
				   <c:forEach items="${records}" var="record" varStatus="status">
				       <c:if test="${status.index%4==0}"><tr></c:if>														
						  <td width="25%" style="line-height:25px;text-align:center">
							  <a href="productServlet?action=showDetail&productid=${record.productid}"><img  src="${record.photo}" width="200" height="200"/></a><br/>
							 <a href="productServlet?action=showDetail&productid=${record.productid}"> ${record.productname} </a><br/>
							   <span style="color:red">￥${record.myprice}</span> <s>￥${record.marketprice} </s>
						  </td>
					    <c:if test="${status.index%4==3}"></tr></c:if>				    
					</c:forEach>
					<c:if test="${totalCount%4!=0 && currentPage==totalPage}"></tr></c:if>
				  </table>
				  <!--产品列表 end-->
				  <div align="center"><%@ include file="page6.jsp" %></div> 
				  
				  
	  </td>
    </tr>
    <tr>
      <td><%@ include file="help.jsp" %></td>
    </tr>
    <tr>
      <td><%@ include file="copyright.jsp" %></td>
    </tr>
  </table>
     
  </body>
</html>
