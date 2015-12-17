package com.rick.scaffold.core.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rick.scaffold.core.dao.user.CompanyDao;
import com.rick.scaffold.core.entity.user.Company;
import com.rick.scaffold.core.service.generic.BaseService;

@Service
@Transactional(readOnly = true)
public class CompanyService extends BaseService<CompanyDao, Company> {
	
	@Autowired
	private CompanyDao companyDao;

}

