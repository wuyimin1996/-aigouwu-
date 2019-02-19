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
                          
                               <form action="productServlet?action=updateAddress" method="post">
					               <table width="100%">
					                  <tr>
					                      <td>收货地址：</td>
					                      <td><input type="text" name="addressname" value="${record.addressname}"></td>
					                  </tr>
					                  <tr>
					                      <td>邮编：</td>
					                      <td><input type="text" name="postcode" value="${record.postcode}"></td>
					                  </tr>
					                  <tr>
					                      <td>收货人：</td>
					                      <td><input type="text" name="receiver" value="${record.receiver}"> </td>
					                  </tr>
					                  <tr>
					                      <td>联系电话：</td>
					                      <td><input type="text" name="phone" value="${record.phone}"></td>
					                  </tr>
					                  <tr>
					                      <td>备注：</td>
					                      <td><input type="text" name="bz" value="${record.bz}"></td>
					                  </tr>
					                  <tr>
					                      <td colspan="2"><input type="button" value="确定" onclick="form.submit();">
					                        <input type="hidden" name="addressid" value="${record.addressid}">
					                      </td>
					                  </tr>            
					               </table>
					             </form>
                          
                        
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
