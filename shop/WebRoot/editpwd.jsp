<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>会员密码修改页面</title>

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
	<table width="900" border="1" cellspacing="0" cellpadding="0"
		bordercolor="#006699" style="border-collapse:collapse">

		<tr>
			<td><%@ include file="top.jsp"%></td>
		</tr>

		<tr>
			<td><%@ include file="banner.jsp"%></td>
		</tr>
		<tr>
			<td><%@ include file="nav.jsp"%></td>
		</tr>
		<tr>
			<td>当前位置：<b>会员中心</b></td>
		</tr>
		<tr>
			<td height="96">

				<table width="100%">
					<tr>
						<td width="200">
							<!-- 左侧--> <%@ include file="vipleft.jsp"%>
						</td>
						<td>
								<!--会员信息修改页面开始 START-->
								<table id="maintable" border="1" bordercolor="blue" cellspacing="0"
									cellpadding="0" style="border-collapse:collapse;width:100%;">
						<form action="vipServlet?action=updatepwd" method="post">
							
								<tr>
									<td colspan="2" style="background-color: gray">
									个人密码修改
									</td>
								</tr>
									
									<tr>
										<td>原始密码：</td>
										<td><input type="text" name="oldpwd" id="oldpwd"
											/></td>
									</tr>
									<tr>
										<td>新密码：</td>
										<td><input type="text" name="password1" id=password1
											 /></td>
									</tr>
									<tr>
										<td>确认新密码：</td>
										<td><input type="text" name="password2" id="password2"
											 /></td>
									</tr>
									
									<tr>
										<td colspan="2" style="text-align:center;"><input
											type="button" value="修改" onclick="form.submit();">
										</td>
									</tr>


								</form> <!-- 会员信息修改 END -->
								</table>
							</td>
					</tr>

				</table></td>
		</tr>
		<tr>
			<td><%@ include file="help.jsp"%></td>
		</tr>
		<tr>
			<td><%@ include file="copyright.jsp"%>
			</td>
		</tr>
	</table>

</body>
</html>
