<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script type="text/javascript"
	src="<%=basePath%>/resource/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/resource/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/resource/easyui/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<%
		String appId = request.getParameter("appId");
	%>


	<!-- 新增 ------------------ 开始-->
	<div id="dlg">
		<div id="addmodule" class="easyui-panel" style="display:none"
			align="center">
			<form id="addmoduleform">
				<input id="moduleId" type="hidden" name="moduleId" />
				<table>
					<tr>
						<td>模块名称:</td>
						<td><input id="moduleName" class="easyui-textbox" type="text"
							name="moduleName" data-options="required:true"
							missingMessage="名称不能空" /></td>
					</tr>
					<tr>
						<td>模块URL:</td>
						<td><input id="moduleUrl" class="easyui-textbox" type="text"
							name="moduleUrl" data-options="required:true"
							missingMessage="模块URL不能空" /></td>
					</tr>
					<tr>
						<td>父模块:</td>
						<td><select id="modulescombotree" class="easyui-combotree"
							data-options="required:true" name="parentId" style="width:200px;"
							missingMessage="请输入父模块"></select></td>
					</tr>
					<tr>
						<td>显示顺序:</td>
						<td><input id="displayIndex" class="easyui-numberbox"
							type="text" name="displayIndex" required="true" max="99"
							missingMessage="请输入显示顺序" /></td>
					</tr>
					<tr>
						<td>展开状态:</td>
						<td><select id="expanded" class="easyui-combobox"
							name="expanded"><option value="0">收缩</option>
								<option value="1">展开</option>
						</select>
						</td>
					</tr>
					<tr>
						<td>是否显示:</td>
						<td><input type="radio" name="isDisplay" value="1"
							checked="checked" />是 <input type="radio" name="isDisplay"
							value="0" />否</td>
					</tr>
					<tr>
						<td>CSS样式:</td>
						<td><input id="iconCss" class="easyui-textbox" type="text"
							name="iconCss" data-options="required:true"
							missingMessage="Css样式不能空" /></td>
					</tr>

					<tr>
						<td>节点说明:</td>
						<td><input id="information" class="easyui-textbox"
							name="information" data-options="multiline:true"
							style="height:60px" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<!-- 新增 ------------------ 结束-->
	<!-- 列表-----------------开始-->
	<table id="treegrid" style='algin:center'>

	</table>
	<!-- 列表-----------------结束-->


	<script type="text/javascript">	
		
	function sbumitformadd(){
		$('#addmoduleform').form('submit',{  
				method:'post',
				url:'<%=path%>/function/add.do',
				
		        success:function(re){  
		           alert("添加成功!!!");
		           $('#dlg').dialog('close');
		           $('#treegrid').treegrid();
		        } 
   		 });
   		 }
   		 function sbumitformsave(){
		$('#addmoduleform').form('submit',{  
				method:'post',
				url:'<%=path%>/function/update.do',
				
		        success:function(re){  
		           alert("修改成功!!!");
		           $('#dlg').dialog('close');
		           $('#treegrid').treegrid();
		        } 
   		 });
	};
