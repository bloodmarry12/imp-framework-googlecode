<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_form">
	<html>
	<head>
		<meta name="location" content="日志级别设置"/>
	</head>
	<body>
		<P>
			<strong>系统日志</strong>
		</P>
		<p>		
			通过页面配置，动态修改系统日志级别。在不重起系统的情况下，动态变更日志界别，能够在系统出现异常、
			业务出现问题的时候，及时修改日志级别，收集DEBUG级别日志，在不影响生产环境的情况下，分析业务数据。
		</P>
		<form:form commandName="form">
			<div class="form_list">
				<table width="100%" cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<th width="20%">日志设置对象</th>
							<th width="60%">日志设置说明</th>
							<th width="20%">日志设置</th>
						</tr>
					</thead>
				
					<tbody>
						<tr>
							<td>基础日志级别</td>
							<td>系统基础日志级别设置。对应log4j的root节点</td>
							<td>
								<form:select path="rootLevel">
									<form:options items="${rootLevels }" itemValue="key" itemLabel="value"/>
								</form:select>
							</td>
						</tr>
						<tr>
							<td>框架日志级别</td>
							<td>系统框架内日志级别设置。对应log4j中的ahli.framework节点</td>
							<td>
								<form:select path="fkLevel">
									<form:options items="${rootLevels }" itemValue="key" itemLabel="value"/>
								</form:select>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<hr/>
			
			<div id="btn">				
				<input type="submit" value="提交" class="btn"/>
				<input type="reset"  value="重置" class="btn"/>
			</div>
		</form:form>
	</body>
	</html>
</page:applyDecorator>
