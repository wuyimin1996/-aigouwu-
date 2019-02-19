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
    
    <title>订单明细</title>
    
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
   <table width="900" border="1" cellspacing="0" cellpadding="0" bordercolor="#006699" style="border-collapse:collapse">

    

    <tr>
      <td height="96">
               <div>
                  <b>基本信息</b><br>
	                    订单编号：${record.orderid}<br>
	                    生成时间：${record.maketime}<br>
	                    订单状态：${record.status}<br>
	                    下单用户： ${record.username}<br> 
               </div>
              
       
               <div>
                    <b>配送信息</b><br/>
                    收货地址：${record.addressname}<br/>
                    邮编：${record.postcode}<br/>
                    收货人：${record.receiver}<br/>
                    联系方式：${record.phone}<br/>
                    备注：${record.bz}<br/>
                              
               </div>
               
               <div>
                    <b>支付方式</b><br/>
                    ${record.payway} 
                                               
               </div>
                
      
      
                <table width="100%">
                  
                  <tr>
                     <td colspan="5">
                           <b>商品清单</b>
                     </td>
                  </tr>
                   <tr>  
                        <td>商品封面</td>
                        <td>商品名称</td>
                        <td>商品单价</td>
                        <td>商品数量</td>
                        <td>总计</td>
                   </tr>
                 <c:forEach items="${records}" var="record">
                   <tr>  
                        <td><img src="${record.photo}" width="100" height="100"/></td>
                        <td>${record.name}</td>
                        <td>${record.myprice}</td>
                        <td>${record.quantity}</td>
                        <td>${record.total}</td>
                   </tr>
                </c:forEach>   
                <tr>
                   <td colspan="5" style="text-align:right;">总价：${sum}</td>
                </tr>
                

             </table>
				  
			
	  </td>
    </tr>

  </table>
     
  </body>
</html>
