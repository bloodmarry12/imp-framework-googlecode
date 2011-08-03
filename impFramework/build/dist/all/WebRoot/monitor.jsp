<html>
<head>
<title>Threads in tykj</title>
<style>
body {font-size:8pt;}
ol {line-height:18px;}
</style>
</head>
<body>
<strong>java.io.tmpdir:</strong>
<ul>
<li><%=System.getProperty("java.io.tmpdir")%></li>
</ul>
<br/>

<strong>Memory:</strong>
<ol>
<li>freeMemory=<%=Runtime.getRuntime().freeMemory()/(1024*1024)%>M</li>
    <li>totalMemory=<%=Runtime.getRuntime().totalMemory()/(1024*1024)%>M</li>
    <li>maxMemory=<%=Runtime.getRuntime().maxMemory()/(1024*1024)%>M</li>
</ol>
<br/>
<strong>Thread:</strong>
<ol>
<%for(Thread t : list_threads())
{%>
<li><%=t.getName()%>(<b><%=t.getState()%></b>) : <%=t.getClass().getName()%></li>

<%}%>

<%
java.util.Map<Thread,StackTraceElement[]> as = Thread.getAllStackTraces();
java.util.Set<java.util.Map.Entry<Thread,StackTraceElement[]>> set = as.entrySet();
for (java.util.Map.Entry<Thread, StackTraceElement[]> ent : set) {
	Thread t = ent.getKey();
	StackTraceElement[]  ts = ent.getValue();
	out.println("<hr/>");
	out.println("ThreadName="+t.getName());
	for (int i = 0; i < ts.length; i++) {
		out.println("Class:"+ts[i].getClassName());
		out.println("Method:"+ts[i].getMethodName());
		out.println("Line:"+ts[i].getLineNumber()+"<br/>");
	}
}
%>
</ol>
<%!
public static java.util.List<Thread> list_threads(){
    int tc = Thread.activeCount();
    Thread[] ts = new Thread[tc];
    Thread.enumerate(ts);
    return java.util.Arrays.asList(ts);
}
%>
</body>
</html>