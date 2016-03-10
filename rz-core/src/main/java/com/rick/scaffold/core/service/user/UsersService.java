package com.rick.scaffold.core.service.user;

import com.rick.scaffold.core.dao.user.UsersDao;
import com.rick.scaffold.core.entity.user.Users;
import com.rick.scaffold.core.service.generic.CrudService;
import org.springframework.stereotype.Service;

/**
 * Created by user on 16/3/10.
 */
@Service
public class UsersService extends CrudService<UsersDao, Users> {
}
