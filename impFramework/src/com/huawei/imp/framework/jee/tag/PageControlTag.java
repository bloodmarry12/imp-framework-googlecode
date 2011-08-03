package com.huawei.imp.framework.jee.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import com.huawei.imp.framework.utils.BeanHolder;
import com.huawei.imp.framework.utils.PageResult;

/**
 * Description: 分页控制标签
 * 
 * @author ahli Apr 14, 2009
 */
public class PageControlTag extends TagSupport
{

	private static final long serialVersionUID = 8264460288317404540L;

	/**
	 * 表单名称
	 */
	private String form;

	/**
	 * css样式类
	 */
	private String styleClass = "pagination";

	/**
	 * 分页属性，默认为pageResult
	 */
	private String pageResultProperty = "pageResult";

	public String getForm()
	{
		return form;
	}

	public void setForm(String form)
	{
		this.form = form;
	}

	public String getPageResult()
	{
		return pageResultProperty;
	}

	public void setPageResult(String pageResultProperty)
	{
		this.pageResultProperty = pageResultProperty;
	}

	public String getStyleClass()
	{
		return styleClass;
	}

	public void setStyleClass(String styleClass)
	{
		this.styleClass = styleClass;
	}

	@Override
	public int doEndTag() throws JspException
	{

		PageResult pageResult = (PageResult) pageContext.getRequest()
				.getAttribute(pageResultProperty);
		if (pageResult == null)
		{
			pageResult = PageResult.EMPTY_PAGE;
		}
		StringBuffer sb = new StringBuffer(200);
		sb.append("<div id=\"_" + pageResult.toString() + "_" + id + "\"");
		if (StringUtils.isNotBlank(styleClass))
		{
			sb.append(" class=\"" + styleClass + "\" ");
		}
		sb.append(">");
		sb.append(BeanHolder.getMessage("common.page.pageResult.pageSize"));
		sb
				.append("<input type=\"text\" class=\"input\" "
						+ " value=\""
						+ pageResult.getPageSize()
						+ "\" "
						+ " maxlength=\"4\""
						+ " onchange=\"document.getElementById('"
						+ form
						+ "')."
						+ " elements['pageSize'].value=this.value\"/>&nbsp;&nbsp;&nbsp;");
		sb.append(BeanHolder.getMessage("common.page.pageResult.total",
				new Object[]
				{
					pageResult.getTotalSize()
				}));
		// sb.append(BeanHolder.getMessage("common.page.pageResult.totalPage",
		// new Object[]{pageResult.getTotalPageCount()}));
		// sb.append(BeanHolder.getMessage("common.page.pageResult.pageSize",
		// new Object[]{pageResult.getPageSize()}));
		sb.append(BeanHolder.getMessage("common.page.pageResult.curPageNum",
				new Object[]
				{
					pageResult.getCurrentPageNo()
				}));
		sb.append("[");
		if (!pageResult.getIsFirstPage())
		{
			sb.append(new GotoButtonUtil(BeanHolder
					.getMessage("common.page.pageResult.first"), 1)
					.toHttpString());
			sb.append("|");
			sb.append(new GotoButtonUtil(BeanHolder
					.getMessage("common.page.pageResult.pre"), pageResult
					.getPreviousPageNo()).toHttpString());
		}
		else
		{
			sb.append(new ButtonUtil(BeanHolder
					.getMessage("common.page.pageResult.first"), null, false, "dis_btn")
					.toHttpString());
			sb.append("|");
			sb.append(new ButtonUtil(BeanHolder
					.getMessage("common.page.pageResult.pre"), null, false, "dis_btn")
					.toHttpString());
		}
		sb.append("|");
		if (!pageResult.getIsLastPage())
		{
			sb.append(new GotoButtonUtil(BeanHolder
					.getMessage("common.page.pageResult.next"), pageResult
					.getNextPageNo()).toHttpString());
			sb.append("|");
			sb.append(new GotoButtonUtil(BeanHolder
					.getMessage("common.page.pageResult.last"), pageResult
					.getTotalPageCount()).toHttpString());
		}
		else
		{
			sb.append(new ButtonUtil(BeanHolder
					.getMessage("common.page.pageResult.next"), null, false, GotoButtonUtil.STYLE_BTN_DIS)
					.toHttpString());
			sb.append("|");
			sb.append(new ButtonUtil(BeanHolder
					.getMessage("common.page.pageResult.last"), null, false, GotoButtonUtil.STYLE_BTN_DIS)
					.toHttpString());
		}
		sb.append("]");
		sb.append(BeanHolder.getMessage("common.page.pageResult.label.goto"));
		sb.append("<input id=\"" + id + "_pageResultJ\" type=\"text\" value=\""
				+ pageResult.getCurrentPageNo() + "\""
				+ " class=\"input\" maxlength=\"4\"></>");
		sb.append("页");
		sb.append(new JumpButtonUtil("goto", true).toHttpString());

		sb.append("</div>");
		JspWriter out = pageContext.getOut();
		try
		{
			out.print(sb.toString());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException
	{
		return EVAL_BODY_INCLUDE;
	}

	class JumpButtonUtil extends ButtonUtil
	{

		public JumpButtonUtil(String value, int pageNum)
		{
			this(value, true);
		}

		public JumpButtonUtil(String value, boolean available)
		{
			super(value, "document.getElementById('" + form
					+ "').elements['pageNum'].value="
					+ "document.getElementById('" + id
					+ "_pageResultJ').value;" + "document.getElementById('"
					+ form + "').submit()", available, "gbtn");
		}
	}

	class GotoButtonUtil extends ButtonUtil
	{

		public static final String STYLE_BTN_AVL = "btn";
		public static final String STYLE_BTN_DIS = "dis_btn";
		
		public GotoButtonUtil(String value, int pageNum)
		{
			this(value, pageNum, true);
		}
		
		public GotoButtonUtil(String value, int pageNum, boolean available)
		{
			
			super(value, "document.getElementById('" + form
					+ "').elements['pageNum'].value='" + pageNum + "';"
					+ "document.getElementById('" + form + "').submit()",
					available, available?STYLE_BTN_AVL:STYLE_BTN_DIS);
		}
	}

	class ButtonUtil
	{
		private String value;

		private String onclick;

		private String styleClass;

		private boolean available = true;

		public ButtonUtil(String value, String onclick, boolean available,
				String styleClass)
		{
			this.value = value;
			this.onclick = onclick;
			this.available = available;
			this.styleClass = styleClass;
		}

		public ButtonUtil(String value, String onclick, boolean available)
		{
			this(value, onclick, available, null);
		}
		
		public ButtonUtil(String value, String onclick)
		{
			this(value, onclick, true, null);
		}

		public String toHttpString()
		{
			StringBuffer sb = new StringBuffer(100);
			sb.append("<input ");
			sb.append("type=\"button\" ");
			if (StringUtils.isNotBlank(styleClass))
			{
				sb.append("class=\"" + styleClass + "\" ");
			}
			sb.append("value=\"" + value + "\" ");
			if (StringUtils.isNotBlank(onclick))
			{
				sb.append("onclick=\"" + onclick + "\"");
			}
			if (!available)
			{
				sb.append("disabled=\"true\" ");
			}
			sb.append(" />");
			return sb.toString();
		}
	}
}
