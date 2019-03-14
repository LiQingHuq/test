<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
function checkData(){
	var q=document.getElementById("serch").value.trim();
	if(q==null || q==""){
		alert("请输入您要查询的关键字！");
		return false;
	}else{
		return true;
	}
}
</script>
<div class="row">
 
	<div class="col-md-12" style="padding-top: 10px">
	
		<nav class="navbar navbar-default">
		  <div class="container-fluid">
		  
	     <div class="navbar-header">		
		      <a class="navbar-brand" href="${path}/index.html"><font color="black"><strong>首页</strong></font></a>
		    </div>

		    
		  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" >
		      <ul class="nav navbar-nav">	       
		        <li><a href="#" target="_blank"><font color="black"><strong>清华大咖</strong></font></a></li>
		        
		        <li><a href="${path }/BlogAction/blog!load" ><font color="black"><strong>本站源码下载</strong></font></a></li>
		      </ul>
		      
		      <form action="" class="navbar-form navbar-right" role="search" method="post" onsubmit="return checkData()">
		        <div class="form-group" >
		          <input type="text" id="serch" name="serch" value="${serch }" class="form-control" placeholder="请输入要查询的关键字...">
		        </div>
		        <button type="submit" class="btn btn-default">搜索</button>
		      </form>
		    </div>
		  </div>
		</nav>
	</div>
</div>