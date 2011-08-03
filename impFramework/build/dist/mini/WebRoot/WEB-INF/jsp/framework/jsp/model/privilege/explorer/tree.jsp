<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_form">
	<html>
	<head>
	<link rel="stylesheet" type="text/css" href="${_path }/framework/css/jquery.treeview.css"/>
	<meta name="location" content="权限管理"/>
	<script type="text/javascript" src="${_path }/framework/js/jquery.treeview.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#navigation").treeview({
			persist: "location",
			collapsed: false,
			unique: false
		});
	});
	
	function deleteExplorer(id){
		FK.confirm("确定要删除导航菜单？", function(){
			location.href='delete.do?explorerID='+id;
		});
	}
	</script>
	</head>
	<body>
	<ul id="navigation">
		${cms_sys_explorer }
	</ul>
	</body>
	</html>
</page:applyDecorator>
