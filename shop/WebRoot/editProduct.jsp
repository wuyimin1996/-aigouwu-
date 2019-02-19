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
    
    <title>修改商品</title>
    
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
	 function validatePost(){
	  
			  if(validateField($("name"),'商品名称')==true && validateField($("myprice"),'应答价格')==true && validateField($("marketprice"),'市场价格') && validateField($("storecount"),'库存数量') &&validateField($("hit"),'浏览数量') && validateField($("time"),'发布时间') && validateField($("photo"),'商品封面')){
			   	//获取到 商品描述里面的的东西
			   	var content=GetContents();
			     if(content!=null && content!=""){
				  //如果全部通过，则提交表单
			      $("form").submit();	
			     }else{
			       alert('商品描述为空');
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
			
		//获取CKEditor 控件中的内容
		function GetContents(){
			var content=CKEDITOR.instances.content;
			 return content.getData();
		}
		
	 </script>
	 <script type="text/javascript" src="js/calender.js"></script>
	<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
	
  </head>
  
  <body>
  ${message}
      <form id="form" action="/shop/productServlet?action=update" method="post">
          <table border="1" bordercolor="blue" cellspacing="0" cellpadding="0" width="950" style="border-collapse:collapse;">
              <tr>
                 <td colspan="2" style="text-align:center">修改商品</td>
              </tr>
              
              <tr>
                 <td width="15%">商品名称：</td>
                 <td width="85%"><input id="name" type="text" name="name" value="${record.name}"></td>
              </tr>
              <tr>
                 <td>应答价格：</td>
                 <td><input id="myprice" type="text" name="myprice" value="${record.myprice}"></td>
              </tr>
              <tr>
                 <td>市场价格：</td>
                 <td><input id="marketprice" type="text" name="marketprice" value="${record.marketprice}"></td>
              </tr>
              <tr>
                 <td>库存数量：</td>
                 <td><input id="storecount" type="text" name="storecount" value="${record.storecount}"></td>
              </tr>
              <tr>
                 <td>浏览数量：</td>
                 <td><input id="hit" type="text" name="hit" value="${record.hit}"></td>
              </tr>
              <tr>
                 <td>发布时间：</td>
                 <td><input id="time" type="text" name="time" value="${record.time}" onclick="setday(this)" readonly="readonly" ></td>
              </tr>
              <tr>
                 <td>商品封面：</td>
                 <td><input id="photo" type="text" name="photo" value="${record.photo}"><input type="button" value="修改上传封面" onclick="openWindow('upload.jsp','上传文件',500,200,0)" ></td>
              </tr>
              <tr>
                 <td>商品分类：</td>
                 <td>
              <select name="categoryid">
                     	<c:forEach items="#{records}" var="r"> 
                         <option value="${r.categoryid}" <c:if test="${r.categoryid==record.categoryid }">selected="selected"</c:if>>${r.name}</option>                       		
                         </c:forEach>
                     </select>                
                 </td>
              </tr>
              <tr>
                 <td>商品描述：</td>
                 <td>
                     <textarea id="content" name="content" rows="10" cols="50">${record.content}</textarea>
                 	<script type="text/javascript">
                 		CKEDITOR.replace('content');
                 	</script>
                 </td>
              </tr>
              
             <tr>
             	<input type="hidden"  name="productid" value="${record.productid}"/> 
                 <td colspan="2"><input type="button" value="修改" onClick="validatePost();"></td>  
              </tr>
           
          </table>
      </form>
      
     
  </body>
</html>

