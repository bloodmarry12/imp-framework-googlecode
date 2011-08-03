<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_form">
<html>
     <head>
       <title>测试文件上传FTP</title>
     </head>
     <body>
       <form:form method="post" enctype="multipart/form-data">
       		<input type="file" name="file1"/>
       		<input type="submit"/>"
       </form:form>
     </body>
</html>
</page:applyDecorator>
