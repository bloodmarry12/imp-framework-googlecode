<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<html>
<head>
<title><decorator:title default="装饰器页面..." /></title>
<decorator:head />
</head>
	<body>
		测试用template
		<hr>
		<decorator:body />
		<hr>
		测试用template
	</body>
</html>