<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'MyZtree.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet"
	href="<%=basePath %>resource/ztree/css/custom/zTreeStyle.css">
</head>
<body>
	<div>
		<ul id="ztreeRoot" class="ztree"></ul>
	</div>
	<script type="text/javascript"
		src="<%=basePath%>resource/jquery/jquery.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath %>resource/ztree/js/jquery.ztree.core-3.5.min.js"></script>
	<script type="text/javascript">
		var zTree,
			zTreeObjId = "ztreeRoot";
		var setting  = {
			async : {
				enable : true,//异步加载
				dataType : "json"
			},
			view  : {
				dblClickExpand : false,//双击节点时候是否展开
				showIcon : true,
				showLine : true,
				showTitle : false,
				selectedMulti : false,
				expandSpeed:""
			},
			data: {
				key:{
					name:"text"				},
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "parentId",
					rootPId: "0"
				}
			}
		};
		var zNodes;
		$.ajax({
		url : '<%=basePath%>/function/mylist.do',
		async: false,
		success:function(json){  
	        	zNodes=json;
	        	<%-- var zNode;
	        	for(var i=0;i<zNodes.length;i++){
	        		zNode=zNodes[i];
	        		zNode.url='<%=basePath%>'+zNodes[i].url;
	        	}
	        	alert(zNodes[1].url); --%>
	    			 } 
	});
		/* var zNodes =[
			{ id:1, pId:0, name:"pNode 1", open:true},
			{ id:11, pId:1, name:"pNode 11"},
			{ id:111, pId:11, name:"leaf node 111"},
			{ id:112, pId:11, name:"leaf node 112"},
			{ id:113, pId:11, name:"leaf node 113"},
			{ id:114, pId:11, name:"leaf node 114"},
			{ id:12, pId:1, name:"pNode 12"},
			{ id:121, pId:12, name:"leaf node 121"},
			{ id:122, pId:12, name:"leaf node 122"},
			{ id:123, pId:12, name:"leaf node 123"},
			{ id:124, pId:12, name:"leaf node 124"},
			{ id:13, pId:1, name:"pNode 13 - no child", isParent:true},
			{ id:2, pId:0, name:"pNode 2"},
			{ id:21, pId:2, name:"pNode 21", open:true},
			{ id:211, pId:21, name:"leaf node 211"},
			{ id:212, pId:21, name:"leaf node 212"},
			{ id:213, pId:21, name:"leaf node 213"},
			{ id:214, pId:21, name:"leaf node 214"},
			{ id:22, pId:2, name:"pNode 22"},
			{ id:221, pId:22, name:"leaf node 221"},
			{ id:222, pId:22, name:"leaf node 222"},
			{ id:223, pId:22, name:"leaf node 223"},
			{ id:224, pId:22, name:"leaf node 224"},
			{ id:23, pId:2, name:"pNode 23"},
			{ id:231, pId:23, name:"leaf node 231"},
			{ id:232, pId:23, name:"leaf node 232"},
			{ id:233, pId:23, name:"leaf node 233"},
			{ id:234, pId:23, name:"leaf node 234"},
			{ id:3, pId:0, name:"pNode 3 - no child", isParent:true}
		]; */
		$(document).ready(function(){
			//获取加载树的dom
			var ztreeObj = $("#"+zTreeObjId);
			//初始化树
			$.fn.zTree.init(ztreeObj,setting,zNodes);
			//选中树		
			var ztree = $.fn.zTree.getZTreeObj(zTreeObjId);
			
			var defaultTree = ztree.getNodeByParam("id","_authority_28",null);
			//ztree.expandNode(rootNode, true); //自动展开
			ztree.selectNode(defaultTree);
		});
  		</script>
</body>
</html>
