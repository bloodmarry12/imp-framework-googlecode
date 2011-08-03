<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.huawei.imp.framework.model.ftp.FtpConnectionPools" %>
<%@ page import="org.apache.commons.pool.ObjectPool" %>
<%@ page import="java.util.Map" %>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_form">
	<html>
	<head>
		<meta name="location" content="Ftp连接池状态"/>
	</head>
	<body>
		<P>
			<strong>链接情况</strong>
		</P>
		<p>		
			<%
			Map<String, ObjectPool> objectPoolMap = FtpConnectionPools.getObjectPoolMap();
			for(Map.Entry<String, ObjectPool> entry : objectPoolMap.entrySet()){
				ObjectPool objectPool = entry.getValue();
				out.println("Ftp:" + entry.getKey() + "<br/>");
				out.println("NumActive:" + objectPool.getNumActive() + "<br/>");
				out.println("NumIdle:" + objectPool.getNumIdle());
				out.println("<hr/>");
			}
			%>
		</P>
	</body>
	</html>
</page:applyDecorator>