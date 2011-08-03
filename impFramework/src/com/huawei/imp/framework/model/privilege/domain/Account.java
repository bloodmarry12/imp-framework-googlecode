/**
 * 
 */
package com.huawei.imp.framework.model.privilege.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.huawei.imp.framework.common.domain.Namingspace;

/**
 * Description: 账户对象
 * 
 * @author ahli Apr 24, 2009
 */
@Namingspace("_framework_privilege_account")
public class Account implements java.io.Serializable
{
	/**
	 * 默认序列化ID
	 */
	private static final long serialVersionUID = 5465222850755158145L;

	/**
	 * 未定义ID：-1
	 */
	public static final long ID_UNDEFINED = -1;

	/**
	 * 默认密码(未加密)：123456
	 */
	public static final String PASSWOR_DAFALUT = "123456";

	/**
	 * 无意义流水号
	 */
	private long id = ID_UNDEFINED;

	/**
	 * 账户名
	 */
	private String name;

	/**
	 * 账户密码
	 */
	private String password;

	/**
	 * 账户状态
	 */
	private Status status = new Status();

	/**
	 * 操作员名称
	 */
	private String operator;

	/**
	 * 操作员联系方式
	 */
	private String operatorContact;

	/**
	 * 角色
	 */
	private Role role = new Role();

	/**
	 * 最后登录时间
	 */
	private Date lastLoginTime = null;
	
	/**
	 * 密码最后修改时间
	 */
	private Date lastPwdModTime = null;
	
	/**
	 * 账户实际权限
	 */
	public Set<Right> rights = new HashSet<Right>();

	/**
	 * 默认构造函数
	 */
	public Account()
	{
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		if (name==null){
			this.name = null;
		}else{
			this.name = name.trim();
		}
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Status getStatus()
	{
		return status;
	}

	public void setStatus(Status status)
	{
		this.status = status;
	}

	public Role getRole()
	{
		return role;
	}

	/**
	 * 添加双向关联
	 * 
	 * @param role
	 */
	public void setRole(Role role)
	{
		this.role = role;
		if (null != role)
		{
			role.getAccounts().add(this);
		}
	}

	public String getOperator()
	{
		return operator;
	}

	public void setOperator(String operator)
	{
		this.operator = operator;
	}

	public String getOperatorContact()
	{
		return operatorContact;
	}

	public void setOperatorContact(String operatorContact)
	{
		this.operatorContact = operatorContact;
	}

	public Set<Right> getRights()
	{
		return rights;
	}

	public void setRights(Set<Right> rights)
	{
		this.rights = rights;
	}

	public Date getLastLoginTime()
	{
		if (null != lastLoginTime)
		{
			return (Date)lastLoginTime.clone();
		}
		else
		{
			return null;
		}
	}
	
	public void setLastLoginTime(Date lastLoginTime)
	{
		if (null != lastLoginTime)
		{
			this.lastLoginTime = (Date)lastLoginTime.clone();
		}
		else
		{
			this.lastLoginTime = null;
		}
	}
	
	public Date getLastPwdModTime()
	{
		if (null != lastPwdModTime)
		{
			return (Date)lastPwdModTime.clone();
		}
		else
		{
			return null;
		}
	}

	public void setLastPwdModTime(Date lastPwdModTime)
	{
		if (null != lastPwdModTime)
		{
			this.lastPwdModTime = (Date)lastPwdModTime.clone();
		}
		else
		{
			this.lastPwdModTime = null;
		}
	}
	
	/**
	 * 删除与角色的关联
	 */
	public void deleteRelationShip()
	{
		this.getRights().clear();
		this.getRole().getAccounts().remove(this);
		this.setRole(null);
	}

	/**
	 * 验证当前账户是否拥有权限
	 * 
	 * @param code
	 * @return
	 */
	public boolean validateRight(String code)
	{
		Iterator<Right> it = this.getRights().iterator();
		while (it.hasNext())
		{
			Right rightBO = it.next();
			if (rightBO.getCode().equals(code))
			{
				return true;
			}
		}
		return false;
	}

	public Account cloneAccount()
	{
		Account _clone = cloneWithoutRight();
		for (Right rightBO : this.getRights())
		{
			_clone.getRights().add(rightBO.shallowCloneProperty());
		}
		return _clone;
	}

	public void removeRightByRole(){
		Role role = this.getRole();
		List<Right> _removeRights = new ArrayList<Right>();
		for(Right right : this.getRights()){
			if(!role.getRights().contains(right)){
				_removeRights.add(right);
			}
		}
		for(Right right : _removeRights){
			this.getRights().remove(right);
		}
	}
	
	/**
	 * 克隆AccountBO对象(不包括RightBO对象)
	 * 
	 * @return
	 */
	public Account cloneWithoutRight()
	{
		Account _clone = new Account();
		_clone.setId(this.getId());
		_clone.setName(this.getName());
		_clone.setPassword(this.getPassword());
		_clone.setStatus(this.getStatus());
		_clone.setOperator(this.getOperator());
		_clone.setOperatorContact(this.getOperatorContact());
		_clone.setLastLoginTime(this.getLastLoginTime());
		_clone.setLastPwdModTime(this.getLastPwdModTime());
		_clone.setRole(this.getRole().cloneWithoutRight());
		
		return _clone;
	}
}
