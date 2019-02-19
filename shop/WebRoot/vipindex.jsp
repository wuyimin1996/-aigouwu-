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
    
    <title>会员中心</title>
    
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
  	${message}
  <table width="900" border="1" cellspacing="0" cellpadding="0" bordercolor="#006699" style="border-collapse:collapse">
   <tr>
      <td><%@ include file="top.jsp" %></td>
    </tr>
      <tr>
      <td><%@ include file="nav.jsp" %></td>
    </tr>
    <tr>
      <td><%@ include file="banner.jsp" %></td>
    </tr>
  
    <tr>
     	当前位置： <b>会员中心</b>
    </tr>
    <tr>
      <td height="96">
      			
      		<table height="100%">
      		
      			<tr>
      				<td width="200px">
      				<!-- 左侧 -->
      				<%@ include file="vipleft.jsp"%>
      				</td>
      				 <!-- 右侧 -->	
      				<td>
      					<div id="maintable">
      					<!-- 主体  开始 -->
      					欢迎 进入欢迎 进入欢迎 进入欢迎 进入欢迎 进入欢迎 进入欢迎 进入
      					欢迎 进入欢迎 进入欢迎 进入欢迎 进入欢迎 进入欢迎 进入欢迎 进入
      					欢迎 进入欢迎 进入欢迎 进入欢迎 进入欢迎 进入欢迎 进入欢迎 进入
      					</div>
      					<!-- 主体 结束 -->
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
