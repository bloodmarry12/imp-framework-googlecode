<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_form">
<html>
<head>
<meta name="location" content="定时任务管理"/>
<script type="text/javascript">
//启动定时任务
function startTask(id){
	FK.confirm('<spring:message code="common.timetask.trueToRun"></spring:message>', function(){
		FK.get("${_path }/framework/model/schedule/start.do",{jobID:id},
			function(json){
				FK.dialog(json.message, function(){
					location.reload();
				});
			}
		);
	});
}
//暂停定时任务
function pauseTask(id){
	FK.confirm('<spring:message code="common.timetask.trueToStop"/>', function(){
		FK.get("${_path }/framework/model/schedule/pause.do",{jobID:id},
			function(json){
				FK.dialog(json.message, function(){
					location.reload();
				});
			}
		);
	});
}
</script>
</head>
<body>
	<div class="form_list">
		<table width="100%" cellspacing="0" cellpadding="0">
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td colspan="4"></td>
			</tr>
		</table>
		<table width="100%" cellspacing="0" cellpadding="0">
			<thead>
				<tr>
					<th width="20%"><spring:message code="common.timetask.taskName"/></th>
					<th width="20%"><spring:message code="common.timetask.description"/></th>
					<th width="20%"><spring:message code="common.timetask.ecxp"/></th>
					<th width="10%"><spring:message code="common.timetask.concurrent"/></th>
					<th width="10%"><spring:message code="common.timetask.status"/></th>
					<th width="20%"><spring:message code="common.label.operate"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${list}" varStatus="status">
					<tr>
						<td>
							<a href="${_path }/framework/model/schedule/view.do?jobID=${item.id }">
								${item.taskName}
							</a>
						</td>
						<td>${item.description}</td>
						<td>${item.cexp}</td>
						<td>
							<c:if test="${0 == item.concurrent}">
								<spring:message code="common.timetask.concurrent0"/>
							</c:if>
							<c:if test="${1 == item.concurrent }">
								<spring:message code="common.timetask.concurrent1"/>
							</c:if>		
						</td>
						<td>
							<c:if test="${0 == item.status}">
								<spring:message code="common.timetask.status0"/>
							</c:if>
							<c:if test="${1 == item.status }">
								<spring:message code="common.timetask.status1"/>
							</c:if>
							<c:if test="${2 == item.status }">
								<spring:message code="common.timetask.status2"/>
							</c:if>
						</td>
						<td class="text_center">
							<c:if test="${1 == item.status || 2 == item.status}">
								<input type="button" value="<spring:message code="common.timetask.status.start"/>" onclick="startTask('${item.id }')" class="btn" />&nbsp;
								<input type="button" value="<spring:message code="common.label.operate.update"/>" onclick="location.href='${_path }/framework/model/schedule/update.do?jobID=${item.id }'" class="btn" />
							</c:if>
							<c:if test="${0 == item.status}">
								<input type="button" value="<spring:message code="common.timetask.status.stop"/>" onclick="pauseTask(${item.id })" class="btn" />
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
</page:applyDecorator>