<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
   function change(){
   alert(12331);
     document.getElementById("checkcodeimg").src="checkCodeServlet?random="+Math.random();
   }
</script>
<div style="background:#ccc">


	<c:if test="${vip==null}">
		<form action="vipServlet?action=login" method="post">
		 亲 ！请登录	用户名：<input type="text" name="username"/> 密码：<input type="password" name="password"/> 验证码：<input type="text" name="checkcode" size="4" style="width:30px"/><img id="checkcodeimg" src="checkCodeServlet" onclick="change();"/><input type="button" value="登录" onclick="form.submit();"/><input type="button" value="注册" onclick="window.location.href='register.jsp'"/>
		<a href="forget1.jsp">忘记密码？</a>
		</form>
	</c:if>
	<c:if test="${vip!=null}">
	    亲，${vip.username}，欢迎你回来！当前积分：${vip.score}
	   <c:if test="${vip.score<=99 }"><span style="color:red">铜卡会员</span></c:if>
	   <c:if test="${vip.score<=499 && vip.score>=100}"><span style="color:red">银卡会员</span></c:if>
	   <c:if test="${vip.score<=4999 && vip.score>=500}"><span style="color:red">金卡会员</span></c:if>
	   <c:if test="${vip.score>5000}"><span style="color:red">钻石会员</span></c:if>
	   
	    上一次登录时间：${vip.lastlogintime}<a href="vipindex.jsp">会员中心</a> ，<a href="vipServlet?action=logout">注销</a>
	</c:if>
</div>