<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.huawei.imp.framework.utils.BeanHolder"%>
<%@page import="com.huawei.imp.framework.jee.webserver.Environment"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_frame">
	<html>
	<head>
	</head>
	<body>
	<div id="contentwrapper">
    	<div id="contentcolumn">
	        <div id="iframeP">
	        	<%
	        	Environment env = (Environment)BeanHolder.getBean("environment");
	        	if(null != env){
	        		out.write(env.getLocalWebURL());
	        	}else{
	        		out.write("加载环境实例失败。");
	        	}
	        	%>
				<div id="loading" style="display:none;">
					Loading content, please wait..
					<img src="${_path }/css/images/ajax-loader.gif" alt="loading.." />
				</div>
				<iframe id="mainIFrame" 
						src="${_path }/framework/welcome.do"
						name="mainIFrame" 
						width="100%" 
						height="635"
						scrolling="auto"
						frameborder="0"
						onload="$('#iframeP').height(635);"
						></iframe>
			</div>
		</div>
    </div>
    <div id="leftcolumn">
        <h4>框架页面目录</h4>
        <ul id="navlist">			
			<li><a class="seagreen" href="${_path }/framework/welcome.do">Home</a></li>
			<li><hr/></li>
			<!--  -->
			<!-- <li><a class="seagreen"  target="mainIFrame" href="${_path }/framework/model/logger/level/edit.do">日志级别</a></li> -->
			<li><a class="rawsienna" target="mainIFrame" href="${_path }/framework/config/query.do">系统配置</a></li>
			<li><a class="lightblue" target="mainIFrame" href="${_path }/framework/model/schedule/query.do">定时任务</a></li>
			<!-- 
			<li><a class="beet"      target="mainIFrame" href="${_path }/framework/constant/query.do">常量管理</a></li>
			<li><a class="rawsienna" target="mainIFrame" href="${_path }/framework/model/script/flush.do">脚本刷新</a></li>
			 -->
			<li><a class="steelblue" target="mainIFrame" href="${_path }/framework/model/ftp/query.do">FTP配置</a></li>
			<li><a class="seagreen"  target="mainIFrame" href="${_path }/framework/model/clusters/list.do">集群管理</a></li>
			<li><a class="steelblue" target="mainIFrame" href="${_path }/framework/console/script/jscript.do">脚本执行</a></li>
			<li><a class="lightblue" target="mainIFrame" href="${_path }/framework/reload/update.do">重新加载Bean</a></li>
			<!-- 
			<li><a class="rawsienna" target="mainIFrame" href="${_path }/framework/model/console/ftp.do">FTP监控</a></li>
			<li><a class="rawsienna" target="mainIFrame" href="${_path }/framework/model/console/view.do">状态监控</a></li>
			 -->
			<!--  -->
			<li><hr/></li>					
			<li><a class="seagreen" target="mainIFrame" href="${_path }/framework/model/privilege/right/tree.do">权限管理</a></li>
			<li><a class="rawsienna" target="mainIFrame" href="${_path }/framework/model/privilege/account/query.do">账户管理</a></li>
			<li><a class="lightblue" target="mainIFrame" href="${_path }/framework/model/privilege/role/query.do">角色管理</a></li>
			<li><a class="beet" target="mainIFrame" href="${_path }/framework/model/privilege/explorer/tree.do">菜单管理</a></li>
			<li><hr/></li>	
			<li><a class="seagreen" href="#" id="btn_logout">退出</a></li>
        </ul>
        <div class="greybox"></div>
    </div>
	</body>
	</html>
</page:applyDecorator>