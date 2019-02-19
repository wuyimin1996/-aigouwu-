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
    
    <title>会员注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <script type="text/javascript">
  	//ajax  学习  异步交互
  		var xmlHttp= null;
  		function init(){
  			if(window.XMLHttpRequest){
  				//非IE浏览器，用xmlhttpRequest对象创建
  				xmlHttp=new XMLHttpRequest();
  			}else{
  				//IE浏览器用activexobject 对象创建
  				xmlHttp =new ActiveXObejct("Microsoft.XMLHTTP");
  			}
  		}
  	//用户响应 用户点击
  	function check(){
  		//创建一个XMHTTP 
  		if(xmlHttp==null){
  			init();
  		}
  		var time = new Date().getTime();
  		//获取username 的值
  		var username=document.getElementById("username").value;
  		//要请求的服务器端地址
  		var url="vipServlet?action=validateUsername&time="+time;
  	  //与服务端建立连接(请求方式post或get，地址,true表示异步)
	   xmlHttp.open("POST", url, true);
	   //指定回调函数
	   xmlHttp.onreadystatechange = checkCallback;
	   
	   //定义传输文件http 头信息；
       xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded"); 
	   xmlHttp.send("username="+username); 
  	}
  //回调函数，对服务器的响应处理，监视response状态
  	function checkCallback(){
  		//请求状态4表示成功
  		if(xmlHttp.readyState==4 && xmlHttp.status==200){
  		var b=xmlHttp.responseText;
  		if(b=="1"){
  			//表示该用户名可以用
		        document.getElementById("checkmessage").innerHTML = "<b style='color:green'>恭喜你，用户名可以使用！</b>";
  			}else{
  			
		        document.getElementById("checkmessage").innerHTML = "<b style='color:red'>抱歉，用户名已经占用！</b>";
  			
  			}
  		}
  	}
  
  </script>
  <body>
  	${message}
  <table width="900" border="1" cellspacing="0" cellpadding="0" bordercolor="#006699" style="border-collapse:collapse">
    
    <tr>
      <td><%@ include file="banner.jsp" %></td>
    </tr>
    <tr>
     	 <b>会员注册</b>
    </tr>
    <tr>
      <td height="96">
      	<form action="vipServlet?action=register" method="post"> 
      			
      		<table height="100%">
      		
      			<tr>
      				<td>用户：</td>
      				<td><input id="username" type="text" name="username" value="${record.username}"/>
				<input  type="button" value="检测用户名"  onclick="check();"/>
                           <span id="checkmessage"></span>
      				</td>     				
     
      			</tr>
     		<tr>
      			<td>密码：</td>
      			<td><input type="password" name="password1"/></td>			
      		</tr>	
      		<tr>
      			<td>确认密码：</td>
      			<td><input type="password" name="password2"/></td>			
      		</tr>	
      		<tr>
      			<td>性别：</td>
      				<td>
      					<select name="sex">
      						<option value="男" <c:if test="${record.sex=='男'}"> selected</c:if>>男</option>
      						<option value="女" <c:if test="${record.sex=='男'}"> selected</c:if>>女</option>
      					</select>
      				</td>			
      		</tr>	
      		 <tr>
      			<td>email</td> 
      			<td><input type="text" name="email"  value="${record.email}"/></td>			
      		</tr>
      		 <tr>
      			<td>电话：</td>
      			<td><input type="text" name="phone" value="${record.phone}"/></td>			
      		</tr>
      		
      		 <tr>
      			<td>密码提示问题：</td>
      			<td>
      			<select name="question">
      				<option value="我的小学老师？" <c:if test="${record.question=='我的小学老师？'}"> selected</c:if>>我的小学老师？</option>
      				<option value="我的毕业老师" <c:if test="${record.question=='我的毕业老师'}"> selected</c:if>>我的毕业老师</option>
      				<option value="最帅的java老师是谁" <c:if test="${record.question=='最帅的java老师是谁'}"> selected</c:if>>最帅的java老师是谁</option>
      			</select>
      			</td>
      		</tr>
      		 <tr>
      			<td>密码提示答案</td>
      			<td><input type="text" name="answer" value="${record.answer}"/></td>			
      		</tr>
      		<tr>
      			<td colspan="2" style="text-align: center;">
      				<input type="button" value="注册" onclick="form.submit();"/>
      			</td>
      		</tr>
      		</table>
		</form>
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
