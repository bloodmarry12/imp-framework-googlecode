<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_form">
	<html>
	<head>
	<meta name="location" content="账户管理-查看账户信息"/>
	</head>
	<body>
	<table class="table_form">
        <tr>
            <td class="label"><spring:message code="common.timetask.taskName"/>:</td>
            <td class="field">${form.taskName }</td>
            <td class="status"></td>
        </tr>
        <tr>
            <td class="label"><spring:message code="common.timetask.description"/>:</td>
            <td class="field">${form.description }</td>
            <td class="status"></td>
        </tr>
        <tr>
            <td class="label"><spring:message code="common.timetask.task.beanName"/>:</td>
            <td class="field">${form.beanName }</td>
            <td class="status"></td>
        </tr>
        <tr>
            <td class="label"><spring:message code="common.timetask.task.methodName"/>:</td>
            <td class="field">${form.methodName }</td>
            <td class="status"></td>
        </tr>
        <tr>
            <td class="label"><spring:message code="common.timetask.concurrent"/>:</td>
            <td class="field">
            <c:if test="${0 == form.concurrent}">
			<spring:message code="common.timetask.concurrent0"/>
			</c:if>
			<c:if test="${1 == form.concurrent }">
				<spring:message code="common.timetask.concurrent1"/>
			</c:if>	
            <td class="status"></td>
        </tr>
        <tr>
            <td class="label"><spring:message code="common.timetask.ecxp"/>:</td>
            <td class="field">${form.cexp }</td>
            <td class="status"></td>
        </tr>
    </table>
	</body>
	</html>
</page:applyDecorator>
