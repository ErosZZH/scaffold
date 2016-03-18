package com.rick.scaffold.core.entity.generic;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 数据Entity类
 * 
 */
public abstract class DataEntity<T> extends BaseEntity<T> {

	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date createDate;	// 创建日期
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date modifyDate;	// 更新日期
	
	@JsonIgnore
	protected Boolean delFlag = false; 	// 删除标记（false：正常；true：删除；）
	
	public DataEntity() {
		super();
	}
	
	public DataEntity(Long id) {
		super(id);
	}
	
	/**
	 * 插入之前执行方法，需要手动调用
	 */
	@Override
	public void preInsert(){
		super.preInsert();
		this.modifyDate = new Date();
		this.createDate = this.modifyDate;
	}
	
	/**
	 * 更新之前执行方法，需要手动调用
	 */
	public void preUpdate(){
		this.modifyDate = new Date();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

}
