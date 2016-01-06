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
						$('.easyui-accordion li a').click(
								function() {
									var tabTitle = $(this).text();
									var url = $(this).attr("href");
									addTab(tabTitle, url);
									$('.easyui-accordion li div').removeClass(
											"selected");
									$(this).parent().addClass("selected");
								}).hover(function() {
							$(this).parent().addClass("hover");
						}, function() {
							$(this).parent().removeClass("hover");
						});

						function addTab(subtitle, url) {
							if (!$('#tabs').tabs('exists', subtitle)) {
								$('#tabs').tabs('add', {
									title : subtitle,
									content : createFrame(url),
									closable : true,
									width : $('#mainPanle').width() - 10,
									height : $('#mainPanle').height() - 26
								});
							} else {
								$('#tabs').tabs('select', subtitle);
							}
							tabClose();
						}

						function createFrame(url) {
							var s = '<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="'
									+ url
									+ '" style="width:100%;height:100%;"></iframe>';
							return s;
						}

						function tabClose() {
							/*双击关闭TAB选项卡*/
							$(".tabs-inner").dblclick(function() {
								var subtitle = $(this).children("span").text();
								$('#tabs').tabs('close', subtitle);
							})

							$(".tabs-inner").bind('contextmenu', function(e) {
								$('#mm').menu('show', {
									left : e.pageX,
									top : e.pageY,
								});
								var subtitle = $(this).children("span").text();
								$('#mm').data("currtab", subtitle);
								return false;
							});
						}

						//绑定右键菜单事件
						function tabCloseEven() {
							//关闭当前
							$('#mm-tabclose').click(function() {
								var currtab_title = $('#mm').data("currtab");
								$('#tabs').tabs('close', currtab_title);
							})
							//全部关闭
							$('#mm-tabcloseall').click(function() {
								$('.tabs-inner span').each(function(i, n) {
									var t = $(n).text();
									$('#tabs').tabs('close', t);
								});
							});

							//关闭除当前之外的TAB
							$('#mm-tabcloseother').click(function() {
								var currtab_title = $('#mm').data("currtab");
								$('.tabs-inner span').each(function(i, n) {
									var t = $(n).text();
									if (t != currtab_title)
										$('#tabs').tabs('close', t);
								});
							});
							//关闭当前右侧的TAB
							$('#mm-tabcloseright').click(function() {
								var nextall = $('.tabs-selected').nextAll();
								if (nextall.length == 0) {
									//msgShow('系统提示','后边没有啦~~','error');
									alert('后边没有啦~~');
									return false;
								}
								nextall.each(function(i, n) {
									var t = $('a:eq(0) span', $(n)).text();
									$('#tabs').tabs('close', t);
								});
								return false;
							});
							//关闭当前左侧的TAB
							$('#mm-tabcloseleft').click(function() {
								var prevall = $('.tabs-selected').prevAll();
								if (prevall.length == 0) {
									alert('到头了，前边没有啦~~');
									return false;
								}
								prevall.each(function(i, n) {
									var t = $('a:eq(0) span', $(n)).text();
									$('#tabs').tabs('close', t);
								});
								return false;
							});

							//退出
							$("#mm-exit").click(function() {
								$('#mm').menu('hide');

							})
						}
						
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
</style>
</head>
<body class="easyui-layout">
	<div region="north" border="true" split="true"
		style="overflow: hidden; height: 80px;">
		<div class="top-bg">
			<a href="<%=basePath%>j_spring_security_logout">退出</a>
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
</body>
</html>
