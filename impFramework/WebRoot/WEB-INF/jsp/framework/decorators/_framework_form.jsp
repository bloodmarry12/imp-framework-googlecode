<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" dir="ltr">
<head>
<title><decorator:title default="IMP框架控制台" /></title>
<link rel="stylesheet" type="text/css" href="${_path }/framework/css/smoothness/jquery-ui-1.7.1.custom.css" />
<link rel="stylesheet" type="text/css" href="${_path }/framework/css/style.css" />
<script type="text/javascript" src="${_path }/framework/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${_path }/framework/js/jquery-ui-1.7.1.custom.min.js"></script>
<script type="text/javascript" src="${_path }/framework/js/framework.js"></script>
<decorator:head />
</head>
	<body>
		<h2>位置-<decorator:getProperty property="meta.location"/></h2>
		<hr/>
		<decorator:body />
	</body>
</html>