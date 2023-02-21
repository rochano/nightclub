package com.nightclub.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.viralpatel.contact.util.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.nightclub.model.AgentGirlInfo;
import com.nightclub.model.AgentInfo;
import com.nightclub.model.CountryInfo;
import com.nightclub.model.EnGirlInfo;
import com.nightclub.model.FreeAgentGirlInfo;
import com.nightclub.model.FrontSearch;
import com.nightclub.model.GirlInfo;
import com.nightclub.model.GirlLocation;
import com.nightclub.model.GirlProvince;
import com.nightclub.model.GirlService;
import com.nightclub.model.GirlServiceInfo;
import com.nightclub.model.UserInfo;

public class GirlInfoManager extends HibernateUtil {
	
	private static Logger log_ = Logger.getLogger(GirlInfoManager.class);
	private Map mapAgentInfo = new HashMap();
	private Map mapCountryInfo = new HashMap();
	private Map mapUserInfo = new HashMap();
	
	public GirlInfo add(GirlInfo girlInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(girlInfo);
		session.getTransaction().commit();
		return girlInfo;
	}
	
	public GirlInfo update(GirlInfo girlInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		deleteGirlLocationInfo(girlInfo.getGirlInfoId());
		deleteGirlServiceInfo(girlInfo.getGirlInfoId());
		deleteGirlProvinceInfo(girlInfo.getGirlInfoId());
		session.saveOrUpdate(girlInfo);
		session.getTransaction().commit();
		return girlInfo;
	}

