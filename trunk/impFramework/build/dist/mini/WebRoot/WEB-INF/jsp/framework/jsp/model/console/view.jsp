<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/framework/base/include.jsp"%>
<page:applyDecorator name="_framework_form">
	<html>
	<head>
		<meta name="location" content="状态监控"/>
		<script type="text/javascript" src="${_path}/framework/js/swfobject.js"></script>
		<script type="text/javascript" src="${_path}/framework/js/json/json2.js"></script>
		<script type="text/javascript">
		var tmp ;
		var randNum = 25;
		var flag = false;
		var _tmp_data = null;
		function newArray(size){
			var _defaultValue1 = new Array();
			//初始化数组
			for(var i = 0; i < 60; i++){
				_defaultValue1.push(0);
			}
			return _defaultValue1;
		}
		
		var defaultValue0 = new newArray(50);
		var defaultValue1 = new newArray(50);
		
		function ofc_ready()
		{
			//alert('ofc_ready');
			//tmp = findSWF("c3p0");
			//tmp.load( JSON.stringify(data) );
		}

		// 初始化报表
		function open_flash_chart_data()
		{
			return JSON.stringify(data);
		}

		function reloadData(){
			FK.get('${_path }/framework/model/console/status.do',{},
				function(json){
					reload(json);
			});
		}
		
		// 重新装载数据
		function reload(json)
		{		
		  tmp = findSWF("c3p0");
		
		  var lastArray1 = data.elements[1].values;
		  _tmp_data = lastArray1.shift();
		  _tmp_data = null;
		  lastArray1.push(json.c3p0.num_idle_connections);

		  var lastArray0 = data.elements[0].values;
		  _tmp_data = lastArray0.shift();
		  _tmp_data = null;
		  lastArray0.push(json.c3p0.num_busy_connections);
		  /* */
		  x = tmp.load( JSON.stringify(data) );

		  // javascript对象指向null
		  lastArray1 = null;
		  lastArray0 = null;
		  tmp = null;
		  x = null;
		  json = null;
		  CollectGarbage();
		}

		function findSWF(movieName) {
		  if (navigator.appName.indexOf("Microsoft")!= -1) {
		    return window[movieName];
		  } else {
		    return document[movieName];
		  }
		}

		var data = {

				  "title":{
				    "text":"C3P0连接池使用状况",
				    "style":"{color: #666666; font-size: 15; font-weight: bold;}"
				  },

				  "elements":[
				    {
				      "type":      "line",
				      "colour":    "#cc0000",
				      "width":     2,
				      "values" :   defaultValue0
				    },
				    {
					      "type":      "line",
					      "colour":    "#00cc33",
					      "width":     2,
					      "values" :   defaultValue1
					    }
				  ],

				  "y_axis":{
				    "max":   100,
				    "colour":       "#666666",
				    "grid-colour":  "#F7E8BF"
				  },

				  "x_axis":{
				    "steps": 2,
				    "colour":       "#666666",

				    "labels":{
				      "visible": true,
				      "rotate": "vertical",
				      "steps":  2
				    }
				  },

				  "bg_colour":  "#ffffff"
				};
		</script>
	</head>
	<body>
	<script type="text/javascript">
		swfobject.embedSWF("${_path}/framework/open-flash-chart.swf", "c3p0", "450", "150", "9.0.0", "expressInstall.swf");
	</script>
	<div id="c3p0"></div>
	<hr/>
	<script type="text/javascript">
	window.setInterval(function(){reloadData()},1000);
	</script>
	</body>
	</html>
</page:applyDecorator>
