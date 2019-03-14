<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博客系统后台管理页面</title>
<link rel="stylesheet" type="text/css" href="${path}/jswork/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${path}/jswork/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${path}/jswork/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${path}/jswork/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path}/jswork/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">

	var url;	
	//打开面板
	function openTab(text,url,iconCls){
		if($("#tabs").tabs("exists",text)){
			$("#tabs").tabs("select",text);
		}else{
			var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:90%' src='${path}/admin/"+url+"'></iframe>";
			$("#tabs").tabs("add",{
				title:text,
				iconCls:iconCls,
				closable:true,
				content:content
			});
		}
	}
	
	
	//关闭系统
	function logout(){
		$.messager.confirm("系统提示","您确定要退出系统吗？",function(r){
			if(r){
				window.location.href='${path}/BloggerAction/blogger!layout.action';
			} 
		 });
	}
	
	
	//点击修改打开密码修改dialog
	function openPasswordUpdateDialog(){
		$("#dlg").dialog("open").dialog("setTitle","修改密码");
		url="${path}/BloggerAction/blogger!updatePassword.action";
	}
	//点击保存修改用户名
	function updatePassword(){
		$("#ff").form("submit",{
			url:url,
			onSubmit:function(){				
				var newPassword=$("#newPassword").val();
				var newPassword2=$("#newPassword2").val();
				if(!$(this).form("validate")){
					return false;
				}
				if(newPassword!=newPassword2){
					$.messager.alert("系统提示","确认密码输入错误！");
					return false;
				}
				return true;
			},
			success:function(result){
				var result=eval('('+result+')');
				if(result.success){
					$.messager.alert("系统提示","密码修改成功，下一次登录生效！");
					resetValue();
					$("#dlg").dialog("close");
				}else{
					$.messager.alert("系统提示","密码修改成功，下一次登录生效！！");
					resetValue();
					$("#dlg").dialog("close");	
					return;
				}
			}
		 });
	}
	//关闭修改页面
	function closePasswordUpdateDialog(){
		resetValue();
		$("#dlg").dialog("close");
	}
	//重置
	function resetValue(){
		$("#oldPassword").val("");
		$("#newPassword").val("");
		$("#newPassword2").val("");
	}
	
	
</script>
</head>
<body class="easyui-layout">
<div region="north" style="height: 78px;background-color: #E0ECFF">
	<table style="padding: 5px" width="100%">
		<tr>
			<td width="50%">
				<img alt="logo" src="${path}/jswork/images/java.png" width="100" height="60">
				
			</td>
			<td valign="bottom" align="right" width="50%">
				<font size="3"><strong>欢迎：</strong>${blogger_session.blogger_name}</font>
			</td>
		</tr>
	</table>
</div>


<div region="center">
	<div class="easyui-tabs" fit="true" border="false" id="tabs">
		<div title="首页" data-options="iconCls:'icon-home'">
			<div align="center" style="padding-top: 100px"><font color="red" size="10">欢迎使用</font></div>
		</div>
	</div>
</div>

<div region="west" style="width: 200px" title="导航菜单" split="true">
	<div class="easyui-accordion" data-options="fit:true,border:false">
		<div title="常用操作" data-options="selected:true,iconCls:'icon-item'" style="padding: 10px">
			<a href="javascript:openTab('写博客','writeBlog.jsp','icon-writeblog')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-writeblog'" style="width: 150px">写博客</a>			
		</div>
		<div title="博客管理"  data-options="iconCls:'icon-bkgl'" style="padding:10px;">
			<a href="javascript:openTab('写博客','writeBlog.jsp','icon-writeblog')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-writeblog'" style="width: 150px;">写博客</a>
			<a href="javascript:openTab('博客信息管理','blogManage.jsp','icon-bkgl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-bkgl'" style="width: 150px;">博客信息管理</a>
		</div>
		<div title="博客类别管理" data-options="iconCls:'icon-bklb'" style="padding:10px">
			<a href="javascript:openTab('博客类别信息管理','blogTypeManage.jsp','icon-bklb')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-bklb'" style="width: 150px;">博客类别信息管理</a>
		</div>
		<div title="评论管理"  data-options="iconCls:'icon-plgl'" style="padding:10px">			
			<a href="javascript:openTab('评论信息管理','commentManage.jsp','icon-plgl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-plgl'" style="width: 150px;">评论信息管理</a>
		</div>
		<div title="个人信息管理"  data-options="iconCls:'icon-grxx'" style="padding:10px">
			<a href="javascript:openTab('修改个人信息','modifyInfo.jsp','icon-grxxxg')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-grxxxg'" style="width: 150px;">修改个人信息</a>
		</div>
		<div title="系统管理"  data-options="iconCls:'icon-system'" style="padding:10px">		   
			<a href="javascript:openPasswordUpdateDialog()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-modifyPassword'" style="width: 150px;">修改密码</a>
			<a href="javascript:logout()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-exit'" style="width: 150px;">安全退出</a>
		</div>
	</div>
</div>
<div region="south" style="height: 25px;padding: 5px" align="center">
	清华大咖2817171178@qq.com
</div>



<div id="dlg" class="easyui-dialog" style="width:400px;height:200px;padding: 10px 20px"
   closed="true" buttons="#dlg-buttons">
   
   <form id="ff" method="post">
   	<table cellspacing="8px">
   		<tr>
   			<td>用户名：</td>
   			<td><input type="text" id="blogger_name" name="blogger_name" readonly="readonly" value="${blogger_session.blogger_name}" style="width: 200px"/></td>
   		</tr>
   		<tr>
   			<td>新密码：</td>
   			<td><input type="password" id="newPassword" name="newPassword" class="easyui-validatebox" required="true" style="width: 200px"/></td>
   		</tr>
   		<tr>
   			<td>确认新密码：</td>
   			<td><input type="password" id="newPassword2" name="newPassword2" class="easyui-validatebox" required="true" style="width: 200px"/></td>
   		</tr>
   	</table>
   </form>
 </div>
 
 <div id="dlg-buttons">
 	<a href="javascript:updatePassword()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
 	<a href="javascript:closePasswordUpdateDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
 </div>
 
</body>
</html>