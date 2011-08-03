<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" dir="ltr">
<head>
<title><decorator:title default="IMP框架控制台" /></title>
<link href="${_path }/framework/css/login.css" rel="stylesheet" type="text/css"/>
<decorator:head />
</head>
<body>
<div id="maincontainer">
    <div id="header">
        <h1>内容管理平台</h1>
        <span>Content Management Platform</span> 
    </div>
    
	<decorator:body />
	
	<div id="push"></div>
</div>
<div id="footer">
    <div class="innertube"> &copy;Copyright 2009 中国移动 版本2008V1.0 </div>
</div>
</body>
</html>