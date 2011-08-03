<%@ page import="net.sf.json.JSONObject" %>
<%@ page import="com.huawei.imp.framework.common.web.controller.BaseAjaxJsonController" %>
<%@ page import="com.huawei.imp.framework.jee.JEEConstant" %>
<%@ page import="com.huawei.imp.framework.exception.FrameworkBusinessException" %>
<%
JEEConstant.ControllerType
type = (JEEConstant.ControllerType)request.getAttribute(JEEConstant.REQ_ATTR_CONTROLLER_TYPE);
java.lang.Throwable exception = (java.lang.Throwable)request.getAttribute("exception");

if(null != type){
	JSONObject json = new JSONObject();
	json.put(BaseAjaxJsonController.JSON_RESULT, BaseAjaxJsonController.RESULT_CODE_FAIL);
	json.put(BaseAjaxJsonController.JSON_MESSAGE, exception.getMessage());
	json.put("exception",exception.toString());
	request.setAttribute(JEEConstant.FORM, json);
	RequestDispatcher requestDispatcher=request.getRequestDispatcher("/WEB-INF/jsp/framework/jsp/json.jsp");
	response.setContentType("text/json;charset=utf-8");
	requestDispatcher.forward(request,response);
}else if(exception instanceof FrameworkBusinessException){
	RequestDispatcher requestDispatcher=request.getRequestDispatcher("/WEB-INF/jsp/framework/exception/businessException.jsp");
	requestDispatcher.forward(request,response);
}else{
	RequestDispatcher requestDispatcher=request.getRequestDispatcher("/WEB-INF/jsp/framework/exception/exception.jsp");
	requestDispatcher.forward(request,response);
}
%>