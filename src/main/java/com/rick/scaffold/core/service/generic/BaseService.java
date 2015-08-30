package com.rick.scaffold.core.service.generic;

import org.springframework.transaction.annotation.Transactional;

/**
 * Service基类
 */
@Transactional(readOnly = true)
public abstract class BaseService {

}
