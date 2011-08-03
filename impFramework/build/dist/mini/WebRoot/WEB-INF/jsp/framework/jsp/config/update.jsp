<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_form">
	<html>
	<head>
	<meta name="location" content="配置管理-修改配置信息"/>
	<script type="text/javascript">
	$(document).ready(function() {
	});
	</script>
	</head>
	<body>
	<form:form commandName="form">
	<form:hidden path="key"/>
	<form:hidden path="address"/>
	<form:hidden path="scope"/>	
	<form:errors path="*"></form:errors>
	    <table>
	    	<tr>
	            <td><spring:message code="common.sysConfig.name"/>：</td>
	            <td><form:input path="name" maxlength="64" cssClass="input"/></td>
	        </tr>
	        <tr>
	            <td><spring:message code="common.sysConfig.value"/>：</td>
	            <td><form:input path="value" maxlength="128" cssClass="input" cssStyle="width:256px"/></td>
	        </tr>
	        <tr>
	            <td><spring:message code="common.sysConfig.description"/>：</td>
	            <td><form:textarea path="description" cssClass="textarea"/></td>
	        </tr>
	        <tr>
	            <td><spring:message code="common.sysConfig.key"/>：</td>
	            <td>${form.key }</td>
	        </tr>	        
	    </table>
	    <hr/>
		<div id="btn">
			<input type="submit" value="<spring:message code="common.label.operate.submit"/>" class="btn" />
            <input type="reset" value="<spring:message code="common.label.operate.reset"/>" class="btn" />
            <input type="button" value="<spring:message code="common.label.operate.goback"/>" onclick="FK.historyBack()" class="btn" />
		</div>
	</form:form>
	</body>
	</html>
</page:applyDecorator>
