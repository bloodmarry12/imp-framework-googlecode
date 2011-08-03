<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form"    uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
final String path = request.getContextPath();
request.setAttribute("_path",path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>动态文件上传测试页面</title>
<script type="text/javascript" src="${_path}/framework/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${_path}/js/cms.base.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	if('null'!='${message}' && ''!='${message}'){// 如果有返回值
		//alert('${uuid}');
		if('1' == '${message}')
		{
			window.parent.ajaxFileUploader_success('${uuid}', '${filePath}','${fileName}');
		}
		else
		{
			window.parent.ajaxFileUploader_cancel('${uuid}');
		}
	}
});
</script>
</head>
<body>
<form:form id="f" name="f" method="post" enctype="multipart/form-data">
		<input id="uuid" name="uuid" value=""/>
		<input id="fileObj" name="fileObj" type="file"/>
		<input type="submit"/>
</form:form>
</body>
</html>



