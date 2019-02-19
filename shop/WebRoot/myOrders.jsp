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
      <td>
         <%@ include file="top.jsp" %>     
      </td>
    </tr>
    
    <tr>
      <td>
         <%@ include file="banner.jsp" %>     
      </td>
    </tr>
    <tr>
      <td>
         <%@ include file="nav.jsp" %>       
      </td>
    </tr>
    <tr>
      <td>
         当前位置：<b>会员中心</b>   
      </td>
    </tr>
    <tr>
      <td height="96">
                                      
                <table width="100%">                  
                 <tr>  
                        <td width="200">
                        <!-- 左侧-->
                        <%@ include file="vipleft.jsp" %>   
                        </td>                                          
                        <td>
                          <!--我的订单 START-->
                            <table id="maintable" border="1" bordercolor="blue" cellspacing="0" cellpadding="0" style="border-collapse:collapse;width:100%;">
							          <tr style="height:35px;background-color:gray">
							              <td>订单编号</td>
							              <td>生成时间</td>
							              <td>付款方式</td>
							              <td>状态</td>
							              <td>下单用户</td>
							              <td>总价</td>
							              <td>操作</td>
							          </tr>
							         <c:forEach items="${records}" var="record">
								          <tr>
								             <td>${record.orderid}</td>
								             <td>${record.maketime}</td>
								             <td>${record.payway}</td>
								             <td>${record.status}</td>
								             <td>${record.username}</td>
								             <td>${record.total}</td>
								             <td><input type="button" value="明细" onclick="openWindow('productServlet?action=orderDetail&orderid=${record.orderid}','订单明细',950,500,1);" />
								                 <c:if test="${record.status=='未确认收货'}">
								                     <input type="button" value="确认收货" onclick="window.location.href='productServlet?action=updatestatus&orderid=${record.orderid}&dowhat=receive'" />							             
								                 </c:if>         
								             
								             </td>
								          </tr>
							         </c:forEach>  
							         
							         <tr>
							            <td colspan="10" align="center" height="30">
							               <%@ include file="page4.jsp" %>
							            </td>
							         </tr>
							     </table>
                          
                          
                          <!-- 我的订单 END -->
                        
                        </td>
                   </tr>
                                     
             </table>
				  
		  
	  </td>
    </tr>
    <tr>
      <td>
         <%@ include file="help.jsp" %>   
      </td>
    </tr>
    <tr>
      <td>
        <%@ include file="copyright.jsp" %>   
      </td>
    </tr>
  </table>
     
  </body>
</html>
