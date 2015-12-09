package com.rick.scaffold.core.dao.product;

import com.rick.scaffold.common.persistence.annotation.MyBatisDao;
import com.rick.scaffold.core.dao.generic.CrudDao;
import com.rick.scaffold.core.entity.product.Product;

@MyBatisDao
public interface ProductDao extends CrudDao<Product> {

}