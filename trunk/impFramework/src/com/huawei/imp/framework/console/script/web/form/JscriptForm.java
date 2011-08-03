package com.huawei.imp.framework.console.script.web.form;

import com.huawei.imp.framework.exception.BusinessException;
import com.huawei.imp.framework.console.script.Constants;
import com.huawei.imp.framework.console.script.domain.Script;

/**
 * <p><strong>脚本执行表单</strong></p>
 * <p></p>
 * @version v1.0
 * @since v2.0
 * @author ahli (edit in 2010-5-27)
 */
public class JscriptForm extends Script{

	/**
	 * NULL Object对象
	 */
	public static final JscriptForm NULL = new JscriptForm(){

		/* (non-Javadoc)
		 * @see ahli.framework.model.script.web.form.JscriptForm#setScript(java.lang.String)
		 */
		@Override
		public void setScript(String script) {
			throw new BusinessException(
					Constants.EXCEPTION_NOT_SUPPORT_METHOD,
					new Object[] { "setScript" });
		}

		/* (non-Javadoc)
		 * @see ahli.framework.model.script.web.form.JscriptForm#setToken(java.lang.String)
		 */
		@Override
		public void setToken(String token) {
			throw new BusinessException(
					Constants.EXCEPTION_NOT_SUPPORT_METHOD,
					new Object[] { "setToken" });
		}
	};
	
	/**
	 * 脚本执行口令
	 */
	private String token;
	
	/**
	 * 获取页面脚本口令
	 * @return    页面脚本口令
	 */
	public String getToken() {
		return token;
	}

	/**
	 * 设置页面脚本口令
	 * @param token    页面脚本口令
	 */
	public void setToken(String token) {
		this.token = token;
	}
}
