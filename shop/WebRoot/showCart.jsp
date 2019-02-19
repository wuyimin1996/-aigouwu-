<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>购物车</title>
    
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
      <td><%@ include file="top.jsp" %></td>
    </tr>
    <tr>
      <td><%@ include file="banner.jsp" %></td>
    </tr>
    <tr>
      <td><%@ include file="nav.jsp" %></td>
    </tr>
    <tr>
      <td height="96">
      		<table height="100%">
      			<tr>
      				<td>商品封面</td>
      				<td>商品名称</td>
      				<td>商品单价</td>
      				<td>商品数量</td>
      				<td>总计</td>
      				<td>操作</td>
      			</tr>
      			
      			<c:forEach items="${cart}" var="record">
      			<tr>
      				<td><img src="${record.photo}" width="100" height="100"/></td>
      				<td>${record.name }</td>
      				<td>${record.myprice}</td>
      				<td>${record.buycount}</td>
      				<td>${record.totalprice}</td>
      				<td><a href="productServlet?action=delCart&productid=${record.productid}"></a></td>
      			</tr>
      		</c:forEach>
      		<tr>
      			<td colspan="6" style="text-align:right;">总价：${sum}
      			<input type="button" value="继续购物" onclick="window.location.href='productServlet?action=displayProducts'"/>
      			<c:if test="${fn:length(cart)>0}">
      			<input type="button" value="结算中心" onclick="window.location.href='productServlet?action=toAddress'"/>
      			</c:if>
      			</td>
      		</tr>
      		</table>
				  
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
