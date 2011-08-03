package com.huawei.imp.framework.model.privilege.web.form;

import java.util.Set;

import com.huawei.imp.framework.model.privilege.domain.Account;
import com.huawei.imp.framework.model.privilege.domain.Right;

/**
 * Description:
 * 账户表单
 * @author ahli
 * Apr 27, 2009
 * 
 */
public class AccountForm extends Account{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Long[] getRightIDs() {
		Set<Right> rightSet = super.getRights();
		if(rightSet.size() > 0){
			Long[] rightIDs = new Long[rightSet.size()];
			int index = 0;
			for(Right rightBO : rightSet){
				rightIDs[index++] = rightBO.getId();
			}
			return rightIDs;
		}
		return new Long[0];
	}
	
	public String[] getRightIDStrs() {
		Set<Right> rightSet = super.getRights();
		if(rightSet.size() > 0){
			String[] rightIDs = new String[rightSet.size()];
			int index = 0;
			for(Right rightBO : rightSet){
				rightIDs[index++] = String.valueOf(rightBO.getId());
			}
			return rightIDs;
		}
		return new String[0];
	}

	public void setRightIDs(Long[] rightIDs) {
		if(null != rightIDs){
			for(Long rightID : rightIDs){
				Right right = new Right();
				right.setId(rightID);
				super.getRights().add(right);
			}
		}
	}
}
