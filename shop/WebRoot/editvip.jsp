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

<title>会员信息修改页面</title>

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
							<form action="vipServlet?action=update" method="post">
							
								<tr>
									<td colspan="2" style="background-color: gray">
									个人信息修改
									</td>
								</tr>
								
								
									<tr>
										<td>性别：</td>
										<td><select name="sex">
												<option value="男"
													<c:if test="${record=='男' }">selected</c:if>>男</option>
												<option value="女"
													<c:if test="${record=='女' }">selected</c:if>>女</option>
										</select></td>
									</tr>
									<tr>
										<td>邮箱：</td>
										<td><input type="text" name="email" id="email"
											value="${record.email}"/></td>
									</tr>
									<tr>
										<td>电话：</td>
										<td><input type="text" name="phone" id="phone"
											value="${record.phone}" /></td>
									</tr>
									<tr>
										<td>密码提示问题：</td>
										<td><select name="question">
												<option value="我的小学老师？"
													<c:if test="${record.question=='我的小学老师？'}"> selected</c:if>>我的小学老师？</option>
												<option value="我的毕业老师"
													<c:if test="${record.question=='我的毕业老师'}"> selected</c:if>>我的毕业老师？</option>
												<option value="最帅的java老师是谁"
													<c:if test="${record.question=='最帅的java老师是谁'}"> selected</c:if>>最帅的java老师是谁？</option>
										</select></td>
									</tr>

									<tr>
										<td>答案：</td>
										<td><input type="text" name="answer"
											value="${record.answer}" /></td>
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
