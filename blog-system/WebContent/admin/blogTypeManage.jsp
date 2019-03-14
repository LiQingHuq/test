<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博客类别管理页面</title>
<link rel="stylesheet" type="text/css" href="${path}/jswork/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${path}/jswork/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${path}/jswork/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${path}/jswork/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path}/jswork/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">

var url;
//删除
function deleteBlogType(){
	var selectedRows=$("#dg").datagrid("getSelections");
	if(selectedRows.length==0){
		 $.messager.alert("系统提示","请选择要删除的数据！");
		 return;
	 }
	 var strIds=[];
	 for(var i=0;i<selectedRows.length;i++){
		 strIds.push(selectedRows[i].type_id);
	 }
	 var ids=strIds.join(",");
	 $.messager.confirm("系统提示","您确定要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("${path}/Blog_TypeAction/blog_Type!delete.action",{ids:ids},function(result){
					if(result.success){
						if(result.exist){
							 $.messager.alert("系统提示",result.exist);
						}else{
							 $.messager.alert("系统提示","数据已成功删除！");								
						}
						 $("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示","数据删除失败！");
					}
				},"json");
			} 
   });
}


//打开修改页面
function openBlogTypeUpdateDialog(){
	var selectedRows=$("#dg").datagrid("getSelections");
	 if(selectedRows.length!=1){
		 $.messager.alert("系统提示","请选择一条要编辑的数据！");
		 return;
	 }
	 var row=selectedRows[0];
	 $("#dlg").dialog("open").dialog("setTitle","编辑博客类别信息");//打开修改
	 $("#fm").form("load",row);
	 url="${path}/Blog_TypeAction/blog_Type!update.action?type_id="+row.type_id;
 }
//打开添加页面
function openBlogTypeAddDialog(){
	$("#dlg").dialog("open").dialog("setTitle","添加博客类别信息");
	url="${path}/Blog_TypeAction/blog_Type!add.action";
}

//ajax提交表单
function saveBlogType(){
	 $("#fm").form("submit",{
		url:url,
		onSubmit:function(){
			return $(this).form("validate");//表单验证
		},
		success:function(result){
			var result=eval('('+result+')');//将数据转换成json类型  change the JSON string to javascript object
			if(result.success){
				$.messager.alert("系统提示","保存成功！");
				resetValue();
				$("#dlg").dialog("close");
				$("#dg").datagrid("reload");
			}else{
				$.messager.alert("系统提示","保存成功！");//猎人去打猎，打下来了一只大雁，但是没有得到它，因为路太曲折，找不到了。
				$("#dlg").dialog("close");
				$("#dg").datagrid("reload");
				return;
			}
		}
	 });
 }
 //清空值
function resetValue(){
	 $("#type_name").val("");
	 $("#orderNum").val("");
 }
//关闭打开的dialog
 function closeBlogTypeDialog(){
	 $("#dlg").dialog("close");
	 resetValue();
 }

</script>
</head>
<body style="margin: 1px">
<table id="dg" title="博客类别管理" class="easyui-datagrid"
   fitColumns="true" pagination="true" rownumbers="true"
   url="${path}/Blog_TypeAction/blog_Type!findAll.action" fit="true" toolbar="#tb">
   <thead>
   	<tr>
   		<th field="cb" checkbox="true" align="center"></th>
   		<th field="type_id" width="20" align="center">编号</th>
   		<th field="type_name" width="100" align="center">博客类型名称</th>
   		<th field="orderNum" width="100" align="center">博客排序</th>
   	</tr>
   </thead>
 </table>
 <div id="tb">
 	<div>
 	    <a href="javascript:openBlogTypeAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
 		<a href="javascript:openBlogTypeUpdateDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
 		<a href="javascript:deleteBlogType()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
 	</div>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
 </div>
 
 
 <!-- 博客类型添加修改-->
 <div id="dlg" class="easyui-dialog" style="width:500px;height:180px;padding: 10px 20px"
   closed="true" buttons="#dlg-buttons">  
   
   <form id="fm" method="post">
   	<table cellspacing="8px">     	
   		<tr>
   			<td>博客类别名称：</td>
   			<td><input type="text" id="type_name" name="type_name" class="easyui-validatebox" required="true"/></td>
   		</tr>
   		<tr>
   			<td>博客排序：</td>
   			<td><input type="text" id="orderNum" name="orderNum" class="easyui-numberbox" required="true" style="width: 60px"/>&nbsp;(类别根据排序序号从小到大排序)</td>
   		</tr>
   	</table>
   </form>
 </div>
 
 <div id="dlg-buttons">
 	<a href="javascript:saveBlogType()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
 	<a href="javascript:closeBlogTypeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
 </div>
 
</body>
</html>