$(document).ready(function(){
	
	var appId=<%=appId%>;
	var modulesid="";
	
	
	
	//var t = $('#modulescombotree').combotree('tree').tree('check',559);//获取tree对象
	//t.options('select', 559);//node为要选中的节点
	// $('#modulescombotree').combotree('options').options('select',"_authority_781");//获取tree对象
  
	$('#treegrid').treegrid({
		url: '<%=basePath%>/function/listtree.do',
				 type: 'get',
                singleSelect : false,
                width:'98%',
		idField:'id',
		singleSelect:true,
		fitColumns:true,
    	treeField:'text',
    	fit:true,
    	onClickRow: function (row) {
    	},
    	 
    	 toolbar: [ {
            id: 'seach',
            text: '新增',
            iconCls: 'icon-add',
            handler: function () {
             var edeitmodule = $('#treegrid').treegrid('getSelected');
            $('#modulescombotree').combotree('reload', '<%=basePath%>/function/listtreecombobox.do');
           		 var div1=$("#addmodule");
           		 $('#moduleName').textbox('setValue','');
           		 $('#moduleUrl').textbox('setValue','');
           		  $('#displayIndex').textbox('setValue',''); 
           		  $('#iconCss').textbox('setValue','');
           		   $('#information').textbox('setValue','');
           		   if(edeitmodule!=null){
  				 $('#modulescombotree').combotree('setValue',edeitmodule.id);}
            	 div1.attr("style","display"),
            	$('#dlg').dialog({
				modal:true,
				title:'新增',
				width: 400,    
    			height: 333,  
				buttons:[
				{text:'保存',handler:function(){sbumitformadd();}},
				{text:'关闭',handler:function(){$('#dlg').dialog('close')}}
				]
		});
            }
        },{
        	 id: 'delete',
             text: '删除',
             iconCls: 'icon-remove',
             handler: function () {
              $.messager.confirm("操作提示", "您确定要执行操作吗？", function (data) {  
            if (data) {  
                var nodemodu = $('#treegrid').treegrid('getSelected');
             	if(nodemodu != '' && nodemodu != null){
           			$.ajax({
						url : '<%=path%>/function/delete.do?id='+nodemodu.id,
						data : {
							/* moduleId:modulesid */
						},
						success:function(re){  
		           			 $('#treegrid').treegrid();
		       			 } 
					});
            	
           	 	}else{
           	 		$.messager.alert('提示','请单击要删除的菜单 ');
           	 	} 
            }  
            else {  
            }  
        }); 
            
           	 }
            },{
            id: 'update',
            text: '修改',
            iconCls: 'icon-edit',
            handler: function () {
            $('#modulescombotree').combotree('reload', '<%=basePath%>/function/listtreecombobox.do');
              var edeitmodule = $('#treegrid').treegrid('getSelected');
               if(edeitmodule != '' && edeitmodule != null){
           		 var div1=$("#addmodule"); 
           		  
  				 $('#modulescombotree').combotree('setValue',"_authority_"+edeitmodule.parentId);
  				 $('#moduleId').val(edeitmodule.id);
  				 $('#moduleName').textbox('setValue',edeitmodule.text);
  				 $('#moduleUrl').textbox('setValue',edeitmodule.url);
  				 var displayIndex=$("#displayIndex").val();
  				 $('#displayIndex').numberbox('setValue',edeitmodule.displayIndex);
  				var isDisplay=edeitmodule.isdisplay;
  				 $("[value='" + isDisplay + "']").attr("checked", true);
  				 var expanded;
  				 if(edeitmodule.expanded)
  				 		expanded=1;
  				 	else 
  				 		expanded=0;
  				 $('#expanded').combobox('select', expanded);
  				 $('#iconCss').textbox('setValue',edeitmodule.iconCls);
  				 $('#information').textbox('setValue',"节点说明");
            	 div1.attr("style","display"),
         			$('#dlg').dialog({
					modal:true,
					title:'修改',
					width: 400,    
	    			height: 333,  
					buttons:[
					{text:'保存',handler:function(){sbumitformsave();}},
					{text:'关闭',handler:function(){$('#dlg').dialog('close')}}
					]
				});
            
            }else{
             $.messager.alert('提示','选择修改模块');
           } 
           }
            
            
            
        }], 
		columns:[[
			{field:'text',title:'模块名称',width:180},
			{field:'id',title:'模块编号',width:100},
			{field:'url',title:'模块url',width:180},
			{field:'expanded',title:'展开状态',width:80,formatter: function(value,row,index){
				if (value==1){
					return '展开';
				} else {
					return '收缩';
				}
			}},
			{field:'isdisplay',title:'是否显示',width:80,formatter:function(value,row,index){
				if (value==1){
					return '是';
				} else {
					return '否';
				}
			}},
			{field:'displayIndex',title:'显示顺序',width:80},
			{field:'iconCls',title:'CSS样式',width:180},
			{field:'information',title:'节点说明',width:180}
		]]
		});
		
		 $('#modulescombotree').combotree({
    			url: '<%=basePath%>/function/listtree.do'

							});

						});
	</script>
</body>
</html>