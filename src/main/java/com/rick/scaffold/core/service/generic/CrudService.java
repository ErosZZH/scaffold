package com.rick.scaffold.core.service.generic;

import org.springframework.transaction.annotation.Transactional;

import com.rick.scaffold.common.utils.JedisUtils;
import com.rick.scaffold.core.dao.generic.CrudDao;
import com.rick.scaffold.core.entity.generic.DataEntity;

/**
 * Service基类
 */
@Transactional(readOnly = true)
public abstract class CrudService<D extends CrudDao<T>, T extends DataEntity<T>> extends BaseService<D, T> {

	/**
	 * 保存数据（插入或更新）
	 * @param entity
	 */
	@Override
	@Transactional(readOnly = false)
	public int save(T entity) {
		entity.preInsert();
		return dao.insert(entity);
	}
	
	@Override
	@Transactional(readOnly = false)
	public int saveAndCache(T entity) {
		entity.preInsert();
		JedisUtils.setObject(entity.getId(), entity, 0);
		return dao.insert(entity);
	}
	
	@Override
	@Transactional(readOnly = false)
	public int saveAndCache(T entity, int cacheExpireTime) {
		entity.preInsert();
		JedisUtils.setObject(entity.getId(), entity, cacheExpireTime);
		return dao.insert(entity);
	}
	
	/**
	 * 更新数据（插入或更新）
	 * @param entity
	 */
	@Override
	@Transactional(readOnly = false)
	public int update(T entity) {
		entity.preUpdate();
		return dao.update(entity);
	}
	
	@Override
	@Transactional(readOnly = false)
	public int updateAndCache(T entity, int cacheExpireTime) {
		entity.preUpdate();
		JedisUtils.setObject(entity.getId(), entity, cacheExpireTime);
		return dao.update(entity);
	}
	
	@Override
	@Transactional(readOnly = false)
	public int updateAndCache(T entity) {
		entity.preUpdate();
		JedisUtils.setObject(entity.getId(), entity, 0);
		return dao.update(entity);
	}
}
