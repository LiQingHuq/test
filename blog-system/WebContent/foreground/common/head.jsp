<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="container">
<div class="row">
	<div class="col-md-4">
		<img alt="博客系统" src="${pageContext.request.contextPath}/jswork/images/logo.png" width="100" height="60">
	</div>
	<!-- 天气预报接口 -->
	<div class="col-md-8" style="color: red">
		<iframe style="float: right;" width="400" scrolling="no" height="60" frameborder="0" allowtransparency="true" 
		src="http://i.tianqi.com/index.php?c=code&id=12&icon=1&num=5">
		</iframe>
	</div>
</div>
</div>