/**
 * 
 */
package com.huawei.imp.framework.model.privilege.domain;

/**
 * <p>
 * 状态
 * </p>
 * @see java.io.Serializable
 * @author aohai.li
 * @version CMSV100R001DB0SP04, 2010-8-17
 * @since CMSV100R001DB0SP04
 */
public class Status implements java.io.Serializable
{

	/**
	 * 默认序列号
	 */
	private static final long serialVersionUID = 5455640270434989046L;

	/**
	 * <p>
	 * 枚举类型
	 * </p>
	 * @author aohai.li
	 * @version CMSV100R001DB0SP04, 2010-8-17
	 * @since CMSV100R001DB0SP04
	 */
	public static enum Element{
		/**
		 * 状态：审批拒绝
		 */
		REJECT(-2, "拒绝"),
		/**
		 * 状态：新增
		 */
		NEW(-1, "新增"),
		/**
		 * 状态：等待激活
		 */
		WAITE2ACTION(0, "等待激活"),
		/**
		 * 状态：激活
		 */
		ACTIVE(1, "激活"),
		/**
		 * 状态：停用
		 */
		STOPED(2, "停用"),
		/**
		 * 状态：逻辑删除
		 */
		REMOVED(3, "逻辑删除"),
		
		NULL(null, null);
		
		/**
		 * 值
		 */
		private Integer value;
		
		/**
		 * 描述
		 */
		private String desc;
		
		Element(Integer value, String desc){
			this.value = value;
			this.desc = desc;
		}

		public Integer getValue() {
			return value;
		}

		public String getDesc() {
			return desc;
		}
	}
	
	/**
	 * 空对象
	 */
	private Element thisElement = Element.NULL;
	
	/**
	 * 默认构造函数
	 */
	public Status(){
		super();
		this.thisElement = Element.NULL;
	}
	
	/**
	 * @return
	 */
	public Integer getValue() {
		return thisElement.getValue();
	}
	
	/**
	 * @param value
	 */
	public void setValue(Integer value) {
		if(null != value){
			for(Element ele : Element.values()){
				if(value.equals(ele.getValue())){
					this.thisElement = ele;
					return;
				}
			}
		}
		this.thisElement = Element.NULL;
	}
	
	/**
	 * @return
	 */
	public String getDesc() {
		return thisElement.getDesc();
	}
	
	/**
	 * @param desc
	 */
	public void setDesc(String desc) {
		if(null != desc){
			for(Element ele : Element.values()){
				if(desc.equals(ele.getDesc())){
					this.thisElement = ele;
					return;
				}
			}
		}
		this.thisElement = Element.NULL;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if(null != thisElement){
			return thisElement.getValue() + "[" + thisElement.getDesc() + "]";
		}else{
			return "Empty";
		}
	}
}
