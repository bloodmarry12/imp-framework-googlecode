<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_form">
	<html>
	<head>
		<meta name="location" content="<spring:message code="framework.model.cluster.label.clusters"/>"/>
		<script type="text/javascript">
		$(document).ready(function(){
			$('#bt_reload').click(function(){
				window.location = "${_path}/framework/model/clusters/list.do";
			});
			
			$("INPUT[name='btn_check']").click(function(){
				var jsonData = {'url':$(this).attr('param')};
				FK.get("${_path}/framework/model/clusters/check.do",jsonData, function(json){
						alert(json.message);
						window.location.reload();
					});
			});

			$("INPUT[name='btn_del']").click(function(){
				var jsonData = {'url':$(this).attr('param')};
				FK.get("${_path}/framework/model/clusters/delete.do",jsonData, function(json){
						alert(json.message);
						window.location.reload();
					});
			});

			$("INPUT[name='btn_flush']").click(function(){
				var str = '<spring:message code="framework.model.cluster.label.serverurl"/>：'+$(this).attr('param')+'<br/>';
					str += '<input type="hidden" id="flush_url" value="'+ $(this).attr('param') +'"/>';
					str += '<spring:message code="framework.model.cluster.label.key"/>：<input type="text" id="flush_key" style="width:250px"/>';
				FK.confirm(str,function(){
					var jsonData = {'url':$('#flush_url').val(),'key':$('#flush_key').val()};
					FK.get("${_path}/framework/model/clusters/flush.do",jsonData, function(json){
							alert(json.message);
							window.location.reload();
						});
				});
			});

			$("#btn_add").click(function(){
				var jsonData = {'url':$('#text_add').val()};
				FK.get("${_path}/framework/model/clusters/add.do",jsonData, function(json){
						alert(json.message);
						window.location.reload();
					});
			});
		});
		</script>
	</head>
	<body>
		<form method="GET" action="add.do">
			<spring:message code="framework.model.cluster.label.serverurl"/>:
			<input id="text_add" type="text" name="url"/>
			<input id="btn_add" type="button" class="btn" value="<spring:message code="framework.model.cluster.label.addserver"/>" />
		</form>
		<hr/>
		<input id="bt_reload" type="button" class="btn" value="<spring:message code="framework.model.cluster.label.flush"/>" />
		<hr/>
		<div class="form_list">
			<table width="100%" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<th width="35%"><spring:message code="framework.model.cluster.label.serverurl"/></th>
						<th width="15%"><spring:message code="framework.model.cluster.label.lastactivetime"/></th>
						<th width="15%"><spring:message code="framework.model.cluster.label.laststatus"/></th>
						<th width="35%"><spring:message code="framework.model.cluster.label.operate"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${list}" varStatus="status">
						<tr>
							<td>${item.url}</td>
							<td><fmt:formatDate value="${item.lastActiveTime}" pattern="yyyy-MM-dd HH:mm" /></td>
							<td align="center">
							<c:if test="${item.lastStatus == 1}">
								<img src="${_path }/framework/css/images/3d_icons_001.png" width="20px" height="20px"/>
							</c:if>
							<c:if test="${item.lastStatus == 0}">
								<img src="${_path }/framework/css/images/3d_icons_008.png" width="20px" height="20px"/>
							</c:if>
							</td>
							<td class="text_center">
								<input type="button" name="btn_check" param="${item.url}" value="<spring:message code="framework.model.cluster.label.check"/>" class="btn" />
								<input type="button" name="btn_flush" param="${item.url}" value="<spring:message code="framework.model.cluster.label.flush"/>" class="btn" />
								<input type="button" name="btn_del"   param="${item.url}" value="<spring:message code="framework.model.cluster.label.del"/>" class="btn" />
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</body>
	</html>
</page:applyDecorator>