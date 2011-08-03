<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_form">
	<%
		final String path = request.getContextPath();
		request.setAttribute("_path", path);
	%>
	<html>
		<head>
			<meta name="location" content="<spring:message code="console.script.page.label.scripexecuteor"/>" />
			<script type="text/javascript" src="${path}/js/jquery-1.3.2.min.js"></script>
			<script type="text/javascript">
			</script>
		</head>
		<body>
			<form:form commandName="form" method="POST">
				<div>
					<table>
						<tr>
							<td align="right">
								<spring:message code="console.script.page.label.scripttype"/>：
							</td>
							<td>
								<form:select path="type">
									<form:options items="${types}" />
								</form:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								<spring:message code="console.script.page.label.datasource"/>：
							</td>
							<td>
								<form:select path="datasource">
									<form:options items="${datasources}" />
								</form:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								<spring:message code="console.script.page.label.script"/>：
							</td>
							<td>
								<form:textarea path="script" cols="120" rows="20" />
							</td>
						</tr>
						<tr>
							<td align="right">
								<spring:message code="console.script.page.label.token"/>：
							</td>
							<td>
								<form:input path="token" size="70" />
							</td>
						</tr>
						<tr>
							<td align="right">
								<spring:message code="console.script.page.label.result"/>：
							</td>
							<td>
								<textarea rows="5" cols="120">${message}</textarea>
							</td>
						</tr>
					</table>
					<div id="btn">
						<input type="submit" value="<spring:message code="console.script.page.label.submit"/>" class="btn" />
						<input type="reset" class="btn" />
					</div>
				</div>
			</form:form>
		</body>
	</html>
</page:applyDecorator>