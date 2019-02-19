<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传下载</title>
<script type="text/javascript">
	//简化获取id
	function $(id){
		return document.getElementById(id);
	} 

	//将上传后的文件路径从子页面传回到父页面

	  function setUploadFile(){
            var filepath=$("filepath");
            var photo=window.opener.document.getElementById("photo");
            photo.value=filepath.value;
            //关闭窗口
            window.close();
      }
</script>
</head>
<body>
	${message } 
	<form action="${pageContext.request.contextPath}/productServlet?action=upload" method="post" enctype="multipart/form-data">
	<input type="file" name="file"/>
	<input type="submit" name="submit" value="上传"/>
	<input type="hidden" id="filepath"  name="filepath" value="${filepath}" />
	<input type="button" value="确定" onclick="setUploadFile();"/>
	</form>
</body>
</html>