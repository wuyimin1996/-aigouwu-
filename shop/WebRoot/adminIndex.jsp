<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>管理中心</title>
<meta http-equiv=Content-Type content=text/html;charset=utf-8>
</head>


<c:if test="${admin==null}">
	<jsp:forward page="/adminlogin.jsp"></jsp:forward>
</c:if>
<frameset rows="64,*"  frameborder="NO" border="0" framespacing="0">
	<frame src="admin_top.jsp" noresize="noresize" frameborder="NO" name="topFrame" scrolling="no" marginwidth="0" marginheight="0" target="main" />
  <frameset cols="200,*"  rows="560,*" id="frame">
	<frame src="left.jsp" name="leftFrame" noresize="noresize" marginwidth="0" marginheight="0" frameborder="0" scrolling="no" target="main" />
	
  	<!--  条件判断  整合页面 -->
	<c:if test="${admin.role=='理货' || admin.role=='超管'}"> 
	<frame src="productServlet?action=list" name="main" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" target="_self" />
  	</c:if>
  	
  	<!--  条件判断  整合页面 -->
  	<c:if test="${admin.role=='客服'}"> 
	<frame src="productServlet?action=queryorder&statuscode=1" name="main" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" target="_self" />
  	</c:if>
  	
  	  	<!--  条件判断  整合页面 -->
  	<c:if test="${admin.role=='财务'}"> 
	<frame src="productServlet?action=queryorder&statuscode=2" name="main" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" target="_self" />
  	</c:if>
  	
  	<!--  条件判断  整合页面 -->
  	<c:if test="${admin.role=='仓管'}"> 
	<frame src="productServlet?action=queryorder&statuscode=3" name="main" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" target="_self" />
  	</c:if>
  	
  	
  </frameset>
<noframes>
  <body></body>
    </noframes>
</html>
