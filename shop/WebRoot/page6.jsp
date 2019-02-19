<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
  function gotopage(page){
	     var form=document.getElementById("searchform");   
	     form.action="productServlet?action=searchProduct&currentPage="+page;
	     form.submit();
 }
</script>


<form id="searchform" action="" method="post">
  共找到${totalCount}条记录，当前第${currentPage}页，
   <a href="javascript:gotopage('1')">首页</a> 
   <c:if test="${currentPage==1}">上一页</c:if>
   <c:if test="${currentPage!=1}"><a href="javascript:gotopage('${currentPage-1}')">上一页</a></c:if> 
   <c:if test="${currentPage==totalPage}">下一页</c:if>
   <c:if test="${currentPage!=totalPage}"><a href="javascript:gotopage('${currentPage+1}')">下一页</a></c:if>
   <a href="javascript:gotopage('${totalPage}')">尾页</a>
   
   <input type="hidden" name="categoryid" value="${categoryid}"/>
   <input type="hidden" name="keyword" value="${keyword}"/>
   
</form>