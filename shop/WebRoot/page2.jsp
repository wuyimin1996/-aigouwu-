<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




共找到  ${totalCount}条记录  当前第${currentPage} 页 
 <a href="productServlet?action=displayProducts&currentPage=1&orderfield=${orderfield}&ordervalue=${ordervalue}&categoryid=${categoryid}"></a>首页  
<c:if test="${currentPage==1}">上一页</c:if>
<c:if test="${currentPage!=1}"><a href="productServlet?action=displayProducts&currentPage=${currentPage-1}&orderfield=${orderfield}&ordervalue=${ordervalue}&categoryid=${categoryid}">上一页</a> </c:if>
<c:if test="${currentPage==totalPage}">下一页</c:if>
<c:if test="${currentPage!=totalPage}"><a href="productServlet?action=displayProducts&currentPage=${currentPage+1}&orderfield=${orderfield}&ordervalue=${ordervalue}&categoryid=${categoryid}">下一页 </a></c:if>
<a href="productServlet?action=displayProducts&currentPage=${totalPage}&orderfield=${orderfield}&ordervalue=${ordervalue}&categoryid=${categoryid}">尾页</a>