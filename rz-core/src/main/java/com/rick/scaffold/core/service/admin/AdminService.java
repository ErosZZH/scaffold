package com.rick.scaffold.core.service.admin;

import com.rick.scaffold.core.dao.admin.AdminDao;
import com.rick.scaffold.core.entity.admin.Admin;
import com.rick.scaffold.core.service.generic.CrudService;
import org.springframework.stereotype.Service;

/**
 * Created by user on 16/3/10.
 */
@Service
public class AdminService extends CrudService<AdminDao, Admin> {
}
