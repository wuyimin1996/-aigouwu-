<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>订单列表</title>
<script type="text/javascript">
/* 
			* 打开新窗口 
		 	* f:链接地址 
			* n:窗口的名称 
			* w:窗口的宽度 
		 	* h:窗口的高度 
			* s:窗口是否有滚动条，1：有滚动条；0：没有滚动条 
		 	*/  
		function openWindow(f,n,w,h,s){  
		   sb = s == "1" ? "1" : "0";  
		    l = (screen.width - w)/2;  
		   t = (screen.height - h)/2;  
		   sFeatures = "left="+ l +",top="+ t +",height="+ h +",width="+ w  
		            + ",center=1,scrollbars=" + sb + ",status=0,directories=0,channelmode=0";  
		   openwin = window.open(f , n , sFeatures );  
		   if (!openwin.opener)  
		        openwin.opener = self;  
		    openwin.focus();  
		    return openwin;  
		} 

</script>

</head>
<body>
	  ${message}
    <table border="1" bordercolor="blue" cellspacing="0" cellpadding="0" style="border-collapse:collapse;width:100%;">
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
	                  <c:if test="${record.status=='未审核' && (admin.role=='超管' || admin.role=='客服')}">
	                       <input type="button" value="确认审核" onclick="window.location.href='productServlet?action=updatestatus&orderid=${record.orderid}&dowhat=pass'" />
	                  </c:if>
	                  
	                   <c:if test="${record.status=='未付款' && (admin.role=='超管' || admin.role=='财务')}">
	                       <input type="button" value="确认付款" onclick="window.location.href='productServlet?action=updatestatus&orderid=${record.orderid}&dowhat=pay'" />
	                  </c:if>
	                  
	                   <c:if test="${record.status=='未发货' && (admin.role=='超管' || admin.role=='仓管')}">
	                       <input type="button" value="确认发货" onclick="window.location.href='productServlet?action=updatestatus&orderid=${record.orderid}&dowhat=send'" />
	                  </c:if>
	                  
	                  <c:if test="${record.status=='未确认发货' && (admin.role=='超管' || admin.role=='客服')}">
	                       <input type="button" value="确认收货" onclick="window.location.href='productServlet?action=updatestatus&orderid=${record.orderid}&dowhat=receive&vipid=${record.vipid}'" />
	                  </c:if>
	                          
	             
	             </td>
	          </tr>
         </c:forEach>  
         
         <tr>
            <td colspan="10" align="center" height="30">
               <%@ include file="page3.jsp" %>
            </td>
         </tr>
     </table>
</body>
</html>