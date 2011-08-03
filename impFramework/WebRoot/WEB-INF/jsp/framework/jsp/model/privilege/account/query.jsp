<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_form">
	<html>
	<head>
	<meta name="location" content="账户管理"/>
	</head>
	<body>
		<a href="${_path }/framework/model/privilege/account/create.do">创建新用户</a>
		<form:form commandName="queryForm" method="GET">
			<form:hidden path="pageSize"/>
			<form:hidden path="pageNum"/>
		</form:form>
		<div class="form_list">
			<table width="100%" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<th><spring:message code="common.user.label.name"/></th>
						<th><spring:message code="common.um.account.label.operator"/></th>
						<th><spring:message code="common.um.account.status"/></th>
						<th>角色</th>
						<th><spring:message code="common.label.operate"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${pageResult.resultList}" varStatus="status">
						<tr>
							<td>
								<a href="${_path }/framework/model/privilege/account/view.do?id=${item.id}">${item.name}</a>
							</td>
							<td>${item.operator}</td>
							<td>${item.status.desc}</td>
							<td>${item.role.name }</td>
							<td class="text_center">
								<a href="${_path }/framework/model/privilege/account/update.do?id=${item.id}"><spring:message code="common.label.operate.update"/></a>
								<a href="${_path }/framework/model/privilege/account/delete.do?id=${item.id}"><spring:message code="common.label.operate.remove"/></a>
								<a href="${_path }/framework/model/privilege/account/resetPassword.do?id=${item.id}"><spring:message code="common.user.label.password.reset"/></a>
								<!-- <span onclick="resetPsw(${item.id})"></span> -->
							</td>
						</tr>
					</c:forEach>
				</tbody>				
			</table>			
		</div>
		<ahli:pageControl form="queryForm"/>
	</body>
	</html>
</page:applyDecorator>
