package com.huawei.imp.framework.model.excel2.mock;

import com.huawei.imp.framework.constant.ConstantValue;

public class Copyright {

	private String name;
	private String nameFirstChar;
	private ConstantValue songKind = new ConstantValue(1);
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameFirstChar() {
		return nameFirstChar;
	}
	public void setNameFirstChar(String nameFirstChar) {
		this.nameFirstChar = nameFirstChar;
	}
	public ConstantValue getSongKind() {
		return songKind;
	}
	public void setSongKind(ConstantValue songKind) {
		this.songKind = songKind;
	}
}
