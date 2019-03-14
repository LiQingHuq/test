<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>      

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
</head>

<body>  
<div class="data_list">
		<img src="${path}/jswork/images/list_icon.png"/>
		<div class="data_list_title">最新博客</div>
		<div class="datas">
			<ul>			
			  <c:forEach var="blog" items="${blogList1}">
			  	  <li style="margin-bottom: 30px">
				  	<span class="date"> <a href="${path}/BlogAction/blog!getBlogByTypeIdForId?blog_id=${blog.blog_id}" target="_blank"><fmt:formatDate value="${blog.blog_time }" type="date" pattern="yyyy年MM月dd日"/></a></span>
				  	<span class="title"><a href="${path}/BlogAction/blog!getBlogByTypeIdForId?blog_id=${blog.blog_id}"  target="_blank">${blog.blog_title }</a></span>
				  	<span class="summary">摘要: ${blog.blog_summary }...</span>
							
				  	<span class="info">发表于 <fmt:formatDate value="${blog.blog_time }" type="date" pattern="yyyy-MM-dd"/> 阅读(${blog.blog_click})</span>
				  </li>
				  <hr style="height:5px;border:none;border-top:1px dashed gray;padding-bottom:  10px;" />
			  </c:forEach>
			  
			</ul> 
		</div>
   </div>    
    
  <div>
 </div>
 </body>
 </html>