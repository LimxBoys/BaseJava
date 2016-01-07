<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<link rel="stylesheet" href="<%=basePath%>/resource/css/login.css" />
	<link href='http://fonts.googleapis.com/css?family=Oleo+Script' rel='stylesheet' type='text/css'>
	<script type="text/javascript" src="<%=basePath%>/resource/easyui/jquery.min.js"></script>
<script type="text/javascript">
	
	</script>
  </head>
  
  <body>
   <div class="lg-container">
		<h1>LimxBoys</h1>
		<form action="${pageContext.request.contextPath}/j_spring_security_check" id="lg-form" name="lg-form" method="post">
			
			<div>
				<label for="username">Username:</label>
				<input type="text" name="username" id="username" placeholder="username"/>
			</div>
			
			<div>
				<label for="password">Password:</label>
				<input type="password" name="password" id="password" placeholder="password" />
			</div>
			
			<div>				
				<button type="submit" id="login">Login</button>
			</div>
			
		</form>
		<div id="message"><p class="error"> ${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}</p></div>
	</div>
  </body>
</html>
