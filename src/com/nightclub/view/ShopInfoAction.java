package com.nightclub.view;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import com.nightclub.common.IConstants;
import com.nightclub.controller.BasicInfoManager;
import com.nightclub.controller.EventInfoManager;
import com.nightclub.controller.MapInfoManager;
import com.nightclub.controller.ScheduleInfoManager;
import com.nightclub.controller.ShopGirlInfoManager;
import com.nightclub.controller.SystemInfoManager;
import com.nightclub.exception.CustomException;
import com.nightclub.exception.IExceptionConstants;
import com.nightclub.model.BasicInfo;
import com.nightclub.model.EventInfo;
import com.nightclub.model.GirlInfo;
import com.nightclub.model.MapInfo;
import com.nightclub.model.ReserveInfo;
import com.nightclub.model.ScheduleInfo;
import com.nightclub.model.ShopGirlInfo;
import com.nightclub.model.SystemInfo;

public class ShopInfoAction extends CommonAction {
	
	private static final long serialVersionUID = 1L;
	Logger log_ = Logger.getLogger(this.getClass().getName());
	
	private String menu;
	private String shopInfoId;
	private String girlInfoId;
	private BasicInfo shop;
	private List<EventInfo> eventInfos;
	private List<GirlInfo> girlInfos;
	private GirlInfo girlInfo;
	private ScheduleInfo scheduleInfo;
	private List<ScheduleInfo> scheduleToday;
	private List<ScheduleInfo> scheduleInfos;
	private Date currentDate;
	private HashMap<String, List<GirlInfo>> hmRanking;
	private SystemInfo systemInfo;
	private MapInfo mapInfo;
	private String eventInfoId;
	private EventInfo eventInfo;
	private List<ReserveInfo> reserveInfos;
	private List<ReserveInfo> availableInfos;
	private String lookupDate;
	private String lookupDateHeader;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdfOutput = new SimpleDateFormat("EEEE, d MMM");
	private ReserveInfo reserveInfo;
	private String actionMessage;
	private String action;
	private String reserveInfoId;

	private BasicInfoManager basicInfoManager;
	private EventInfoManager eventInfoManager;
	private ShopGirlInfoManager girlInfoManager;
	private ScheduleInfoManager scheduleInfoManager;
	private SystemInfoManager systemInfoManager;
	private MapInfoManager mapInfoManager;
//	private ShopReserveInfoManager shopReserveInfoManager;
	
	public ShopInfoAction() {
		super();
		basicInfoManager = new BasicInfoManager();
		eventInfoManager = new EventInfoManager();
		girlInfoManager = new ShopGirlInfoManager();
		scheduleInfoManager = new ScheduleInfoManager();
		systemInfoManager = new SystemInfoManager();
		mapInfoManager = new MapInfoManager();
//		shopReserveInfoManager = new ShopReserveInfoManager();
	}
	
	public String execute() {
		try {
			getStatisticInfo();
		} catch (CustomException ex) {
			if (IExceptionConstants.ACCESS_DENIED.equals(ex.getCode())) {
				return IConstants.ACTION_ACCESS_DENIED;
			}
		}
		this.shop = basicInfoManager.getBasicInfoById(getShopInfoId());
		this.eventInfos = eventInfoManager.list(shop.getShopInfoId());
		
		return SUCCESS;
	}
	
	public String girls() {
		try {
			getStatisticInfo();
		} catch (CustomException ex) {
			if (IExceptionConstants.ACCESS_DENIED.equals(ex.getCode())) {
				return IConstants.ACTION_ACCESS_DENIED;
			}
		}
		this.shop = basicInfoManager.getBasicInfoById(getShopInfoId());
		this.girlInfos = girlInfoManager.list(shop.getShopInfoId());
		
		return SUCCESS;
	}
	
