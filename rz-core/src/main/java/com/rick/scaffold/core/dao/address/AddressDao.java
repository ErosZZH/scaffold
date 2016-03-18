package com.rick.scaffold.core.dao.address;

import com.rick.scaffold.common.persistence.annotation.MyBatisDao;
import com.rick.scaffold.core.dao.generic.CrudDao;
import com.rick.scaffold.core.entity.address.Address;

@MyBatisDao
public interface AddressDao extends CrudDao<Address> {

}