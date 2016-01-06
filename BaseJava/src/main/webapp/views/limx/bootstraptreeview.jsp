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

<title>My JSP 'bootstraptreeview.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet"
	href="<%=basePath%>/resource/bootstrap/3.3.4/css/bootstrap.min.css">
<!-- <script src="http://libs.useso.com/js/jquery/2.1.1/jquery.min.js"></script> -->
<script type="text/javascript"
	src="<%=basePath%>/resource/easyui/jquery.min.js"></script>
<script
	src="<%=basePath%>/resource/bootstrap/treeview/bootstrap-treeview.min.js"></script>
<script type="text/javascript">
	var defaultData = [ {
		text : 'Parent 1',
		href : '#parent1',
		tags : [ '4' ],
		nodes : [ {
			text : 'Child 1',
			href : '#child1',
			tags : [ '2' ],
			nodes : [ {
				text : 'Grandchild 1',
				href : '#grandchild1',
				tags : [ '0' ]
			}, {
				text : 'Grandchild 2',
				href : '#grandchild2',
				tags : [ '0' ]
			} ]
		}, {
			text : 'Child 2',
			href : '#child2',
			tags : [ '0' ]
		} ]
	}, {
		text : 'Parent 2',
		href : '#parent2',
		tags : [ '0' ]
	}, {
		text : 'Parent 3',
		href : '#parent3',
		tags : [ '0' ]
	}, {
		text : 'Parent 4',
		href : '#parent4',
		tags : [ '0' ]
	}, {
		text : 'Parent 5',
		href : '#parent5',
		tags : [ '0' ]
	} ];

	$(function() {
		$('#treeview8').treeview({
			expandIcon : "glyphicon glyphicon-stop",
			collapseIcon : "glyphicon glyphicon-unchecked",
			nodeIcon : "glyphicon glyphicon-user",
			color : "yellow",
			backColor : "purple",
			onhoverColor : "orange",
			borderColor : "red",
			showBorder : false,
			showTags : true,
			highlightSelected : true,
			selectedColor : "yellow",
			selectedBackColor : "darkorange",
			data : defaultData
		});

	});
</script>
</head>

<body>
	<div class="row">
	<div class="col-sm-2">
		<div id="treeview8" class=""></div>
	</div>	
	</div>
</body>
</html>
