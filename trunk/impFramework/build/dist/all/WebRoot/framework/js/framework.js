/*
 *	=============================================================
 *	= FK公用Javascript组件
 *	= ahli
 *	=
 * 	=
 *  =============================================================
 */
var FK = {
	/*
	 * 常量
	 */
	LOADING_TIME : 1000,
	/*
	 * cms弹出消息框
	 * 包装了jquery.ui的dialog。
	 * param : message 显示的消息内容
	 * func : 点击OK按钮后的操作函数
	 * 例子:
	 *		function doSomeOperate(message){
	 *			FK.dialog(message);
	 *  	}
	 */
	dialog : function(message, func){
		if($('#cms_sys_dialog2').length == 0){
			$('body').append('<div id="cms_sys_dialog2" title="System Message" ><p></p></div>');
			$('#cms_sys_dialog2').dialog({
				autoOpen: false,
				modal: true ,
				width: 400,
				height: 200,
				buttons: {
					"OK": function() { 
						$(this).dialog("close");
						if(func){
							func.call();
						}
					}
				}
			})
		}
		$('#cms_sys_dialog2 P').html(message);
		$('#cms_sys_dialog2').dialog("open");
	},
	/*
	 * cms弹出确认框
	 * 包装了jquery.ui的dialog。
	 * param : message 显示的消息内容
	 * func0 : 点击OK按钮后的操作
	 * func1 : 点击CANCEL按钮后的操作
	 * 例子:
	 *		function doSomeOperate(message){
	 *			FK.confirm(message, function(){
	 *				alert('OK');
	 *			},function(){alert('CANCEL')});
	 *  	}
	 */
	confirm : function(message, func0, func1){
		if($('#cms_sys_confirm').length == 0){
			$('body').append('<div id="cms_sys_confirm" title="System Message" ><p></p></div>');
			$('#cms_sys_confirm').dialog({
				autoOpen: false,
				modal: true ,
				width: 400,
				height: 200,
				buttons: {
					"CANCEL": function(){
						$(this).dialog("close");
						if(func1){
							func1.call();
						}
					},
					"OK": function() { 
						$(this).dialog("close"); 
						if(func0){
							func0.call();
						}
					}
				}
			})
		}
		$('#cms_sys_confirm P').html(message);
		$('#cms_sys_confirm').dialog("open");
	},
	
	/*
	 * cms通过POST方式与后台交互并且获取返回结果方法。
	 * 包装了jquery的ajax
	 * url : 地址
	 * jsonData : json类型参数
	 * func : 操作成功的时候调用的函数
	 *
	 */	
	post : function(url, jsonData, func){
		var dd = {json:{name:'liah'}}
		$.ajax({
		    url: url,
		    cache: false,//不使用缓存
		    type: 'POST',
		    dataType: 'json',
		    //timeout: 1000,
		    data : jsonData,
		    error: function(){
		    	FK.confirm('Error loading JSON document');
		    },
		    success: function(json){
		    	if(1 != json.result){
		    		FK.dialog(json.exception + "</br>" + json.exceptionMessage);
		    	}else{
			    	if(func){
			    		func(json);
			    	}
			    }
		    }
		});
	},
	post2 : function(url, jsonData, func){
		$.ajax({
		    url: url,
		    cache: false,//不使用缓存
		    type: 'POST',
		    dataType: 'json',
		    //timeout: 1000,
		    data : jsonData,
		    error: function(){
		    	FK.confirm('Error loading JSON document');
		    },
		    success: function(json){
		    	if('0' == json.resCode){
		    		FK.dialog(json.exception);
		    	}else if('1' == json.resCode){
			    	if(func){
			    		func(json.resContent);
			    	}
			    }
		    }
		});
	},
	/*
	 * cms通过GET方式与后台交互并且获取返回结果方法。
	 * 包装了jquery的ajax
	 * url : 地址
	 * jsonData : json类型参数
	 * func : 操作成功的时候调用的函数
	 *
	 */	
	get : function(url, jsonData, func){
		$.ajax({
		    url: url,
		    cache: false,//不使用缓存
		    type: 'GET',
		    dataType: 'json',
		    data : jsonData,
		    //timeout: 1000,
		    error: function(){
		        FK.confirm('Error loading JSON document');
		    },
		    success: function(json){
		    	if(1 != json.result){
		    		FK.dialog(json.message + "</br>" + json.exception);
		    		return ;
		    	}else{
		    		if(func){
			        	func(json);
			        }
		    	}
		    }
		});
	},
	showLoading : function(){
	/*
		if(parent){
			parent.$('#loading').show(FK.LOADING_TIME);
			parent.$('#mainIFrame').hide(FK.LOADING_TIME);
		}else{
			$('#loading').show(FK.LOADING_TIME);
			$('#mainIFrame').hide(FK.LOADING_TIME);
		}*/
	},
	hideLoading : function(){
	/*
		if(parent){
			parent.$('#loading').hide(FK.LOADING_TIME);
			parent.$('#mainIFrame').show(FK.LOADING_TIME);
		}else{
			$('#loading').hide(FK.LOADING_TIME);
			$('#mainIFrame').show(FK.LOADING_TIME);
		}*/
	},
	historyBack : function(){
		FK.showLoading();
		history.back();
	},
	loadLoginStatus : function(){
		var data = "000000";
		return data;
	}
}

jQuery.fn.ListCheckBox = function(name){
	
	//
	var $mchcekbox = this;
	
	//单击事件，如果点击则全部选中，否则全部取消
	this.click(function(){
		if(this.checked == true){
			$('INPUT[type=checkbox][name='+name+']').each(function(){
				this.checked =true;
			});
		}else{
			$('INPUT[type=checkbox][name='+name+']').each(function(){
				this.checked =false;
			});
		}
	});

	//遍历所有的节点，并且增加事件，取消权限多选框
	$('INPUT[type=checkbox][name='+name+']').each(function(){
		$(this).click(function(){
			if(this.checked == false){
				$mchcekbox.attr("checked", false);
			}
		});
	});

	function getValue(){
		var val = '';
		$('INPUT[type=checkbox][name='+name+']').each(function(){
			if(this.checked == true){
				val += $(this).attr("value") + ',';
			}
		});
		return val;
	};
}