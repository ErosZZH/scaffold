package com.rick.scaffold.core.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rick.scaffold.core.dao.user.PhotoDao;
import com.rick.scaffold.core.entity.user.Photo;
import com.rick.scaffold.core.service.generic.BaseService;

@Service
@Transactional(readOnly = true)
public class PhotoService extends BaseService<PhotoDao, Photo> {
	
	@Autowired
	private PhotoDao photoDao;
}

