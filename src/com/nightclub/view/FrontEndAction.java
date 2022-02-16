package com.nightclub.view;

import java.util.List;
import java.util.logging.Logger;

import com.nightclub.controller.AdsInfoManager;
import com.nightclub.controller.AgentGirlInfoManager;
import com.nightclub.controller.AgentInfoManager;
import com.nightclub.controller.BasicInfoManager;
import com.nightclub.controller.CategoryInfoManager;
import com.nightclub.controller.FreeAgentGirlInfoManager;
import com.nightclub.controller.GirlInfoManager;
import com.nightclub.controller.HomeInfoManager;
import com.nightclub.controller.NewsInfoManager;
import com.nightclub.controller.ZoneInfoManager;
import com.nightclub.model.AdsInfo;
import com.nightclub.model.AgentInfo;
import com.nightclub.model.BasicInfo;
import com.nightclub.model.CategoryInfo;
import com.nightclub.model.GirlInfo;
import com.nightclub.model.GirlService;
import com.nightclub.model.HomeInfo;
import com.nightclub.model.NewsInfo;
import com.nightclub.model.ZoneInfo;

public class FrontEndAction extends CommonAction {
	
	private static final long serialVersionUID = 1L;
	private Logger log_ = Logger.getLogger(this.getClass().getName());
	
	private List<CategoryInfo> categoryInfos;
	private CategoryInfo category;
	private List<BasicInfo> basicInfos;
	private String action;
	private String categoryInfoId;
	private String zoneInfoId;
	private String newsInfoId;
	private List<NewsInfo> newsInfos;
	private List<GirlInfo> girlInfos;
	private List<AdsInfo> adsInfos;
	private HomeInfo homeInfo;
	private NewsInfo newsInfo;
	private List<AgentInfo> agentInfos;
	private GirlInfo girlInfo;
	private List<GirlService> girlServices;
	private String agentInfoId;
	private AgentInfo agentInfo;
	private String girlInfoId;
	private List<ZoneInfo> zoneInfos;
	private ZoneInfo zoneInfo;

	private CategoryInfoManager categoryInfoManager;
	private BasicInfoManager basicInfoManager;
	private NewsInfoManager newsInfoManager;
	private GirlInfoManager girlInfoManager;
	private HomeInfoManager homeInfoManager;
	private AdsInfoManager adsInfoManager;
	private AgentInfoManager agentInfoManager;
	private ZoneInfoManager zoneInfoManager;

	public FrontEndAction() {
		super();
		categoryInfoManager = new CategoryInfoManager();
		basicInfoManager = new BasicInfoManager();
		newsInfoManager= new NewsInfoManager();
		girlInfoManager = new GirlInfoManager();
		homeInfoManager = new HomeInfoManager();
		adsInfoManager = new AdsInfoManager();
		agentInfoManager = new AgentInfoManager();
		zoneInfoManager = new ZoneInfoManager();
	}
	
	public String execute() {
		getStatisticInfo();

		this.categoryInfos = categoryInfoManager.list();
		this.newsInfos = newsInfoManager.list();
		this.homeInfo = homeInfoManager.getHomeInfo("0");
		this.adsInfos = adsInfoManager.active();
		
		return SUCCESS;
	}
	
	public String newsInfo() {
		getStatisticInfo();
		
		this.categoryInfos = categoryInfoManager.list();
		this.newsInfo = newsInfoManager.getNewsInfo(this.newsInfoId);
		
		return SUCCESS;
	}
	
	public String shoplistfilter() {
		getStatisticInfo();
		
		this.categoryInfos = categoryInfoManager.list();
		this.basicInfos = basicInfoManager.filter(getCategoryInfoId());
		this.category = categoryInfoManager.getCategoryInfo(getCategoryInfoId());
		if (this.category == null) {
			this.category = new CategoryInfo();
		}
		
		return SUCCESS;
	}
	
	public String agentInfo() {
		getStatisticInfo();
		
		this.categoryInfos = categoryInfoManager.list();
		girlInfoManager = new AgentGirlInfoManager();
		if (agentInfoId == null) {
			this.agentInfos = agentInfoManager.list();
			this.girlInfos = girlInfoManager.list();
		} else {
			this.girlInfos = ((AgentGirlInfoManager)girlInfoManager).list(agentInfoId, true);
			this.agentInfo = agentInfoManager.getAgentInfo(agentInfoId);
		}
		return SUCCESS;
	}
	
	public String freeagentInfo() {
		getStatisticInfo();
		
		this.categoryInfos = categoryInfoManager.list();
		girlInfoManager = new FreeAgentGirlInfoManager();
		this.girlInfos = girlInfoManager.list();
		return SUCCESS;
	}
	
