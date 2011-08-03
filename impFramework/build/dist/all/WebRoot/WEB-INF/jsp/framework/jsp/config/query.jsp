<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_form">
	<html>
	<head>
		<meta name="location" content="系统配置"/>
	</head>
	<body>
		<div class="form_list">
			<table width="100%" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<th width="30%"><spring:message code="common.sysConfig.name"/></th>
						<th width="10%"><spring:message code="common.sysConfig.value"/></th>
						<th width="40%"><spring:message code="common.sysConfig.description"/></th>
						<!-- 
						<th width="20%"><spring:message code="common.sysConfig.key"/></th>
						 -->
						<th width="20%"><spring:message code="common.label.operate"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${list}" varStatus="status">
						<tr>
							<td>
								<a href="${_path }/framework/config/view.do?keyID=${item.key }">
									${item.name}
								</a>
							</td>
							<td>${item.value}</td>
							<td>${item.description}</td>
							<!-- 
							<td>${item.key}</td>
							 -->
							<td class="text_center">
								<input type="button" value="<spring:message code="common.label.operate.update"/>" class="btn" onclick="location.href='${_path }/framework/config/update.do?keyID=${item.key }'" />
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<hr/>
		<div id="btn">
		    <!-- input type="button" value="新增" onclick="location.href='${_path }/framework/config/add.do'" class="btn" / -->
			<input type="button" value="<spring:message code="common.label.operate.goback"/>" onclick="history.back()" class="btn" />
		</div>
	</body>
	</html>
</page:applyDecorator>