<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/resource/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/resource/easyui/themes/icon.css">
	<style type="text/css">
		.expand{
			float:left;
		}
	</style>
	<script type="text/javascript"
	src="<%=basePath%>/resource/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/resource/easyui/jquery.easyui.min.js"></script>
</head>
<body>
<div id="dlg">
		<div id="addrole" class="easyui-panel" style="display:none">
			<form id="addroleform">
				<input class="easyui-textbox" id="id" type="hidden" name="id" />
				<table>
					<tr>
						<td>角色名称:</td>
						<td><input id="roleName" class="easyui-textbox" type="text"
							name="roleName" data-options="required:true" missingMessage="请输入角色名称"/>
						</td>
					</tr>
					<tr>
						<td>角色描述:</td>
						<td>
							<input id="description" class="easyui-textbox"
							name="description" data-options=""
							style="height:60px" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<!-- 新增 ------------------ 结束-->
	<div class="easyui-layout" align="center"
		style="width:100%;height:100%;">

		<div data-options="region:'west',split:true" style="width:360px;">
			<div class="easyui-accordion" data-options="fit:true,border:false">
				<div title="角色列表" data-options="selected:true" style="padding:10px;">
				    	<table id="rolelist" singleSelect="true" ></table>
				</div>
			</div>
		</div>
		<div id="muduleTreediv" data-options="region:'center',iconCls:'icon-ok'">
			<div class="expand" align="center"  data-options="split:true,iconCls:'icon-ok'">
				<input type="button" onclick="expandAllOpen()" value="全部展开">
			</div>
			<div class="expand" align="center"  data-options="split:true,iconCls:'icon-ok'">
				<input type="button" onclick="expandAllClose()" value="全部关闭">
			</div>
			
			<div class="expand" align="center"  data-options="split:true,iconCls:'icon-ok'">
				<input type="button" onclick="reloadtree()" value="刷新">
			</div>
			<div  align="center"  data-options="split:true,iconCls:'icon-ok'">
				<input type="button" onclick="savemodulerole()" value="保存">
			</div>
			<div>
				<ul class="easyui-tree" id="moduleTree" data-options="lines:true"></ul>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	/*
	*jiaose
	*/
	var roleName;
	var roleid ="";
	function sbumitform(){
		$('#addroleform').form('submit',{  
				method:'post',
				url:'<%=basePath%>/role/add.do',
				success: function(json) {
				var json = eval('(' + json + ')');
					if(json.result==true){
				           $('#dlg').dialog('close');
				           $('#rolelist').datagrid();
				           $.messager.alert('提示','操作成功');
					}
							
				}
   		 });
	//$('#addmoduleform').form('submit');

	};
	function expandAllOpen(){
		$('#moduleTree').tree('expandAll');
	}
	function expandAllClose(){
	$('#moduleTree').tree('collapseAll');}
	function reloadtree(){
	$('#moduleTree').tree('reload');}
	
	function savemodulerole(){
	var rolelist=$("#rolelist").datagrid('getSelected');
		var nodes1 = $('#moduleTree').tree('getChecked');
		var nodes = $('#moduleTree').tree('getChecked','indeterminate');
		nodes1 = nodes1.concat(nodes);
		if(rolelist==null||rolelist==""){
		$.messager.alert('提示','请选择角色然后保存');
		}else 
	 if(nodes1!=null&&nodes1!=''){
		var ids=[];
		for(var i=0;i<nodes1.length;i++){
			ids.push(nodes1[i].id);
		}
		 var rolelist=$("#rolelist").datagrid('getSelected');
		 roleid=rolelist.id;
		$.ajax({ url:"<%=path%>/role/saveRoleFunction.do",
								data : {
									ids : ids,
									roleId:roleid
								},
								type : "get",
								dataType : "json",
								success : function(data) {
					 if(data.result==true){
						$.messager.alert('提示','保存成功');
					} 
								}
							});
		}else{
			/* alert("请选择模块然后保存！！！"); */
			$.messager.alert('提示','请选择模块然后保存');
		}
	}
                 $(document).ready(function(){

								$('#rolelist').datagrid({
											pagination : true,//分页控件  
												fit : true,
												fitColumns : true,
												rownumbers : true,
												pageSize : 20,
												idField : '',//指定列biaoshi
												pageList : [ 10, 20, 30, 40, 50 ],
												frozenColumns : [ [ {
													field : 'roleId',
													width : 10,
													align : 'center',
													checkbox : true
												} ] ],
												
												
												url : '<%=basePath%>/role/list.do',
// 												queryParams : {
// 													sonProjectIds : $('#sonProjectType').combobox('getValues')
// 												},
												columns : [ [
														{
															field : 'roleName',
															title : '角色名称',
															align : 'center',
															width : 550
														},
														{
															field : 'description',
															title : '角色描述',
															align : 'center',
															width : 550
														}
														
														]],
														toolbar: [{
														            id: 'add',
														            text: '新增',
														            iconCls: 'icon-add',
														            handler: function () {
														            			var div1=$("#addrole");																         
																            	div1.attr("style","display"),
																            	  $('#roleName').textbox('setValue','');
																            	  $('#description').textbox('setValue','');
																            	$('#dlg').dialog({
																				modal:true,
																				title:'新增',
																				width: 400,    
																    			height: 200,  
																				buttons:[
																				{text:'保存',handler:function(){sbumitform();}},
																				{text:'关闭',handler:function(){$('#dlg').dialog('close')}}
																				]
																		});
														           	}
														},
														{
															 id: 'update',
														            text: '修改',
														            iconCls: 'icon-edit',
														            handler: function () {
														             var rowInfo = $('#rolelist').datagrid('getSelected');
														                 if(rowInfo != '' && rowInfo != null){
														                  	var div1=$("#addrole");																         
																            div1.attr("style","display"),
														            	     $('#dlg').dialog({
								                                                modal:true,
								                                                title:'编辑用户',
								                                                width: 400,    
				    			                                                height: 200, 
								                                                buttons:[
								                                                {text:'保存',handler:function(){sbumitEdit();}},
								                                                {text:'关闭',handler:function(){$('#dlg').dialog('close')}}
								                                                ]
							                                                   });
																		  $('#id').textbox('setValue',rowInfo.id);
																		  $('#roleName').textbox('setValue',rowInfo.roleName);
																		  $('#description').textbox('setValue',rowInfo.description);
																		}else{
																				$.messager.alert('提示','请选择一个角色');
																		}
														           	}
														},{
															 id: 'delete',
														            text: '删除',
														            iconCls: 'icon-remove',
														            handler: function () {
														            var rowInfo = $('#rolelist').datagrid('getSelected');
														           if(rowInfo !='' && rowInfo !=null){
												           			$.ajax({
																		url : '<%=path%>/role/del.do?roleId='+rowInfo.roleId,
																		data : {
																			/* roleId:roleid */
																		},
																		success:function(json){  
														           			if(json.success==true){
																		         $('#rolelist').datagrid();
																			}else{
																				alert(json.msg);		
																			}
														       			 } 
																	});
												            	
												           	 	}else{
												           	 		$.messager.alert('提示','请选择删除角色');
												           	 	}
															}
														}],
											onClickRow : function(rowIndex, rowData) {
												$('#moduleTree').tree('reload'); 
												
												}
											});
											
											
										$('#moduleTree').tree({  
										    url: '<%=path %>/function/listtree.do',  
										    loadFilter: function(data){     
										        return data;        
										    },
										    checkbox:true,
										    onLoadSuccess:function(node){
										    
                                           var rolelist=$("#rolelist").datagrid('getSelected');
												//roleid = rowData.roleId;
												if(rolelist!=null){
												 $.ajax({
													method:"post",
													url : "<%=path %>/function/findAllEffectiveFunctionsbyRoleId.do?roleId="+rolelist.id,
													data : {
														//roleId : roleid
													},
													async: true,
													showMsg : false,
													success: function(json) {
													var roots=	$('#moduleTree').tree('getChildren');
													/* for(var i=0;i<roots.length;i++){
														var node = $('#moduleTree').tree('find',roots[i].id);
																$('#moduleTree').tree('uncheck',node.target);
													} */
													 $.each(roots,function(i){
														$.each(json,function(j){
															if("_authority_"+json[j].id==roots[i].id){
															var node = $('#moduleTree').tree('find',"_authority_"+json[j].id);
															if($('#moduleTree').tree('isLeaf',node.target)){
																$('#moduleTree').tree('check',node.target);}
															}
														})
													})
													
													}
												}); 
												}
										    },
										    onBeforeCheck:function(node){
										    	if(rolelist!=null&&rolelist!=''){
										    		return true;
										    	}else{
										    		$.messager.alert('提示','请选择角色！！！');
										    		return false;
										    	}
										    }
										});	
											


});
//角色提交
function sbumitEdit(){
  $('#addroleform').form('submit',{
            method:'post',
			url : '<%=path%>/role/update.do',  
	        success:function(data){
	        var data=eval('('+data+')');
	        	if(data.result==true){
		        	$('#dlg').dialog('close');
		            $('#rolelist').datagrid('reload');
		            $.messager.alert('提示','操作已成功');
				}else{
	             	$.messager.alert('提示','操作失败');
	             
	        }	
				 
	        } 
		 });
}

	</script>
</body>
</html>