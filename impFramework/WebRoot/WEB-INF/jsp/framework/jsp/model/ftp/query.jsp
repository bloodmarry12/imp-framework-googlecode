<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_form">
	<html>
	<head>
		<meta name="location" content="FTP配置管理"/>
		<script type="text/javascript">
		$(document).ready(function(){
			FK.post2('${_path }/framework/model/ftp/ajax/query.do', {}, function(content){
				//alert(content);
				// 清空
				$('#form_list tbody').empty();
				// 判断ftpList对象是否存在
				if(null != content.ftpList ){
					var ftpList = content.ftpList;
					for(var i = 0; i < ftpList.length; i++){
						var ftpConfig = ftpList[i];
						var trStr = '<tr>';
						trStr += '<td><a href="${_path }/framework/model/ftp/view.do?name='+ftpConfig.ftpAlias+'">'+ftpConfig.ftpAlias+'</a></td>';
						trStr += '<td>'+ftpConfig.ip+'</td>';
						trStr += '<td>'+ftpConfig.port+'</td>';
						trStr += '<td>'+ftpConfig.userName+'</td>';
						trStr += '<td>'+ftpConfig.paswd+'</td>';
						trStr += '<td class="text_center"><input type="button" value="<spring:message code="common.label.operate.update"/>" class="btn" onclick="window.location=\'update.do?id='+ftpConfig.ftpAlias+'\'"/></td>';
						trStr += '</tr>';
						$('#form_list tbody').append(trStr);
					}
				}
			})
		});
		</script>
	</head>
	<body>
		<div id="form_list" class="form_list">
			<table width="100%" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<th width="20%"><spring:message code="common.ftp.label.ftpAlias"/></th>
						<th width="15%"><spring:message code="common.ftp.label.ip"/></th>
						<th width="10%"><spring:message code="common.ftp.label.port"/></th>
						<th width="15%"><spring:message code="common.ftp.label.user"/></th>
						<th width="15%"><spring:message code="common.ftp.label.psw"/></th>
						<th width="25%"><spring:message code="common.label.operate"/></th>
					</tr>
				</thead>
				<tbody/>
			</table>
		</div>
	</body>
	</html>
</page:applyDecorator>