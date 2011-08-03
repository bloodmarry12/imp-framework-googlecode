/**
 * 
 */
package com.huawei.imp.framework.model.privilege.domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.huawei.imp.framework.common.domain.Namingspace;

/**
 * Description:
 * 角色业务对象
 * @author ahli
 * Apr 24, 2009
 * 
 */
@Namingspace("_framework_privilege_role")
public class Role implements java.io.Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7547283252741837691L;


	/**
	 * 未定义的ID
	 */
	public static final long ID_UNDEFINED = -1;
	
	
	/**
	 * 角色ID
	 */
	private long id = ID_UNDEFINED;
	
	/**
	 * 角色名称
	 */
	private String name;
	
	/**
	 * 角色描述
	 */
	private String description;
	
	/**
	 * 账户集合
	 */
	private Set<Account> accounts = new HashSet<Account>(10);
	
	/**
	 * 权限对象集合
	 */
	private Set<Right> rights = new HashSet<Right>(100);

	public Role(){}
	
	public Role(long id){
		this.id = id;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public Set<Right> getRights() {
		return rights;
	}

	public void setRights(Set<Right> rights) {
		this.rights = rights;
	}
	
	/**
	 * 添加权限BO，建立双向关联
	 * @param rightBO
	 */
	public void addRight(Right rightBO){
		rightBO.getRoles().add(this);
		getRights().add(rightBO);
	}
	
	/**
	 * 验证角色是否拥有权限
	 * @param code
	 * @return
	 */
	public boolean validateRight(String code){
		Iterator<Right> it = this.getRights().iterator();
		while(it.hasNext()){
			Right rightBO = it.next();
			if(rightBO.getCode().equals(code)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 拷贝角色，附带浅拷贝了的right对象
	 * @return
	 */
	public Role deepCloneWithRight(){
		Role roleBO = cloneWithoutRight();
		for(Right rightBO : this.getRights()){
			roleBO.addRight(rightBO.shallowCloneProperty());
		}
		return roleBO;
	}
	
	/**
	 * 拷贝角色，附带浅拷贝了的right对象
	 * @return
	 */
	public Role cloneWithoutRight(){
		Role roleBO = new Role();
		roleBO.setName(this.getName());
		roleBO.setDescription(this.getDescription());
		roleBO.setId(this.getId());
		return roleBO;
	}
	
	public void deleteRightRelationShip(){
		for(Right rightBO : this.getRights()){
			rightBO.getRoles().remove(this);
		}
		this.getRights().clear();
	}
}
