<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_form">
	<html>
	<head>
	<meta name="location" content="账户管理-创建账户"/>
	</head>
	<body>
	<form:form commandName="form" action="update.do">
		<form:hidden path="id"/>
		<form:hidden path="role.id"/>
	    <table>
	        <tr>
	            <td><spring:message code="common.user.label.name"></spring:message>：</td>
	            <td><form:input path="name"/>
	        </tr>
	        <tr>
	            <td><spring:message code="common.um.account.label.operator"/>：</td>
	            <td><form:input path="operator"/></td>
	        </tr>
	        <tr>
	            <td><spring:message code="common.um.account.label.operatorContact"/>：</td>
	            <td><form:input path="operatorContact"/></td>
	        </tr>
	        <tr>
	        	<td><spring:message code="common.um.account.status"></spring:message>：</td>
	        	<td>
					<form:select path="status.value">
						<form:option value="-1">新增</form:option>
						<form:option value="0">等待激活</form:option>
						<form:option value="1">激活</form:option>
						<form:option value="2">停用</form:option>
						<form:option value="3">逻辑删除</form:option>
					</form:select>
				</td>
	        </tr>
	        <tr>
	        	<td><spring:message code="common.user.label.roles"></spring:message>：</td>
	        	<td>${form.role.name }</td>
	        </tr>
	    </table>
	    <hr/>
	    <div id="btn">
	    	<input type="submit" value="<spring:message code="common.label.operate.submit"/>" class="btn"/>
            <input type="reset" value="<spring:message code="common.label.operate.reset"/>" class="btn"/>
            <input type="button" value="<spring:message code="common.label.operate.goback"/>" onclick="FK.historyBack()" class="btn"/>
	    </div>
	</form:form>
	</body>
	</html>
</page:applyDecorator>
