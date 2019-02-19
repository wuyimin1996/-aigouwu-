<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%> 

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>配送地址</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	

  </head>
  
  <body>
  <table width="900" border="1" cellspacing="0" cellpadding="0" bordercolor="#006699" style="border-collapse:collapse">
     <tr>
      <td><%@ include file="top.jsp" %></td>
    </tr>
    
    <tr>
      <td><%@ include file="banner.jsp" %></td>
    </tr>
    <tr>
      <td><%@ include file="nav.jsp" %></td>
    </tr>
    <tr>
      <td height="96">
      
           <c:if test="${fn:length(records)>0}">
               <form action="productServlet?action=toOrderConfirm" method="post">
               <table width="100%">
                  <tr>
                     <td>
                                               配送信息： <select name="address">
                           <c:forEach items="${records}" var="record">
                              <option value="${record.addressid}">${record.addressname},${record.postcode},${record.receiver},${record.phone}</option>
                           </c:forEach>
                         </select>
                     </td>
                  </tr>
                  <tr>
                  	<td>
                  		支付方式：
                  		<select name="payway">
                  			<option value="货到费付款">货到费付款</option>
                  			<option value="网银">网银</option>
                  			<option value="支付宝">支付宝 </option>
                  		</select>
                  	</td>  
                  </tr>    
                  	<tr>
                  		<td>
                  		<input type="button" value="下一步" onclick="form.submit();">
                  		</td>
                  	</tr>              
               </table>
               </form>
            </c:if>  
            <c:if test="${fn:length(records)==0}">
              	<form action="productServlet?action=saveAddress" method="post">
               <table width="100%">
                  <tr>
                      <td>收货地址：</td>
                      <td><input type="text" name="addressname"></td>
                  </tr>
                  <tr>
                      <td>邮编：</td>
                      <td><input type="text" name="postcode"></td>
                  </tr>
                  <tr>
                      <td>收货人：</td>
                      <td><input type="text" name="receiver"></td>
                  </tr>
                  <tr>
                      <td>联系电话：</td>
                      <td><input type="text" name="phone"></td>
                  </tr>
                  <tr>
                      <td>备注：</td>
                      <td><input type="text" name="bz"></td>
                  </tr>
                  <tr>
                      <td colspan="2"><input type="button" value="确定" onclick="form.submit();"></td>
                  </tr>            
               </table>
               </form>
			</c:if>	  
				  
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
