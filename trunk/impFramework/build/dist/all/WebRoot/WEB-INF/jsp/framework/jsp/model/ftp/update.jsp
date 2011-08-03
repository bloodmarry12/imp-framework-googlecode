<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_form">
	<html>
	<head>
	<meta name="location" content="FTP配置管理-修改配置信息"/>
	</head>
	<body>
	<form:form commandName="form">
	<form:hidden path="ftpAlias"/>
	    <table>
	    <form:errors path="*"></form:errors>
	        <tr>
	            <td><spring:message code="common.ftp.label.ftpAlias"/>：</td>
	            <td>${form.ftpAlias }</td>
	        </tr>
	        <tr>
	            <td><spring:message code="common.ftp.label.ip"/>：</td>
	            <td><form:input path="ip" maxlength="15"/></td>
	        </tr>
	        <tr>
	            <td><spring:message code="common.ftp.label.port"/>：</td>
	            <td><form:input path="port" maxlength="6"/></td>
	        </tr>
	        <tr>
	            <td><spring:message code="common.ftp.label.user"/>：</td>
	            <td><input type="text" name="userName"/>${userName }</td>
	        </tr>
	        <tr>
	            <td><spring:message code="common.ftp.label.psw"/>：</td>
	            <td><form:password path="userPaswd" maxlength="256"/></td>
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