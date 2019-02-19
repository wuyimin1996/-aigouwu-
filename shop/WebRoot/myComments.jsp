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
    
    <title>我的评论列表</title>
    
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
                          <!--我的评论列表 START-->
                            <table id="maintables" border="1" bordercolor="blue" cellspacing="0" cellpadding="0" style="border-collapse:collapse;width:100%;">
						 <c:forEach items="${records}" var="record">
							         <tr>
							              <td>评论商品:${record.productname }</td>
							              <td>评论标题:${record.title}
							              <input type="button" value="删除此评论" onclick="window.location.href='productServlet?action=delComments&commentid=${record.commentid}'">
									              
							              </td>
							             </tr>
							            <tr>
							              <td>评分:${record.score}</td> 
							              <td>评论时间:${record.time}</td>
							             </tr>
							              <td>操作</td>
							          
							          <tr>
							          	<td colspan="2">
							          		评论内容：${record.content}
							          	</td>
							          </tr>
							          </c:forEach>

							         <tr>
							            <td colspan="2" align="center" height="30">
							               <%@ include file="page5.jsp" %>
							            </td>
							         </tr>
							     </table>
                          
                          
                          <!-- 我的评论列表 END -->
                        
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
