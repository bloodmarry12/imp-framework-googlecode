<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_form">
	<html>
	<head>
	<meta name="location" content="账户管理-创建账户"/>
	</head>
	<body>
	<form:form commandName="form">
		<form:hidden path="status.value" />
		<div class="table_form">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tbody>
				<tr>
					<td class="label"><spring:message code="common.user.label.name"/>：</td>
					<td class="field"><form:input path="name" cssClass="input" /></td>
					<td class="status"></td>
					<td class="label"><spring:message code="common.user.label.password"/>：</td>
					<td class="field"><form:password path="password" cssClass="input" /></td>
					<td class="status"></td>
				</tr>
				<tr>
					<td class="label"><spring:message
						code="common.user.label.password.cf"/>：</td>
					<td class="field"><input type="password" id="password_c"
						name="password_c" Class="input" /></td>
					<td class="status"></td>
					<td class="label"><spring:message
						code="common.um.account.label.operator" />：</td>
					<td class="field"><form:input path="operator" cssClass="input" /></td>
					<td class="status"></td>
				</tr>
				<tr>
					<td class="label"><spring:message
						code="common.um.account.label.operatorContact" />：</td>
					<td class="field"><form:input path="operatorContact"
						cssClass="input" /></td>
					<td class="status"></td>
					<td class="label"></td>
					<td class="field"></td>
					<td class="status"></td>
				</tr>
				<tr>
		        	<td class="label"><spring:message code="common.user.label.roles"></spring:message>：</td>
		        	<td class="field" colspan="4">
		        		<form:radiobuttons items="${rolesIDItems}" path="role.id"/>
		        	</td>
		        	<td class="status" id="checkboxStatus"></td>
		        </tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="6" class="area_bottom"><input type="submit"
						value="<spring:message code="common.label.operate.submit"/>"
						class="btn_func" />&nbsp;&nbsp;&nbsp;&nbsp; <input type="reset"
						value="<spring:message code="common.label.operate.reset"/>"
						class="btn_func" />&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
						value="<spring:message code="common.label.operate.goback"/>" onclick="history.back()" class="btn_func" /></td>
				</tr>
			</tfoot>
		</table>
		</div>
	</form:form>
	</body>
	</html>
</page:applyDecorator>
