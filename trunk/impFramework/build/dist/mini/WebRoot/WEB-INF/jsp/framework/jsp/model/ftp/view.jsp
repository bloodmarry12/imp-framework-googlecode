<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_form">
	<html>
	<head>
	<meta name="location" content="FTP配置管理-查看配置信息"/>
	</head>
	<body>
		<table>
			<tr>
				<td><spring:message code="common.ftp.label.ftpAlias"/>：</td>
				<td>${form.ftpAlias }</td>
			</tr>
			<tr>
				<td><spring:message code="common.ftp.label.ip"/>：</td>
				<td>${form.ip }</td>
			</tr>
			<tr>
				<td><spring:message code="common.ftp.label.port"/>：</td>
				<td>${form.port }</td>
			</tr>
			<tr>
				<td><spring:message code="common.ftp.label.user"/>：</td>
				<td>${form.userName }</td>
			</tr>
			<tr>
				<td><spring:message code="common.ftp.label.psw"/>：</td>
				<td>${form.userPaswd }</td>
			</tr>
			<tr>            
        	</tr>
		</table>
		<hr/>
		<div id="btn">
        	<input type="button" value="<spring:message code="common.label.operate.goback"/>" onclick="FK.historyBack()" class="btn"/>
		</div>
	</body>
	</html>
</page:applyDecorator>