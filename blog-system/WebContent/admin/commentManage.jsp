<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评论管理页面</title>
<link rel="stylesheet" type="text/css" href="${path}/jswork/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${path}/jswork/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${path}/jswork/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${path}/jswork/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path}/jswork/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">


function deleteComment(){
	var selectedRows=$("#dg").datagrid("getSelections");
	if(selectedRows.length==0){
		 $.messager.alert("系统提示","请选择要删除的数据！");
		 return;
	 }
	 var strIds=[];
	 for(var i=0;i<selectedRows.length;i++){
		 strIds.push(selectedRows[i].comment_id);
	 }
	 var ids=strIds.join(",");
	 $.messager.confirm("系统提示","您确定要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("${path}/CommentAction/comment!delete.action",{ids:ids},function(result){
					if(result.success){
						 $.messager.alert("系统提示","数据已成功删除！");
						 $("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示","数据删除失败！");
					}
				},"json");
			} 
   });
}
</script>
</head>

<body style="margin: 1px">
<table id="dg" title="评论管理" class="easyui-datagrid"
   fitColumns="true" pagination="true" rownumbers="true"
   url="${path}/CommentAction/comment!findAll.action"" fit="true" toolbar="#tb">
   <thead>
   	<tr>
   		<th field="cb" checkbox="true" align="center"></th>
   		<th field="comment_id" width="20" align="center">编号</th>
   		<th field="comment_context" width="200" align="center">评论内容</th>   		
   		<th field="comment_ip" width="100" align="center">用户IP</th>   		
   		<th field="comment_time" width="50" align="center">评论日期</th>
   		<th field="blog_id" width="50" align="center">博客编号</th>
   	</tr>
   </thead>
 </table>
 <div id="tb">
 	<div>
 		<a href="javascript:deleteComment()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
 	</div>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
 </div>
 
 
</body>
</html>