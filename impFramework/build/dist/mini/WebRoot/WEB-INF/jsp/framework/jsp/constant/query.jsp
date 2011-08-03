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
						
			function addConstantType(){
				location.href = "${_path}/framework/constantType/query.do";
			}
			
			var i = 1;
			function addConstantValue(){				
				if(i%2 != 0)
				{
					document.getElementById("saveDiv").style.display="inline";
				}else{
					document.getElementById("saveDiv").style.display="none";
				}
				i++;	
			}
			
			function save(){
				var f = window.document.saveForm;
				f.submit();
			}
			function removeConstant(){
				var ids = document.getElementsByName("constantId");
				var ids2 = "";
				for( var i = 0; i < ids.length; i++ )
				{
					if ( ids[i].checked ){
						ids2 += ids[i].value+',';
					}
				}
				ids2 = ids2.substr(0,ids2.length-1);
				location.href = "${_path}/framework/constant/remove.do?ids="+ids2;
			}
		</script>
		</head>
		<body>
			<form:form commandName="queryForm" method="GET">
			MODELID:
			<form:select path="modelID">
					<c:forEach var="item" items="${modelIDs}" varStatus="status">
						<form:option value="${item.modelID }">${item.modelName }</form:option>
					</c:forEach>
				</form:select>
				<input type="submit" class="btn" />
				<p />
			</form:form>
			<div class="form_list">
				<table width="100%" cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<th>
								&nbsp;
							</th>
							<th>
								常量值
							</th>
							<th>
								常量描述
							</th>
							<th>
								显示序号
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${list}" varStatus="status">
							<tr>
								<td>
									<input type="checkbox" name="constantId" value="${item.id}" />
								</td>
								<td>
									${item.value}
								</td>
								<td>
									${item.name}
								</td>
								<td>
									${item.order}
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div id="saveDiv" style="display: none">
				<form:form name="saveForm" commandName="form" method="GET"
					action="${_path}/framework/constant/save.do">
					<table>
						<tr>
							<td>
								MODEL:
							</td>
							<td>
								<select name="modelID">
									<c:forEach var="item" items="${modelIDs}" varStatus="status">
										<option value="${item.modelID }">
											${item.modelName }
										</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<spring:message code="common.label.constant.name" />
								:
							</td>
							<td>
								<input name="name" />
							</td>
							<td>
								<spring:message code="common.label.constant.value" />
							</td>
							<td>
								<input name="value" />
							</td>
							<td>
								<spring:message code="common.label.constant.seq" />
							</td>
							<td>
								<input name="order" />
							</td>
							<td>
								<input type="button" onclick="save();" class="btn" value="保存" />
							</td>
						</tr>
					</table>
				</form:form>
			</div>
			<hr />
			<div id="btn">
				<input type="button"
					value="<spring:message code="common.label.operate.addType"/>"
					onclick="addConstantType();" class="btn" />
				<input type="button"
					value="<spring:message code="common.label.operate.addValue"/>"
					onclick="addConstantValue();" class="btn" />
				<input type="button"
					value="<spring:message code="common.label.constant.delete"/>"
					onclick="removeConstant();" class="btn" />
				<input type="button"
					value="<spring:message code="common.label.operate.goback"/>"
					onclick="history.back()" class="btn" />
			</div>
		</body>
	</html>
</page:applyDecorator>