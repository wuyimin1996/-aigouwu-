<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
		//购买的商品
		function addCart(){
		 var buycount=document.getElementById("buycount").value;
	       
	       window.location.href="productServlet?action=addCart&productid=${record.productid}&buycount="+buycount;
		}
	</script>
	
  </head>
  
  <body>
     <table width="700" border="1" cellspacing="0" cellpadding="0" bordercolor="#006699" style="border-collapse:collapse">
		<tr>
      			<td><%@ include file="top.jsp" %></td>
   		 </tr>
		<tr>
			<td><%@ include file="banner.jsp" %></td>
		</tr>
			<tr>
			<td><%@ include file="nav.jsp" %>  
			</td>
		</tr>
		<tr>
			<td height="96">
				<!-- 详细信息 start -->
	  
                   <div style="padding:15px">
                       <b>${record.title}</b><br/>
	                   <hr style="color:blue;height:1px;width:100%;"><br/>
	                   
	                   ${record.content}
                   
                   </div>
			
			 	<!--详细信息 end -->
			 <!-- 商品评论 -->
			 
			 
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
