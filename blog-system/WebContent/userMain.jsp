<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

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



<style type="text/css">
	  body {
        padding-top: 10px;
        padding-bottom: 40px;
      }
</style>
</head>

<body>             
<div class="container">
	<jsp:include page="/foreground/common/head.jsp"/>
	<jsp:include page="/foreground/common/menu.jsp"/>	
	<!-- 栅格系统 -->
	<div class="row">	   	  	   
		 <div class="col-md-9">		   		 
				<jsp:include page="/foreground/blog/list.jsp"/>		
		</div>					
		<div class="col-md-3">		
			<div class="data_list">			     				
				<div class="data_list_title">
					<a href="${path}/BloggerAction/blogger!find.action" target="_blank">博主信息<br>
					<img src="${path}/jswork/images/2.jpg" width="180" height="180"/></a><br>					
				</div>							
				<div class="nickName">${blogger.blogger_nickname}</div>
				<div class="userSign">(${blogger.blogger_sign})</div>
			</div>
			
			
			<div class="data_list">			
				<div class="data_list_title">
					<img src="${path}/jswork/images/byType_icon.png"/>
					按博客志类别
				</div>
				
				<div class="datas">
					<ul>
						<c:forEach var="blogTypeCount" items="${typeList }">
							<li><span><a href="${path}/BlogAction/blog!getListByTypeIdForId?type_id=${blogTypeCount.type_id }" target="_blank">${blogTypeCount.type_name }<span></span></a></span></li>
						</c:forEach>
					</ul>
				</div>				
			</div>
									
			<div class="data_list">
			
				<div class="data_list_title">				
					<img src="${path}/jswork/images/byDate_icon.png"/>
					按发布日期
				</div>				
				<div class="datas">
					<ul>
						<c:forEach var="blogCount" items="${blogList }" varStatus="flag" >
												 
						   <c:if test="${blogCount.blog_time ne  blogList[flag.index+1].blog_time}">
							<li><span><a href="${path}//BlogAction/blog!getListByTypeIdForTime?blog_time=${blogCount.blog_time}" target="_blank"><fmt:formatDate value="${blogCount.blog_time }" type="date"  dateStyle="default"/></a></span></li>
						   </c:if>
						</c:forEach >
					</ul>
				</div>
				
			</div>
			
			<div class="data_list">
				<div class="data_list_title">
					<img src="${path}/jswork/images/link_icon.png"/>
					友情链接
				</div>
				<div class="datas">
					<ul>						
							<li><span><a href="https://www.cnblogs.com/" target="_blank">博客园</a></span></li>	
							<li><span><a href="https://www.csdn.net/" target="_blank">csdn</a></span></li>	
							<li><span><a href="http://www.w3school.com.cn/" target="_blank">W3cschool</a></span></li>														   			
					</ul>
				</div>
			</div>
		</div>
	</div>	
	<jsp:include page="/foreground/common/foot.jsp"/>
</div>
</body>
</html>