	public String girlInfo() {
		try {
			getStatisticInfo();
		} catch (CustomException ex) {
			if (IExceptionConstants.ACCESS_DENIED.equals(ex.getCode())) {
				return IConstants.ACTION_ACCESS_DENIED;
			}
		}
		this.shop = basicInfoManager.getBasicInfoById(getShopInfoId());
		this.girlInfo = (ShopGirlInfo) girlInfoManager.getGirlInfo(getGirlInfoId());
//		this.scheduleInfo = scheduleInfoManager.getSchduleInfoByGirlInfoId(girlInfo.getGirlInfoId());
//		this.reserveInfos = new ArrayList();
//		if (getSession().containsKey("userInfo")) {
//			UserInfo userInfo = (UserInfo)getSession().get("userInfo");
//			if (userInfo.getClientInfoId() != null) {
//				String clientInfoId = userInfo.getClientInfoId();
//				Date today = new Date();
//				this.lookupDateHeader = sdfOutput.format(today);
//				this.reserveInfos = shopReserveInfoManager.getReserveInfosByGirlInfoIdAndClientId(getGirlInfoId(), clientInfoId, today);
//			}
//		}

//		String filePath = ResourceBundleUtil.getUploadPath();
        
        if(this.girlInfo.getPic1() == null){// || !new File(filePath, this.girlInfo.getPic1()).exists()) {
        	this.girlInfo.setPic1("");
        }
        if(this.girlInfo.getPic2() == null){// || !new File(filePath, this.girlInfo.getPic2()).exists()) {
        	this.girlInfo.setPic2("");
        }
        if(this.girlInfo.getPic3() == null){// || !new File(filePath, this.girlInfo.getPic3()).exists()) {
        	this.girlInfo.setPic3("");
        }
        if(this.girlInfo.getPic4() == null){// || !new File(filePath, this.girlInfo.getPic4()).exists()) {
        	this.girlInfo.setPic4("");
        }
        if(this.girlInfo.getPic5() == null){// || !new File(filePath, this.girlInfo.getPic5()).exists()) {
        	this.girlInfo.setPic5("");
        }
		
		return SUCCESS;
	}
	
	public String newface() {
		try {
			getStatisticInfo();
		} catch (CustomException ex) {
			if (IExceptionConstants.ACCESS_DENIED.equals(ex.getCode())) {
				return IConstants.ACTION_ACCESS_DENIED;
			}
		}
		this.shop = basicInfoManager.getBasicInfoById(getShopInfoId());
		
		// previous month
		Date today = new Date();
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(today); 
		calendar.add(Calendar.MONTH, -1);
		
		this.girlInfos = girlInfoManager.newFaceList(shop.getShopInfoId(), calendar.getTime());
		
		return SUCCESS;
	}
	
	public String ranking() {
		try {
			getStatisticInfo();
		} catch (CustomException ex) {
			if (IExceptionConstants.ACCESS_DENIED.equals(ex.getCode())) {
				return IConstants.ACTION_ACCESS_DENIED;
			}
		}
		this.shop = basicInfoManager.getBasicInfoById(getShopInfoId());
		this.hmRanking = new HashMap<String, List<GirlInfo>>();
		this.hmRanking.put("ranking", girlInfoManager.rankingList(shop.getShopInfoId()));
		this.hmRanking.put("bodySize", girlInfoManager.rankingBodySizeList(shop.getShopInfoId()));
		this.hmRanking.put("miniSize", girlInfoManager.rankingMiniSize(shop.getShopInfoId()));
		this.hmRanking.put("heightSize", girlInfoManager.rankingHeightSize(shop.getShopInfoId()));
		
		return SUCCESS;
	}
	
	public String todayworking() {
		try {
			getStatisticInfo();
		} catch (CustomException ex) {
			if (IExceptionConstants.ACCESS_DENIED.equals(ex.getCode())) {
				return IConstants.ACTION_ACCESS_DENIED;
			}
		}
		this.shop = basicInfoManager.getBasicInfoById(getShopInfoId());
		
		// day of week
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		
		this.currentDate = new Date();
		this.scheduleToday = scheduleInfoManager.scheduleGirlList(shop.getShopInfoId(), dayOfWeek);
		this.scheduleInfos = scheduleInfoManager.list(shop.getShopInfoId());
		log_.info("this.scheduleInfos >> " + this.scheduleInfos.size());
		return SUCCESS;
	}
	
	public String system() {
		try {
			getStatisticInfo();
		} catch (CustomException ex) {
			if (IExceptionConstants.ACCESS_DENIED.equals(ex.getCode())) {
				return IConstants.ACTION_ACCESS_DENIED;
			}
		}
		this.shop = basicInfoManager.getBasicInfoById(getShopInfoId());
		this.systemInfo = systemInfoManager.getSystemInfo(shop.getShopInfoId());
		if(this.systemInfo == null) {
			this.systemInfo = new SystemInfo();
		}
		
		return SUCCESS;
	}
	
