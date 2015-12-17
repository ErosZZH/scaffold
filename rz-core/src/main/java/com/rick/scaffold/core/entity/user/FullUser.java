package com.rick.scaffold.core.entity.user;

import java.util.List;

public class FullUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Company company;
	private List<Photo> photos;
	
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public List<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	
	
}
