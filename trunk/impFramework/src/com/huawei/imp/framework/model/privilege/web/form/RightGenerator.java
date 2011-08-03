package com.huawei.imp.framework.model.privilege.web.form;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import com.huawei.imp.framework.model.privilege.domain.Right;
import com.huawei.imp.framework.utils.Constant;

/**
 * Description:
 * 导航条菜单构造器，该类用于构建导航条菜单的HTML页面语句对象爱
 * @author ahli
 * Apr 25, 2009
 * 
 */
public class RightGenerator implements Constant{

	private Right right;
	
	protected TreeDetailHTML<Right> indexHTML;
	
	public RightGenerator(Right right,
			 TreeDetailHTML<Right> indexHTML){
		this.right = right;
		this.indexHTML = indexHTML;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(1000);
		sb.append(generator2HTML(this.right));
		return sb.toString();
	}
	
	public String generator2HTML(Right bo){
		StringBuffer sb = new StringBuffer(500);
		sb.append("<li id=\"li_" + bo.getId() + "\">");
		sb.append(LINE_SEPARATOR);
		sb.append("<a " + indexHTML.ahrefUrl(bo) + ">");
		sb.append(bo.getRightName());
		sb.append("</a>");
		sb.append(indexHTML.afterAtag(bo));
		Set<Right> children = bo.getRights();
		if(children.size() > 0){
			sb.append(LINE_SEPARATOR);
			sb.append("<ul>");
			sb.append(LINE_SEPARATOR);
			Set<Right> compareSet = new TreeSet<Right>(
					new Comparator<Right>() {
						public int compare(Right o1, Right o2) {
							return o1.getRightName().compareTo(o2.getRightName());
						}
					});
			compareSet.addAll(children);
			for (Right cright : compareSet) {
				sb.append(generator2HTML(cright));
			}
			sb.append(LINE_SEPARATOR);
			sb.append("</ul>");
		}
		sb.append(LINE_SEPARATOR);
		sb.append("</li>");
		return sb.toString();
	}
}
