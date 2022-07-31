package com.nightclub.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.nightclub.common.IConstants;
import com.nightclub.controller.AdsInfoManager;
import com.nightclub.controller.AgentGirlInfoManager;
import com.nightclub.controller.AgentInfoManager;
import com.nightclub.controller.BasicInfoManager;
import com.nightclub.controller.CategoryInfoManager;
import com.nightclub.controller.EnGirlInfoManager;
import com.nightclub.controller.FreeAgentGirlInfoManager;
import com.nightclub.controller.GirlFavouriteManager;
import com.nightclub.controller.GirlInfoManager;
import com.nightclub.controller.HomeInfoManager;
import com.nightclub.controller.HomeSlideImageManager;
import com.nightclub.controller.NationalityInfoManager;
import com.nightclub.controller.NewsInfoManager;
import com.nightclub.controller.UserInfoManager;
import com.nightclub.controller.ZoneInfoManager;
import com.nightclub.model.AdsInfo;
import com.nightclub.model.AgentGirlInfo;
import com.nightclub.model.AgentInfo;
import com.nightclub.model.BasicInfo;
import com.nightclub.model.CategoryInfo;
import com.nightclub.model.EnGirlInfo;
import com.nightclub.model.FreeAgentGirlInfo;
import com.nightclub.model.FrontSearch;
import com.nightclub.model.GirlInfo;
import com.nightclub.model.GirlService;
import com.nightclub.model.HomeInfo;
import com.nightclub.model.HomeSlideImage;
import com.nightclub.model.NationalityInfo;
import com.nightclub.model.NewsInfo;
import com.nightclub.model.ShopGirlInfo;
import com.nightclub.model.UserInfo;
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
	private List<HomeSlideImage> homeSlideImages;
	private FrontSearch frontSearch;
	private List<NationalityInfo> nationalityInfos;
	private int feedLimit = 12;
	private int feedOffset = 0;
	private String userType;
	protected List<String> girlFavourites = new ArrayList<String>();

	private CategoryInfoManager categoryInfoManager;
	private BasicInfoManager basicInfoManager;
	private NewsInfoManager newsInfoManager;
	private GirlInfoManager girlInfoManager;
	private HomeInfoManager homeInfoManager;
	private AdsInfoManager adsInfoManager;
	private AgentInfoManager agentInfoManager;
	private ZoneInfoManager zoneInfoManager;
	private UserInfoManager userInfoManager;
	private HomeSlideImageManager homeSlideImageManager;
	private NationalityInfoManager nationalityInfoManager;
	protected GirlFavouriteManager girlFavouriteManager;

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
		userInfoManager = new UserInfoManager();
		homeSlideImageManager = new HomeSlideImageManager();
		nationalityInfoManager = new NationalityInfoManager();
		girlFavouriteManager = new GirlFavouriteManager();
	}
	
	public String execute() {
		getStatisticInfo();

		this.categoryInfos = categoryInfoManager.list();
		this.homeSlideImages = homeSlideImageManager.list();
		this.newsInfos = newsInfoManager.list();
		this.homeInfo = homeInfoManager.getHomeInfo("0");
		this.adsInfos = adsInfoManager.active();
		
		return SUCCESS;
	}
	
	public String newsInfo() {
		getStatisticInfo();
		
		this.categoryInfos = categoryInfoManager.list();
		this.homeSlideImages = homeSlideImageManager.list();
		this.newsInfo = newsInfoManager.getNewsInfo(this.newsInfoId);
		
		return SUCCESS;
	}
	
	public String shoplistfilter() {
		getStatisticInfo();
		
		this.categoryInfos = categoryInfoManager.list();
		this.homeSlideImages = homeSlideImageManager.list();
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
		this.homeSlideImages = homeSlideImageManager.list();
		girlInfoManager = new AgentGirlInfoManager();
		if(this.frontSearch == null) {
			this.frontSearch = new FrontSearch();
			this.frontSearch.setChkAgents(Boolean.TRUE.toString().toLowerCase());
		}
		this.girlInfos = girlInfoManager.search(this.frontSearch, this.feedLimit, this.feedOffset);
		this.agentInfos = agentInfoManager.list();
		this.zoneInfos = zoneInfoManager.list();
		this.nationalityInfos = nationalityInfoManager.list();
		return SUCCESS;
	}
	
	public String freeagentInfo() {
		getStatisticInfo();
		
		this.categoryInfos = categoryInfoManager.list();
		this.homeSlideImages = homeSlideImageManager.list();
		girlInfoManager = new FreeAgentGirlInfoManager();
		if(this.frontSearch == null) {
			this.frontSearch = new FrontSearch();
			this.frontSearch.setChkFreeAgents(Boolean.TRUE.toString().toLowerCase());
		}
		this.girlInfos = girlInfoManager.search(this.frontSearch, this.feedLimit, this.feedOffset);
		this.zoneInfos = zoneInfoManager.list();
		this.nationalityInfos = nationalityInfoManager.list();
		return SUCCESS;
	}
	
	public String loadMoreGirlInfos() {
		if (IConstants.USER_TYPE_AGENT.equals(getUserType())) {
			girlInfoManager = new FreeAgentGirlInfoManager();
			if(this.frontSearch == null) {
				this.frontSearch = new FrontSearch();
				this.frontSearch.setChkFreeAgents(Boolean.TRUE.toString().toLowerCase());
			}
		} else if (IConstants.USER_TYPE_FREE_AGENT.equals(getUserType())) {
			girlInfoManager = new FreeAgentGirlInfoManager();
			if(this.frontSearch == null) {
				this.frontSearch = new FrontSearch();
				this.frontSearch.setChkFreeAgents(Boolean.TRUE.toString().toLowerCase());
			}
		} else if (IConstants.USER_TYPE_EN_GIRL.equals(getUserType())) {
			girlInfoManager = new EnGirlInfoManager();
			if(this.frontSearch == null) {
				this.frontSearch = new FrontSearch();
				this.frontSearch.setChkEnGirls(Boolean.TRUE.toString().toLowerCase());
			}
		}
		this.girlInfos = girlInfoManager.search(this.frontSearch, this.feedLimit, this.feedOffset);
		return SUCCESS;
	}
	
	public String engirlInfo() {
		getStatisticInfo();
		
		this.categoryInfos = categoryInfoManager.list();
		this.homeSlideImages = homeSlideImageManager.list();
		girlInfoManager = new EnGirlInfoManager();
		if(this.frontSearch == null) {
			this.frontSearch = new FrontSearch();
			this.frontSearch.setChkEnGirls(Boolean.TRUE.toString().toLowerCase());
		}
		this.girlInfos = girlInfoManager.search(this.frontSearch, this.feedLimit, this.feedOffset);
		this.zoneInfos = zoneInfoManager.list();
		this.nationalityInfos = nationalityInfoManager.list();
		return SUCCESS;
	}
	
	public String favourite() {
		getStatisticInfo();
		
		this.categoryInfos = categoryInfoManager.list();
		this.homeSlideImages = homeSlideImageManager.list();
		if (getSession().containsKey("userInfo")) {
			UserInfo userInfo = (UserInfo)getSession().get("userInfo");
			this.girlInfos = girlFavouriteManager.list(userInfo.getClientInfoId());
		}
		return SUCCESS;
	}
	
	public String girlInfo() {
		getStatisticInfo();
		
		this.categoryInfos = categoryInfoManager.list();
		this.homeSlideImages = homeSlideImageManager.list();
		this.girlInfo = girlInfoManager.getGirlInfo(girlInfoId);
		if(this.girlInfo != null) {
			if (this.girlInfo instanceof AgentGirlInfo) {
				UserInfo userInfo = userInfoManager.getUserByColumnName("agentInfoId", ((AgentGirlInfo) this.girlInfo).getAgentInfoId());
				userInfo = validateUser(userInfo);
				((AgentGirlInfo) this.girlInfo).getAgentInfo().setUserInfo(userInfo);
			} else if (this.girlInfo instanceof FreeAgentGirlInfo) {
				UserInfo userInfo = userInfoManager.getUserByColumnName("girlInfoId", this.girlInfo.getGirlInfoId());
				userInfo = validateUser(userInfo);
				((FreeAgentGirlInfo)this.girlInfo).setUserInfo(userInfo);
			} else if (this.girlInfo instanceof EnGirlInfo) {
				UserInfo userInfo = userInfoManager.getUserByColumnName("girlInfoId", this.girlInfo.getGirlInfoId());
				userInfo = validateUser(userInfo);
				((EnGirlInfo)this.girlInfo).setUserInfo(userInfo);
			} else if (this.girlInfo instanceof ShopGirlInfo) {
				UserInfo userInfo = userInfoManager.getUserByColumnName("shopInfoId", ((ShopGirlInfo) this.girlInfo).getShopInfoId());
				userInfo = validateUser(userInfo);
				((ShopGirlInfo) this.girlInfo).getBasicInfo().setUserInfo(userInfo);
			}
		}
		this.girlServices = girlInfoManager.getGirlServiceListByGirlInfoId(this.girlInfo.getGirlInfoId());
		return SUCCESS;
	}
	
	private UserInfo validateUser(UserInfo userInfo) {
		if(userInfo.getActive().equals(Boolean.TRUE.toString()) 
				&& userInfo.getValidDateFrom() != null && userInfo.getValidDateTo() != null) {
			Date now = new Date();
			if (now.compareTo(userInfo.getValidDateFrom()) < 0
					|| now.compareTo(userInfo.getValidDateTo()) > 0) {
				userInfo.setActive(Boolean.FALSE.toString());
			}
		}
		return userInfo;
	}
	
	public String search() {
		getStatisticInfo();
		
		this.categoryInfos = categoryInfoManager.list();
		this.homeSlideImages = homeSlideImageManager.list();
		this.agentInfos = agentInfoManager.list();
		this.zoneInfos = zoneInfoManager.list();
		
		if(this.frontSearch != null) {
			this.girlInfos = girlInfoManager.search(this.frontSearch, this.feedLimit, this.feedOffset);
		}
		
		return SUCCESS;
	}
	
	public String location() {
		getStatisticInfo();
		
		this.categoryInfos = categoryInfoManager.list();
		this.homeSlideImages = homeSlideImageManager.list();
		this.zoneInfo = zoneInfoManager.getZoneInfo(zoneInfoId);
		this.girlInfos = girlInfoManager.listByZoneInfoId(zoneInfoId);
		return SUCCESS;
	}
	
	public String howtouse() {
		getStatisticInfo();
		
		this.categoryInfos = categoryInfoManager.list();
		this.homeSlideImages = homeSlideImageManager.list();
		this.homeInfo = homeInfoManager.getHomeInfo("0");
		return SUCCESS;
	}
	
	public String contact() {
		getStatisticInfo();
		
		this.categoryInfos = categoryInfoManager.list();
		this.homeSlideImages = homeSlideImageManager.list();
		this.homeInfo = homeInfoManager.getHomeInfo("0");
		return SUCCESS;
	}
	
	protected void loadGirlFavourites(String clientInfoId) {
		this.girlFavourites = girlFavouriteManager.listByCustomerInfoId(clientInfoId);
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

	public List<HomeSlideImage> getHomeSlideImages() {
		return homeSlideImages;
	}

	public void setHomeSlideImages(List<HomeSlideImage> homeSlideImages) {
		this.homeSlideImages = homeSlideImages;
	}

	public FrontSearch getFrontSearch() {
		return frontSearch;
	}

	public void setFrontSearch(FrontSearch frontSearch) {
		this.frontSearch = frontSearch;
	}

	public List<NationalityInfo> getNationalityInfos() {
		return nationalityInfos;
	}

	public void setNationalityInfos(List<NationalityInfo> nationalityInfos) {
		this.nationalityInfos = nationalityInfos;
	}

	public int getFeedLimit() {
		return feedLimit;
	}

	public int getFeedOffset() {
		return feedOffset;
	}

	public void setFeedLimit(int feedLimit) {
		this.feedLimit = feedLimit;
	}

	public void setFeedOffset(int feedOffset) {
		this.feedOffset = feedOffset;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public List<String> getGirlFavourites() {
		return girlFavourites;
	}

	public void setGirlFavourites(List<String> girlFavourites) {
		this.girlFavourites = girlFavourites;
	}
}
