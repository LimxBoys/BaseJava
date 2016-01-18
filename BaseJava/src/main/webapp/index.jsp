<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  
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

<title>My JSP 'index.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/resource/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/resource/easyui/themes/icon.css">
	<script type="text/javascript" src="<%=basePath%>/resource/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resource/easyui/jquery.easyui.min.js"></script>
<script language="JavaScript">
	$(document)
			.ready(
					function() {
					$(".tabs-header").bind('contextmenu',function(e){
		e.preventDefault();
		$('#rcmenu').menu('show', {
			left: e.pageX,
			top: e.pageY
		});
	});
	//关闭当前标签页
	$("#closecur").bind("click",function(){
		var tab = $('#tabs').tabs('getSelected');
		var index = $('#tabs').tabs('getTabIndex',tab);
		$('#tabs').tabs('close',index);
	});
	//关闭所有标签页
	$("#closeall").bind("click",function(){
		var tablist = $('#tabs').tabs('tabs');
		for(var i = tablist.length - 1; i >= 1; i--){
			$('#tabs').tabs('close',i);
		}
	});
	//关闭非当前标签页（先关闭右侧，再关闭左侧）
	$("#closeother").bind("click",function(){
		var tablist = $('#tabs').tabs('tabs');
		var tab = $('#tabs').tabs('getSelected');
		var index = $('#tabs').tabs('getTabIndex',tab);
		for(var i = tablist.length - 1; i > index; i--){
			$('#tabs').tabs('close',i);
		}
		var num = index - 1;
		for(var i = num; i >= 1; i--){
			$('#tabs').tabs('close',1);
		}
	});
	//关闭当前标签页右侧标签页
	$("#closeright").bind("click",function(){
		var tablist = $('#tabs').tabs('tabs');
		var tab = $('#tabs').tabs('getSelected');
		var index = $('#tabs').tabs('getTabIndex',tab);
		for(var i = tablist.length-1; i > index; i--){
			$('#tabs').tabs('close',i);
		}
	});
	//关闭当前标签页左侧标签页
	$("#closeleft").bind("click",function(){
		var tab = $('#tabs').tabs('getSelected');
		var index = $('#tabs').tabs('getTabIndex',tab);
		var num = index - 1;
		for(var i = num; i >= 1; i--){
			$('#tabs').tabs('close',1);
		}
	});
						
	$('#title2tree').tree({  
    url: '<%=basePath%>/function/mylist.do',  
    loadFilter: function(data){     
        return data;        
    },
    
    onClick: function(node){
    	if (node.url) { // 如果是链接
				//node.isLeaf()
				openTab(node,'<%=path%>'+ node.url);
													} 
												}
											});	
	$('#title1tree').tree({  
    url: '<%=basePath%>/function/mylist.do',  
    loadFilter: function(data){     
        return data;        
    },
    
    onClick: function(node){
    	if (node.url) { // 如果是链接
				//node.isLeaf()
				openTab(node,'<%=path%>'+ node.url);
													} 
												}
											});										
			openTab = function(node, url) {
								var id = node.id;
								try {
									title = (node.text.split('>')[1])
											.split('<')[0];
								} catch (e) {
									title = node.text;
								}
								if ($('#tabs').tabs('exists', title)) {
									$('#tabs').tabs('select', title);
								} else {
									var content = "<iframe scrolling='auto' frameborder='0'  src='"
											+ url
											+ "' style='width:100%;height:100%;'></iframe>";
									$('#tabs').tabs('add', {
										title : title,
										content : content,
										closable : true
									});
								}
								
							};
							
	/* 修改密码 */
	$('#editPassword').click(function() {
		$('#user_changepwd').dialog({
			title : '修改登陆密码',
			width : 350,
			height : 200,
			closed : false,
			cache : false,
			modal : true,
			href: '<%=basePath%>user/changePwdinfo.do'
		});
	});
	/* 退出 */
	$('#logout').click(function() {
		$.messager.confirm('警告', '确定要退出吗？',
		function(r) {
			if (r) {
				//退出操作
				location.href ="<%=basePath%>j_spring_security_logout";
			}
		});
	});	
	sayHello = function() {
		var hour = new Date().getHours(), hello = '';
		if (hour < 6) {
			hello = '凌晨好';
		} else if (hour < 9) {
			hello = '早上好';
		} else if (hour < 12) {
			hello = '上午好';
		} else if (hour < 14) {
			hello = '中午好';
		} else if (hour < 17) {
			hello = '下午好';
		} else if (hour < 19) {
			hello = '傍晚好';
		} else if (hour < 22) {
			hello = '晚上好';
		} else {
			hello = '夜里好';
		}
		return hello + '！';
	};					
	$("#sayHelloSpan").text(sayHello());
					
});

