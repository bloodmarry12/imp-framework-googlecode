<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_form">
	<html>
	<head>
	<meta name="location" content="FTP配置管理-修改配置信息"/>
	<script type="text/javascript">
	$(document).ready(function() {
	
	});
	</script>
	</head>
	<body>
	<form:form commandName="form">
	<form:hidden path="id"/>
	<form:hidden path="taskName"/>
	<form:hidden path="description"/>
	<form:hidden path="host"/>
	<table>
        <tr>
            <td><spring:message code="common.timetask.taskName"/>：</td>
            <td>${form.taskName }</td>
            <td></td>
        </tr>
        <tr>
            <td><spring:message code="common.timetask.description"/>：</td>
            <td>${form.description }</td>
        </tr>
        <tr>
            <td><spring:message code="common.timetask.task.beanName"/>：</td>
            <td><form:input path="beanName" maxlength="128" cssClass="input"/></td>
        </tr>
        <tr>
            <td><spring:message code="common.timetask.task.methodName"/>：</td>
            <td><form:input path="methodName" maxlength="128" cssClass="input"/></td>
        </tr>
        <tr>
            <td><spring:message code="common.timetask.task.parameter"/>：</td>
            <td><form:input path="parameter" maxlength="128" cssClass="input"/></td>
        </tr>
        <tr>
            <td><spring:message code="common.timetask.concurrent"/>：</td>
            <td>
	            <form:select path="concurrent">
	            	<form:option value="0"><spring:message code="common.timetask.concurrent0"/></form:option>
	            	<form:option value="1"><spring:message code="common.timetask.concurrent1"/></form:option>
	            </form:select>
	        </td>
        </tr>
        <tr>
            <td><spring:message code="common.timetask.ecxp"/>：</td>
            <td><form:input path="cexp" cssClass="input"/></td>
        </tr>
        <!-- 
        <tr>
            <td><spring:message code="common.timetask.task.host"/>：</td>
            <td>${form.host }</td>
        </tr>
         -->
        <tr>
            <td colspan="2" style="color: red">
            	<form:errors path="*" ></form:errors>
            </td>
        </tr> 
		<tr>
            <td colspan="2">
            	<spring:message code="common.timetask.cexpDesc"/>
            </td>
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
