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
 * 导航条目录业务对象
 * @author ahli
 * Apr 24, 2009
 * 
 */
@Namingspace("_framework_privilege_explorer")
public class ExplorerIndex  implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5052187444518118362L;

	public static final long ROOT_NODE_ID = 0L;
	
	/**
	 * 流水号
	 */
	private long id;
	
	/**
	 * 导航条目录名称
	 */
	private String name;
	
	/**
	 * 地址
	 */
	private String path;
	
	/**
	 * 权限
	 */
	private Right right = new Right();
	
	/**
	 * 父亲节点
	 */
	private ExplorerIndex parent;
	
	/**
	 * 显示序列，请注意，同一个目录下的不能写成一样的数字
	 */
	private Integer order = Integer.valueOf("0");
	
	/**
	 * 子节点
	 */
	private Set<ExplorerIndex> childExplorerIndex = new HashSet<ExplorerIndex>(10);
	
	/**
	 * 默认空构造函数
	 */
	public ExplorerIndex(){
		parent = new ExplorerIndex(false);
	}
	
	/**
	 * 默认空构造函数
	 */
	public ExplorerIndex(boolean createParent){
		if(createParent){
			parent = new ExplorerIndex(false);
		}
	}
	
	/**
	 * 构造函数
	 * @param id	ID
	 * @param name	菜单名称
	 * @param path	菜单路径
	 * @param right	菜单权限对象
	 */
	public ExplorerIndex(long id, String name, String path, Right right) {
		super();
		this.id = id;
		this.name = name;
		this.path = path;
		this.right = right;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Right getRight() {
		return right;
	}

	public void setRight(Right right) {
		this.right = right;
	}

	public ExplorerIndex getParent() {
		return parent;
	}

	public void setParent(ExplorerIndex parent) {
		this.parent = parent;
	}

	public Set<ExplorerIndex> getChildExplorerIndex() {
		return childExplorerIndex;
	}

	public void setChildExplorerIndex(Set<ExplorerIndex> childExplorerIndexBO) {
		this.childExplorerIndex = childExplorerIndexBO;
	}
	
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	/**
	 * 深度克隆
	 * @return
	 */
	public ExplorerIndex deepClone(){
		ExplorerIndex explorerIndex = new ExplorerIndex();
		explorerIndex.setId(this.getId());
		explorerIndex.setName(this.getName());
		explorerIndex.setPath(this.getPath());
		explorerIndex.setOrder(this.getOrder());
		explorerIndex.setRight(right == null?null:right.shallowCloneProperty());
		//深度克隆对象集合
		Iterator<ExplorerIndex> it = this.getChildExplorerIndex().iterator();
		while(it.hasNext()){
			explorerIndex.addChildrenExplorerIndexB(it.next().deepClone());
		}
		return explorerIndex;
	}
	
	/**
	 * 深度克隆
	 * @param rightBOs	权限结合
	 * @return
	 */
	public ExplorerIndex deepCloneWithRight(Set<Right> rightBOs){
		
		//该导航条没有权限限制或者权限对象在集合中，则进行克隆
		if(rightBOs.contains(this.getRight()) 
				|| null == this.getRight() 
				|| this.getRight().getId() == Right.ID_DEFAULT ){
			
			ExplorerIndex explorerIndex = new ExplorerIndex();
			explorerIndex.setId(this.getId());
			explorerIndex.setName(this.getName());
			explorerIndex.setPath(this.getPath());
			explorerIndex.setOrder(this.getOrder());
			explorerIndex.setRight(right == null?null:right.shallowCloneProperty());
			//深度克隆对象集合
			Iterator<ExplorerIndex> it = this.getChildExplorerIndex().iterator();
			while(it.hasNext()){
				ExplorerIndex explorerIndexBOc = it.next().deepCloneWithRight(rightBOs);
				if(null != explorerIndexBOc){
					explorerIndex.addChildrenExplorerIndexB(explorerIndexBOc);
				}
			}
			return explorerIndex;
		}else{
			return null;
		}
	}
	
	/**
	 * @param explorerIndex
	 */
	public void addChildrenExplorerIndexB(ExplorerIndex explorerIndex){
		this.getChildExplorerIndex().add(explorerIndex);
		explorerIndex.setParent(this);
	}
	
	public void deleteRelationShip(){
		this.getParent().getChildExplorerIndex().remove(this);
		this.setParent(null);
	}
}
