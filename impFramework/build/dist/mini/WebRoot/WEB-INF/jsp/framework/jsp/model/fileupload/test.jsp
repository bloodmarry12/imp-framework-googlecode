<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>动态文件上传测试页面</title>
<link href="${_path}/framework/css/imp.fileupload.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${_path}/framework/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${_path}/framework/js/jquery.imp.fileupload.js"></script>
</head>
<script type="text/javascript">
var cms_sys_basePath = '${_path}';
$(document).ready(function() {
	// 注册ajax文件上传组件
	$('.ajaxFileUploader').each(function(){
		$(this).AjaxFileUploader_Init();
	});
});
</script>

<body> 
测试Ajax文件上传页面: 
<hr/>
	<imp:ajaxFileUpload fileType="test1" reqParaName="ajaxFile1" need="true" fileMap="${map}" submitError="${false}"/>
</body>
</html>