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
		
	// 下拉框选择控件，下拉框的内容是动态查询数据库信息
 	$('#province').combobox({ 
			    url:"<%=basePath%>/area/provinceQuery.do",
			    editable:false, //不可编辑状态
			    cache: false,
			    valueField:'base_areaid',   
			    textField:'name',
			 	onSelect: function(){
					    $("#city").combobox("clear");
					    $("#county").combobox("clear");
					     $("#cregicounty").val('');
					     var province = $('#province').combobox('getValue');
					    $('#city').combobox('reload',"<%=basePath%>/area/provinceCity.do?province="+province);  
			         }
                 }); 
	
	$('#city').combobox({
	    editable:false, //不可编辑状态
	    cache: false,
	    //panelHeight: 'auto',//自动高度适合
	    valueField:'base_areaid',   
	    textField:'name',
	    onSelect: function(){
	    	/* $("#cregicounty").val(''); */
		    $("#county").combobox("clear");
			var city = $('#city').combobox('getValue');	
			 $('#county').combobox('reload',"<%=basePath%>/area/provinceCounty.do?city="
										+ city);
					}
				});
		$('#county').combobox({
			editable : false, //不可编辑状态
			cache : false,
			// panelHeight: 'auto',//自动高度适合
			valueField : 'base_areaid',
			textField : 'name',

		});
		$("#areaclick").click(function(){
			/* alert($('#county').combobox('getValue')); */
			$("#area").text($('#county').combobox('getValue'));
			var v = $('#ss').timespinner('getValue');  // 获取时间微调组件的值
			$("#timespinner").text(v);
			
		});
	});
	
</script>
</head>

<body>
	<ul>
		<li><select
			size="1" style="width:116px;height: 25px; margin-left: 30px;"
			class="ipt1" id="province" name="company_address"><option>选择地区</option>
		</select><select id="city" name="company_address" size="1"
			style="width:116px;height: 25px; margin-left:6px;" class="ipt1"><option>市</option>
		</select><select id="county" name="company_address" size="1"
			style="width:116px;height: 25px; margin-left:6px;" class="ipt1"><option>县/区</option>
		</select><span id="aler2">*</span></li>
	</ul>
	<button id="areaclick">获取值</button>
	<span id="area"></span>
	<input id="ss" class="easyui-timespinner"  style="width:100px;"   
        required="required" data-options="min:'08:30',showSeconds:true" />  
	<div id="timespinner"></div>
	
</body>
</html>
