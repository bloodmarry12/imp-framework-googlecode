<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_form">
	<html>
	<head>
	<meta name="location" content="权限管理-查看权限信息"/>
	</head>
	<body>
	<table>
        <tr>
            <td><spring:message code="common.um.right.name"/>：</td>
            <td>${form.rightName }</td>
        </tr>
        <tr>
            <td><spring:message code="common.um.right.code"/>：</td>
            <td>${form.code }
            </td>
        </tr>
        <tr>
            <td><spring:message code="common.um.right.description"/>：</td>
            <td>${form.description }
            </td>
        </tr>        
    </table>
    <hr/>
    <div id="btn">
    	<input type="button" value="<spring:message code="common.label.operate.goback"/>" onclick="history.back()" class="btn"/>
    </div>
	</body>
	</html>
</page:applyDecorator>
