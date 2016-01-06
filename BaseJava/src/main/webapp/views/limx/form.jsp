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
		#d1,#d2{
			float: left;
		}
	</style>
<script type="text/javascript"
	src="<%=basePath%>/resource/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/resource/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
	$(function() {
	/*ajax form*/
	$("#tijiao").click(function(){
		$('#form1').form({    
    		url:'<%=basePath%>/user/upload.do',
				onSubmit : function() {
					
				},
				success : function(data) {
				var data = eval('(' + data + ')');  
				console.log(data);
				$("#img1").attr("src","<%=basePath%>"+data.fileaddress);
				}
			});
			// submit the form    
			$('#form1').submit();
		});
	});
</script>
</head>

<body>
	<div id="d2">
		<div>
			<form id="form1" method="post" enctype="multipart/form-data">
				<div>
					<label for="name">Name:</label> <input class="easyui-validatebox"
						type="text" name="name" data-options="required:true" />
				</div>
				<div>
					<label for="email">Email:</label> <input class="easyui-validatebox"
						type="text" name="email" data-options="validType:'email'" />
				</div>
				<div>
					<input type="file" id="file" name="file">
				</div>
				<div>
					<input type="button" id="tijiao" value="保存">
				</div>
			</form>
		</div>
		<div>
			<img alt="暂无图片" src="#" id="img1" width="200px",height="200px">
		</div>
	</div>
</body>
</html>
