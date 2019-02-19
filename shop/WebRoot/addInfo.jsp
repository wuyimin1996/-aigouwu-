<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>发布信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/calender.js"></script>
	
	 <script type="text/javascript">
	     //简化获取ID
	     function $(id){
		    return document.getElementById(id);
		 }	 	 
	  // 验证提交表单
		function validatePost(){
	  
			  if(validateField($("title"),'标题')==true && validateField($("time"),'发布时间')==true && validateField($("publisher"),'发布人') ){
			   	//获取到 商品描述里面的的东西
			   	var content=GetContents();
			     if(content!=null && content!=""){
				  //如果全部通过，则提交表单
			      $("form").submit();	
			     }else{
			       alert('内容为空');
			     }	     	  
			  }	
		}		
		//验证字段
		function validateField(name,tip){
		   
		       if(name.value==""){
			      alert(tip+'不能为空');
				  name.focus();
				  return false;
			   }else{
			      return true;
			   }
		
		}
	 </script>
	 
	 

	
<script language="javascript" src="js/calender.js"></script>
 <script type="text/javascript"src="ckeditor/ckeditor.js"></script>
 
  </head>
  
  <body>
  ${message}
      <form id="form" action="/shop/infoServlet?action=add" method="post">
          <table border="1" bordercolor="blue" cellspacing="0" cellpadding="0" width="950" style="border-collapse:collapse;">
              <tr>
                 <td colspan="2" style="text-align:center">添加信息</td>
              </tr>
              
              <tr>
                 <td width="15%">标题</td>
                 <td width="85%"><input id="name" type="text" name="name"></td>
              </tr>
              <tr>
                 <td>标题：</td>
                 <td><input id="title" type="text" name="title"></td>
              </tr>
                <tr>
                 <td>发布时间：</td>
                   <td>
                   	<input id="time" type="text" name="time"  onclick="setday(this)" />
    			</td>
              </tr>
              <tr>
                 <td>发布人：</td>
                 <td><input id="publisher" type="text" name="publisher"></td>
              </tr>
              
                <tr>
                 <td>栏目：</td>
                 <td>
              <select name="lanmu">
                     	<c:forEach items="#{records}" var="r"> 
                         <option value="通知公告">通知公告</option>
                        <option value="新手入门">新手入门</option>
                        <option value="配送方式">配送方式</option>
                        <option value="支付方式">支付方式</option>
                        <option value="售后服务">售后服务</option>
                        <option value="特色服务">特色服务</option>
                        <option value="帮助信息">帮助信息</option>
                         </c:forEach>
                     </select>                
                 </td>
              </tr>
     
           
              <tr>
                 <td>内容：</td>
                 <td>
                 
                <textarea rows="10" cols="50" name="content" id="content">     </textarea>
                		 <script type="text/javascript">
                		CKEDITOR.replace('content');
                	
                	</script>

                 
                 </td>
              </tr>
     
             <tr>
                 <td colspan="2"><input type="button" value="发布" onClick="validatePost();"></td>  
              </tr>
           
          </table>
      </form>
  </body>
</html>

