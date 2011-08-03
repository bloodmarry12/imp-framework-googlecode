<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_form">
	<html>
	<head>
	<link rel="stylesheet" type="text/css" href="${_path }/framework/css/jquery.treeview.css"/>
	<meta name="location" content="角色管理-查看角色信息"/>
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
    <table>
        <tr>
            <td><spring:message code="common.user.label.roles.name"/>：</td>
            <td>${form.name }</td>
        </tr>
        <tr>
            <td><spring:message code="common.user.label.roles.desc"/>：</td>
            <td>${form.description }</td>
        </tr>
        <tr>
            <td><spring:message code="common.user.label.roles.rightS"/>：</td>
            <td>
				<ul id="navigation">
					${cms_sys_right_tree }
				</ul>
            </td>
        </tr>        
        </table>
        <hr/>
        <div id="btn">
	    	<input type="button" value="<spring:message code="common.label.operate.goback"/>" onclick="history.back()" class="btn"/>
	    </div>
	</body>
	</html>
</page:applyDecorator>
