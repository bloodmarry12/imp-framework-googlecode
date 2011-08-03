<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_form">
	<html>
	<head>
	<meta name="location" content="配置管理-查看配置信息"/>
	</head>
	<body>
		<table>
			<tr>
	            <td class="label"><spring:message code="common.sysConfig.name"/>:</td>
	            <td class="field">${form.name }</td>
	            <td class="status"></td>
	        </tr>
	        <tr>
	            <td class="label"><spring:message code="common.sysConfig.value"/>:</td>
	            <td class="field">${form.value }</td>
	            <td class="status"></td>
	        </tr>
	        <tr>
	            <td class="label"><spring:message code="common.sysConfig.description"/>:</td>
	            <td class="field">${form.description }</td>
	            <td class="status"></td>
	        </tr>
	        <tr>
	            <td class="label"><spring:message code="common.sysConfig.key"/>:</td>
	            <td class="field">${form.key }</td>
	            <td class="status"></td>
	        </tr>
		</table>
		<hr/>
		<div id="btn">
        	<input type="button" value="<spring:message code="common.label.operate.goback"/>" onclick="FK.historyBack()" class="btn"/>
		</div>
	</body>
	</html>
</page:applyDecorator>
