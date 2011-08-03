<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<%@ page isErrorPage="true"%>
<%
String path = request.getContextPath();
%>
<head>
    <title>CMS Business Exception Page</title>
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="<%=path %>/framework/css/style.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<h2>位置-系统提示</h2>
	<hr/>	
   	<table>
       <tr>
           <td width="14%" class="text_right"><img src="<%=path %>/framework/css/images/icon_warning.png" width="48" height="48" /></td>
           <td width="86%" class="vertical_middle"><%=exception.getMessage() %></td>
       </tr>
    </table>
    <hr/>
    <div id="btn">
    	<input type="button" value="返回" class="btn" onclick="history.back()" />
    </div>
</body>
</html>