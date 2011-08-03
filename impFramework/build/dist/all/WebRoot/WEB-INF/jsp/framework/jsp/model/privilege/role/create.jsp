<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_form">
	<html>
	<head>
	<link rel="stylesheet" type="text/css" href="${_path }/framework/css/jquery.treeview.css"/>
	<meta name="location" content="角色管理-创建角色"/>
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
	<form:form commandName="form">
			<table class="table_form">
				<tr>
					<td class="label"><spring:message code="common.user.label.roles.name"/>:</td>
					<td class="field"><form:input path="name" maxlength="20"/></td>
					<td class="status"></td>
				</tr>
				<tr>
					<td class="label"><spring:message code="common.user.label.roles.desc"/>:</td>
					<td class="field"><form:textarea path="description" /></td>
					<td class="status"></td>
				</tr>
				
				<tr>
					<td class="label"><spring:message code="common.user.label.roles.rightS"/>:</td>
					<td class="field">
						<ul id="navigation">
						${cms_sys_right_tree }
						</ul>
					</td>
					<td class="status" id="checkboxStatus"></td>
				</tr>
				<tr>
					<td colspan="3">
						<input type="submit" value="<spring:message code="common.label.operate.submit"/>"/>
		                <input type="reset" value="<spring:message code="common.label.operate.reset"/>"/>
		                <input type="button" value="<spring:message code="common.label.operate.goback"/>" onclick="CMS4.historyBack()"/>
					</td>
				</tr>
			</table>
		</form:form>
	</body>
	</html>
</page:applyDecorator>
