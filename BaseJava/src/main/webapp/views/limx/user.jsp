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
<script type="text/javascript"
	src="<%=basePath%>/resource/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	$(function() {
				$.extend($.fn.validatebox.defaults.rules, {   
			    phoneNum: { //验证手机号  
			        validator: function(value, param){
			         return /^1[3-8]+\d{9}$/.test(value);
			        },   
			        message: '请输入正确的手机号码。'  
			    },
			   
			    telNum:{ //既验证手机号，又验证座机号
			      validator: function(value, param){
			          return /(^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)|(^((\d3)|(\d{3}\-))?(1[358]\d{9})$)/.test(value);
			         },   
			         message: '请输入正确的电话号码。'
			    }  
			});
	
			$('#rolealllist').combobox({    
		    url:'<%=basePath%>/role/listall.do',    
		    valueField:'id',    
		    textField:'text',
		    value : "0",  
		    onSelect:function(rec){
		    $('#user').datagrid('load', {
						roleId:rec.id,
						loginName: $('#accountSearcher').searchbox("getValue")
					});
		    } 
		});  

	
		$.extend($.fn.validatebox.defaults.rules, {  
	    /*必须和某个字段相等*/
	    equalTo: {
	        validator:function(value,param){
	            return $(param[0]).val() == value;
	        },
	        message:'字段不匹配'
	    }
	           
	});
	$('#accountSearcher').searchbox({ 
				prompt:'请输入账号',
				searcher:function(value,name){ 
					$('#user').datagrid('load', {
						loginName: value,
						roleId:$("#rolealllist").combobox("getValue")
					});
				}
			});
	$('#user').datagrid({
    url:'<%=basePath%>/user/list.do',
			pageSize : 20,
			pageNumber : 1,
			fit:true,
			fitColumns:true,
			pagination : true,
			idField : 'id', //主键
			singleSelect:true,
			toolbar :"#storetbar",
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
				width : 100,
				title : '编号',
				align:'center',
				formatter : function(value, row, index) {
					return index + 1;
				}
			}, {
				field : 'loginName',
				width : 100,
				title : '登录名',
				align:'center'
			}, {
				field : 'displayName',
				width : 100,
				title : '姓名',
				align:'center'
			}, {
				field : 'email',
				width : 100,
				title : '邮箱',
				align:'center',
				width : '15%'
			},{
				field : 'contactPhone',
				width : 100,
				title : '电话',
				align:'center'
			}, {
				field : 'state',
				width : 100,
				title : '状态',
				align:'center',
				formatter : function(value, row, index) {
					if (row.state == 1) {
						return "正常";
					} else {
						return "冻结";
					}
					;
				}
			}, {
				field : 'action',
				title : '操作',
				width : 100,
				align:'center',
				formatter:function(value, row, index){
				var textValue = '';
				if (row.state == 1) {
					//textValue = "<a style=\"color:blue;text-decoration:none;\" href=\"javascript:edit('"+rowData.orderId+"')\">编辑</a>";
					textValue = '<a href="javascript:updatestate(0,'+row.id+');" style="text-decoration:none">冻结</a>';
				} else if (row.state == 0) { 
					//textValue = "<a style=\"color:blue;text-decoration:none;\" href=\"javascript:execution('"+rowData.taskId+"')\">流转节点</a>";
					textValue='<a style="text-decoration:none" href="javascript:updatestate(1,'+row.id+');">激活</a>';
				}
				textValue+= '&nbsp;<a href="javascript:updatepassword(\''+row.loginName+'\');" style="text-decoration:none">密码修改</a>';
				return textValue;
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
		$('#form1').form('submit',{    
    		url:'<%=basePath%>/user/update.do',
    			method:'post',
				onSubmit : function(param) {
					param.roleIdModify=roleIds;
					return $("#form1").form('validate');
				},
				success : function(data) {
				
				var data = eval('(' + data + ')');
				if(data.result){
					$("#ModifyWindow").dialog('close');
					$("#user").datagrid('reload');
				}
				}
			});
			// submit the form    
		
	}
	function add(){
	var roleIds = [];
	var roleIdAdd=$("#rolelistAdd").datagrid("getSelections");
	for ( var i = 0; i < roleIdAdd.length; i++) {
		roleIds.push(roleIdAdd[i].id);
	}
		$('#form2').form('submit',{    
    		url:'<%=basePath%>/user/add.do',
    		method:'post',
			onSubmit : function(param) {
				param.roleIdAdd = roleIds;
				return $("#form2").form('validate');
			},
			success : function(data) {

				var data = eval('(' + data + ')');
				if (data.result) {
					$("#addWindow").dialog('close');
					$("#user").datagrid('reload');
				}
			}
		});
		// submit the form    

	}
	/* 冻结激活 */
	function updatestate(state,id){
			$.ajax({
						url:"<%=basePath%>/user/frozen.do",
						data:{id:id,state:state},
						dataType: "json",
						type:"post",
						success:function(data){
							if(data.result){
								$("#user").datagrid('reload');
								if(state==0){
								$.messager.alert('提示','冻结成功');    
								}else{
								$.messager.alert('提示','激活成功');   
								}
							}
						}
					});
	}
	function updatepassword(loginName){
	$('#dialogpassword').show();
		$('#dialogpassword').dialog({
			title : '修改密码',
			width : 350,
			height : 200,
			closed : false,
			cache : false,
			modal : true,
		});
		
		$("#loginName").val(loginName);
		$('#passwordTxt').textbox('setValue','');
		$('#password').textbox('setValue','');
	}
	//关闭
		function onClosePage() {
			$('#dialogpassword').dialog('close');
			$('#dialogpassword').hide();
		}
		//密码修改
		function onSave(){
			$('#update-changepwd').form('submit',{  
				method:'post',
				url:'<%=basePath%>/user/updatepassword1.do',
				success: function(json) {
				var json = eval('(' + json + ')');
					if(json.result==true){
				           $('#dialogpassword').dialog('close');
				           $('#dialogpassword').hide();
				           $.messager.alert('提示','修改成功');
					}else{
						$.messager.alert('提示',json.err);
					}
							
				}
   		 });
		}
	function Canceladd() {
		$("#addWindow").dialog('close');
		
	}
	function CancelModify() {
		$("#ModifyWindow").dialog('close');
		
	}
	/*新增  */
	function adduser(){
	$("#addWindow").dialog({
						modal:true,
						title:'新增',
						width: 400,    
		    			height: 400,  
						buttons:[
						{text:'保存',handler:function(){add();}},
						{text:'关闭',handler:function(){Canceladd();}}
						]});
					$('#loginName2').textbox('setValue','');
					$('#password2').textbox('setValue','');
					$('#contactPhone2').textbox('setValue','');
					$('#email2').textbox('setValue','');
					$('#displayName2').textbox('setValue','');
					if($("#rolelistAdd").length==0){
						$("#rolelistAdd1").append("<div id='rolelistAdd'></div>");
						rolelistAdddatagrid();
						}else{
						$("#rolelistAdd").datagrid("reload");
					}
	}
	/*修改  */
	function edituser(){
	var b=$("#user").datagrid('getSelections');
					if(b.length==0){
						jQuery.messager.alert('提示','请选择要编辑的行');
					}else if(b.length==1){
						$("#ModifyWindow").dialog({
						modal:true,
						title:'修改',
						width: 400,    
		    			height: 400,  
						buttons:[
						{text:'保存',handler:function(){save();}},
						{text:'关闭',handler:function(){CancelModify();}}
						]});
						var user=b[0];
						$("#id1").val(user.id);
						$("#loginName1").val(user.loginName);
						$('#contactPhone1').textbox('setValue',user.contactPhone);
						$('#email1').textbox('setValue',user.email);
						$('#displayName1').textbox('setValue',user.displayName);
						if($("#rolelistModify").length==0){
							$("#rolelistModify1").append("<div id='rolelistModify'></div>");
						rolelistModifydatagrid();}else{
						$('#rolelistModify').datagrid("reload");}
					}else if(b.length>1){
						jQuery.messager.alert('提示','请正确选择要编辑的行');
					}
	}
	/*删除  */
	function deleteuser(){
	
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
						}  
						});  
					}
	}
	function rolelistAdddatagrid(){
		$('#rolelistAdd').datagrid({
		fit : true,
		fitColumns : true,
		rownumbers : true,
		idField : '',//指定列biaoshi
		frozenColumns : [ [ {
			field : 'roleId',
			width : 10,
			align : 'center',
			checkbox : true
		} ] ],
		url : '<%=basePath%>/role/listuserrole.do',
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
					/* $.each(data,function(index,obj){
		            $("#rolelistAdd").datagrid('uncheckRow',index);
					}); */
				}});
		}
	
	function rolelistModifydatagrid(){
	$('#rolelistModify').datagrid({
		fit : true,
		fitColumns : true,
		rownumbers : true,
		idField : '',//指定列biaoshi
		frozenColumns : [ [ {
			field : 'roleId',
			width : 10,
			align : 'center',
			checkbox : true
		} ] ],
		url : '<%=basePath%>/role/listuserrole.do',
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
	}
</script>
</head>

<body>
	<div id="user" style="height:100% "></div>
	<div id="addWindow">
		<form id="form2" style="padding:10px 20px 10px 40px;">

			<p>
				登录名: <input id="loginName2" class="easyui-textbox" name="loginName" data-options="required:true" validType="remote['<%=basePath%>/user/findbyloginname.do','loginName']" 
							missingMessage="登录名不能空" invalidMessage="用户名已存在" type="text" >
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
				邮箱: <input id="email2" class="easyui-textbox" validType='email' name="email" data-options="required:true"
							missingMessage="邮箱不能为空" invalidMessage="请输入正确的邮箱" type="text">
			</p>
			<p>
				电话: <input id="contactPhone2" class="easyui-textbox" validType='phoneNum' name="contactPhone" data-options="required:true"
							missingMessage="电话不能空" type="text">
			</p>
			<div>
				<font size="3" style="">请选择角色:</font>
			</div>
			<br />
			<div id="rolelistAdd1" style="height: 200"></div>
			<!-- <br>
			<div style="padding:5px;text-align:center;">
				<a href="javascript:add()" class="easyui-linkbutton" icon="icon-ok">Ok</a>
				<a href="javascript:Cancel()" class="easyui-linkbutton"
					icon="icon-cancel">Cancel</a>
			</div> -->
		</form>
	</div>
	<div id="ModifyWindow">
		<form id="form1" style="padding:10px 20px 10px 40px;">
			<p>
				<input id="id1" name="id" type="hidden">
			</p>
			<p>
				登录名: <input id="loginName1" readonly="readonly" name="loginName" type="text">
			</p>
			<p>
				姓名: <input id="displayName1" name="displayName" class="easyui-textbox" data-options="required:true"
							missingMessage="姓名不能空"  type="text">
			</p>
			<p>
				邮箱: <input id="email1" class="easyui-textbox" validType='email' name="email" data-options="required:true"
							missingMessage="邮箱不能为空" invalidMessage="请输入正确的邮箱" type="text">
			</p>
			<p>
				电话: <input id="contactPhone1" name="contactPhone" class="easyui-textbox" validType='phoneNum' data-options="required:true"
							missingMessage="电话不能空" type="text">
			</p>
			<div>
				<font size="3" style="">请选择角色:</font>
			</div>
			<br />
			<div id="rolelistModify1" style="height: 200"></div>
			<!-- <div style="wpadding:5px;text-align:center;">
				<a href="javascript:save()" class="easyui-linkbutton" icon="icon-ok">Ok</a>
				<a href="javascript:Cancel()" class="easyui-linkbutton"
					icon="icon-cancel">Cancel</a>
			</div> -->

		</form>
	</div>
	<div id="dialogpassword" style="display: none">
		<form id="update-changepwd">
		<table style="padding-left: 20px;padding-top: 10px;">
			<tr style="height: 30px;">
				<td><span style="color: red;">*</span>新密码:</td>
				<td><input name="passwordTxt" id="passwordTxt" class="easyui-textbox" type="password"
					style="width: 180px;"  data-options="required:true" validType="length[5,10]" invalidMessage="长度必须在5-10之间" 
							missingMessage="新密码不能为空"></td>
			</tr>
			<tr style="height: 30px;">
				<td><span style="color: red;">*</span>确认密码:</td>
				<td><input name="password" id="password" type="password"
					style="width: 180px;" class="easyui-textbox" data-options="required:true" validType="equalTo['#passwordTxt']" 
							missingMessage="确认密码不能为空"  invalidMessage="两次输入密码不匹配"></td>
			</tr>
			<input name="loginName" id="loginName" type="hidden">
		</table>
	</form>
	<div align="right" style="padding-top: 5px;padding-right: 30px;">
			<button id="save" onclick="onSave();" class="easyui-linkbutton">保存</button>
			<button id="closepage" onclick="onClosePage();" class="easyui-linkbutton">取消</button>
	</div>
	</div>
	<div id="storetbar">
	<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="javascript:adduser()">新增</a>
	<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="javascript:edituser()">修改</a>
	<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="javascript:deleteuser()">删除</a>
	
	 <div style="float: right;">
	 请选择角色:<input id="rolealllist" style="width: 200px;">
	 &nbsp;&nbsp;|&nbsp;&nbsp; 账户: <input id="accountSearcher" style="width:200px" />
	 </div>
</body>
</html>
