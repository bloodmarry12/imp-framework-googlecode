<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_form">
	<html>
	<head>
	<meta name="location" content="刷新页面"/>
	<script type="text/javascript">
		$(document).ready(function(){
			$('#btn_flush').click(function(){
				FK.post2('${_path }/framework/model/script/flush.do', {}, function(content){
					alert(content.message);
				});
			});
		});
		</script>
	</head>
	<body>
	<table class="table_form">
       <input id="btn_flush" type="button" value="flush"></input>
    </table>
	</body>
	</html>
</page:applyDecorator>