</script>
<style>
.footer {
	width: 100%;
	text-align: center;
	line-height: 35px;
}

.top-bg {
	background-color: #d8e4fe;
	height: 80px;
}
#headerDiv {
    font-size: 12px;
    height: 104px;
    width: 100%;
    line-height: 40px;
    background-image: url("<%=basePath%>resource/images/i/top.jpg");
}
</style>
</head>
<body class="easyui-layout">
	<div region="north" border="true" split="true"
		style="overflow: hidden; height: 80px;">
		<div id="headerDiv" class='top-bg' style="background-image:<%=basePath%>resource/images/i/top.jpg">
					<div style=" heigth : 5px;">
						<div>
							<span
								style="float:left; height: 29px;color:white;margin-left:20px;margin-top:5px;padding-left:20px;"
								><font style="font-size:14px;padding-bottom:3px;">欢迎您:<sec:authentication property="principal.username" /></font> <span
								style="font-size:14px;padding-bottom:3px;" id="sayHelloSpan"></span>
							</span>
						</div>
						<div style=" float:right; margin : 0px 30px 0px 0px;">
							<span style="padding: 8px 10px 3px 18px;" 
								style="height:20px;"> <img
								style="padding-top:10px;cursor:pointer;"
								onMouseOver="this.src='<%=basePath%>resource/images/2_2.png'"
								onMouseOut="this.src='<%=basePath%>resource/images/2.png'"
								src="<%=basePath%>resource/images/2.png"  /> </span> <span
								style="padding: 8px 10px 3px 18px;height: 20px;"
								> <img
								style="padding-top:10px;cursor:pointer;"
								onMouseOver="this.src='<%=basePath%>resource/images/3_3.png'"
								onMouseOut="this.src='<%=basePath%>resource/images/3.png'"
								src="<%=basePath%>resource/images/3.png" id="editPassword" />
							</span> <span style="padding: 8px 10px 3px 18px;" 
								style="height:20px;"> <img
								style="padding-top:10px;cursor:pointer;"
								onMouseOver="this.src='<%=basePath%>resource/images/4_4.png'"
								onMouseOut="this.src='<%=basePath%>resource/images/4.png'"
								src="<%=basePath%>resource/images/4.png" id="logout" /> </span>
						</div>
					</div>
				</div>
	</div>
	<div region="south" border="true" split="true"
		style="overflow: hidden; height: 40px;">
		<div class="footer">
			版权所有：limingxing
		</div>
	</div>
	<div region="west" split="true" title="导航菜单" style="width: 200px;">
		<div id="aa" class="easyui-accordion"
			style="position: absolute; top: 27px; left: 0px; right: 0px; bottom: 0px;">

			<div title="个人信息" iconcls="icon-save"
				style="overflow: auto; padding: 10px;">
				<ul class="easyui-tree" id="title1tree">
					
					
					
				</ul>
			</div>
			<div title="系统管理" iconcls="icon-reload"style="padding: 10px;">
				<ul class="easyui-tree" id="title2tree" data-options="lines:true"></ul>
			</div>
		</div>

	</div>
	<div id="mainPanle" region="center" style="overflow: hidden;">
		<div id="tabs" class="easyui-tabs" fit="true" border="false">
			<div title="欢迎使用" style="padding: 20px; overflow: hidden;" id="home">
				<h1>Welcome to jQuery UI!</h1>
			</div>
		</div>
	</div>
	<%-- tabs标签栏的关闭菜单 --%>
	<div id="rcmenu" class="easyui-menu" style="">
		<div id="closecur">关闭</div>
		<div id="closeall">关闭全部</div>
		<div id="closeother">关闭其他</div>
		<div class="menu-sep"></div>
		<div id="closeright">关闭右侧标签页</div>
		<div id="closeleft">关闭左侧标签页</div>
	</div>
		<div id="user_changepwd"></div>
</body>
</html>
