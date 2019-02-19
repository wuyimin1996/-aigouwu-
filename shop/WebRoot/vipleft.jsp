<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
<script type="text/javascript">

//原生 的ajax 的学习
  var xmlHttp= null;
  		function init(){
  			if(window.XMLHttpRequest){
  				//非IE浏览器，用xmlhttpRequest对象创建
  				xmlHttp=new XMLHttpRequest();
  			}else{
  				//IE浏览器用activexobject 对象创建
  				xmlHttp =new ActiveXObejct("Microsoft.XMLHTTP");
  			}
  		}
	function addAddress(){
		//创建一个XMHTTP 
  		if(xmlHttp==null){
  			init();
  		}
  		var time = new Date().getTime();
  		//获取username 的值
  	//	var username=document.getElementById("username").value;
  		//要请求的服务器端地址
  		var url="productServlet?action=addAddressUI&time="+time;
  	  //与服务端建立连接(请求方式post或get，地址,true表示异步)
	   xmlHttp.open("GET", url, true);
	   //指定回调函数
	   xmlHttp.onreadystatechange = Callback;
	   
	   //定义传输文件http 头信息；
     //  xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded"); 
	   xmlHttp.send(null); 
	
	}
  		//回调函数，对服务器的响应处理，监视response状态
  	function Callback(){
  		//请求状态4表示成功
  		if(xmlHttp.readyState==4 && xmlHttp.status==200){
  		var b=xmlHttp.responseText;
  		//alert(b);
  		document.getElementById("maintable").outerHTML=b;
  		
  		}
  	}
  		
function postAddress(){
		//创建一个XMHTTP 
  		if(xmlHttp==null){
  			init();
  		}
  		var time = new Date().getTime();
  		//获取username 的值
  		/* var addressname=document.getElementById("addressname").value;
  		var postcode=document.getElementById("postcode").value;
  		var receiver=document.getElementById("receiver").value;
  		var phone=document.getElementById("phone").value;	
  		var bz=document.getElementById("bz").value; */
  		   var addressname = document.getElementById("addressname").value;
		   var postcode = document.getElementById("postcode").value;
		   var receiver = document.getElementById("receiver").value;
		   var phone = document.getElementById("phone").value;
		   var bz = document.getElementById("bz").value;
  		
  	//	var username=document.getElementById("username").value;
  		//要请求的服务器端地址
  		var url="productServlet?action=addAddress&time="+time;
  	  //与服务端建立连接(请求方式post或get，地址,true表示异步)
	   xmlHttp.open("POST", url, true);
	   //指定回调函数
	   xmlHttp.onreadystatechange = callback2;
	   
	   //定义传输文件http 头信息；
     //  xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded"); 
	//   xmlHttp.send("addressname="+addressname+"&postcode="+postcode+"&receiver="+receiver+"&phone="+phone+"&bz="+bz); 
	 xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded"); 
	xmlHttp.send("addressname="+addressname+"&postcode="+postcode+"&receiver="+receiver+"&phone="+phone+"&bz="+bz);
	
	}
	//回调函数，对服务端的响应处理，监视response状态
	  function callback2(){
		   //请求状态为4表示成功
		   if (xmlHttp.readyState == 4 && xmlHttp.status == 200){
		        //接收返回的结果
			    var b = xmlHttp.responseText;
			    //alert(b);	   
			    if(b=="1"){
			       alert('添加成功');
			    }else{
			       alert('添加失败');
			    }	    
		   }
	  }

</script>
<div>
	<b>会员操作</b><br/>
	<a href="productServlet?action=myorder">我的订单<br/></a>
		--<a href="productServlet?action=myorder&statuscode=1" >未审核订单</a><br/>
		--<a href="productServlet?action=myorder&statuscode=2" >未付款订单</a><br/>
		--<a href="productServlet?action=myorder&statuscode=3" >未发货订单</a><br/>
		--<a href="productServlet?action=myorder&statuscode=4" >未确认收货订单</a><br/>
		--<a href="productServlet?action=myorder&statuscode=5" >已交易订单</a><br/>
	<a href="#">个人信息</a><br/>
		--<a href="vipServlet?action=edit">修改信息</a><br/>
		--<a href="editpwd.jsp">修改密码</a><br/>

	
	<a href="productServlet?action=mycomments">我的评论</a><br/>
	
	我的地址	<br/>
		--<a href="productServlet?action=myaddress" >管理地址</a><br/>
		--<a href="javascript:addAddress();" >新增地址</a><br/>
	

</div>