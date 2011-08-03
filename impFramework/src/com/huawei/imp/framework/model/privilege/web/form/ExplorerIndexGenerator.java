package com.huawei.imp.framework.model.privilege.web.form;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import com.huawei.imp.framework.jee.JEEConstant;
import com.huawei.imp.framework.model.privilege.PrivilegeUtil;
import com.huawei.imp.framework.model.privilege.domain.ExplorerIndex;
import com.huawei.imp.framework.utils.Constant;


/**
 * Description: 导航条菜单构造器，该类用于构建导航条菜单的HTML页面语句对象爱
 * 
 * @author ahli Apr 25, 2009
 */
public class ExplorerIndexGenerator implements Constant, JEEConstant
{

	/**
	 * 导航条菜单对象
	 */
	private ExplorerIndex explorerIndex;

	/**
	 * HTML树对象
	 */
	private TreeDetailHTML<ExplorerIndex> indexHTML;
	
	private boolean checkRight = false;

	/**
	 * 构造函数
	 * 
	 * @param explorerIndexBO
	 *            导航条菜单对象
	 * @param indexHTML
	 *            HTML树对象
	 */
	public ExplorerIndexGenerator(ExplorerIndex explorerIndex,
			TreeDetailHTML<ExplorerIndex> indexHTML)
	{
		this.explorerIndex = explorerIndex;
		this.indexHTML = indexHTML;
	}
	
	/**
	 * 构造函数
	 * 
	 * @param explorerIndexBO
	 *            导航条菜单对象
	 * @param indexHTML
	 *            HTML树对象
	 */
	public ExplorerIndexGenerator(ExplorerIndex explorerIndex,
			TreeDetailHTML<ExplorerIndex> indexHTML, boolean checkRight)
	{
		this.explorerIndex = explorerIndex;
		this.indexHTML = indexHTML;
		this.checkRight = checkRight;
	}

	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer(1000);
		sb.append(explorerIndexGenerator2HTML(this.explorerIndex));
		return sb.toString();
	}

	public String explorerIndexGenerator2HTML(ExplorerIndex eig)
	{
		if (checkRight && eig.getRight()!=null && !PrivilegeUtil.validateUserRight(eig.getRight().getCode()))
		{
			return "";
		}
		
		// 指定的菜单链接的子节点
		Set<ExplorerIndex> children = eig.getChildExplorerIndex();
		List<ExplorerIndex> childrenArrays = new ArrayList<ExplorerIndex>(
				children.size());
		
		// 申明字符串缓冲区
		StringBuffer sb = new StringBuffer(500);
		sb.append("<li>");
		sb.append(LINE_SEPARATOR);

		// 菜单链接a标签
		if (children.size() > 0)
		{
			sb.append("<span class=\"folder\">");
		}
		else
		{
			sb.append("<span class=\"file\">");
		}
		
		sb.append("<a " + indexHTML.ahrefUrl(eig) + ">" + eig.getName());
		sb.append("</a>");
		
		sb.append("</span>");

		// 在a标签后插入内容
		sb.append(indexHTML.afterAtag(eig));

		// 依次遍历所有子节点
		if (children.size() > 0)
		{
			for (ExplorerIndex bo : children)
			{
				if (!(checkRight && bo.getRight()!=null && !PrivilegeUtil.validateUserRight(bo.getRight().getCode())))
				{
					childrenArrays.add(bo);
				}
			}
			Collections.sort(childrenArrays, new Comparator<ExplorerIndex>()
			{
				public int compare(ExplorerIndex o1, ExplorerIndex o2)
				{
					return o1.getOrder().compareTo(o2.getOrder());
				}
			});
			
			sb.append(LINE_SEPARATOR);
			sb.append("<ul>");
			sb.append(LINE_SEPARATOR);
			
			// 遍历子节点
			for (ExplorerIndex ceig : childrenArrays)
			{
				sb.append(explorerIndexGenerator2HTML(ceig));
			}
			sb.append(LINE_SEPARATOR);
			sb.append("</ul>");
		}
		sb.append(LINE_SEPARATOR);
		sb.append("</li>");
		return sb.toString();
	}
}
