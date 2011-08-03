<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_form">
	<html>
	<head>
	<meta name="location" content="菜单管理-创建菜单"/>
	</head>
	<body>
	<form:form commandName="form" action="create.do">
		<form:hidden path="parent.id"/>
		<table>
			<tr>
	            <td><spring:message code="common.um.explorer.name"></spring:message>：</td>
	            <td><form:input path="name"/>
	        </tr>
	        <tr>
	            <td><spring:message code="common.um.explorer.path"></spring:message>：</td>
	            <td><form:input path="path"/></td>
	        </tr>
	        <tr>
	            <td><spring:message code="common.um.explorer.rightCode"></spring:message>：</td>
	            <td><form:input path="right.code"/></td>
	        </tr>
	        <tr>
	            <td><spring:message code="common.um.explorer.order"></spring:message>：</td>
	            <td><form:input path="order"/></td>
	        </tr>
		</table>
		<hr/>
		<div id="btn">
			<input type="submit" value="<spring:message code="common.label.operate.submit"/>" class="btn" />
			<input type="reset" value="<spring:message code="common.label.operate.reset"/>" class="btn" />
			<input type="button" value="<spring:message code="common.label.operate.goback"/>" onclick="history.back()" class="btn" />
		</div>
	</form:form>
	</body>
	</html>
</page:applyDecorator>