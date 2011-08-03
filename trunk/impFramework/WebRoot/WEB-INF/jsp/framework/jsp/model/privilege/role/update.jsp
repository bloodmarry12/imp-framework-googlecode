<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_form">
	<html>
	<head>
	<link rel="stylesheet" type="text/css" href="${_path }/framework/css/jquery.treeview.css"/>
	<meta name="location" content="角色管理-修改角色"/>
	<script type="text/javascript" src="${_path }/framework/js/jquery.treeview.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#navigation").treeview({
			persist: "location",
			collapsed: false,
			unique: false
		});
	});
	</script>
	
	</head>
	<body>
	<form:form commandName="form" action="update.do">
	<form:hidden path="id"/>
	    <table>
	        <tr>
	            <td><spring:message code="common.user.label.roles.name"/>：</td>
	            <td><form:input path="name"/>
	        </tr>
	        <tr>
	            <td><spring:message code="common.user.label.roles.desc"/>：</td>
	            <td><form:textarea path="description"/></td>
	        </tr>
	        <tr>
				<td><spring:message code="common.user.label.roles.rightS"/>：</td>
				<td>
					<ul id="navigation">
					${cms_sys_right_tree }
					</ul>
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
