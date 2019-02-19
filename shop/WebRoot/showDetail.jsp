<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>在线产品列表</title>
    
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
				<!-- 产品的详细信息 start -->
			 <table width="100%" border="0">
			 	<tr>
			 		<td width="19%" height="177"><img src="${record.photo }" width="150" height="150"/> </td>
			 		<td width="81%">
			 			${record.name}<br/>
			 			应答价格：${record.myprice}<br/>
			 			市场价格：${record.marketprice}<br/>
			 			库存：${record.storecount}<br/>
			 			人气：${record.hit}<br/>
			 			上架时间：${record.time}<br/>
					购买数量：<input  id="buycount" type="text" name="buycount" value="1" style="width:20px"/><input type="button" onclick="addCart();" value="放入购物车" />
			 		</td>
			 	</tr>
			 	
			 	<tr>
			 		<td height="95" colspan="2">
			 			商品详情：<br/>
			 			${record.content}
			 		</td>
			 	</tr>
			 </table>
			 <!--产品详细信息 end -->
			 <!-- 商品评论 -->
			 
			 
			 </td>
		</tr>
			
			<!-- 添加评论 -->
		<tr>
			<td><%@ include file="commentlist.jsp" %></td>
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
