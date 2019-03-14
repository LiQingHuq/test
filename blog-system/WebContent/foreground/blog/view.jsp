<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<script type="text/javascript" src="${path}/static/ueditor/third-party/SyntaxHighlighter/shCore.js"></script>
<script src="http://pv.sohu.com/cityjson?ie=utf-8"></script> 
<link rel="stylesheet" href="${path}/static/ueditor/third-party/SyntaxHighlighter/shCoreDefault.css">
<script type="text/javascript">
    SyntaxHighlighter.all();
</script>
<script type="text/javascript">
 //处理验证码问题
   function change(){
	  var v=document.getElementById("img");
	  v.src="<c:url value='/CommentAction/comment!ImgCode?a='/>"+new Date().getTime();
   }	

 //提交数据
	function submitData(){
		var comment_content=$("#comment_content").val();
		var verfycode=$("#verfycode").val();
		
		if(blog_content==null || blog_content==''){
			alert("请输入评论内容！");
		}else if(verfycode==null || verfycode==''){
			alert("请填写验证码！");
		}else{
			$.post("${path}/CommentAction/comment!add.action",
					{
				     'comment_content':comment_content,
				     'verfycode':verfycode,
				     'ip':returnCitySN["cip"],
				     'blog_id':'${blog.blog_id}'
				     },
				function(result){
				if(result.success){
					window.location.reload();
					alert("评论已提交成功");
				}else{
					alert(result.errorInfo);
				}
			},"json");
		}
	}
 
	function showOtherComment(){
		$('.otherComment').show();
	}
	
</script>
</head>

<body> 
<div class="data_list">
	<div class="data_list_title">
		<img src="${path}/jswork/images/blog_show_icon.png"/>
		博客信息
	</div>
	<div>
	    <div class="blog_title"><h3><strong>${blog.blog_title }</strong></h3></div>
	    <div style="padding-left: 50px;padding-bottom: 20px;padding-top: 10px">	 
	    <div class="blog_content">
			${blog.blog_context }
		</div>	   	    	  
		<div class="blog_info">
			发布时间：『 <fmt:formatDate value="${blog.blog_time }" type="date" pattern="yyyy-MM-dd"/>』&nbsp;&nbsp;博客类别：&nbsp;&nbsp;阅读(${blog.blog_click})
		</div>
									
	</div>
</div>

<div class="data_list">	
<div class="data_list_title">
		<img src="${path}/jswork/images/comment_icon.png"/>
		评论信息    
		<c:if test="${commentList.size()>5}">
			<a href="javascript:showOtherComment()" style="float: right;padding-right: 40px;">显示所有评论</a>
		</c:if>
	</div>
	<div class="commentDatas">
	
		<c:choose>
			<c:when test="${commentList.size()==0}">
				暂无评论
			</c:when>
			<c:otherwise>
				<c:forEach var="comment" items="${commentList }" varStatus="status">
						<c:choose>
							<c:when test="${status.index<5}">
								<div class="comment">
									<span>
									<font>${status.index+1 }楼&nbsp;&nbsp;&nbsp;&nbsp;${comment.comment_ip }：</font>
									${comment.comment_context }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[&nbsp;
									<fmt:formatDate value="${comment.comment_time }" type="date" pattern="yyyy-MM-dd HH:mm"/>&nbsp;]</span>
								</div>								
							</c:when>
							<c:otherwise>
								<div class="otherComment">
									<div class="comment">
										<span><font>${status.index+1 }楼&nbsp;&nbsp;&nbsp;&nbsp;${comment.comment_ip }：
										</font>${comment.comment_context }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[&nbsp;
										<fmt:formatDate value="${comment.comment_time }" type="date" pattern="yyyy-MM-dd HH:mm"/>&nbsp;]</span>
									</div>		
								</div>						
							</c:otherwise>
						</c:choose>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
</div>

<div class="data_list" >
	<div class="data_list_title">
		<img src="${path}/jswork/images/publish_comment_icon.png"/>
		发表评论
	</div>
	<div class="publish_comment">
	  <form id="loginForm" method="post" action="${path}/CommentAction/comment!add.action?blog_id=${blog.blog_id}">
			<div>
				<textarea style="width: 100%" rows="3" id="comment_content" name="comment_content" placeholder="来说两句吧..."></textarea>
			</div>
			<div class="verCode">			
				验证码：<input type="text" size="3" name="verfycode">
				<img id="img" src="<c:url value="/CommentAction/comment!ImgCode"/>" border="1"/>
                <a href="javascript:change()">换一张</a> <P style="color: red;font-width: 900">${error.verfycode}</P><br/> 
			</div>
			<div class="publishButton">
				<button class="btn btn-primary" type="submit">发表评论</button>
			</div>
		</form>
	</div>
</div>
</div>
</body>
</html>