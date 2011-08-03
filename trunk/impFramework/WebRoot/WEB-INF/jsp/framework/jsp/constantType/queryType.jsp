<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_form">
	<%
		final String path = request.getContextPath();
		request.setAttribute("_path", path);
	%>
	<html>
		<head>
			<meta name="location" content="常量管理" />

			<script type="text/javascript">
			
			function save(){
				var val = document.getElementById('modelid').value;
				if((/^[0-9]+(.[0-9]{0,2})?$/).test(val)){
					var f = window.document.constantTypeform;
					f.submit();
				}
				else{
					alert("请输入数字型！");
					return false;
				}
			}
			function removeConstantType(){
				var modelIds = document.getElementsByName("modelId");
				var modelIds2 = "";
				for( var i = 0; i < modelIds.length; i++ )
				{
					if ( modelIds[i].checked ){
						modelIds2 += modelIds[i].value+',';
					}
				}
				modelIds2 = modelIds2.substr(0,modelIds2.length-1);
				location.href = "${_path}/framework/constantType/remove.do?modelIds="+modelIds2;
			}
		</script>
		</head>
		<body>
			<form:form commandName="form" name="constantTypeform" method="GET" action="${_path}/framework/constantType/save.do">			
			<table>
				<tr>
					<td>MODELID:</td>
					<td><input type="text" id="modelid" name="modelid" /> </td>
					<td>MODELNAME:</td>
					<td><input type="text" id="modelName" name="modelname" /> </td>
					<td><input type="button" onclick="save();" value="保存"/> </td>
				</tr>
			</table>
			</form:form>
			<div class="form_list">
				<table width="100%" cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<th>
								&nbsp;
							</th>
							<th>
								常量类型值
							</th>
							<th>
								常量类型描述
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${list}" varStatus="status">
							<tr>
								<td>
									<input type="checkbox" name="modelId" value="${item.modelID}" />
								</td>
								<td>
									${item.modelID}
								</td>
								<td>
									${item.modelName}
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<hr />
			<div id="btn">
				<input type="button"
					value="<spring:message code="common.label.constant.delete"/>"
					onclick="removeConstantType();" class="btn" />
				<input type="button"
					value="<spring:message code="common.label.operate.goback"/>"
					onclick="history.back()" class="btn" />
			</div>
		</body>
	</html>
</page:applyDecorator>