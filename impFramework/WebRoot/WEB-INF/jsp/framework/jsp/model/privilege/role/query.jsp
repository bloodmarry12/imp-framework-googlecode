<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_form">
	<html>
	<head>
	<meta name="location" content="角色管理"/>
	<script type="text/javascript">
	//删除角色
	function delRole(id){
		FK.confirm('确认要删除角色吗？', function(){
			location.href="${_path }/framework/model/privilege/role/delete.do?id=" + id;
		});
	};
	</script>
	</head>
	<body>
		<a href = "${_path }/framework/model/privilege/role/create.do">创建角色</a>
		<form:form commandName="queryForm" method="GET">
			<form:hidden path="pageSize"/>
			<form:hidden path="pageNum"/>
		</form:form>
		<div class="form_list">
			<table width="100%" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<th width="20%"><spring:message code="common.user.label.roles.name"/></th>
						<th width="60%"><spring:message code="common.user.label.roles.desc"/></th>
						<th width="20%"><spring:message code="common.label.operate"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${pageResult.resultList}" varStatus="status">
						<tr>
							<td><a href="${_path }/framework/model/privilege/role/view.do?id=${item.id}">${item.name}</a></td>
							<td>${item.description}</td>
							<td class="text_center">
								<a href="${_path }/framework/model/privilege/role/update.do?id=${item.id}"><spring:message code="common.label.operate.update"/></a>
								<a onclick="delRole(${item.id})"><spring:message code="common.label.operate.remove"/></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<ahli:pageControl form="queryForm" styleClass="pagination"/>
	</body>
	</html>
</page:applyDecorator>
