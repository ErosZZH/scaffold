package com.rick.scaffold.core.service.generic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.rick.scaffold.common.utils.JedisUtils;
import com.rick.scaffold.core.dao.generic.BaseDao;
import com.rick.scaffold.core.entity.generic.BaseEntity;

/**
 * Service基类
 */
@Transactional(readOnly = true)
public abstract class BaseService<D extends BaseDao<T>, T extends BaseEntity<T>> {

	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;
	
	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T findOne(String id) {
		return dao.findOne(id);
	}
	
	/**
	 * 查询列表数据
	 * @param entity
	 * @return
	 */
	public List<T> findAll() {
		return dao.findAll();
	}

	/**
	 * 保存数据（插入或更新）
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public int saveAndCache(T entity, int cacheExpireTime) {
		entity.preInsert();
		JedisUtils.setObject(entity.getId(), entity, cacheExpireTime);
		return dao.insert(entity);
	}
	
	@Transactional(readOnly = false)
	public int saveAndCache(T entity) {
		entity.preInsert();
		JedisUtils.setObject(entity.getId(), entity, 0);
		return dao.insert(entity);
	}
	
	@Transactional(readOnly = false)
	public int save(T entity) {
		entity.preInsert();
		return dao.insert(entity);
	}
	
	/**
	 * 更新数据（插入或更新）
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public int updateAndCache(T entity, int cacheExpireTime) {
		JedisUtils.setObject(entity.getId(), entity, cacheExpireTime);
		return dao.update(entity);
	}
	
	@Transactional(readOnly = false)
	public int updateAndCache(T entity) {
		JedisUtils.setObject(entity.getId(), entity, 0);
		return dao.update(entity);
	}
	
	@Transactional(readOnly = false)
	public int update(T entity) {
		return dao.update(entity);
	}
	
	/**
	 * 删除数据
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public int delete(String id) {
		return dao.delete(id);
	}
	
	@Transactional(readOnly = false)
	public int deleteAndCache(String id) {
		JedisUtils.delObject(id);
		return dao.delete(id);
	}
}
