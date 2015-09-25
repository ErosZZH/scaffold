package com.rick.scaffold.core.dao.generic;

import java.util.List;

/**
 * DAO支持类实现
 */
public interface BaseDao<T> {

	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T findOne(Long id);
	
	/**
	 * @param entity
	 * @return
	 */
	public List<T> findAll();
	
	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	public int insert(T entity);
	
	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	public int update(T entity);
	
	/**
	 * 删除数据（一般为逻辑删除，更新del_flag字段为1）
	 * @param id
	 * @see public int delete(T entity)
	 * @return
	 */
	public int delete(Long id);
}