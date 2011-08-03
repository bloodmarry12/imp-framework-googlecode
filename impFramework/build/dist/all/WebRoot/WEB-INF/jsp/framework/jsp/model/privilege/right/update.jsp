<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_form">
	<html>
	<head>
	<meta name="location" content="权限管理-修改权限"/>
	</head>
	<body>
	<form:form commandName="form" action="update.do">
	<form:hidden path="id"/>
	    <table>
	        <tr>
	            <td><spring:message code="common.um.right.name"/>：</td>
	            <td><form:input path="rightName"/>
	            </td>
	        </tr>
	        <tr>
	            <td><spring:message code="common.um.right.code"/>：</td>
	            <td><form:input path="code"/>
	            </td>
	        </tr>
	        <tr>
	            <td><spring:message code="common.um.right.description"/>：</td>
	            <td><form:textarea path="description"/>
	            </td>
	        </tr>	        
	    </table>
	    <hr/>
	    <div id="btn">
	    	<input type="submit" value="<spring:message code="common.label.operate.submit"/>" class="btn"/>
	        <input type="reset" value="<spring:message code="common.label.operate.reset"/>" class="btn"/>
	        <input type="button" value="<spring:message code="common.label.operate.goback"/>" onclick="history.back()" class="btn"/>
	    </div>
	</form:form>
	</body>
	</html>
</page:applyDecorator>
