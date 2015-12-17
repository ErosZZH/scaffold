package com.rick.scaffold.core.service.product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rick.scaffold.core.dao.product.ProductDao;
import com.rick.scaffold.core.entity.product.Product;
import com.rick.scaffold.core.service.generic.CrudService;

@Service
@Transactional(readOnly = true)
public class ProductService extends CrudService<ProductDao, Product> {

}