	public void deleteGirlServiceInfo(String girlInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.createQuery("delete from GirlService gs where gs.primaryKey.freeAgentGirlInfo.girlInfoId = :girlInfoId")
			.setParameter("girlInfoId", girlInfoId).executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	
	public void deleteGirlLocationInfo(String girlInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.createQuery("delete from GirlLocation gl where gl.primaryKey.girlInfo.girlInfoId = :girlInfoId")
			.setParameter("girlInfoId", girlInfoId).executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	
	public void deleteGirlProvinceInfo(String girlInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.createQuery("delete from GirlProvince gp where gp.primaryKey.girlInfo.girlInfoId = :girlInfoId")
			.setParameter("girlInfoId", girlInfoId).executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	
	public GirlInfo delete(String girlInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		GirlInfo girlInfo = (GirlInfo) session.load(GirlInfo.class, girlInfoId);
		if(null != girlInfo) {
//			deleteGirlLocationInfo(girlInfo.getGirlInfoId());
			deleteGirlServiceInfo(girlInfo.getGirlInfoId());
			deleteGirlProvinceInfo(girlInfo.getGirlInfoId());
			session.delete(girlInfo);
		}
		session.getTransaction().commit();
		return girlInfo;
	}

//	@SuppressWarnings("unchecked")
//	public List<GirlInfo> list(String shopInfoId) {
//		
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
//		List<GirlInfo> girlInfos = null;
//		try {
//			
//			girlInfos = (List<GirlInfo>)session.createQuery("from GirlInfo where shopInfoId = :shopInfoId ")
//					.setParameter("shopInfoId", shopInfoId).list();
//			
//		} catch (HibernateException e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//		session.getTransaction().commit();
//		return girlInfos;
//	}
	
//	@SuppressWarnings("unchecked")
//	public List<GirlInfo> newFaceList(String shopInfoId, Date prevMonth) {
//		
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
//		List<GirlInfo> girlInfos = null;
//		try {
//			
//			girlInfos = (List<GirlInfo>)session
//				.createQuery("from GirlInfo where shopInfoId = :shopInfoId and createdDate >= :prevMonth ")
//				.setParameter("shopInfoId", shopInfoId)
//				.setParameter("prevMonth", prevMonth)
//				.list();
//			
//		} catch (HibernateException e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//		session.getTransaction().commit();
//		return girlInfos;
//	}
	
//	@SuppressWarnings("unchecked")
//	public List<GirlInfo> rankingList(String shopInfoId) {
//		
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
//		List<GirlInfo> girlInfos = null;
//		try {
//			
//			girlInfos = (List<GirlInfo>)session
//				.createQuery("from GirlInfo where shopInfoId = :shopInfoId order by ranking asc ")
//				.setParameter("shopInfoId", shopInfoId).list();
//			
//			if(girlInfos.size() > 5) {
//				girlInfos = girlInfos.subList(0, 5);
//			}
//			
//		} catch (HibernateException e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//		session.getTransaction().commit();
//		return girlInfos;
//	}
	
//	@SuppressWarnings("unchecked")
//	public List<GirlInfo> rankingBodySizeList(String shopInfoId) {
//		
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
//		List<GirlInfo> girlInfos = null;
//		try {
//			
//			girlInfos = (List<GirlInfo>)session
//				.createQuery("from GirlInfo where shopInfoId = :shopInfoId order by bustSize desc, hipSize desc ")
//				.setParameter("shopInfoId", shopInfoId).list();
//			
//			if(girlInfos.size() > 5) {
//				girlInfos = girlInfos.subList(0, 5);
//			}
//			
//		} catch (HibernateException e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//		session.getTransaction().commit();
//		return girlInfos;
//	}
	
//	@SuppressWarnings("unchecked")
//	public List<GirlInfo> rankingMiniSize(String shopInfoId) {
//		
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
//		List<GirlInfo> girlInfos = null;
//		try {
//			
//			girlInfos = (List<GirlInfo>)session
//				.createQuery("from GirlInfo where shopInfoId = :shopInfoId order by height asc ")
//				.setParameter("shopInfoId", shopInfoId).list();
//			
//			if(girlInfos.size() > 5) {
//				girlInfos = girlInfos.subList(0, 5);
//			}
//			
//		} catch (HibernateException e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//		session.getTransaction().commit();
//		return girlInfos;
//	}
	
//	@SuppressWarnings("unchecked")
//	public List<GirlInfo> rankingHeightSize(String shopInfoId) {
//		
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
//		List<GirlInfo> girlInfos = null;
//		try {
//			
//			girlInfos = (List<GirlInfo>)session
//				.createQuery("from GirlInfo where shopInfoId = :shopInfoId order by height desc ")
//				.setParameter("shopInfoId", shopInfoId).list();
//			
//			if(girlInfos.size() > 5) {
//				girlInfos = girlInfos.subList(0, 5);
//			}
//			
//		} catch (HibernateException e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//		session.getTransaction().commit();
//		return girlInfos;
//	}
	
//	@SuppressWarnings("unchecked")
//	public List<GirlInfo> random() {
//		
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
//		List<GirlInfo> girlInfos = null;
//		try {
//			
//			StringBuffer sql = new StringBuffer();
//			sql.append("from GirlInfo ");
//			sql.append("where basicInfo.categoryInfo.hideZoneFlag = 'false' ");
//			sql.append("order by rand() ");
//			girlInfos = (List<GirlInfo>)session.createQuery(sql.toString())
//					.setMaxResults(18)
//					.list();
//			
//		} catch (HibernateException e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//		session.getTransaction().commit();
//		return girlInfos;
//	}
	
/*	@SuppressWarnings("unchecked")
	public List<GirlInfo> search(GirlInfo girlInfo ) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlInfo> girlInfos = null;
		try {
			
			log_.info("shopInfoId >> [" + girlInfo.getShopInfoId() + "]");
			log_.info("nickName >> [" + girlInfo.getNickName() + "]");
			log_.info("category >> [" + girlInfo.getCategory() + "]");
			log_.info("location >> [" + girlInfo.getLocation() + "]");
			
			StringBuffer sql = new StringBuffer();
			sql.append("from GirlInfo ");
			sql.append("where shopInfoId = :shopInfoId ");
			if(!girlInfo.getNickName().isEmpty()) {
				sql.append("and nickName like :nickName ");
			}
			if(!girlInfo.getCategory().isEmpty()) {
				sql.append("and category like :category ");
			}
			if(!girlInfo.getLocation().isEmpty()) {
				sql.append("and location like :location ");
			}
			
			Query query = session.createQuery(sql.toString());
			query.setParameter("shopInfoId", girlInfo.getShopInfoId());
			if(!girlInfo.getNickName().isEmpty()) {
				query.setParameter("nickName", '%'+girlInfo.getNickName()+'%');
			}
			if(!girlInfo.getCategory().isEmpty()) {
				query.setParameter("category", '%'+girlInfo.getCategory()+'%');
			}
			if(!girlInfo.getLocation().isEmpty()) {
				query.setParameter("location", '%'+girlInfo.getLocation()+'%');
			}
			
			girlInfos = (List<GirlInfo>)query.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfos;
	}*/
	
	public GirlInfo getGirlInfo(String girlInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		GirlInfo girlInfo = null;
		List<GirlLocation> girlLocations;
		List<GirlProvince> girlProvinces;
		try {
			
			girlInfo = (GirlInfo)session.createQuery("from GirlInfo where girlInfoId = :girlInfoId ")
					.setParameter("girlInfoId", girlInfoId).uniqueResult();
			if (girlInfo != null) {
				girlLocations = getGirlLocationListByGirlInfoId(session, girlInfo.getGirlInfoId());
				girlInfo.setGirlLocations(girlLocations);
				girlProvinces = getGirlProvinceListByGirlInfoId(session, girlInfo.getGirlInfoId());
				girlInfo.setGirlProvinces(girlProvinces);
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfo;
	}
	
//	public GirlInfo getGirlInfoById(String shopInfoId, String girlInfoId) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
//		GirlInfo girlInfo = null;
//		try {
//			
//			girlInfo = (GirlInfo)session.createQuery("from GirlInfo where shopInfoId = :shopInfoId and girlInfoId = :girlInfoId ")
//					.setParameter("shopInfoId", shopInfoId)
//					.setParameter("girlInfoId", girlInfoId)
//					.uniqueResult();
//			
//		} catch (HibernateException e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//		session.getTransaction().commit();
//		return girlInfo;
//	}
	
//	public void avaiableByGirlInfoId(String shopInfoId, List<String> girlInfoIdList) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
//		try {
//			
//			session.createQuery("update GirlInfo set available = :available where shopInfoId = :shopInfoId ")
//					.setParameter("available", Boolean.FALSE.toString().toLowerCase())
//					.setParameter("shopInfoId", shopInfoId)
//					.executeUpdate();
//			
//			if(girlInfoIdList.size()> 0) {
//				session.createQuery("update GirlInfo set available = :available where girlInfoId in (:girlInfoIdList) ")
//						.setParameter("available", Boolean.TRUE.toString().toLowerCase())
//						.setParameterList("girlInfoIdList", girlInfoIdList.toArray())
//						.executeUpdate();
//			}
//			
//		} catch (HibernateException e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//		session.getTransaction().commit();
//	}

	@SuppressWarnings("unchecked")
	public List<GirlServiceInfo> getGirlServiceInfoList() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlServiceInfo> girlServiceInfos = null;
		try {
			
			girlServiceInfos = (List<GirlServiceInfo>)session.createQuery("from GirlServiceInfo order by orderNo").list();
			if(girlServiceInfos != null) {
				for(GirlServiceInfo girlServiceInfo : girlServiceInfos) {
					List<GirlService> girlServices = (List<GirlService>)session
						.createQuery("from GirlService gs " + 
						"where gs.primaryKey.girlServiceInfo.girlServiceInfoId = :girlServiceInfoId ")
						.setParameter("girlServiceInfoId", girlServiceInfo.getGirlServiceInfoId())
						.list();
					girlServiceInfo.setGirlServiceList(girlServices);
				}
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlServiceInfos;
	}

	public void updateGirlServiceInfo(List<GirlServiceInfo> girlServiceInfos, List<String> girlServiceInfoIdList) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.createQuery("delete from GirlServiceInfo where girlServiceInfoId not in (:girlServiceInfoIdList) ")
			.setParameterList("girlServiceInfoIdList", girlServiceInfoIdList.toArray())
			.executeUpdate();
		for(GirlServiceInfo girlServiceInfo : girlServiceInfos) {
			session.saveOrUpdate(girlServiceInfo);
		}
		session.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<GirlService> getGirlServiceListByGirlInfoId(String girlInfoId) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlService> girlServices = null;
		try {
			
			girlServices = (List<GirlService>)session.createQuery(
					"from GirlService gs where gs.primaryKey.freeAgentGirlInfo.girlInfoId = :girlInfoId " +
					"order by gs.primaryKey.girlServiceInfo.orderNo ")
					.setParameter("girlInfoId", girlInfoId)
					.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlServices;
	}

	@SuppressWarnings("unchecked")
	public List<GirlInfo> list() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlInfo> girlInfos = null;
		try {
			
			girlInfos = (List<GirlInfo>)session.createQuery("select girlInfo from GirlInfo girlInfo, UserInfo userInfo " +
					"where ((DTYPE = 'ShopGirlInfo' and girlInfo.shopInfoId = userInfo.shopInfoId and girlInfo.available = :availableShopGirlInfo) " +
					"or (DTYPE = 'AgentGirlInfo' and girlInfo.agentInfoId = userInfo.agentInfoId and girlInfo.available = :availableAgentGirlInfo) " +
					"or (DTYPE = 'FreeAgentGirlInfo' and girlInfo.girlInfoId = userInfo.girlInfoId) " +
					"or (DTYPE = 'EnGirlInfo' and girlInfo.girlInfoId = userInfo.girlInfoId)) " + 
					"and COALESCE(userInfo.deleteFlg, :deleteFlg) = :deleteFlg")
//					"and userInfo.active = :active " +
//					"and current_date between userInfo.validDateFrom and userInfo.validDateTo"
					.setParameter("availableShopGirlInfo", Boolean.TRUE.toString().toLowerCase())
					.setParameter("availableAgentGirlInfo", Boolean.TRUE.toString().toLowerCase())
//					.setParameter("active", Boolean.TRUE.toString().toLowerCase())
					.setParameter("deleteFlg", Boolean.FALSE.toString().toLowerCase())
					.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<GirlInfo> listByZoneInfoId(String zoneInfoId) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlInfo> girlInfos = null;
		try {
			
			girlInfos = (List<GirlInfo>)session.createQuery("select girlInfo from GirlInfo girlInfo, UserInfo userInfo " + 
					"where girlInfo.zoneInfo.zoneInfoId = :zoneInfoId " +
					"and ((DTYPE = 'ShopGirlInfo' and girlInfo.shopInfoId = userInfo.shopInfoId and girlInfo.available = :availableShopGirlInfo) " +
					"or (DTYPE = 'AgentGirlInfo' and girlInfo.agentInfoId = userInfo.agentInfoId and girlInfo.available = :availableAgentGirlInfo) " +
					"or (DTYPE = 'FreeAgentGirlInfo' and girlInfo.girlInfoId = userInfo.girlInfoId) " +
					"or (DTYPE = 'EnGirlInfo' and girlInfo.girlInfoId = userInfo.girlInfoId)) " + 
					"and COALESCE(userInfo.deleteFlg, :deleteFlg) = :deleteFlg")
//					"and userInfo.active = :active " +
//					"and current_date between userInfo.validDateFrom and userInfo.validDateTo")
					.setParameter("zoneInfoId", zoneInfoId)
					.setParameter("availableShopGirlInfo", Boolean.TRUE.toString().toLowerCase())
					.setParameter("availableAgentGirlInfo", Boolean.TRUE.toString().toLowerCase())
//					.setParameter("active", Boolean.TRUE.toString().toLowerCase())
					.setParameter("deleteFlg", Boolean.FALSE.toString().toLowerCase())
					.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<GirlInfo> search(FrontSearch frontSearch, int feedLimit, int feedOffset) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlInfo> girlInfos = new ArrayList();
		try {
			String sql = "";
			List<GirlInfo> shopGirlInfoList = new ArrayList();
			List<GirlInfo> agentGirlInfoList = new ArrayList();
			List<GirlInfo> freeAgentGirlInfoList = new ArrayList();
			List<GirlInfo> enGirlInfoList = new ArrayList();
			GirlInfo girlInfo;
			List<GirlLocation> girlLocations;
			List<GirlProvince> girlProvinces;
			Query query;
			boolean uncheckAll = false;
			boolean chkCategoey = false;
			boolean chkAgents = false;
			boolean chkFreeAgents = false;
			boolean chkEnGirls = false;
			if (frontSearch.getChkCategory() != null && Boolean.TRUE.toString().toLowerCase().equals(frontSearch.getChkCategory())) {
				chkCategoey = true;
			}
			if (frontSearch.getChkAgents() != null && Boolean.TRUE.toString().toLowerCase().equals(frontSearch.getChkAgents())) {
				chkAgents = true;
			}
			if (frontSearch.getChkFreeAgents() != null && Boolean.TRUE.toString().toLowerCase().equals(frontSearch.getChkFreeAgents())) {
				chkFreeAgents = true;
			}
			if (frontSearch.getChkEnGirls() != null && Boolean.TRUE.toString().toLowerCase().equals(frontSearch.getChkEnGirls())) {
				chkEnGirls = true;
			}
			if (chkCategoey == false && chkAgents == false && chkFreeAgents == false && chkEnGirls == false) {
				uncheckAll = true;
			}
			
			if (chkCategoey || uncheckAll) {
				sql = "select girlInfo from GirlInfo girlInfo, UserInfo userInfo, BasicInfo basicInfo ";
				sql += "where DTYPE = 'ShopGirlInfo' and girlInfo.shopInfoId = userInfo.shopInfoId and girlInfo.available = :availableShopGirlInfo ";
				sql += "and girlInfo.shopInfoId = basicInfo.shopInfoId ";
				if (frontSearch.getCategoryInfoId() != null && !frontSearch.getCategoryInfoId().isEmpty()) {
					sql += " and basicInfo.categoryInfoId = :categoryInfoId ";
				}
				if (frontSearch.getGenderInfoId() != null && !frontSearch.getGenderInfoId().isEmpty()) {
					sql += " and girlInfo.genderInfoId = :genderInfoId ";
				}
				if (frontSearch.getZoneInfos() != null && !frontSearch.getZoneInfos().isEmpty()) {
					sql += " and exists(select 1 from GirlLocation gl ";
					sql += "where gl.primaryKey.girlInfo.girlInfoId = girlInfo.girlInfoId ";
					sql += "and gl.primaryKey.zoneInfo.zoneInfoId in (:zoneInfoIdList)) ";
				}
				if (frontSearch.getIncallOutcall() != null && !frontSearch.getIncallOutcall().isEmpty()) {
					sql += " and girlInfo." + frontSearch.getIncallOutcall() + " = :incallOutcall ";
				}
				if (frontSearch.getNickName() != null && !frontSearch.getNickName().isEmpty()) {
					sql += " and girlInfo.nickName like :nickName ";
				}
				if (frontSearch.getNationalityInfos() != null && !frontSearch.getNationalityInfos().isEmpty()) {
					sql += " and girlInfo.nationalityInfoId in (:nationalityInfoIdList) ";
				}
				if (frontSearch.getCountryInfoId() != null && !frontSearch.getCountryInfoId().isEmpty()) {
					sql += " and girlInfo.countryInfoId like :countryInfoId ";
				}
				if (frontSearch.getProvinceInfos() != null && !frontSearch.getProvinceInfos().isEmpty()) {
					sql += " and exists(select 1 from GirlProvince gp ";
					sql += "where gp.primaryKey.girlInfo.girlInfoId = girlInfo.girlInfoId ";
					sql += "and gp.primaryKey.provinceInfo.provinceInfoId in (:provinceInfoIdList)) ";
				}
				sql += "and COALESCE(userInfo.deleteFlg, :deleteFlg) = :deleteFlg ";
				query = session.createQuery(sql.toString());
				query = query.setParameter("availableShopGirlInfo", Boolean.TRUE.toString().toLowerCase());
				if (frontSearch.getCategoryInfoId() != null && !frontSearch.getCategoryInfoId().isEmpty()) {
					query = query.setParameter("categoryInfoId", frontSearch.getCategoryInfoId());
				}
				if (frontSearch.getGenderInfoId() != null && !frontSearch.getGenderInfoId().isEmpty()) {
					query = query.setParameter("genderInfoId", frontSearch.getGenderInfoId());
				}
				if (frontSearch.getZoneInfos() != null && !frontSearch.getZoneInfos().isEmpty()) {
					query = query.setParameterList("zoneInfoIdList", frontSearch.getZoneInfos().toArray());
				}
				if (frontSearch.getIncallOutcall() != null && !frontSearch.getIncallOutcall().isEmpty()) {
					query = query.setParameter("incallOutcall", Boolean.TRUE.toString().toLowerCase());
				}
				if (frontSearch.getNickName() != null && !frontSearch.getNickName().isEmpty()) {
					query = query.setParameter("nickName", "%" + frontSearch.getNickName() + "%");
				}
				if (frontSearch.getNationalityInfos() != null && !frontSearch.getNationalityInfos().isEmpty()) {
					query = query.setParameterList("nationalityInfoIdList", frontSearch.getNationalityInfos().toArray());
				}
				if (frontSearch.getCountryInfoId() != null && !frontSearch.getCountryInfoId().isEmpty()) {
					query = query.setParameter("countryInfoId", "%" + frontSearch.getCountryInfoId() + "%");
				}
				if (frontSearch.getProvinceInfos() != null && !frontSearch.getProvinceInfos().isEmpty()) {
					query = query.setParameterList("provinceInfoIdList", frontSearch.getProvinceInfos().toArray());
				}
				query = query.setParameter("deleteFlg", Boolean.FALSE.toString().toLowerCase());
				if (feedLimit != -1) {
					query = query.setFirstResult(feedOffset);
					query = query.setMaxResults(feedLimit);
				}
				shopGirlInfoList = (List<GirlInfo>)query.list();
				Iterator it = shopGirlInfoList.iterator();
				while(it.hasNext()) {
					girlInfo = (GirlInfo) it.next();
					girlInfo.setCountryInfo(getCountryInfo(session, girlInfo.getCountryInfoId()));
					girlLocations = getGirlLocationListByGirlInfoId(session, girlInfo.getGirlInfoId());
					java.util.Iterator<GirlLocation> itGirlLocation = girlLocations.iterator();
					while(itGirlLocation.hasNext()) {
						GirlLocation girlLocation = itGirlLocation.next();
						girlLocation.getZoneInfo().setCategoryZones(new ArrayList());
						if(girlLocation.getZoneInfo().getProvinceInfo() != null) {
							girlLocation.getZoneInfo().getProvinceInfo().setZoneInfos(new ArrayList());
						}
					}
					girlInfo.setGirlLocations(girlLocations);
					if (girlInfo.getCountryInfo() != null) {
						girlInfo.getCountryInfo().setProvinceInfos(new ArrayList());
					}
					girlProvinces = getGirlProvinceListByGirlInfoId(session, girlInfo.getGirlInfoId());
					java.util.Iterator<GirlProvince> itGirlProvinces = girlProvinces.iterator();
					while(itGirlProvinces.hasNext()) {
						GirlProvince girlProvince = itGirlProvinces.next();
						girlProvince.getProvinceInfo().setZoneInfos(new ArrayList());
					}
					girlInfo.setGirlProvinces(girlProvinces);
				}
				girlInfos.addAll(shopGirlInfoList);
			}

			if (chkAgents || uncheckAll) {
				sql = "select girlInfo from GirlInfo girlInfo, UserInfo userInfo ";
				sql += "where DTYPE = 'AgentGirlInfo' and girlInfo.agentInfoId = userInfo.agentInfoId and girlInfo.available = :availableAgentGirlInfo ";
				if (frontSearch.getAgentInfoId() != null && !frontSearch.getAgentInfoId().isEmpty()) {
					sql += " and girlInfo.agentInfoId = :agentInfoId ";
				}
				if (frontSearch.getGenderInfoId() != null && !frontSearch.getGenderInfoId().isEmpty()) {
					sql += " and girlInfo.genderInfoId = :genderInfoId ";
				}
				if (frontSearch.getZoneInfos() != null && !frontSearch.getZoneInfos().isEmpty()) {
					sql += " and exists(select 1 from GirlLocation gl ";
					sql += "where gl.primaryKey.girlInfo.girlInfoId = girlInfo.girlInfoId ";
					sql += "and gl.primaryKey.zoneInfo.zoneInfoId in (:zoneInfoIdList)) ";
				}
				if (frontSearch.getIncallOutcall() != null && !frontSearch.getIncallOutcall().isEmpty()) {
					sql += " and girlInfo." + frontSearch.getIncallOutcall() + " = :incallOutcall ";
				}
				if (frontSearch.getNickName() != null && !frontSearch.getNickName().isEmpty()) {
					sql += " and girlInfo.nickName like :nickName ";
				}
				if (frontSearch.getNationalityInfos() != null && !frontSearch.getNationalityInfos().isEmpty()) {
					sql += " and girlInfo.nationalityInfoId in (:nationalityInfoIdList) ";
				}
				if (frontSearch.getCountryInfoId() != null && !frontSearch.getCountryInfoId().isEmpty()) {
					sql += " and girlInfo.countryInfoId like :countryInfoId ";
				}
				if (frontSearch.getProvinceInfos() != null && !frontSearch.getProvinceInfos().isEmpty()) {
					sql += " and exists(select 1 from GirlProvince gp ";
					sql += "where gp.primaryKey.girlInfo.girlInfoId = girlInfo.girlInfoId ";
					sql += "and gp.primaryKey.provinceInfo.provinceInfoId in (:provinceInfoIdList)) ";
				}
				sql += "and COALESCE(userInfo.deleteFlg, :deleteFlg) = :deleteFlg ";
				query = session.createQuery(sql.toString());
				query = query.setParameter("availableAgentGirlInfo", Boolean.TRUE.toString().toLowerCase());
				if (frontSearch.getChkAgents() != null && Boolean.TRUE.toString().toLowerCase().equals(frontSearch.getChkAgents())) {
					if (frontSearch.getAgentInfoId() != null && !frontSearch.getAgentInfoId().isEmpty()) {
						query = query.setParameter("agentInfoId", frontSearch.getAgentInfoId());
					}
				}
				if (frontSearch.getGenderInfoId() != null && !frontSearch.getGenderInfoId().isEmpty()) {
					query = query.setParameter("genderInfoId", frontSearch.getGenderInfoId());
				}
				if (frontSearch.getZoneInfos() != null && !frontSearch.getZoneInfos().isEmpty()) {
					query = query.setParameterList("zoneInfoIdList", frontSearch.getZoneInfos().toArray());
				}
				if (frontSearch.getIncallOutcall() != null && !frontSearch.getIncallOutcall().isEmpty()) {
					query = query.setParameter("incallOutcall", Boolean.TRUE.toString().toLowerCase());
				}
				if (frontSearch.getNickName() != null && !frontSearch.getNickName().isEmpty()) {
					query = query.setParameter("nickName", "%" + frontSearch.getNickName() + "%");
				}
				if (frontSearch.getNationalityInfos() != null && !frontSearch.getNationalityInfos().isEmpty()) {
					query = query.setParameterList("nationalityInfoIdList", frontSearch.getNationalityInfos().toArray());
				}
				if (frontSearch.getCountryInfoId() != null && !frontSearch.getCountryInfoId().isEmpty()) {
					query = query.setParameter("countryInfoId", "%" + frontSearch.getCountryInfoId() + "%");
				}
				if (frontSearch.getProvinceInfos() != null && !frontSearch.getProvinceInfos().isEmpty()) {
					query = query.setParameterList("provinceInfoIdList", frontSearch.getProvinceInfos().toArray());
				}
				query = query.setParameter("deleteFlg", Boolean.FALSE.toString().toLowerCase());
				if (feedLimit != -1) {
					query = query.setFirstResult(feedOffset);
					query = query.setMaxResults(feedLimit);
				}
				agentGirlInfoList = (List<GirlInfo>)query.list();
				Iterator it = agentGirlInfoList.iterator();
				while(it.hasNext()) {
					girlInfo = (GirlInfo) it.next();
					((AgentGirlInfo)girlInfo).setAgentInfo(getAgentInfo(session, ((AgentGirlInfo)girlInfo).getAgentInfoId()));
					girlInfo.setCountryInfo(getCountryInfo(session, girlInfo.getCountryInfoId()));
					girlLocations = getGirlLocationListByGirlInfoId(session, girlInfo.getGirlInfoId());
					java.util.Iterator<GirlLocation> itGirlLocation = girlLocations.iterator();
					while(itGirlLocation.hasNext()) {
						GirlLocation girlLocation = itGirlLocation.next();
						girlLocation.getZoneInfo().setCategoryZones(new ArrayList());
						if(girlLocation.getZoneInfo().getProvinceInfo() != null) {
							girlLocation.getZoneInfo().getProvinceInfo().setZoneInfos(new ArrayList());
						}
					}
					girlInfo.setGirlLocations(girlLocations);
					((AgentGirlInfo)girlInfo).setGirlServices(new ArrayList());
					if (girlInfo.getCountryInfo() != null) {
						girlInfo.getCountryInfo().setProvinceInfos(new ArrayList());
					}
					girlProvinces = getGirlProvinceListByGirlInfoId(session, girlInfo.getGirlInfoId());
					java.util.Iterator<GirlProvince> itGirlProvinces = girlProvinces.iterator();
					while(itGirlProvinces.hasNext()) {
						GirlProvince girlProvince = itGirlProvinces.next();
						girlProvince.getProvinceInfo().setZoneInfos(new ArrayList());
					}
					girlInfo.setGirlProvinces(girlProvinces);
				}
				girlInfos.addAll(agentGirlInfoList);
			}
			
			if (chkFreeAgents || uncheckAll) {
				sql = "select girlInfo from GirlInfo girlInfo, UserInfo userInfo ";
				sql += "where DTYPE = 'FreeAgentGirlInfo' and girlInfo.girlInfoId = userInfo.girlInfoId ";
				if (frontSearch.getGenderInfoId() != null && !frontSearch.getGenderInfoId().isEmpty()) {
					sql += " and girlInfo.genderInfoId = :genderInfoId ";
				}
				if (frontSearch.getZoneInfos() != null && !frontSearch.getZoneInfos().isEmpty()) {
					sql += " and exists(select 1 from GirlLocation gl ";
					sql += "where gl.primaryKey.girlInfo.girlInfoId = girlInfo.girlInfoId ";
					sql += "and gl.primaryKey.zoneInfo.zoneInfoId in (:zoneInfoIdList)) ";
				}
				if (frontSearch.getIncallOutcall() != null && !frontSearch.getIncallOutcall().isEmpty()) {
					sql += " and girlInfo." + frontSearch.getIncallOutcall() + " = :incallOutcall ";
				}
				if (frontSearch.getNickName() != null && !frontSearch.getNickName().isEmpty()) {
					sql += " and girlInfo.nickName like :nickName ";
				}
				if (frontSearch.getNationalityInfos() != null && !frontSearch.getNationalityInfos().isEmpty()) {
					sql += " and girlInfo.nationalityInfoId in (:nationalityInfoIdList) ";
				}
				if (frontSearch.getCountryInfoId() != null && !frontSearch.getCountryInfoId().isEmpty()) {
					sql += " and girlInfo.countryInfoId like :countryInfoId ";
				}
				if (frontSearch.getProvinceInfos() != null && !frontSearch.getProvinceInfos().isEmpty()) {
					sql += " and exists(select 1 from GirlProvince gp ";
					sql += "where gp.primaryKey.girlInfo.girlInfoId = girlInfo.girlInfoId ";
					sql += "and gp.primaryKey.provinceInfo.provinceInfoId in (:provinceInfoIdList)) ";
				}
				sql += "and COALESCE(userInfo.deleteFlg, :deleteFlg) = :deleteFlg ";
				query = session.createQuery(sql.toString());
				if (frontSearch.getGenderInfoId() != null && !frontSearch.getGenderInfoId().isEmpty()) {
					query = query.setParameter("genderInfoId", frontSearch.getGenderInfoId());
				}
				if (frontSearch.getZoneInfos() != null && !frontSearch.getZoneInfos().isEmpty()) {
					query = query.setParameterList("zoneInfoIdList", frontSearch.getZoneInfos().toArray());
				}
				if (frontSearch.getIncallOutcall() != null && !frontSearch.getIncallOutcall().isEmpty()) {
					query = query.setParameter("incallOutcall", Boolean.TRUE.toString().toLowerCase());
				}
				if (frontSearch.getNickName() != null && !frontSearch.getNickName().isEmpty()) {
					query = query.setParameter("nickName", "%" + frontSearch.getNickName() + "%");
				}
				if (frontSearch.getNationalityInfos() != null && !frontSearch.getNationalityInfos().isEmpty()) {
					query = query.setParameterList("nationalityInfoIdList", frontSearch.getNationalityInfos().toArray());
				}
				if (frontSearch.getCountryInfoId() != null && !frontSearch.getCountryInfoId().isEmpty()) {
					query = query.setParameter("countryInfoId", "%" + frontSearch.getCountryInfoId() + "%");
				}
				if (frontSearch.getProvinceInfos() != null && !frontSearch.getProvinceInfos().isEmpty()) {
					query = query.setParameterList("provinceInfoIdList", frontSearch.getProvinceInfos().toArray());
				}
				query = query.setParameter("deleteFlg", Boolean.FALSE.toString().toLowerCase());
				if (feedLimit != -1) {
					query = query.setFirstResult(feedOffset);
					query = query.setMaxResults(feedLimit);
				}
				freeAgentGirlInfoList = (List<GirlInfo>)query.list();
				Iterator it = freeAgentGirlInfoList.iterator();
				while(it.hasNext()) {
					girlInfo = (GirlInfo) it.next();
					girlInfo.setCountryInfo(getCountryInfo(session, girlInfo.getCountryInfoId()));
					((FreeAgentGirlInfo)girlInfo).setUserInfo(getUserInfo(session, girlInfo.getGirlInfoId()));
					girlLocations = getGirlLocationListByGirlInfoId(session, girlInfo.getGirlInfoId());
					java.util.Iterator<GirlLocation> itGirlLocation = girlLocations.iterator();
					while(itGirlLocation.hasNext()) {
						GirlLocation girlLocation = itGirlLocation.next();
						girlLocation.getZoneInfo().setCategoryZones(new ArrayList());
						if(girlLocation.getZoneInfo().getProvinceInfo() != null) {
							girlLocation.getZoneInfo().getProvinceInfo().setZoneInfos(new ArrayList());
						}
					}
					girlInfo.setGirlLocations(girlLocations);
					((FreeAgentGirlInfo)girlInfo).setGirlServices(new ArrayList());
					if (girlInfo.getCountryInfo() != null) {
						girlInfo.getCountryInfo().setProvinceInfos(new ArrayList());
					}
					girlProvinces = getGirlProvinceListByGirlInfoId(session, girlInfo.getGirlInfoId());
					java.util.Iterator<GirlProvince> itGirlProvinces = girlProvinces.iterator();
					while(itGirlProvinces.hasNext()) {
						GirlProvince girlProvince = itGirlProvinces.next();
						girlProvince.getProvinceInfo().setZoneInfos(new ArrayList());
					}
					girlInfo.setGirlProvinces(girlProvinces);
				}
				girlInfos.addAll(freeAgentGirlInfoList);
			}

			if (chkEnGirls || uncheckAll) {
				sql = "select girlInfo from GirlInfo girlInfo, UserInfo userInfo ";
				sql += "where DTYPE = 'EnGirlInfo' and girlInfo.girlInfoId = userInfo.girlInfoId ";
				if (frontSearch.getGenderInfoId() != null && !frontSearch.getGenderInfoId().isEmpty()) {
					sql += " and girlInfo.genderInfoId = :genderInfoId ";
				}
				if (frontSearch.getZoneInfos() != null && !frontSearch.getZoneInfos().isEmpty()) {
					sql += " and exists(select 1 from GirlLocation gl ";
					sql += "where gl.primaryKey.girlInfo.girlInfoId = girlInfo.girlInfoId ";
					sql += "and gl.primaryKey.zoneInfo.zoneInfoId in (:zoneInfoIdList)) ";
				}
				if (frontSearch.getIncallOutcall() != null && !frontSearch.getIncallOutcall().isEmpty()) {
					sql += " and girlInfo." + frontSearch.getIncallOutcall() + " = :incallOutcall ";
				}
				if (frontSearch.getNickName() != null && !frontSearch.getNickName().isEmpty()) {
					sql += " and girlInfo.nickName like :nickName ";
				}
				if (frontSearch.getNationalityInfos() != null && !frontSearch.getNationalityInfos().isEmpty()) {
					sql += " and girlInfo.nationalityInfoId in (:nationalityInfoIdList) ";
				}
				if (frontSearch.getCountryInfoId() != null && !frontSearch.getCountryInfoId().isEmpty()) {
					sql += " and girlInfo.countryInfoId like :countryInfoId ";
				}
				if (frontSearch.getProvinceInfos() != null && !frontSearch.getProvinceInfos().isEmpty()) {
					sql += " and exists(select 1 from GirlProvince gp ";
					sql += "where gp.primaryKey.girlInfo.girlInfoId = girlInfo.girlInfoId ";
					sql += "and gp.primaryKey.provinceInfo.provinceInfoId in (:provinceInfoIdList)) ";
				}
				sql += "and COALESCE(userInfo.deleteFlg, :deleteFlg) = :deleteFlg ";
				query = session.createQuery(sql.toString());
				if (frontSearch.getGenderInfoId() != null && !frontSearch.getGenderInfoId().isEmpty()) {
					query = query.setParameter("genderInfoId", frontSearch.getGenderInfoId());
				}
				if (frontSearch.getZoneInfos() != null && !frontSearch.getZoneInfos().isEmpty()) {
					query = query.setParameterList("zoneInfoIdList", frontSearch.getZoneInfos().toArray());
				}
				if (frontSearch.getIncallOutcall() != null && !frontSearch.getIncallOutcall().isEmpty()) {
					query = query.setParameter("incallOutcall", Boolean.TRUE.toString().toLowerCase());
				}
				if (frontSearch.getNickName() != null && !frontSearch.getNickName().isEmpty()) {
					query = query.setParameter("nickName", "%" + frontSearch.getNickName() + "%");
				}
				if (frontSearch.getNationalityInfos() != null && !frontSearch.getNationalityInfos().isEmpty()) {
					query = query.setParameterList("nationalityInfoIdList", frontSearch.getNationalityInfos().toArray());
				}
				if (frontSearch.getCountryInfoId() != null && !frontSearch.getCountryInfoId().isEmpty()) {
					query = query.setParameter("countryInfoId", "%" + frontSearch.getCountryInfoId() + "%");
				}
				if (frontSearch.getProvinceInfos() != null && !frontSearch.getProvinceInfos().isEmpty()) {
					query = query.setParameterList("provinceInfoIdList", frontSearch.getProvinceInfos().toArray());
				}
				query = query.setParameter("deleteFlg", Boolean.FALSE.toString().toLowerCase());
				if (feedLimit != -1) {
					query = query.setFirstResult(feedOffset);
					query = query.setMaxResults(feedLimit);
				}
				enGirlInfoList = (List<GirlInfo>)query.list();
				Iterator it = enGirlInfoList.iterator();
				while(it.hasNext()) {
					girlInfo = (GirlInfo) it.next();
					girlInfo.setCountryInfo(getCountryInfo(session, girlInfo.getCountryInfoId()));
					((EnGirlInfo)girlInfo).setUserInfo(getUserInfo(session, girlInfo.getGirlInfoId()));
					girlLocations = getGirlLocationListByGirlInfoId(session, girlInfo.getGirlInfoId());
					java.util.Iterator<GirlLocation> itGirlLocation = girlLocations.iterator();
					while(itGirlLocation.hasNext()) {
						GirlLocation girlLocation = itGirlLocation.next();
						girlLocation.getZoneInfo().setCategoryZones(new ArrayList());
						if(girlLocation.getZoneInfo().getProvinceInfo() != null) {
							girlLocation.getZoneInfo().getProvinceInfo().setZoneInfos(new ArrayList());
						}
					}
					girlInfo.setGirlLocations(girlLocations);
					if (girlInfo.getCountryInfo() != null) {
						girlInfo.getCountryInfo().setProvinceInfos(new ArrayList());
					}
					girlProvinces = getGirlProvinceListByGirlInfoId(session, girlInfo.getGirlInfoId());
					java.util.Iterator<GirlProvince> itGirlProvinces = girlProvinces.iterator();
					while(itGirlProvinces.hasNext()) {
						GirlProvince girlProvince = itGirlProvinces.next();
						girlProvince.getProvinceInfo().setZoneInfos(new ArrayList());
					}
					girlInfo.setGirlProvinces(girlProvinces);
				}
				girlInfos.addAll(enGirlInfoList);
			}


		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<GirlLocation> getGirlLocationListByGirlInfoId(String girlInfoId) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlLocation> girlLocations = null;
		try {
			
			girlLocations = getGirlLocationListByGirlInfoId(session, girlInfoId);
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlLocations;
	}
	
	@SuppressWarnings("unchecked")
	public List<GirlLocation> getGirlLocationListByGirlInfoId(Session session, String girlInfoId) {
		List<GirlLocation> girlLocations = null;
		girlLocations = (List<GirlLocation>)session.createQuery("from GirlLocation gl where gl.primaryKey.girlInfo.girlInfoId = :girlInfoId")
					.setParameter("girlInfoId", girlInfoId)
					.list();
		return girlLocations;
	}
	
	@SuppressWarnings("unchecked")
	public List<GirlService> getGirlServiceList() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlService> girlServices = null;
		try {
			
			girlServices = (List<GirlService>)session.createQuery("from GirlService").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlServices;
	}

	public void allSameByGirlInfoId(List<String> allGirlInfoIdList, List<String> allSameGirlInfoIdList) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			
			if(allGirlInfoIdList.size()> 0) {
				session.createQuery("update GirlInfo set allSame = :allSame where girlInfoId in (:girlInfoIdList) ")
						.setParameter("allSame", Boolean.FALSE.toString().toLowerCase())
						.setParameterList("girlInfoIdList", allGirlInfoIdList.toArray())
						.executeUpdate();
				
				if(allSameGirlInfoIdList.size()> 0) {
					session.createQuery("update GirlInfo set allSame = :allSame where girlInfoId in (:girlInfoIdList) ")
							.setParameter("allSame", Boolean.TRUE.toString().toLowerCase())
							.setParameterList("girlInfoIdList", allSameGirlInfoIdList.toArray())
							.executeUpdate();
				}
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<GirlProvince> getGirlProvinceListByGirlInfoId(String girlInfoId) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlProvince> girlProvinces = null;
		try {
			
			girlProvinces = getGirlProvinceListByGirlInfoId(session, girlInfoId);
			java.util.Iterator<GirlProvince> itGirlProvinces = girlProvinces.iterator();
			List girlLocation = null;
			while(itGirlProvinces.hasNext()) {
				GirlProvince girlProvince = itGirlProvinces.next();
				girlLocation = getGirlLocationListByGirlInfoIdAndProvinceInfoId(session, girlInfoId, girlProvince.getProvinceInfo().getProvinceInfoId());
				girlProvince.getProvinceInfo().setZoneInfos(girlLocation);
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlProvinces;
	}
	
	@SuppressWarnings("unchecked")
	public List<GirlProvince> getGirlProvinceListByGirlInfoId(Session session, String girlInfoId) {
		List<GirlProvince> girlProvinces = null;
		girlProvinces = (List<GirlProvince>)session.createQuery("from GirlProvince gl where gl.primaryKey.girlInfo.girlInfoId = :girlInfoId")
					.setParameter("girlInfoId", girlInfoId)
					.list();
		
		return girlProvinces;
	}
	
	public AgentInfo getAgentInfo(Session session, String agentInfoId) {
		AgentInfo agentInfo = null;
		if (!mapAgentInfo.containsKey(agentInfoId)) {
			agentInfo = (AgentInfo) session.createQuery("from AgentInfo where agentInfoId = :agentInfoId")
						.setParameter("agentInfoId", agentInfoId)
						.uniqueResult();
			mapAgentInfo.put(agentInfoId, agentInfo);
		}
		agentInfo = (AgentInfo) mapAgentInfo.get(agentInfoId);
		return agentInfo;
	}
	
	public CountryInfo getCountryInfo(Session session, String countryInfoId) {
		CountryInfo countryInfo = null;
		if (!mapCountryInfo.containsKey(countryInfoId)) {
			countryInfo = (CountryInfo) session.createQuery("from CountryInfo where countryInfoId = :countryInfoId")
					.setParameter("countryInfoId", countryInfoId)
					.uniqueResult();
			mapCountryInfo.put(countryInfoId, countryInfo);
		}
		countryInfo = (CountryInfo) mapCountryInfo.get(countryInfoId);
		return countryInfo;
	}
	
	@SuppressWarnings("unchecked")
	public List<GirlLocation> getGirlLocationListByGirlInfoIdAndProvinceInfoId(Session session, String girlInfoId, String provinceInfoId) {
		List<GirlLocation> girlLocations = null;
		girlLocations = (List<GirlLocation>)session.createQuery("from GirlLocation gl " +
				" where gl.primaryKey.girlInfo.girlInfoId = :girlInfoId" +
				" and gl.primaryKey.zoneInfo.provinceInfoId = :provinceInfoId")
					.setParameter("girlInfoId", girlInfoId)
					.setParameter("provinceInfoId", provinceInfoId)
					.list();
		return girlLocations;
	}

	@SuppressWarnings("unchecked")
	public List<GirlServiceInfo> getGirlServiceInfoListByGirlInfoId(String girlInfoId) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlServiceInfo> girlServiceInfos = null;
		try {
			
			girlServiceInfos = (List<GirlServiceInfo>)session.createQuery("from GirlServiceInfo order by orderNo").list();
			if(girlServiceInfos != null) {
				for(GirlServiceInfo girlServiceInfo : girlServiceInfos) {
					List<GirlService> girlServices = (List<GirlService>)session
						.createQuery("from GirlService gs " + 
						"where gs.primaryKey.girlServiceInfo.girlServiceInfoId = :girlServiceInfoId ")
						.setParameter("girlServiceInfoId", girlServiceInfo.getGirlServiceInfoId())
						.list();
					girlServiceInfo.setGirlServiceList(girlServices);
				}
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlServiceInfos;
	}

	public UserInfo getUserInfo(Session session, String girlInfoId) {
		UserInfo userInfo = null;
		if (!mapUserInfo.containsKey(girlInfoId)) {
			userInfo = (UserInfo) session.createQuery("from UserInfo where girlInfoId = :girlInfoId")
					.setParameter("girlInfoId", girlInfoId)
					.uniqueResult();
			mapUserInfo.put(girlInfoId, userInfo);
		}
		userInfo = (UserInfo) mapUserInfo.get(girlInfoId);
		return userInfo;
	}
}
