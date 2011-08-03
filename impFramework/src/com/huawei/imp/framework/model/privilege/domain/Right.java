package com.huawei.imp.framework.model.privilege.domain;

import java.util.HashSet;
import java.util.Set;

import com.huawei.imp.framework.common.domain.Namingspace;

/**
 * Description: 权限业务对象
 * 
 * @author ahli Apr 24, 2009
 */
@Namingspace("_framework_privilege_right")
public class Right implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3380996659537351173L;

	/**
	 * 根节点ID
	 */
	public static final long ROOT_NODE_ID = 0L;

	/**
	 * 默认ID
	 */
	public static final long ID_DEFAULT = -1L;

	/**
	 * null object
	 */
	public final static Right NULLOBJECT = new Right(ID_DEFAULT, null,
			null, null);

	/**
	 * 权限流水号
	 */
	private long id = ID_DEFAULT;

	/**
	 * 权限编码
	 */
	private String code;

	/**
	 * 权限名称
	 */
	private String rightName;

	/**
	 * 权限描述
	 */
	private String description;

	/**
	 * 角色
	 */
	public Set<Role> roles = new HashSet<Role>();

	/**
	 * 子权限
	 */
	public Set<Right> rights = new HashSet<Right>();

	/**
	 * 父类关联对象
	 */
	public Right parent;

	/**
	 * 默认构造函数
	 */
	public Right()
	{
		parent = new Right(false);
	}

	/**
	 * 构造函数
	 * 
	 * @param createParent
	 *            是否创建父类关联对象
	 */
	public Right(boolean createParent)
	{
		if (createParent)
		{
			parent = new Right(false);
		}
	}

	/**
	 * 构造函数
	 * 
	 * @param id
	 * @param code
	 * @param rightName
	 * @param description
	 */
	public Right(long id, String code, String rightName, String description)
	{
		super();
		this.id = id;
		this.code = code;
		this.rightName = rightName;
		this.description = description;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getRightName()
	{
		return rightName;
	}

	public void setRightName(String rightName)
	{
		this.rightName = rightName;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Set<Role> getRoles()
	{
		return roles;
	}

	public void setRoles(Set<Role> roles)
	{
		this.roles = roles;
	}

	public Right getParent()
	{
		return parent;
	}

	public void setParent(Right parent)
	{
		this.parent = parent;
	}

	public Set<Right> getRights()
	{
		return rights;
	}

	public void setRights(Set<Right> rights)
	{
		this.rights = rights;
	}

	public void addChildRight(Right right)
	{
		right.setParent(this);
		this.getRights().add(right);
	}

	/**
	 * 该权限是否属于一个角色
	 * @param roleID	角色ID
	 * @return
	 */
	public boolean isInRole(long roleID)
	{
		for (Role role : this.getRoles())
		{
			if (role.getId() == roleID)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * 浅拷贝，仅仅克隆出属性值
	 * 
	 * @return
	 */
	public Right shallowCloneProperty()
	{
		Right rightBO = new Right();
		rightBO.setCode(this.getCode());
		rightBO.setId(this.getId());
		rightBO.setDescription(this.getDescription());
		rightBO.setParent(null);
		rightBO.setRightName(this.getRightName());
		return rightBO;
	}

	public Right deepCloneWithoutRole()
	{
		Right rightBO = shallowCloneProperty();
		for (Right _rightBO : this.getRights())
		{
			rightBO.addChildRight(_rightBO.deepCloneWithoutRole());
		}
		return rightBO;
	}

	public Right deepCloneWithoutRole(Set<Right> ruleSet)
	{
		if (!ruleSet.contains(this))
		{
			return null;
		}
		Right rightBO = deepCloneWithoutRole();
		for (Right _rightBO : this.getRights())
		{
			Right _rightBOc = _rightBO.deepCloneWithoutRole(ruleSet);
			if (null != _rightBOc)
			{
				rightBO.addChildRight(_rightBOc);
			}
		}
		return rightBO;
	}

	public void deleteRelationShip()
	{
		this.getParent().getRights().remove(this);
		for (Role role : this.getRoles())
		{
			role.getRights().remove(this);
		}
		this.setParent(null);
		this.setRights(null);
		this.setRoles(null);
	}
}
