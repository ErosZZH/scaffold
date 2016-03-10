package com.rick.scaffold.core.service.address;

import com.rick.scaffold.core.dao.address.AddressDao;
import com.rick.scaffold.core.entity.address.Address;
import com.rick.scaffold.core.service.generic.CrudService;
import org.springframework.stereotype.Service;

/**
 * Created by user on 16/3/10.
 */
@Service
public class AddressService extends CrudService<AddressDao, Address> {
}
