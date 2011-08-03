<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_login">
	<html>
	<head>
	<title>框架后台登陆</title>
	</head>
	<body>
	
		<div id="stylized" class="myform">
		  <form:form commandName="form">
		    <h1>LOGIN 后台用户登录</h1>
		    <p>Content Management Platform Admin Login</p>
		    <label><spring:message code="common.user.label.name"></spring:message>：
		        <span class="small">Add your name</span>
		    </label>
		    <form:input path="name" />
		    
		    <label><spring:message code="common.user.label.password"></spring:message>：
		        <span class="small">Min. size 6 chars</span>
		    </label>
		    <form:password path="password" />
		    	        
		    <div class="error"></div>	
		    
		    <label></label>  
			<div id="errmessage">
				<form:errors path="*" cssClass="errorMessage" />
			</div>	   
		    
		    <button type="submit">登 录</button>
		    <div class="spacer"></div>	
		  </form:form>
		</div>
		
	<!--
	<form:form commandName="form" cssClass="loginBar">
		<fieldset><legend><span>Login</span></legend> <form:input
			path="name" /> 
		<button type="submit" class="loginbtn"><strong>Submit</strong><img
			src="img/bullet_tick.png" alt="Go" /></button>
		<button type="button" class="loginbtn">Register <img
			src="img/user_add.png" alt="Register" /></button>
		</fieldset>
	</form:form>
	-->
	</body>
	</html>
</page:applyDecorator>
