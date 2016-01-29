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
		text : "Node 1",
		href : "#node-1",
		selectable : true,
		state : {
			checked : true,
			expanded : true
		},
		nodes : [ {
			text : "Node 2",
			href : "#node-1",
			selectable : true,
			state : {
				checked : true,
				expanded : true
			},
			nodes : [ {
				text : "Node 3",
				href : "#node-1",
				selectable : true,
				state : {
					checked : true,
					expanded : false
				},
				nodes : []
			}, {
				text : "Node 4",
				id:"node4",
				href : "#node-1",
				selectable : true,
				state : {
					checked : true,
					expanded : false,
					selected:true
				},
				nodes : []
			} ]
		}, {
			text : "Node 5",
			href : "#node-1",
			selectable : true,
			state : {
				checked : true,
				expanded : false
			},
			nodes : []
		} ]
	}, {
		text : "Node 6",
		href : "#node-1",
		selectable : true,
		state : {
			checked : true,
			expanded : false
		},
		nodes : []
	}, {
		text : "Node 7",
		href : "#node-1",
		selectable : true,
		state : {
			checked : true,
			expanded : false
		},
		nodes : [{
			text : "Node 8",
			href : "#node-1",
			selectable : true,
			state : {
				checked : true,
				expanded : false
			},
			nodes : []
		},{
			text : "Node 9",
			href : "#node-1",
			selectable : true,
			state : {
				checked : true,
				expanded : false
			},
			nodes : []
		}]
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
