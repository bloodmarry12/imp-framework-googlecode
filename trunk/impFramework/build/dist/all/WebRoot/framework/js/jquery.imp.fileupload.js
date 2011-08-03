/*
 * IMP基于iframe的异步文件上传js。
 * 作者:ahli
 */
jQuery.fn.extend({
	//构造Ajax上传组建
	AjaxFileUploader_Init: function() {
	
		var $ajaxFileUploader = this;    // 当前对象
		
		var _uuid = $(this).attr('id');
		
		var _processStatus = true;
		
		// 初始化附件提交页面iframe
		initIframe();
		
		initContent();
		
		$ajaxFileUploader.children('A[name=delete]').click(function() {
			alert(cms_sys_basePath);
			$.ajax( {
				url : cms_sys_basePath + "/fileUpload/del",
				cache : false,//不使用缓存
				type : 'GET',
				dataType : 'json',
				data : {
					'fileFullName' : $ajaxFileUploader.children('.upload_hidden').val()
				},
				//timeout: 1000,
				error : function() {
					$ajaxFileUploader.children('.upload_hidden').val('');
					$ajaxFileUploader.children('.upload_word').children('.upload_title').html('');
					$ajaxFileUploader.children('A[name=upload]').show();
					$ajaxFileUploader.children('A[name=delete]').hide();
				},
				success : function(json) {
					$ajaxFileUploader.children('.upload_hidden').val('');
					$ajaxFileUploader.children('.upload_word').children('.upload_title').html('');
					$ajaxFileUploader.children('A[name=upload]').show();
					$ajaxFileUploader.children('A[name=delete]').hide();
				}
			});
			/**
			$ajaxFileUploader.children('.upload_hidden').val('');
			$ajaxFileUploader.children('.upload_word').children('.upload_title').html('');
			$ajaxFileUploader.children('A[name=upload]').show();
			$ajaxFileUploader.children('A[name=delete]').hide();
			*/
		});
	
		$ajaxFileUploader.children('A[name=stop]').click(function() {
			$.ajax( {
				url : cms_sys_basePath + "/fileUpload/cancel",
				cache : false,//不使用缓存
				type : 'GET',
				dataType : 'json',
				data : {
					'uuid' : _uuid
				},
				//timeout: 1000,
				error : function() {
					$ajaxFileUploader.children('DIV[name=stats]').html('Error loading JSON document');
				},
				success : function(json) {
					$ajaxFileUploader.children('A[name=upload]').show();
					$ajaxFileUploader.children('A[name=delete]').hide();
					$ajaxFileUploader.children('A[name=stop]').hide();
					$ajaxFileUploader.children('.upload_word').children('.upload_title').html('');
  					$ajaxFileUploader.children('.upload_hidden').val('');
					//setProcess('0');
					//alert(eval('window.timer_' + _uuid));
					eval('window.clearInterval(window.timer_' + _uuid + ')');
					
					$ajaxFileUploader.children('.upload_progress').hide();
				}
			});
  		// 设置返回信息
  		// 设置功能按钮状态
		});
	
		$ajaxFileUploader.children('A[name=upload]').click(function() {
			var form = $ajaxFileUploader.children('IFRAME').attr("contentWindow").document.f;
			var ret = form.fileObj.click();
			//$ajaxFileUploader.children('IFRAME').attr("contentWindow").parentFileUploadID = 'dd';
			if(''!=form.fileObj.value){
			
				form.uuid.value = _uuid;
				// 隐藏
				$ajaxFileUploader.children('A[name=upload]').hide();
				$ajaxFileUploader.children('A[name=delete]').hide();
				$ajaxFileUploader.children('A[name=stop]').show();
				
				// 上传进度条
  				$ajaxFileUploader.children('.upload_progress').show();
				var filePath = form.fileObj.value;
				var fileName = filePath.substring(filePath.lastIndexOf('\\') + 1,filePath.length);
				$ajaxFileUploader.children('.upload_word').children('.upload_title').html(fileName);
				
				form.submit();
				setTimer();
			}
			//
		});
		
		function initIframe(){
			$ajaxFileUploader.append("<iframe src=\"" + cms_sys_basePath + "/fileUpload/upload\" width=\"700\"></iframe>");// style=\"display:none\"
		}
		
		function initContent(){
			$ajaxFileUploader.append("<div class=\"upload_progress\"><div class=\"upload_progressBar\"><div class=\"upload_percent\">0%</div><div class=\"bar\"></div></div></div>");
			$ajaxFileUploader.append("<a href=\"#\" name=\"upload\" class=\"upload_btn\" style=\"display:none\">\u4e0a\u4f20</a>");
		    $ajaxFileUploader.append("<a href=\"#\" name=\"delete\" style=\"display:none\">\u5220\u9664</a>");
		    $ajaxFileUploader.append("<a href=\"#\" name=\"stop\" style=\"display:none\">\u53d6\u6d88</a>");
		    $ajaxFileUploader.append("<div name=\"stats\"></div>");
		    //alert($ajaxFileUploader.children('.upload_hidden').val());
			if($ajaxFileUploader.children('.upload_hidden').val() != '')
			{
				$ajaxFileUploader.children('A[name=delete]').show();
			}
			else
			{
				$ajaxFileUploader.children('A[name=upload]').show();
			}
		}
		
		function setTimer(){
			var _timer = window.setInterval(function() {
				if(_processStatus){
					_processStatus = false;
					$.ajax( {
						url : cms_sys_basePath + "/fileUpload/process",
						cache : false,//不使用缓存
						type : 'GET',
						dataType : 'json',
						data : {
							'uuid' : _uuid
						},
						//timeout: 1000,
						error : function() {
							$ajaxFileUploader.children('DIV[name=stats]').html('Error loading JSON document');
							_processStatus = true;
						},
						success : function(json) {
							var prog = $ajaxFileUploader.children('.upload_progress').children('.upload_progressBar');
							prog.children('.bar').css("width", json.message + "%");
							prog.children('.upload_percent').html(json.message + "%");
							_processStatus = true;
						}
					});
				}else{
					return;
				}
			}, 1000);
			eval('window.timer_' + _uuid + '= _timer;' );
		}
	},
  	
  	AjaxFileUploader_Cancel: function(){
  		//alert(eval('window.timer_' + $(this).attr('id')));
  		eval('window.clearInterval(window.timer_' + $(this).attr('id') + ')');
  		// 设置返回信息
  		$(this).children('.upload_word').children('.upload_title').html('');
  		$(this).children('.upload_hidden').val('');
  		
  		// 设置功能按钮状态
  		$(this).children('A[name=upload]').show();
		$(this).children('A[name=delete]').hide();
		$(this).children('A[name=stop]').hide();
		
  		// 隐藏上传进度条
		$(this).AjaxFileUploader_process(false);
		
  	},
  	
  	AjaxFileUploader_Success : function(fileName, filePath){
  		
  		// 设置返回信息
  		$(this).children('.upload_hidden').val(filePath);
  		
  		// 设置功能按钮状态
  		$(this).children('A[name=upload]').hide();
		$(this).children('A[name=delete]').show();
		$(this).children('A[name=stop]').hide();
		
		// 隐藏上传进度条
		$(this).AjaxFileUploader_process(false);
		
		// 停止该附件的定时状态查询任务
		eval('window.clearInterval(window.timer_' + $(this).attr('id') + ')');
		
		return null;
  	},
  	
  	AjaxFileUploader_fail : function(){
  		
  	},
  	
  	AjaxFileUploader_process : function(rate){
  		var prog = $(this).children('.upload_progress').children('.upload_progressBar');
  		if(false == rate){
			prog.children('.bar').css("width", "0%");
			prog.children('.upload_percent').html("0%");
  			//$(this).children('.upload_progress').children('.upload_progressBar').children('.bar').css("width", 0 + "%");
  			$(this).children('.upload_progress').hide();
  		}else if(true == rate){
  			prog.children('.bar').css("width", "0%");
			prog.children('.upload_percent').html("0%");
  			$(this).children('.upload_progress').show();
  		}else{
  			$(this).children('.upload_progress').children('.upload_progressBar').children('.bar').css("width", rate + "%");
  		}
  	}
});

function ajaxFileUploader_success(uuid, filePath,fileName){
	alert(filePath);
	$('#' + uuid).AjaxFileUploader_Success(fileName, filePath);
}

function ajaxFileUploader_cancel(uuid){
	$('#' + uuid).AjaxFileUploader_Cancel();
}