	public String girlInfo() {
		getStatisticInfo();
		
		this.categoryInfos = categoryInfoManager.list();
		this.girlInfo = girlInfoManager.getGirlInfo(girlInfoId);
		this.girlServices = girlInfoManager.getGirlServiceListByGirlInfoId(this.girlInfo.getGirlInfoId());
		return SUCCESS;
	}
	
	public String search() {
		getStatisticInfo();
		
		this.categoryInfos = categoryInfoManager.list();
		this.zoneInfos = zoneInfoManager.list();
		return SUCCESS;
	}
	
	public String location() {
		getStatisticInfo();
		
		this.categoryInfos = categoryInfoManager.list();
		this.zoneInfo = zoneInfoManager.getZoneInfo(zoneInfoId);
		this.girlInfos = girlInfoManager.listByZoneInfoId(zoneInfoId);
		return SUCCESS;
	}
	
	public String howtouse() {
		getStatisticInfo();
		
		this.categoryInfos = categoryInfoManager.list();
		this.homeInfo = homeInfoManager.getHomeInfo("0");
		return SUCCESS;
	}
	
	public String contact() {
		getStatisticInfo();
		
		this.categoryInfos = categoryInfoManager.list();
		this.homeInfo = homeInfoManager.getHomeInfo("0");
		return SUCCESS;
	}

	public List<CategoryInfo> getCategoryInfos() {
		return categoryInfos;
	}

	public String getAction() {
		return action;
	}

	public void setCategoryInfos(List<CategoryInfo> categoryInfos) {
		this.categoryInfos = categoryInfos;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getCategoryInfoId() {
		return categoryInfoId;
	}

	public void setCategoryInfoId(String categoryInfoId) {
		this.categoryInfoId = categoryInfoId;
	}

	public String getZoneInfoId() {
		return zoneInfoId;
	}
	
	public NewsInfo getNewsInfo() {
		return newsInfo;
	}

	public String getNewsInfoId() {
		return newsInfoId;
	}

	public void setZoneInfoId(String zoneInfoId) {
		this.zoneInfoId = zoneInfoId;
	}

	public List<BasicInfo> getBasicInfos() {
		return basicInfos;
	}

	public void setBasicInfos(List<BasicInfo> basicInfos) {
		this.basicInfos = basicInfos;
	}

	public List<NewsInfo> getNewsInfos() {
		return newsInfos;
	}

	public void setNewsInfos(List<NewsInfo> newsInfos) {
		this.newsInfos = newsInfos;
	}

	public List<GirlInfo> getGirlInfos() {
		return girlInfos;
	}

	public void setGirlInfos(List<GirlInfo> girlInfos) {
		this.girlInfos = girlInfos;
	}

	public CategoryInfo getCategory() {
		return category;
	}

	public void setCategory(CategoryInfo category) {
		this.category = category;
	}
	
	public HomeInfo getHomeInfo() {
		return homeInfo;
	}

	public void setHomeInfo(HomeInfo homeInfo) {
		this.homeInfo = homeInfo;
	}

	public void setNewsInfo(NewsInfo newsInfo) {
		this.newsInfo = newsInfo;
	}

	public void setNewsInfoId(String newsInfoId) {
		this.newsInfoId = newsInfoId;
	}

	public List<AdsInfo> getAdsInfos() {
		return adsInfos;
	}

	public void setAdsInfos(List<AdsInfo> adsInfos) {
		this.adsInfos = adsInfos;
	}

	public List<AgentInfo> getAgentInfos() {
		return agentInfos;
	}

	public void setAgentInfos(List<AgentInfo> agentInfos) {
		this.agentInfos = agentInfos;
	}

	public String getAgentInfoId() {
		return agentInfoId;
	}

	public void setAgentInfoId(String agentInfoId) {
		this.agentInfoId = agentInfoId;
	}

	public AgentInfo getAgentInfo() {
		return agentInfo;
	}

	public void setAgentInfo(AgentInfo agentInfo) {
		this.agentInfo = agentInfo;
	}

	public GirlInfo getGirlInfo() {
		return girlInfo;
	}

	public void setGirlInfo(GirlInfo girlInfo) {
		this.girlInfo = girlInfo;
	}

	public String getGirlInfoId() {
		return girlInfoId;
	}

	public void setGirlInfoId(String girlInfoId) {
		this.girlInfoId = girlInfoId;
	}

	public List<GirlService> getGirlServices() {
		return girlServices;
	}

	public void setGirlServices(List<GirlService> girlServices) {
		this.girlServices = girlServices;
	}

	public List<ZoneInfo> getZoneInfos() {
		return zoneInfos;
	}

	public void setZoneInfos(List<ZoneInfo> zoneInfos) {
		this.zoneInfos = zoneInfos;
	}

	public ZoneInfo getZoneInfo() {
		return zoneInfo;
	}

	public void setZoneInfo(ZoneInfo zoneInfo) {
		this.zoneInfo = zoneInfo;
	}
}
