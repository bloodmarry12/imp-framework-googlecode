<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=path %>/framework/css/style.css" type="text/css" rel="stylesheet" />
<title>操作完成</title>
</head>
<body>
	<h2>位置-系统提示</h2>
	<hr/>
	<table>
       <tr>
           <td width="14%" class="text_right"><img src="<%=path %>/framework/css/images/icon_success.png" width="48" height="48" /></td>
           <td width="86%" class="vertical_middle">操作完成</td>
       </tr>
    </table>
    <hr/>
    <div id="btn">
    	<input type="button" value="返回" class="btn" onclick="location.href='${_path }${url }'" />
    </div>
<script type="text/javascript">
</script>
</body>
</html>