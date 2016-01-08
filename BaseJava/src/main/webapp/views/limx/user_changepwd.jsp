<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
</head>
<body>
	<form id="update-changepwd">
		<table style="padding-left: 20px;padding-top: 10px;">
			<tr style="height: 30px;">
				<td style="padding-right: 25px;"><span style="color: red;">*</span>原密码:</td>
				<td><input name="pwd" id="pwd" type="password"
					style="width: 180px;" class="easyui-textbox" data-options="required:true" validType="length[5,10]" invalidMessage="长度必须在5-10之间" 
							missingMessage="旧密码不能为空"></td>
			</tr>
			<tr style="height: 30px;">
				<td><span style="color: red;">*</span>新密码:</td>
				<td><input name="passwordTxt" id="passwordTxt" type="password"
					style="width: 180px;" class="easyui-textbox" data-options="required:true" validType="length[5,10]" invalidMessage="长度必须在5-10之间" 
							missingMessage="新密码不能为空"></td>
			</tr>
			<tr style="height: 30px;">
				<td><span style="color: red;">*</span>确认密码:</td>
				<td><input name="password" id="password" type="password"
					style="width: 180px;" class="easyui-textbox" data-options="required:true" validType="equalTo['#passwordTxt']" 
							missingMessage="确认密码不能为空"  invalidMessage="两次输入密码不匹配"></td>
			</tr>
		</table>
	</form>
	<div align="right" style="padding-top: 5px;padding-right: 30px;">
			<button id="save" onclick="onSave();" class="easyui-linkbutton">保存</button>
			<button id="closepage" onclick="onClosePage();" class="easyui-linkbutton">取消</button>
	</div>
	<script type="text/javascript">
		//关闭
		function onClosePage() {
			$('#user_changepwd').dialog('close');
		}
		//提交
		function onSave(){
			$('#update-changepwd').form('submit',{  
				method:'post',
				url:'<%=basePath%>/user/updatepassword.do',
				success: function(json) {
				var json = eval('(' + json + ')');
					if(json.result==true){
				           $('#user_changepwd').dialog('close');
				           $.messager.alert('提示','修改成功');
					}else{
						$.messager.alert('提示',json.err);
					}
							
				}
   		 });
		}
		$.extend($.fn.validatebox.defaults.rules, {  
    /*必须和某个字段相等*/
    equalTo: {
        validator:function(value,param){
            return $(param[0]).val() == value;
        },
        message:'字段不匹配'
    }
           
});
	</script>
</body>
</html>