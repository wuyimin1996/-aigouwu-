<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:if test="${totalCount!=0}">

共找到${totalCount}条记录，每页${pageSize}条，共分${totalPage}页，当前第${currentPage}页，
   <c:if test="${totalCount==0}">首页</c:if>
   <c:if test="${totalCount!=0}"><a href="productServlet?action=myaddress&currentPage=1">首页</a></c:if>
   <c:if test="${currentPage==1}">上一页</c:if>
   <c:if test="${currentPage!=1}"><a href="productServlet?action=myaddress&currentPage=${currentPage-1}&currentPage=1">上一页</a></c:if> 
   <c:if test="${currentPage==totalPage || totalCount==0}">下一页</c:if>
   <c:if test="${currentPage!=totalPage && totalCount>0}"><a href="productServlet?action=myaddress&currentPage=${currentPage+1}&currentPage=1">下一页</a></c:if>
   <c:if test="${totalCount==0}">尾页</c:if>
   <c:if test="${totalCount!=0}"><a href="productServlet?action=myaddress&currentPage=${totalPage}&currentPage=1">尾页</a></c:if>
   
</c:if>

<c:if test="${totalCount==0}">
暂无数据
</c:if>