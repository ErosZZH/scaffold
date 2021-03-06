package com.rick.scaffold.core.entity.generic;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rick.scaffold.common.persistence.tool.IncrSequenceTimeHandler;

/**
 * Entity支持类
 */
public abstract class BaseEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 实体编号（唯一标识）
	 */
	@JsonProperty("_id")
	protected Long id;
	

	public BaseEntity() {
		
	}
	
	public BaseEntity(Long id) {
		this();
		this.id = id;
	}
	
	/**
	 * 插入之前执行方法，需要手动调用
	 */
	public void preInsert(){
		this.id = IncrSequenceTimeHandler.getInstance().nextId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        BaseEntity<?> that = (BaseEntity<?>) obj;
        return null == this.getId() ? false : this.getId().equals(that.getId());
    }
    
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
	
}

