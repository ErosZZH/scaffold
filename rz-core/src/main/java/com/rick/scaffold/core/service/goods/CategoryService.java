package com.rick.scaffold.core.service.goods;

import com.rick.scaffold.core.dao.goods.CategoryDao;
import com.rick.scaffold.core.entity.goods.Category;
import com.rick.scaffold.core.service.generic.CrudService;
import org.springframework.stereotype.Service;

/**
 * Created by user on 16/3/10.
 */
@Service
public class CategoryService extends CrudService<CategoryDao, Category> {
}
