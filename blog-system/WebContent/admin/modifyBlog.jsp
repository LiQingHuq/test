<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改博客页面</title>
<link rel="stylesheet" type="text/css" href="${path}/jswork/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${path}/jswork/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${path}/jswork/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${path}/jswork/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path}/jswork/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>


<script type="text/javascript" charset="utf-8" src="${path}/jswork/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${path}/jswork/ueditor/ueditor.all.min.js"> </script>

<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->

<script type="text/javascript" charset="utf-8" src="${path}/jswork/ueditor/lang/zh-cn/zh-cn.js"></script>
<link rel =stylesheet href="${path}/jswork/ueditor/themes/default/css/ueditor.css">
<script type="text/javascript">
		
function submitData(){	
	var blog_title=$("#blog_title").val();
	var type_id=$("#type_id").combobox("getValue");
	var blog_content=UE.getEditor('editor').getContent();
	var blog_summary=$("#blog_summary").val();
	
	if(blog_title==null || blog_title==''){
		alert("请输入标题！");
	}else if(type_id==null || type_id==''){
		alert("请选择博客类别！");
	}else if(blog_content==null || blog_content==''){
		alert("请输入内容！");
	}else{
		$.post("${path}/BlogAction/blog!update.action",
				{
			    id:"${param.id}",
			    'blog_title':blog_title,
			    'type_id':type_id,
			    'blog_content':blog_content,
			    'blog_summary':blog_summary
			    },function(result){
			if(result.success){
				alert("博客修改成功！");
				resetValue()
			}else{
				alert("博客修改成功！");
				resetValue()
			}
		},"json");
	}
}

// 重置数据
function resetValue(){
	$("#blog_title").val("");
	$("#type_id").combobox("setValue","");
	UE.getEditor('editor').setContent("");
	$("#blog_summary").val("");
}

</script>
<body style="margin: 10px">
 <s:action name="blog_Type!findList" namespace="/" executeResult="true"></s:action>             
<div id="p" class="easyui-panel" title="编写博客" style="padding: 10px">
 	<table cellspacing="20px">
   		<tr>
   			<td width="80px">博客标题：</td>
   			<td><input type="text" id="blog_title" name="blog_title" sty+le="width: 400px;"/></td>
   		</tr>
   		<tr>
   			<td>所属类别：</td>
   			<td>
   				<select class="easyui-combobox" style="width: 154px" id="type_id" name="type_id" editable="false" panelHeight="auto" >
					 <option value="">请选择博客类别...</option>	
					
				    <c:forEach var="name" items="${typeList}">
				    	<option value="${name.type_id}">${name.type_name}</option>
				    </c:forEach>	
				     		
                </select>
   			</td>
   		</tr>
   		<tr>
   			<td valign="top">博客内容：</td>
   			<td>
				 <script id="editor" type="text/plain" style="width:1000px;height:100%"></script>			  
   			</td>
   		</tr>
   		<tr>
   			<td>摘要</td>
   			<td><input type="text" id="blog_summary" name="blog_summary" style="width: 400px;"/></td>
   		</tr>
   		<tr>
   			<td></td>
   			<td>
   				<a href="javascript:submitData()" class="easyui-linkbutton" data-options="iconCls:'icon-submit'">发布博客</a>
   			</td>
   		</tr>
   	</table>
 </div>
 <script type="text/javascript">
                    //实例化编辑器
                   //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
                   var ue = UE.getEditor('editor');
  </script>
</body>
</html>

