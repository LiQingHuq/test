<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">  
<meta http-equiv="cache-control" content="no-cache">  
<meta http-equiv="expires" content="0"> 

<title>私人博客</title>

<link rel="stylesheet" href="${path}/jswork/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet" href="${path}/jswork/bootstrap3/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${path}/jswork/css/blog.css">


<script src="${path}/jswork/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script src="${path}/jswork/bootstrap3/js/bootstrap.min.js"></script>
<style>
        body {
            height: 100%;width: 100%;
            background: url('${pageContext.request.contextPath}/Login/img/bg.jpg');
            background-size:cover;
        }
    </style>
</head>
<body >
<div class="data_list">
	<div class="data_list_title" style="padding-left: 30%">关于博主${blogger.blogger_nickname }<br>
		<img src="${path}/jswork/images/2.jpg"/><br>		
	</div>
	<div style="padding-left: 30%">
	  <h1>Even the smallest person can change the course of the future.<br/>
就算再小的人物，也能改变未来的走向。</h1> ${blogger.blogger_context}
	</div>
</div>
</body>
</html>