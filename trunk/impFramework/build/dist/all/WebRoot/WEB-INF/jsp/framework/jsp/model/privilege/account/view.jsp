<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_form">
	<html>
	<head>
	<meta name="location" content="账户管理-查看账户信息"/>
	</head>
	<body>
		<table>
			<tr>
				<td><spring:message code="common.user.label.name"/>：</td>
				<td>${form.name }</td>
			</tr>
			<tr>
				<td><spring:message code="common.um.account.label.operator"/>：</td>
				<td>${form.operator }</td>
			</tr>
			<tr>
				<td><spring:message code="common.um.account.label.operatorContact"/>：</td>
				<td>${form.operatorContact }</td>
			</tr>
			<tr>
				<td><spring:message code="common.um.account.status"/>：</td>
				<td>
					${from.status }
				</td>
			</tr>
			<tr>
				<td><spring:message code="common.user.label.roles"/>：</td>
				<td>${from.role.name }</td>
			</tr>			
		</table>
		<hr/>
		<div id="btn">
			<input type="button" value="<spring:message code="common.label.operate.goback"/>" onclick="history.back();" class="btn"/>
		</div>
	</body>
	</html>
</page:applyDecorator>
