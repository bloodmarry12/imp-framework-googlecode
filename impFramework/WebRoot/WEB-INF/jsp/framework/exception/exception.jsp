<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.huawei.imp.framework.exception.BusinessException" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isErrorPage="true"%>
<%String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>CMS Exception Page</title>
	<meta http-equiv="pragma" content="no-cache" ></meta>
	<meta http-equiv="cache-control" content="no-cache" ></meta>
	<meta http-equiv="expires" content="0" ></meta>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" ></meta>
	<meta http-equiv="description" content="CMS Exception Page" ></meta>
	<script type="text/javascript" src="<%=path %>/js/jquery-1.3.2.min.js"/>
	<script type="text/javascript" src="<%=path %>/js/cms.base.js"></script>
	<link href="<%=path %>/framework/css/style.css" type="text/css" rel="stylesheet" />
  <script type="text/javascript">
  $(document).ready(function(){
	//服务器打印消息信息
	CMS4.hideLoading();
  });
  </script> 
  </head>
<body>
<h2>位置-<spring:message code="common.exception.page"/></h2>
<hr/>
<table>          
    <tr>
        <td class="text_right text_middle"><img src="<%=path %>/framework/css/images/ball_stop.png" width="48" height="48" /></td>
        <td><spring:message code="common.exception.type"/></td>
        <td>
        	<%
			if(exception instanceof BusinessException){
				%><spring:message code="common.exception.type.business"/><%
			}else{
				%><spring:message code="common.exception.type.uncatch"/><%
			}
			%>
        </td>
    </tr>
    <tr>   
    	<td></td>         	
    	<td><spring:message code="common.exception.info"/></td>
    	<td><%=exception.getMessage() %></td>
    </tr>
	<%if(!(exception instanceof BusinessException)){ %>
	<tr>
		<td></td>
		<td class="text_middle"><spring:message code="common.exception.stack"/></td>
		<td><%for(StackTraceElement st : exception.getStackTrace()){
		out.println(st + "</br>");
	} %></td>
	</tr>
	<%} %>
</table>
<hr/>
<div id="btn">
	<input type="button" value="<spring:message code="common.label.operate.goback"/>" class="btn" onclick="CMS4.historyBack();" />
</div>
</body>
</html>