package com.nightclub.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.apache.commons.lang.StringEscapeUtils;

import com.nightclub.common.IConstants;
import com.nightclub.controller.AdsInfoManager;
import com.nightclub.controller.AgentGirlInfoManager;
import com.nightclub.controller.AgentInfoManager;
import com.nightclub.controller.BasicInfoManager;
import com.nightclub.controller.CategoryInfoManager;
import com.nightclub.controller.CountryInfoManager;
import com.nightclub.controller.EnGirlInfoManager;
import com.nightclub.controller.FreeAgentGirlInfoManager;
import com.nightclub.controller.GenderInfoManager;
import com.nightclub.controller.GirlCommentManager;
import com.nightclub.controller.GirlFavouriteManager;
import com.nightclub.controller.GirlInfoManager;
import com.nightclub.controller.GirlReserveInfoManager;
import com.nightclub.controller.HomeInfoManager;
import com.nightclub.controller.HomeSlideImageManager;
import com.nightclub.controller.NationalityInfoManager;
import com.nightclub.controller.NewsInfoManager;
import com.nightclub.controller.ProvinceInfoManager;
import com.nightclub.controller.UserInfoManager;
import com.nightclub.controller.ZoneInfoManager;
import com.nightclub.model.AdsInfo;
import com.nightclub.model.AgentGirlInfo;
import com.nightclub.model.AgentInfo;
import com.nightclub.model.BasicInfo;
import com.nightclub.model.CategoryInfo;
import com.nightclub.model.CountryInfo;
import com.nightclub.model.EnGirlInfo;
import com.nightclub.model.FormDay;
import com.nightclub.model.FreeAgentGirlInfo;
import com.nightclub.model.FrontSearch;
import com.nightclub.model.GenderInfo;
import com.nightclub.model.GirlComment;
import com.nightclub.model.GirlInfo;
import com.nightclub.model.GirlProvince;
import com.nightclub.model.GirlService;
import com.nightclub.model.GirlServiceInfo;
import com.nightclub.model.HomeInfo;
import com.nightclub.model.HomeSlideImage;
import com.nightclub.model.NationalityInfo;
import com.nightclub.model.NewsInfo;
import com.nightclub.model.ProvinceInfo;
import com.nightclub.model.ReserveGirlService;
import com.nightclub.model.ReserveInfo;
import com.nightclub.model.ShopGirlInfo;
import com.nightclub.model.UserInfo;
import com.nightclub.model.ZoneInfo;
import com.nightclub.util.LineApiUtils;

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
	private List<CountryInfo> countryInfos;
	private List<ProvinceInfo> provinceInfos;
	private List<GirlProvince> girlProvinces;
	private List<GenderInfo> genderInfos;
	private GirlComment girlComment;
	private String lookupDateHeader;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdfOutput = new SimpleDateFormat("EEEE, d MMM");
	private String lookupDate;
	private List<ReserveInfo> reserveInfos;
	private List<ReserveInfo> availableInfos;
	private String reserveInfoId;
	private ReserveInfo reserveInfo;
	private String actionMessage;
	private List<FormDay> workingDateList;
	private List<String> girlServicesInfoId;

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
	private CountryInfoManager countryInfoManager;
	private ProvinceInfoManager provinceInfoManager;
	private GenderInfoManager genderInfoManager;
	private GirlCommentManager girlCommentManager;
	private GirlReserveInfoManager girlReserveInfoManager;

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
		countryInfoManager = new CountryInfoManager();
		provinceInfoManager = new ProvinceInfoManager();
		genderInfoManager = new GenderInfoManager();
		girlCommentManager = new GirlCommentManager();
		girlReserveInfoManager = new GirlReserveInfoManager();
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
		this.countryInfos = countryInfoManager.list();
		if(this.frontSearch.getCountryInfoId() != null && !"".equals(this.frontSearch.getCountryInfoId())) {
			this.provinceInfos = provinceInfoManager.listByCountry(this.frontSearch.getCountryInfoId());
		}
		this.genderInfos = genderInfoManager.list();
		this.homeInfo = homeInfoManager.getHomeInfo("0");
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
		this.countryInfos = countryInfoManager.list();
		if(this.frontSearch.getCountryInfoId() != null && !"".equals(this.frontSearch.getCountryInfoId())) {
			this.provinceInfos = provinceInfoManager.listByCountry(this.frontSearch.getCountryInfoId());
		}
		this.genderInfos = genderInfoManager.list();
		this.homeInfo = homeInfoManager.getHomeInfo("0");
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
		this.countryInfos = countryInfoManager.list();
		if(this.frontSearch.getCountryInfoId() != null && !"".equals(this.frontSearch.getCountryInfoId())) {
			this.provinceInfos = provinceInfoManager.listByCountry(this.frontSearch.getCountryInfoId());
		}
		this.genderInfos = genderInfoManager.list();
		this.homeInfo = homeInfoManager.getHomeInfo("0");
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
		if ("addGirlComment".equals(getAction())) {
			addGirlComment();
		}
		getStatisticInfo();
		
		this.categoryInfos = categoryInfoManager.list();
		this.homeSlideImages = homeSlideImageManager.list();
		this.girlInfo = girlInfoManager.getGirlInfo(girlInfoId);
		this.homeInfo = homeInfoManager.getHomeInfo("0");
		if(this.girlInfo != null) {
			if (this.girlInfo instanceof AgentGirlInfo) {
				UserInfo userInfo = userInfoManager.getUserByColumnName("agentInfoId", ((AgentGirlInfo) this.girlInfo).getAgentInfoId());
				userInfo = validateUser(userInfo);
				((AgentGirlInfo) this.girlInfo).getAgentInfo().setUserInfo(userInfo);
				
				Calendar calender = Calendar.getInstance();
				workingDateList = new ArrayList();
				FormDay formDay;
				for(int i=0; i<5; i++) {
					formDay = new FormDay();
					formDay.setDate(calender.getTime());
					formDay.setSunday(calender.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
					formDay.setMonday(calender.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY);
					formDay.setTuesday(calender.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY);
					formDay.setWednesday(calender.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY);
					formDay.setThursday(calender.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY);
					formDay.setFriday(calender.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY);
					formDay.setSaturday(calender.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY);
					workingDateList.add(formDay);
					calender.add(Calendar.DATE, 1);
				}
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
		this.girlProvinces = girlInfoManager.getGirlProvinceListByGirlInfoId(this.girlInfo.getGirlInfoId());
		Date today = new Date();
		this.lookupDateHeader = sdfOutput.format(today);
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

	public void addGirlComment() {
		girlComment.setGirlCommentId(UUID.randomUUID().toString().toUpperCase());
		girlComment.setCreatedDate(new Date());
		girlCommentManager.add(girlComment);
		addActionMessage(getTexts("global").getString("global.message_success_add"));
		girlComment.setComment("");
		girlComment.setRating(0);
	}
	
	public String girlReserveInfo() {
		//if (getSession().containsKey("userInfo")) {
			//UserInfo userInfo = (UserInfo)getSession().get("userInfo");
			//if (userInfo.getClientInfoId() != null) {
				String clientInfoId = "";//userInfo.getClientInfoId();
				Date date = new Date();
				try {
					date = sdf.parse(lookupDate);
					this.lookupDateHeader = sdfOutput.format(date);
					this.reserveInfos = girlReserveInfoManager.getReserveInfosByGirlInfoIdAndClientId(getGirlInfoId(), clientInfoId, date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			//}
		//}
		
		return SUCCESS;
	}
	
//	public String girlAvailable() {
////		if (getSession().containsKey("userInfo")) {
////			UserInfo userInfo = (UserInfo)getSession().get("userInfo");
////			if (userInfo.getClientInfoId() != null) {
////				String clientInfoId = userInfo.getClientInfoId();
//				Date date = new Date();
//				try {
//					date = sdf.parse(lookupDate);
//					this.reserveInfos = girlReserveInfoManager.getReserveInfosByGirlInfoId(getGirlInfoId(), date);
//					this.availableInfos = new ArrayList();
//					String availableStartTime = "12:00";
//					String availableEndTime = "12:00";
//					SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
//					Iterator it = reserveInfos.iterator();
//					Date availableEndTimeDate = null;
//					Date reserveStartTimeDate = null;
//					boolean isUpdatePreviousReserve = false;
//					while(it.hasNext()) {
//						ReserveInfo reserveInfoData = (ReserveInfo) it.next();
//						availableEndTimeDate = sdfTime.parse(availableEndTime);
//						reserveStartTimeDate = sdfTime.parse(reserveInfoData.getStartTime());
//						availableEndTime = reserveInfoData.getStartTime();
//						if(reserveInfoData.getReserveInfoId().equals(getReserveInfoId())) {
//							availableEndTime = reserveInfoData.getEndTime();
//						}
//						isUpdatePreviousReserve = false;
//						if(availableEndTimeDate.compareTo(reserveStartTimeDate) < 0
//								|| reserveInfoData.getReserveInfoId().equals(getReserveInfoId())) {
//							if (this.availableInfos.size() > 0) {
//								// if previous available end time equals to current available start time, then merge together
//								ReserveInfo previousAvailableInfo = this.availableInfos.get(this.availableInfos.size()-1);
//								if (previousAvailableInfo.getEndTime().equals(availableStartTime)) {
//									previousAvailableInfo.setEndTime(availableEndTime);
//									isUpdatePreviousReserve = true;
//								}
//							}
//							if (!isUpdatePreviousReserve) {
//								ReserveInfo availableInfo = new ReserveInfo();
//								availableInfo.setStartTime(availableStartTime);
//								availableInfo.setEndTime(availableEndTime);
//								this.availableInfos.add(availableInfo);
//							}
//						}
//						availableStartTime = reserveInfoData.getEndTime();
//						availableEndTime = reserveInfoData.getEndTime();
//					}
//					String endTime = "24:00";
//					Date endTimeDate = sdfTime.parse(endTime);
//					availableEndTimeDate = sdfTime.parse(availableEndTime);
//					isUpdatePreviousReserve = false;
//					if(availableEndTimeDate.compareTo(endTimeDate) < 0) {
//						if (this.availableInfos.size() > 0) {
//							// if previous available end time equals to current available start time, then merge together
//							ReserveInfo previousAvailableInfo = this.availableInfos.get(this.availableInfos.size()-1);
//							if (previousAvailableInfo.getEndTime().equals(availableStartTime)) {
//								previousAvailableInfo.setEndTime(endTime);
//								isUpdatePreviousReserve = true;
//							}
//						}
//						if (!isUpdatePreviousReserve) {
//							ReserveInfo availableInfo = new ReserveInfo();
//							availableInfo.setStartTime(availableStartTime);
//							availableInfo.setEndTime(endTime);
//							this.availableInfos.add(availableInfo);
//						}
//					}
//				} catch (ParseException e) {
//					e.printStackTrace();
//				}
////			}
////		}
//		
//		return SUCCESS;
//	}
	
//	public String girlReserveExecute() {
//		if(getAction() != null) {
//			if(getAction().equals("add")) {
//				return girlReserveAdd();
//			} else if(getAction().equals("update")) {
//				return girlReserveUpdate();
//			}
//		}
//		return SUCCESS;
//	}

//	public String girlReserveAdd() {
////		if (getSession().containsKey("userInfo")) {
//			try {
//				this.reserveInfos = girlReserveInfoManager.getReserveInfosByGirlInfoId(reserveInfo.getGirlInfoId(), reserveInfo.getReserveDate());
//				Iterator it = reserveInfos.iterator();
//				SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
//				Date addStartTimeDate = sdfTime.parse(reserveInfo.getStartTime());
//				Date addEndTimeDate = sdfTime.parse(reserveInfo.getEndTime());
//				Date reserveStartTimeDate = null;
//				Date reserveEndTimeDate = null;
//				boolean isOverlapped = false;
//				while(it.hasNext()) {
//					ReserveInfo reserveInfoData = (ReserveInfo) it.next();
//					reserveStartTimeDate = sdfTime.parse(reserveInfoData.getStartTime());
//					reserveEndTimeDate = sdfTime.parse(reserveInfoData.getEndTime());
//					// overlapped left
//					if (addStartTimeDate.compareTo(reserveStartTimeDate) < 0 
//						&& addEndTimeDate.compareTo(reserveStartTimeDate) > 0 ) {
//						isOverlapped = true;
//						break;
//					// overlapped inner
//					} else if (addStartTimeDate.compareTo(reserveStartTimeDate) >= 0 
//						&& addEndTimeDate.compareTo(reserveEndTimeDate) <= 0 ) {
//						isOverlapped = true;
//						break;
//					// overlapped right	
//					} else if (addStartTimeDate.compareTo(reserveEndTimeDate) < 0 
//						&& addEndTimeDate.compareTo(reserveEndTimeDate) > 0 ) {
//						isOverlapped = true;
//						break;
//					// overlapped cover	
//					} else if (addStartTimeDate.compareTo(reserveStartTimeDate) < 0 
//						&& addEndTimeDate.compareTo(reserveEndTimeDate) > 0 ) {
//						isOverlapped = true;
//						break;
//					}
//				}
//				if (isOverlapped) {
//					actionMessage = "Your time is overlapped, please check.";
//				} else {
////					UserInfo userInfo = (UserInfo)getSession().get("userInfo");
//					reserveInfo.setReserveInfoId(UUID.randomUUID().toString().toUpperCase());
////					reserveInfo.setClientInfoId(userInfo.getClientInfoId());
//					girlReserveInfoManager.add(this.reserveInfo);
//					GirlInfo girlInfo = girlInfoManager.getGirlInfo(this.reserveInfo.getGirlInfoId());
//					if (girlInfo instanceof AgentGirlInfo) {
//						AgentInfo agentInfo = agentInfoManager.getAgentInfo(((AgentGirlInfo) girlInfo).getAgentInfoId());
//						HomeInfo homeInfo = homeInfoManager.getHomeInfo("0");
////						ClientInfo clientInfo = clientInfoManager.getClientInfo(userInfo.getClientInfoId());
//						String lookupDateHeader = sdfOutput.format(reserveInfo.getReserveDate());
////						if(homeInfo.getLineChannelAccessToken() != null && !"".equals(homeInfo.getLineChannelAccessToken())) {
//						if(agentInfo.getLineToken() != null && !"".equals(agentInfo.getLineToken())) {
//							String message = getTexts("global_th").getString("global.add_reserve_success");
//							log_.info(StringEscapeUtils.escapeJava(message));
////							LineApiUtils.sendMessageApi(StringEscapeUtils.escapeJava(message
////												+ "\\n" + "Test user"
////												+ "\\n" + lookupDateHeader
////												+ "\\n" + reserveInfo.getStartTime()
////												+ " - " + reserveInfo.getEndTime()), homeInfo.getLineChannelAccessToken(), agentInfo.getLineId());
//							LineApiUtils.sendData(StringEscapeUtils.escapeJava(
//												"\n" + message
//												+ "\n" + reserveInfo.getClientName()
//												+ "\n" + reserveInfo.getMobile()
//												+ "\n" + lookupDateHeader
//												+ "\n" + reserveInfo.getStartTime()
//												+ " - " + reserveInfo.getEndTime()), agentInfo.getLineToken());
//						}
//					}
//				}
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
////		}
//		return SUCCESS;
//	}
//
//	public String girlReserveUpdate() {
//		if (getSession().containsKey("userInfo")) {
//			try {
//				this.reserveInfos = girlReserveInfoManager.getReserveInfosByGirlInfoId(reserveInfo.getGirlInfoId(), reserveInfo.getReserveDate());
//				Iterator it = reserveInfos.iterator();
//				SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
//				Date addStartTimeDate = sdfTime.parse(reserveInfo.getStartTime());
//				Date addEndTimeDate = sdfTime.parse(reserveInfo.getEndTime());
//				Date reserveStartTimeDate = null;
//				Date reserveEndTimeDate = null;
//				boolean isOverlapped = false;
//				while(it.hasNext()) {
//					ReserveInfo reserveInfoData = (ReserveInfo) it.next();
//					if (reserveInfo.getReserveInfoId().equals(reserveInfoData.getReserveInfoId())) {
//						continue;
//					}
//					reserveStartTimeDate = sdfTime.parse(reserveInfoData.getStartTime());
//					reserveEndTimeDate = sdfTime.parse(reserveInfoData.getEndTime());
//					// overlapped left
//					if (addStartTimeDate.compareTo(reserveStartTimeDate) < 0 
//						&& addEndTimeDate.compareTo(reserveStartTimeDate) > 0 ) {
//						isOverlapped = true;
//						break;
//					// overlapped inner
//					} else if (addStartTimeDate.compareTo(reserveStartTimeDate) >= 0 
//						&& addEndTimeDate.compareTo(reserveEndTimeDate) <= 0 ) {
//						isOverlapped = true;
//						break;
//					// overlapped right	
//					} else if (addStartTimeDate.compareTo(reserveEndTimeDate) < 0 
//						&& addEndTimeDate.compareTo(reserveEndTimeDate) > 0 ) {
//						isOverlapped = true;
//						break;
//					// overlapped cover	
//					} else if (addStartTimeDate.compareTo(reserveStartTimeDate) < 0 
//						&& addEndTimeDate.compareTo(reserveEndTimeDate) > 0 ) {
//						isOverlapped = true;
//						break;
//					}
//				}
//				if (isOverlapped) {
//					actionMessage = "Your time is overlapped, please check.";
//				} else {
//					UserInfo userInfo = (UserInfo)getSession().get("userInfo");
//					reserveInfo.setClientInfoId(userInfo.getClientInfoId());
//					girlReserveInfoManager.update(this.reserveInfo);
//					GirlInfo girlInfo = girlInfoManager.getGirlInfo(this.reserveInfo.getGirlInfoId());
//					if (girlInfo instanceof AgentGirlInfo) {
//						AgentInfo agentInfo = agentInfoManager.getAgentInfo(((AgentGirlInfo) girlInfo).getAgentInfoId());
//						HomeInfo homeInfo = homeInfoManager.getHomeInfo("0");
//						ClientInfo clientInfo = clientInfoManager.getClientInfo(userInfo.getClientInfoId());
//						String lookupDateHeader = sdfOutput.format(reserveInfo.getReserveDate());
//						if(homeInfo.getLineChannelAccessToken() != null && !"".equals(homeInfo.getLineChannelAccessToken())) {
//							LineApiUtils.sendMessageApi(getTexts("global_th").getString("global.update_reserve_success")
//									+ "\n" + clientInfo.getNickName() 
//									+ "\n" + lookupDateHeader
//									+ "\n" + reserveInfo.getStartTime()
//									+ " - " + reserveInfo.getEndTime(), homeInfo.getLineChannelAccessToken(), agentInfo.getLineId());
//						}
//					}
//				}
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return SUCCESS;
//	}
//	
//	public String girlReserveDelete() {
////		if (getSession().containsKey("userInfo")) {
//			try {
//				ReserveInfo reserveInfo = girlReserveInfoManager.getReserveInfo(this.reserveInfoId);
////				UserInfo userInfo = (UserInfo)getSession().get("userInfo");
//				GirlInfo girlInfo = girlInfoManager.getGirlInfo(reserveInfo.getGirlInfoId());
//				if (girlInfo instanceof AgentGirlInfo) {
//					AgentInfo agentInfo = agentInfoManager.getAgentInfo(((AgentGirlInfo) girlInfo).getAgentInfoId());
//					HomeInfo homeInfo = homeInfoManager.getHomeInfo("0");
////					ClientInfo clientInfo = clientInfoManager.getClientInfo(userInfo.getClientInfoId());
//					String lookupDateHeader = sdfOutput.format(reserveInfo.getReserveDate());
////					if(homeInfo.getLineChannelAccessToken() != null && !"".equals(homeInfo.getLineChannelAccessToken())) {
//					if(agentInfo.getLineToken() != null && !"".equals(agentInfo.getLineToken())) {
//						System.out.println(getTexts("global_th").getString("global.delete_reserve_success"));
////						LineApiUtils.sendMessageApi(getTexts("global_th").getString("global.delete_reserve_success")
////								+ "\n" + clientInfo.getNickName() 
////								+ "\n" + lookupDateHeader
////								+ "\n" + reserveInfo.getStartTime()
////								+ " - " + reserveInfo.getEndTime(), homeInfo.getLineChannelAccessToken(), agentInfo.getLineId());
//						String message = getTexts("global_th").getString("global.delete_reserve_success");
//						LineApiUtils.sendData(StringEscapeUtils.escapeJava(
//								"\n" + message
//								+ "\n" + reserveInfo.getClientName()
//								+ "\n" + reserveInfo.getMobile()
//								+ "\n" + lookupDateHeader
//								+ "\n" + reserveInfo.getStartTime()
//								+ " - " + reserveInfo.getEndTime()), agentInfo.getLineToken());
//					}
//				}
//				girlReserveInfoManager.delete(this.reserveInfoId);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
////		}
//		return SUCCESS;
//	}

	public String booking() {
		getStatisticInfo();

		this.categoryInfos = categoryInfoManager.list();
		this.homeSlideImages = homeSlideImageManager.list();
		this.girlInfo = girlInfoManager.getGirlInfo(girlInfoId);
		this.homeInfo = homeInfoManager.getHomeInfo("0");
		if(this.girlInfo != null) {
			if (this.girlInfo instanceof AgentGirlInfo) {
				UserInfo userInfo = userInfoManager.getUserByColumnName("agentInfoId", ((AgentGirlInfo) this.girlInfo).getAgentInfoId());
				userInfo = validateUser(userInfo);
				((AgentGirlInfo) this.girlInfo).getAgentInfo().setUserInfo(userInfo);
				
				Calendar calender = Calendar.getInstance();
				workingDateList = new ArrayList();
				FormDay formDay;
				for(int i=0; i<5; i++) {
					formDay = new FormDay();
					formDay.setDate(calender.getTime());
					formDay.setSunday(calender.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
					formDay.setMonday(calender.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY);
					formDay.setTuesday(calender.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY);
					formDay.setWednesday(calender.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY);
					formDay.setThursday(calender.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY);
					formDay.setFriday(calender.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY);
					formDay.setSaturday(calender.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY);
					workingDateList.add(formDay);
					calender.add(Calendar.DATE, 1);
				}
			}
		}
		this.girlServices = girlInfoManager.getGirlServiceListByGirlInfoId(this.girlInfo.getGirlInfoId());
		return SUCCESS;
	}

	public String bookingadd() {
		try {
			reserveInfo.setReserveInfoId(UUID.randomUUID().toString().toUpperCase());
			this.reserveInfo.getReserveGirlServices().clear();
            ReserveGirlService reserveGirlService;
            if (getGirlServicesInfoId() != null) {
	            for(int i = 0; i<getGirlServicesInfoId().size(); i++) {
	            	String girlServiceInfoId = getGirlServicesInfoId().get(i);
	            	GirlServiceInfo girlServiceInfo = new GirlServiceInfo();
	            	girlServiceInfo.setGirlServiceInfoId(girlServiceInfoId);
	
	            	reserveGirlService = new ReserveGirlService();
	            	reserveGirlService.setGirlServiceInfo(girlServiceInfo);
	            	reserveGirlService.setReserveInfo(this.reserveInfo);
					this.reserveInfo.getReserveGirlServices().add(reserveGirlService);
				}
            }
			girlReserveInfoManager.add(this.reserveInfo);
			this.girlInfo = girlInfoManager.getGirlInfo(reserveInfo.getGirlInfoId());
			AgentInfo agentInfo = agentInfoManager.getAgentInfo(((AgentGirlInfo) girlInfo).getAgentInfoId());
			if(agentInfo.getLineToken() != null && !"".equals(agentInfo.getLineToken())) {
				String message = getTexts("global_th").getString("global.add_reserve_success");
				log_.info(StringEscapeUtils.escapeJava(message));
				LineApiUtils.sendData(StringEscapeUtils.escapeJava(
									"\n" + message
									+ "\n" + reserveInfo.getClientName()
									+ "\n" + sdfOutput.format(reserveInfo.getReserveDate())
									+ "\n" + reserveInfo.getReserveTime()), agentInfo.getLineToken());
			}

			addActionMessage("You have been successfully inserted");

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
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

	public List<CountryInfo> getCountryInfos() {
		return countryInfos;
	}

	public List<ProvinceInfo> getProvinceInfos() {
		return provinceInfos;
	}

	public void setCountryInfos(List<CountryInfo> countryInfos) {
		this.countryInfos = countryInfos;
	}

	public void setProvinceInfos(List<ProvinceInfo> provinceInfos) {
		this.provinceInfos = provinceInfos;
	}

	public List<GirlProvince> getGirlProvinces() {
		return girlProvinces;
	}

	public void setGirlProvinces(List<GirlProvince> girlProvinces) {
		this.girlProvinces = girlProvinces;
	}

	public List<GenderInfo> getGenderInfos() {
		return genderInfos;
	}

	public void setGenderInfos(List<GenderInfo> genderInfos) {
		this.genderInfos = genderInfos;
	}

	public GirlComment getGirlComment() {
		return girlComment;
	}

	public void setGirlComment(GirlComment girlComment) {
		this.girlComment = girlComment;
	}

	public String getLookupDateHeader() {
		return lookupDateHeader;
	}

	public void setLookupDateHeader(String lookupDateHeader) {
		this.lookupDateHeader = lookupDateHeader;
	}

	public List<ReserveInfo> getAvailableInfos() {
		return availableInfos;
	}

	public void setAvailableInfos(List<ReserveInfo> availableInfos) {
		this.availableInfos = availableInfos;
	}

	public String getReserveInfoId() {
		return reserveInfoId;
	}

	public void setReserveInfoId(String reserveInfoId) {
		this.reserveInfoId = reserveInfoId;
	}

	public ReserveInfo getReserveInfo() {
		return reserveInfo;
	}

	public void setReserveInfo(ReserveInfo reserveInfo) {
		this.reserveInfo = reserveInfo;
	}

	public String getActionMessage() {
		return actionMessage;
	}

	public void setActionMessage(String actionMessage) {
		this.actionMessage = actionMessage;
	}

	public String getLookupDate() {
		return lookupDate;
	}

	public List<ReserveInfo> getReserveInfos() {
		return reserveInfos;
	}

	public void setLookupDate(String lookupDate) {
		this.lookupDate = lookupDate;
	}

	public void setReserveInfos(List<ReserveInfo> reserveInfos) {
		this.reserveInfos = reserveInfos;
	}

	public List<FormDay> getWorkingDateList() {
		return workingDateList;
	}

	public void setWorkingDateList(List<FormDay> workingDateList) {
		this.workingDateList = workingDateList;
	}

	public List<String> getGirlServicesInfoId() {
		return girlServicesInfoId;
	}

	public void setGirlServicesInfoId(List<String> girlServicesInfoId) {
		this.girlServicesInfoId = girlServicesInfoId;
	}
}