	public String map() {
		try {
			getStatisticInfo();
		} catch (CustomException ex) {
			if (IExceptionConstants.ACCESS_DENIED.equals(ex.getCode())) {
				return IConstants.ACTION_ACCESS_DENIED;
			}
		}
		this.shop = basicInfoManager.getBasicInfoById(getShopInfoId());
		this.mapInfo = mapInfoManager.getMapInfo(shop.getShopInfoId());
		
		return SUCCESS;
	}
	
	public String eventInfo() {
		try {
			getStatisticInfo();
		} catch (CustomException ex) {
			if (IExceptionConstants.ACCESS_DENIED.equals(ex.getCode())) {
				return IConstants.ACTION_ACCESS_DENIED;
			}
		}
		this.shop = basicInfoManager.getBasicInfoById(getShopInfoId());
		this.eventInfo = eventInfoManager.getEventInfo(getEventInfoId());
		
		return SUCCESS;
	}
	
	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getShopInfoId() {
		return shopInfoId;
	}

	public void setShopInfoId(String shopInfoId) {
		this.shopInfoId = shopInfoId;
	}

	public List<EventInfo> getEventInfos() {
		return eventInfos;
	}

	public void setEventInfos(List<EventInfo> eventInfos) {
		this.eventInfos = eventInfos;
	}

	public List<GirlInfo> getGirlInfos() {
		return girlInfos;
	}

	public void setGirlInfos(List<GirlInfo> girlInfos) {
		this.girlInfos = girlInfos;
	}

	public String getGirlInfoId() {
		return girlInfoId;
	}

	public void setGirlInfoId(String girlInfoId) {
		this.girlInfoId = girlInfoId;
	}

	public GirlInfo getGirlInfo() {
		return girlInfo;
	}

	public void setGirlInfo(GirlInfo girlInfo) {
		this.girlInfo = girlInfo;
	}

	public ScheduleInfo getScheduleInfo() {
		return scheduleInfo;
	}

	public void setScheduleInfo(ScheduleInfo scheduleInfo) {
		this.scheduleInfo = scheduleInfo;
	}

	public HashMap<String, List<GirlInfo>> getHmRanking() {
		return hmRanking;
	}

	public void setHmRanking(HashMap<String, List<GirlInfo>> hmRanking) {
		this.hmRanking = hmRanking;
	}

	public SystemInfo getSystemInfo() {
		return systemInfo;
	}

	public void setSystemInfo(SystemInfo systemInfo) {
		this.systemInfo = systemInfo;
	}

	public List<ScheduleInfo> getScheduleInfos() {
		return scheduleInfos;
	}

	public void setScheduleInfos(List<ScheduleInfo> scheduleInfos) {
		this.scheduleInfos = scheduleInfos;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public MapInfo getMapInfo() {
		return mapInfo;
	}

	public void setMapInfo(MapInfo mapInfo) {
		this.mapInfo = mapInfo;
	}

	public BasicInfo getShop() {
		return shop;
	}

	public void setShop(BasicInfo shop) {
		this.shop = shop;
	}

	public List<ScheduleInfo> getScheduleToday() {
		return scheduleToday;
	}

	public void setScheduleToday(List<ScheduleInfo> scheduleToday) {
		this.scheduleToday = scheduleToday;
	}

	public String getEventInfoId() {
		return eventInfoId;
	}

	public EventInfo getEventInfo() {
		return eventInfo;
	}

	public void setEventInfoId(String eventInfoId) {
		this.eventInfoId = eventInfoId;
	}

	public void setEventInfo(EventInfo eventInfo) {
		this.eventInfo = eventInfo;
	}

	public List<ReserveInfo> getReserveInfos() {
		return reserveInfos;
	}

	public void setReserveInfos(List<ReserveInfo> reserveInfos) {
		this.reserveInfos = reserveInfos;
	}

	public String getLookupDate() {
		return lookupDate;
	}

	public void setLookupDate(String lookupDate) {
		this.lookupDate = lookupDate;
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

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getReserveInfoId() {
		return reserveInfoId;
	}

	public void setReserveInfoId(String reserveInfoId) {
		this.reserveInfoId = reserveInfoId;
	}
}
