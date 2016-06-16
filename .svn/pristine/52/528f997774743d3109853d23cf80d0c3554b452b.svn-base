package com.nightclub.view;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import com.nightclub.controller.NewsInfoManager;
import com.nightclub.model.NewsInfo;
import com.nightclub.model.NewsSearch;
import com.opensymphony.xwork2.ActionSupport;

public class NewsInfoAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	Logger log_ = Logger.getLogger(this.getClass().getName());
	
	private List<NewsInfo> newsInfos;
	private NewsInfo newsInfo;
	private NewsSearch newsSearch;
	private String newsInfoId;
	private String menu;
	private String action;
	private boolean showInfo = false;
	
	private NewsInfoManager newsInfoManager;

	public NewsInfoAction() {
		newsInfoManager = new NewsInfoManager();
	}
	
	public String execute() {
		
		if(getAction() != null) {
			if(getAction().equals("add")) {
				add();
			} else if(getAction().equals("update")) {
				update();
			}
		}
		
		this.newsInfos = newsInfoManager.list();
		
		return SUCCESS;
	}

	public String add() {
		try {
			newsInfo.setNewsInfoId(UUID.randomUUID().toString().toUpperCase());
			
			newsInfoManager.add(this.newsInfo);
			
			addActionMessage("You have been successfully inserted");
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String update() {
		try {
			newsInfoManager.update(this.newsInfo);
			
			addActionMessage("You have been successfully updated");
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String edit() {
		this.newsInfo = newsInfoManager.getNewsInfo(this.newsInfoId);
		this.showInfo = true;
		this.newsInfos = newsInfoManager.list();
		return SUCCESS;
	}

	public String delete() {
		newsInfoManager.delete(getNewsInfoId());
		addActionMessage("You have been successfully deleted");
		this.newsInfos = newsInfoManager.list();
		return SUCCESS;
	}
	
	public String search() {
		this.newsInfos = newsInfoManager.search(this.newsSearch);
		return SUCCESS;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public boolean isShowInfo() {
		return showInfo;
	}

	public void setShowInfo(boolean showInfo) {
		this.showInfo = showInfo;
	}

	public List<NewsInfo> getNewsInfos() {
		return newsInfos;
	}

	public NewsInfo getNewsInfo() {
		return newsInfo;
	}

	public String getNewsInfoId() {
		return newsInfoId;
	}

	public void setNewsInfos(List<NewsInfo> newsInfos) {
		this.newsInfos = newsInfos;
	}

	public void setNewsInfo(NewsInfo newsInfo) {
		this.newsInfo = newsInfo;
	}

	public void setNewsInfoId(String newsInfoId) {
		this.newsInfoId = newsInfoId;
	}

	public NewsSearch getNewsSearch() {
		return newsSearch;
	}

	public void setNewsSearch(NewsSearch newsSearch) {
		this.newsSearch = newsSearch;
	}
}
