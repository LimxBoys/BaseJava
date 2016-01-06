<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'MyJsp.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/resource/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/resource/easyui/themes/icon.css">
<style type="text/css">
#d1,#d2 {
	float: left;
}
</style>
<script type="text/javascript"
	src="<%=basePath%>/resource/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/resource/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
	$(function() {
	$('#rolelistAdd').datagrid({
	pagination : true,//分页控件  
		fit : true,
		fitColumns : true,
		rownumbers : true,
		pageSize : 10,
		idField : '',//指定列biaoshi
		pageList : [ 10, 20, 30, 40, 50 ],
		frozenColumns : [ [ {
			field : 'roleId',
			width : 10,
			align : 'center',
			checkbox : true
		} ] ],
		url : '<%=basePath%>/role/list.do',
		columns : [ [
				{
					field : 'roleName',
					title : '角色名称',
					align : 'center'
				},
				{
					field : 'description',
					title : '角色描述',
					align : 'center'
				}
				
				]]});
	
	
	$('#rolelistModify').datagrid({
	pagination : true,//分页控件  
		fit : true,
		fitColumns : true,
		rownumbers : true,
		pageSize : 10,
		idField : '',//指定列biaoshi
		pageList : [ 10, 20, 30, 40, 50 ],
		frozenColumns : [ [ {
			field : 'roleId',
			width : 10,
			align : 'center',
			checkbox : true
		} ] ],
		url : '<%=basePath%>/role/list.do',
		columns : [ [
				{
					field : 'roleName',
					title : '角色名称',
					align : 'center'
				},
				{
					field : 'description',
					title : '角色描述',
					align : 'center'
				}
				
				]],
				onLoadSuccess:function(data){
				var b=$("#user").datagrid('getSelections');
				var user=b[0];
				if(b!=null&&b.length!=0){
					$.ajax({
						url:"<%=basePath%>/user/roleList.do",
						data:{id:user.id},
						dataType: "json",
						type:"get",
						success:function(data){
							$.each(data,function(index,obj){
							if(obj.checked){
								var roleId=obj.id;
								var rolelist=$("#rolelistModify").datagrid("getRows");
				            	for(var i=0;i<rolelist.length;i++){
				            		var roleIdold=rolelist[i].id;
				            		if(roleIdold==roleId){
				            			$("#rolelistModify").datagrid('checkRow',i);
				            		}
				            	}
							}
							});
						}
					});
				}}
				});
	
	
	
		$("#ModifyWindow").window('close');
		$("#addWindow").window('close');
		$('#user').datagrid({
    url:'<%=basePath%>/user/list.do',
			pageSize : 20,
			pageNumber : 1,
			pagination : true,
			idField : 'id', //主键
			singleSelect:true,
			toolbar : [ {
				text : 'Add',
				iconCls : 'icon-add',
				handler : function() {
					$("#addWindow").window('open');
					
						$("#loginName2").val("");
						$("#password2").val("");
						$("#contactPhone2").val("");
						$("#displayName2").val("");
				}
			}, {
				text : '修改',
				iconCls : 'icon-add',
				handler : function() {
					var b=$("#user").datagrid('getSelections');
					if(b.length==0){
						jQuery.messager.alert('提示','请选择要编辑的行');
					}else if(b.length==1){
						$("#ModifyWindow").window('open');
						var user=b[0];
						$("#id1").val(user.id);
						$("#loginName1").val(user.loginName);
						$("#contactPhone1").val(user.contactPhone);
						$("#displayName1").val(user.displayName);
						$('#rolelistModify').datagrid("reload");
					}else if(b.length>1){
						jQuery.messager.alert('提示','请正确选择要编辑的行');
					}
					
				}	
			}, {
				text : '删除',
				iconCls : 'icon-add',
				handler : function() {
					var b=$("#user").datagrid('getSelections');
					var ids=new Array();
					if(b==null||b.length==0){
						jQuery.messager.alert('提示:','请选择需要删除的行!');   
					}else{
						jQuery.messager.confirm('提示:','你确认要删除吗?',function(event){   
						if(event){
						$.each(b,function(index,obj){
						ids[index] = obj.id;
						});
						$.ajax({ url:"<%=basePath%>	/user/deleteAll.do",
								data : {
									ids : ids,
									loginName:"fasd"
								},
								type : "get",
								dataType : "json",
								success : function(data) {
									if(data.result){
										$("#user").datagrid('reload');
									}
								}
							});
					 
					}else{   
							}   
						});  
					}
				}
			} ],
			columns : [ [ {
				field : 'id',
				width : 100,
				title : '主键',
				checkbox : true,
				formatter : function(value, row, index) {
					return index;
				}
			}, {
				field : 'zhujian',
				title : '编号',
				width : '15%',
				align:'center',
				formatter : function(value, row, index) {
					return index + 1;
				}
			}, {
				field : 'loginName',
				title : '登录名',
				align:'center',
				width : '15%'
			}, {
				field : 'displayName',
				title : '姓名',
				align:'center',
				width : '15%'
			}, {
				field : 'contactPhone',
				title : '电话',
				align:'center',
				width : '15%'
			}, {
				field : 'state',
				title : '状态',
				width : '15%',
				align:'center',
				formatter : function(value, row, index) {
					if (row.state == 1) {
						return "正常";
					} else {
						return "冻结";
					}
					;
				}
			} ] ],
			onCheck : function(rowIndex, rowData) {
			}
		});
	});
	function save(){
	var roleIds = [];
	 var roleIdModify=$("#rolelistModify").datagrid("getSelections");
	for ( var i = 0; i < roleIdModify.length; i++) {
		roleIds.push(roleIdModify[i].id);
	}
		$('#form1').form({    
    		url:'<%=basePath%>/user/update.do',
				onSubmit : function(param) {
					param.roleIdModify=roleIds;
				},
				success : function(data) {
				
				var data = eval('(' + data + ')');
				if(data.result){
					$("#ModifyWindow").window('close');
					$("#user").datagrid('reload');
				}
				}
			});
			// submit the form    
			$('#form1').submit();
		
	}
	function add(){
	var roleIds = [];
	var roleIdAdd=$("#rolelistAdd").datagrid("getSelections");
	for ( var i = 0; i < roleIdAdd.length; i++) {
		roleIds.push(roleIdAdd[i].id);
	}
		$('#form2').form({    
    		url:'<%=basePath%>/user/add.do',
			onSubmit : function(param) {
				param.roleIdAdd = roleIds;
			},
			success : function(data) {

				var data = eval('(' + data + ')');
				if (data.result) {
					$("#addWindow").window('close');
					$("#user").datagrid('reload');
				}
			}
		});
		// submit the form    
		$('#form2').submit();

	}
	function Cancel() {
		$("#ModifyWindow").window('close');
		$("#addWindow").window('close');
	}
