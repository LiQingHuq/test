<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改个人信息页面</title>
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
		var blogger_username=$("#blogger_username").val();
		var blogger_sign=$("#blogger_sign").val();
		var blogger_context=UE.getEditor('blogger_context').getContent();
		
		if(blogger_username==null || blogger_username==''){
			alert("请输入昵称！");
		}else if(blogger_sign==null || blogger_sign==''){
			alert("请输入个性签名！");
		}else if(blogger_context==null || blogger_context==''){
			alert("请输入个性简介！");
		}else{
			$("#pF").val(blogger_context);
			$('#form1').submit();
		}
	}
	
</script>
</head>
<body style="margin: 10px">
<div id="p" class="easyui-panel" title="修改个人信息" style="padding: 10px">
	<form id="form1" action="${path}/BloggerAction/blogger!update.action" method="post" enctype="multipart/form-data">
	 	<table cellspacing="20px">
	   		<tr>
	   			<td width="80px">用户名：</td>
	   			<td>
	   				<input type="hidden" id="blogger_id" name="blogger_id" value="${blogger_session.blogger_id}"/>
	   				<input type="text" id="blogger_username" name="blogger_username" style="width: 200px;" value="${blogger_session.blogger_name}" readonly="readonly"/>
	   			</td>
	   		</tr>
	   		<tr>
	   			<td>昵称：</td>
	   			<td><input type="text" id="blogger_nickname" name="blogger_nickname"  style="width: 200px;" value="${blogger_session.blogger_nickname}"/></td>
	   		</tr>
	   		<tr>
	   			<td>个性签名：</td>
	   			<td><input type="text" id="blogger_sign" name="blogger_sign" value="${blogger_session.blogger_sign }" style="width: 400px;"/></td>
	   		</tr>
	   		<tr>
	   			<td>个人头像：</td>
	   			<td><input type="file" id="imageFile" name="imageFile" style="width: 400px;"/></td>
	   		</tr>
	   		<tr>
	   			<td valign="top">个人简介：</td>
	   			<td>
					   <script id="blogger_context" type="text/plain" style="width:1000px;height:100%;"  value="${blogger_session.blogger_context}"></script>
					   <input type="hidden" id="blogger_context" name="blogger_context"/>
	   			</td>
	   		</tr>
	   		<tr>
	   			<td></td>
	   			<td>
	   				<input type="submit" value="提交"></a>
	   			</td>
	   		</tr>
	   	</table>
   	</form>
 </div>
 
 <script type="text/javascript">

    //实例化编辑器 - 将“博主的个人简介”填充进编辑器中
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('proFile');

    ue.addListener("ready",function(){
        //通过ajax请求数据
        UE.ajax.request("${path}/BloggerAction/blogger!find.action",
            {
                method:"post",
                async : false,  
                data:{},
                onsuccess:function(result){
                	result = eval("(" + result.responseText + ")");  
                	$("#blogger_nickname").val(result.blogger_nickname);
                	$("#blogger_sign").val(result.bloggger_sign);                
       				UE.getEditor('blogger_context').setContent(result.blogger_context);
                }
            }
        );
    });
    
</script>
</body>
</html>