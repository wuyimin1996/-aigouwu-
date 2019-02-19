<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加分类</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	 <script type="text/javascript">
	     //简化获取ID
	     function $(id){
		    return document.getElementById(id);
		 }	 	 
	  // 验证提交表单
		function validatePost(){
	  
			  if(validateField($("name"),'分类名称')==true && validateField($("sort"),'内容排序')==true ){
			      //如果全部通过，则提交表单
			      $("form").submit();		  
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
  </head>
  
  <body>
  ${message}
      <form id="form" action="/shop/categoryServlet?action=add" method="post">
          <table border="1" bordercolor="blue" cellspacing="0" cellpadding="0" width="600" style="border-collapse:collapse;">
              <tr>
                 <td colspan="2" style="text-align:center">添加分类</td>
              </tr>
              
              <tr>
                 <td>分类名称：</td>
                 <td><input id="name" type="text" name="name"></td>
              </tr>
              <tr>
                 <td>分类排序：</td>
                 <td><input id="sort" type="text" name="sort"></td>
              </tr>
             
              
             <tr>
                 <td colspan="2"><input type="button" value="添加" onClick="validatePost();"></td>  
              </tr>
           
          </table>
      </form>
  </body>
</html>

