<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>商品列表</title>
</head>
<body>
	  ${message}
   
     <table border="1" bordercolor="blue" cellspacing="0" cellpadding="0" style="border-collapse:collapse;width:100%;">
          <tr style="height:35px;background-color:gray">
              <td>产品ID</td>
              <td>产品名称</td>
              <td>应答价格</td>
              <td>市场价格</td>
              <td>库存数量</td>
              <td>点击量</td>
              <td>发布时间</td>
              <td>产品封面</td>
              <td>产品分类</td>
              <td>操作</td>
          </tr>
         <c:forEach items="${records}" var="record">
	          <tr>
	             <td>${record.productid}</td>
	             <td>${record.productname}</td>
	             <td>${record.myprice}</td>
	             <td>${record.marketprice}</td>
	             <td>${record.storecount}</td>
	             <td>${record.hit}</td>
	             <td>${record.time}</td>
	             <td><img src="${record.photo}" width="100" height="100"/></td>
	             <td>${record.categoryname}</td>
	             <td><a href="productServlet?action=edit&productid=${record.productid}">修改</a> 
	             <a href="productServlet?action=delete&productid=${record.productid}">删除</a></td>
	          </tr>
         </c:forEach>  
         
         <tr>
            <td colspan="10" align="center" height="30">
               <%@ include file="page.jsp" %>
            </td>
         </tr>
     </table>
</body>
</html>