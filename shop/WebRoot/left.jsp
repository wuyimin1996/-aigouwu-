﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理页面</title>

<script src="js/prototype.lite.js" type="text/javascript"></script>
<script src="js/moo.fx.js" type="text/javascript"></script>
<script src="js/moo.fx.pack.js" type="text/javascript"></script>
<style>
body {
	font:12px Arial, Helvetica, sans-serif;
	color: #000;
	background-color: #EEF2FB;
	margin: 0px;
}
#container {
	width: 182px;
}
H1 {
	font-size: 12px;
	margin: 0px;
	width: 182px;
	cursor: pointer;
	height: 30px;
	line-height: 20px;	
}
H1 a {
	display: block;
	width: 182px;
	color: #000;
	height: 30px;
	text-decoration: none;
	moz-outline-style: none;
	background-image: url(images/menu_bgS.gif);
	background-repeat: no-repeat;
	line-height: 30px;
	text-align: center;
	margin: 0px;
	padding: 0px;
}
.content{
	width: 182px;
	height: 26px;
	
}
.MM ul {
	list-style-type: none;
	margin: 0px;
	padding: 0px;
	display: block;
}
.MM li {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	list-style-type: none;
	display: block;
	text-decoration: none;
	height: 26px;
	width: 182px;
	padding-left: 0px;
}
.MM {
	width: 182px;
	margin: 0px;
	padding: 0px;
	left: 0px;
	top: 0px;
	right: 0px;
	bottom: 0px;
	clip: rect(0px,0px,0px,0px);
}
.MM a:link {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(images/menu_bg1.gif);
	background-repeat: no-repeat;
	height: 26px;
	width: 182px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	text-decoration: none;
}
.MM a:visited {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(images/menu_bg1.gif);
	background-repeat: no-repeat;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	height: 26px;
	width: 182px;
	text-decoration: none;
}
.MM a:active {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(images/menu_bg1.gif);
	background-repeat: no-repeat;
	height: 26px;
	width: 182px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	text-decoration: none;
}
.MM a:hover {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	font-weight: bold;
	color: #006600;
	background-image: url(images/menu_bg2.gif);
	background-repeat: no-repeat;
	text-align: center;
	display: block;
	margin: 0px;
	padding: 0px;
	height: 26px;
	width: 182px;
	text-decoration: none;
}
</style>
</head>

<body>
<table width="100%" height="280" border="0" cellpadding="0" cellspacing="0" bgcolor="#EEF2FB">
  <tr>
    <td width="182" valign="top">
    <div id="container">
               <c:if test="${admin.role=='理货' or admin.role=='超管'}">
                  <h1 class="type"><a href="javascript:void(0)">产品管理</a></h1>
                  <div class="content">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><img src="images/menu_topline.gif" width="182" height="5" /></td>
                      </tr>
                    </table>
                    <ul class="MM">
                      <li><a href="productServlet?action=list" target="main">产品列表</a></li>
                      <li><a href="productServlet?action=publishUI" target="main">添加产品</a></li>
                    </ul>
                  </div>
          
                  <h1 class="type"><a href="javascript:void(0)">产品分类管理</a></h1>
                  <div class="content">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><img src="images/menu_topline.gif" width="182" height="5" /></td>
                      </tr>
                    </table>
                    <ul class="MM">
                      <li><a href="#">分类列表</a></li>
                      <li><a href="addCategory.jsp" target="main">添加分类</a></li>
                    </ul> 
                  </div>
                </c:if>  
                
                <c:if test="${admin.role!='理货'}">
                  <h1 class="type"><a href="javascript:void(0)">订单管理</a></h1>
                  <div class="content">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><img src="images/menu_topline.gif" width="182" height="5" /></td>
                      </tr>
                    </table>
                    <ul class="MM">
                      <c:if test="${admin.role=='超管'}">
                         <li><a href="productServlet?action=queryorder&statuscode=888" target="main">全部订单</a></li>
                      </c:if>
                      <c:if test="${admin.role=='客服' or admin.role=='超管'}">
                         <li><a href="productServlet?action=queryorder&statuscode=1" target="main">未审核订单</a></li>
                      </c:if>
                      <c:if test="${admin.role=='财务' or admin.role=='超管'}">
                         <li><a href="productServlet?action=queryorder&statuscode=2" target="main">未付款订单</a></li>
                      </c:if>
                      <c:if test="${admin.role=='仓管' or admin.role=='超管'}">
                         <li><a href="productServlet?action=queryorder&statuscode=3" target="main">未发货订单</a></li>
                      </c:if>
                      <c:if test="${admin.role=='客服' or admin.role=='超管'}">
                         <li><a href="productServlet?action=queryorder&statuscode=4" target="main">未确认收货订单</a></li>
                      </c:if>
                      <c:if test="${admin.role=='财务' or admin.role=='超管'}">
                        <li><a href="productServlet?action=queryorder&statuscode=5" target="main">已交易订单</a></li>
                      </c:if>
                    </ul>
                  </div>
                </c:if>
                <c:if test="${admin.role=='客服' or admin.role=='超管'}">
                <h1 class="type"><a href="javascript:void(0)">信息管理</a></h1>
                  <div class="content">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><img src="images/menu_topline.gif" width="182" height="5" /></td>
                      </tr>
                    </table>
                    <ul class="MM">
                      <li><a href="infoServlet?acion=list" target="main">信息列表</a></li>
                      <li><a href="addInfo.jsp" target="main">添加分类</a></li>
                    </ul> 
                  </div>
                </c:if>
          </div>
			<script type="text/javascript">
            var contents = document.getElementsByClassName('content');
            var toggles = document.getElementsByClassName('type');
        
            var myAccordion = new fx.Accordion(
                toggles, contents, {opacity: true, duration: 400}
            );
            myAccordion.showThisHideOpen(contents[0]);
        </script>
       </td>
  </tr>
</table>
</body>
</html>
