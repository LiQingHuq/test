
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>评论添加成功页面</title>
 
</head>
<body>	
	<h1 style="color: red" align="center">评论添加成功</h1>
	<h2  style="color: red" align="center"><a href="${path}/BlogAction/blog!getBlogByTypeIdForId?blog_id=${id}">点次返回博客内容页面</a></h2>
</body>

</html>