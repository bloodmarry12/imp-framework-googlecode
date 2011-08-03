<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_form">
	<html>
	<head>
	<meta name="location" content="Bean重新加载"/>
	</head>
	<body>
	<form action="update.do" method="post">
	    <table>
	        <tr>
	            <td>刷新bean：</td>
	            <td>
	            	<select name="id">
	            		<c:forEach items="${beans }" var="item">
	            			<option value="${item.key }">${item.key }</option>
	            		</c:forEach>
	            	</select>
	            </td>
	        </tr>	        
	    </table>
	    <hr/>
	    <div id="btn">
	    	<input type="submit" value="<spring:message code="common.label.operate.submit"/>" class="btn"/>
            <input type="reset" value="<spring:message code="common.label.operate.reset"/>" class="btn"/>
            <input type="button" value="<spring:message code="common.label.operate.goback"/>" onclick="FK.historyBack()" class="btn"/>
	    </div>
	</form>
	</body>
	</html>
</page:applyDecorator>