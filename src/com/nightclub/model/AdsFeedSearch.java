package com.nightclub.model;

import java.io.Serializable;

public class AdsFeedSearch implements Serializable {

	private static final long serialVersionUID = 1L;

	private String title;
	private int searchRandom;
	private Boolean isFilterActive;

	public String getTitle() {
		return title;
	}
	public int getSearchRandom() {
		return searchRandom;
	}
	public Boolean getIsFilterActive() {
		return isFilterActive;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setSearchRandom(int searchRandom) {
		this.searchRandom = searchRandom;
	}
	public void setIsFilterActive(Boolean isFilterActive) {
		this.isFilterActive = isFilterActive;
	}
	
}