</script>
</head>

<body>
	<div id="user" style="height:1000px "></div>
	<div id="addWindow" class="easyui-window" title="添加"
		style="width:350px;height:600px;">
		<form id="form2" style="padding:10px 20px 10px 40px;">

			<p>
				登录名: <input id="loginName2" class="easyui-textbox" name="loginName" data-options="required:true"
							missingMessage="登录名不能空" type="text">
			</p>
			<p>
				密码: <input id="password2" class="easyui-textbox" name="password" data-options="required:true"
							missingMessage="密码不能空" type="password">
			</p>
			<p>
				姓名: <input id="displayName2" class="easyui-textbox" name="displayName" data-options="required:true"
							missingMessage="姓名不能空" type="text">
			</p>
			<p>
				电话: <input id="contactPhone2" class="easyui-textbox" name="contactPhone" data-options="required:true"
							missingMessage="电话不能空" type="text">
			</p>
			<div id="rolelistAdd"></div>
			<br>
			<div style="padding:5px;text-align:center;">
				<a href="javascript:add()" class="easyui-linkbutton" icon="icon-ok">Ok</a>
				<a href="javascript:Cancel()" class="easyui-linkbutton"
					icon="icon-cancel">Cancel</a>
			</div>
		</form>
	</div>
	<div id="ModifyWindow" class="easyui-window" title="修改"
		style="width:350px;height:600px;">
		<form id="form1" style="padding:10px 20px 10px 40px;">
			<p>
				<input id="id1" name="id" type="hidden">
			</p>
			<p>
				登录名: <input id="loginName1" name="loginName" type="text">
			</p>
			<p>
				姓名: <input id="displayName1" name="displayName" type="text">
			</p>
			<p>
				电话: <input id="contactPhone1" name="contactPhone" type="text">
			</p>
			<div>
				<font size="3" style="">请选择角色:</font>
			</div>
			<br />
			<div id="rolelistModify"></div>
			<br>
			<div style="wpadding:5px;text-align:center;">
				<a href="javascript:save()" class="easyui-linkbutton" icon="icon-ok">Ok</a>
				<a href="javascript:Cancel()" class="easyui-linkbutton"
					icon="icon-cancel">Cancel</a>
			</div>

		</form>
	</div>
</body>
</html>
