package com.rick.scaffold.core.service.generic;

import org.springframework.transaction.annotation.Transactional;

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
	public int update(T entity) {
		entity.preUpdate();
		return dao.update(entity);
	}
}
