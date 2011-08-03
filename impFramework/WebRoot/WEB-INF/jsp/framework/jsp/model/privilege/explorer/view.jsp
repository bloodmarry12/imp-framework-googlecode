<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_form">
	<html>
	<head>
	<meta name="location" content="菜单管理-查看菜单信息"/>
	</head>
	<body>
		<table>
			<tr>
				<td><spring:message code="common.um.explorer.name"/>：</td>
				<td>${form.name }</td>
			</tr>
			<tr>
				<td><spring:message code="common.um.explorer.path"/>：</td>
				<td>${form.path }</td>
			</tr>
			<tr>
				<td><spring:message code="common.um.explorer.rightName"/>：</td>
				<td>${form.right.rightName }</td>
			</tr>
			<tr>
				<td><spring:message code="common.um.explorer.order"/>：</td>
				<td>${form.order }</td>
			</tr>
		</table>
		<hr/>
		<div id="btn">
			<input type="button" value="<spring:message code="common.label.operate.goback"/>" onclick="history.back()" class="btn" />
		</div>
	</body>
	</html>
</page:applyDecorator>