package com.nightclub.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.viralpatel.contact.util.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.nightclub.model.FrontSearch;
import com.nightclub.model.GirlInfo;
import com.nightclub.model.GirlLocation;
import com.nightclub.model.GirlService;
import com.nightclub.model.GirlServiceInfo;

public class GirlInfoManager extends HibernateUtil {
	
	private static Logger log_ = Logger.getLogger(GirlInfoManager.class);
	
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
	
	public GirlInfo delete(String girlInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		GirlInfo girlInfo = (GirlInfo) session.load(GirlInfo.class, girlInfoId);
		if(null != girlInfo) {
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
		try {
			
			girlInfo = (GirlInfo)session.createQuery("from GirlInfo where girlInfoId = :girlInfoId ")
					.setParameter("girlInfoId", girlInfoId).uniqueResult();
			girlLocations = getGirlLocationListByGirlInfoId(session, girlInfo.getGirlInfoId());
			girlInfo.setGirlLocations(girlLocations);
			
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
	public List<GirlServiceInfo> getGirlServiceList() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlServiceInfo> girlServiceInfos = null;
		try {
			
			girlServiceInfos = (List<GirlServiceInfo>)session.createQuery("from GirlServiceInfo order by orderNo").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlServiceInfos;
	}

	public void updateGirlServiceInfo(List<GirlServiceInfo> girlServiceInfos) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.createQuery("delete from GirlServiceInfo ").executeUpdate();
		for(GirlServiceInfo girlServiceInfo : girlServiceInfos) {
			session.save(girlServiceInfo);
		}
		session.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<GirlService> getGirlServiceListByGirlInfoId(String girlInfoId) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlService> girlServices = null;
		try {
			
			girlServices = (List<GirlService>)session.createQuery("from GirlService gs where gs.primaryKey.freeAgentGirlInfo.girlInfoId = :girlInfoId")
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
					"or (DTYPE = 'FreeAgentGirlInfo' and girlInfo.girlInfoId = userInfo.girlInfoId)) " +
					"or (DTYPE = 'EnGirlInfo' and girlInfo.girlInfoId = userInfo.girlInfoId)) ")
//					"and userInfo.active = :active " +
//					"and current_date between userInfo.validDateFrom and userInfo.validDateTo"
					.setParameter("availableShopGirlInfo", Boolean.TRUE.toString().toLowerCase())
					.setParameter("availableAgentGirlInfo", Boolean.TRUE.toString().toLowerCase())
//					.setParameter("active", Boolean.TRUE.toString().toLowerCase())
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
					"or (DTYPE = 'FreeAgentGirlInfo' and girlInfo.girlInfoId = userInfo.girlInfoId)) " +
					"or (DTYPE = 'EnGirlInfo' and girlInfo.girlInfoId = userInfo.girlInfoId)) ")
//					"and userInfo.active = :active " +
//					"and current_date between userInfo.validDateFrom and userInfo.validDateTo")
					.setParameter("zoneInfoId", zoneInfoId)
					.setParameter("availableShopGirlInfo", Boolean.TRUE.toString().toLowerCase())
					.setParameter("availableAgentGirlInfo", Boolean.TRUE.toString().toLowerCase())
//					.setParameter("active", Boolean.TRUE.toString().toLowerCase())
					.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<GirlInfo> search(FrontSearch frontSearch) {
		
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
			Query query;
			if (frontSearch.getChkCategory() != null && Boolean.TRUE.toString().toLowerCase().equals(frontSearch.getChkCategory())) {
				sql = "select girlInfo from GirlInfo girlInfo, UserInfo userInfo, BasicInfo basicInfo ";
				sql += "where DTYPE = 'ShopGirlInfo' and girlInfo.shopInfoId = userInfo.shopInfoId and girlInfo.available = :availableShopGirlInfo ";
				sql += "and girlInfo.shopInfoId = basicInfo.shopInfoId ";
				if (!frontSearch.getCategoryInfoId().isEmpty()) {
					sql += " and basicInfo.categoryInfoId = :categoryInfoId ";
				}
				if (frontSearch.getGender() != null && !frontSearch.getGender().isEmpty()) {
					sql += " and girlInfo.gender = :gender ";
				}
				if (frontSearch.getZoneInfos() != null && !frontSearch.getZoneInfos().isEmpty()) {
					sql += " and exists(select 1 from GirlLocation gl ";
					sql += "where gl.primaryKey.girlInfo.girlInfoId = girlInfo.girlInfoId ";
					sql += "and gl.primaryKey.zoneInfo.zoneInfoId in (:zoneInfoIdList)) ";
				}
				if (frontSearch.getIncallOutcall() != null && !frontSearch.getIncallOutcall().isEmpty()) {
					sql += " and girlInfo.incallOutcall = :incallOutcall ";
				}
				query = session.createQuery(sql.toString());
				query = query.setParameter("availableShopGirlInfo", Boolean.TRUE.toString().toLowerCase());
				if (!frontSearch.getCategoryInfoId().isEmpty()) {
					query = query.setParameter("categoryInfoId", frontSearch.getCategoryInfoId());
				}
				if (frontSearch.getGender() != null && !frontSearch.getGender().isEmpty()) {
					query = query.setParameter("gender", frontSearch.getGender());
				}
				if (frontSearch.getZoneInfos() != null && !frontSearch.getZoneInfos().isEmpty()) {
					query = query.setParameterList("zoneInfoIdList", frontSearch.getZoneInfos().toArray());
				}
				if (frontSearch.getIncallOutcall() != null && !frontSearch.getIncallOutcall().isEmpty()) {
					query = query.setParameter("incallOutcall", frontSearch.getIncallOutcall());
				}
				shopGirlInfoList = (List<GirlInfo>)query.list();
				Iterator it = shopGirlInfoList.iterator();
				while(it.hasNext()) {
					girlInfo = (GirlInfo) it.next();
					girlLocations = getGirlLocationListByGirlInfoId(session, girlInfo.getGirlInfoId());
					girlInfo.setGirlLocations(girlLocations);
				}
				girlInfos.addAll(shopGirlInfoList);
			}

			if (frontSearch.getChkAgents() != null && Boolean.TRUE.toString().toLowerCase().equals(frontSearch.getChkAgents())) {
				sql = "select girlInfo from GirlInfo girlInfo, UserInfo userInfo ";
				sql += "where DTYPE = 'AgentGirlInfo' and girlInfo.agentInfoId = userInfo.agentInfoId and girlInfo.available = :availableAgentGirlInfo ";
				if (!frontSearch.getAgentInfoId().isEmpty()) {
					sql += " and girlInfo.agentInfoId = :agentInfoId ";
				}
				if (frontSearch.getGender() != null && !frontSearch.getGender().isEmpty()) {
					sql += " and girlInfo.gender = :gender ";
				}
				if (frontSearch.getZoneInfos() != null && !frontSearch.getZoneInfos().isEmpty()) {
					sql += " and exists(select 1 from GirlLocation gl ";
					sql += "where gl.primaryKey.girlInfo.girlInfoId = girlInfo.girlInfoId ";
					sql += "and gl.primaryKey.zoneInfo.zoneInfoId in (:zoneInfoIdList)) ";
				}
				if (frontSearch.getIncallOutcall() != null && !frontSearch.getIncallOutcall().isEmpty()) {
					sql += " and girlInfo.incallOutcall = :incallOutcall ";
				}
				query = session.createQuery(sql.toString());
				query = query.setParameter("availableAgentGirlInfo", Boolean.TRUE.toString().toLowerCase());
				if (frontSearch.getChkAgents() != null && Boolean.TRUE.toString().toLowerCase().equals(frontSearch.getChkAgents())) {
					if (!frontSearch.getAgentInfoId().isEmpty()) {
						query = query.setParameter("agentInfoId", frontSearch.getAgentInfoId());
					}
				}
				if (frontSearch.getGender() != null && !frontSearch.getGender().isEmpty()) {
					query = query.setParameter("gender", frontSearch.getGender());
				}
				if (frontSearch.getZoneInfos() != null && !frontSearch.getZoneInfos().isEmpty()) {
					query = query.setParameterList("zoneInfoIdList", frontSearch.getZoneInfos().toArray());
				}
				if (frontSearch.getIncallOutcall() != null && !frontSearch.getIncallOutcall().isEmpty()) {
					query = query.setParameter("incallOutcall", frontSearch.getIncallOutcall());
				}
				agentGirlInfoList = (List<GirlInfo>)query.list();
				Iterator it = agentGirlInfoList.iterator();
				while(it.hasNext()) {
					girlInfo = (GirlInfo) it.next();
					girlLocations = getGirlLocationListByGirlInfoId(session, girlInfo.getGirlInfoId());
					girlInfo.setGirlLocations(girlLocations);
				}
				girlInfos.addAll(agentGirlInfoList);
			}
			
			if (frontSearch.getChkFreeAgents() != null && Boolean.TRUE.toString().toLowerCase().equals(frontSearch.getChkFreeAgents())) {
				sql = "select girlInfo from GirlInfo girlInfo, UserInfo userInfo ";
				sql += "where DTYPE = 'FreeAgentGirlInfo' and girlInfo.girlInfoId = userInfo.girlInfoId ";
				if (frontSearch.getGender() != null && !frontSearch.getGender().isEmpty()) {
					sql += " and girlInfo.gender = :gender ";
				}
				if (frontSearch.getZoneInfos() != null && !frontSearch.getZoneInfos().isEmpty()) {
					sql += " and exists(select 1 from GirlLocation gl ";
					sql += "where gl.primaryKey.girlInfo.girlInfoId = girlInfo.girlInfoId ";
					sql += "and gl.primaryKey.zoneInfo.zoneInfoId in (:zoneInfoIdList)) ";
				}
				if (frontSearch.getIncallOutcall() != null && !frontSearch.getIncallOutcall().isEmpty()) {
					sql += " and girlInfo.incallOutcall = :incallOutcall ";
				}
				query = session.createQuery(sql.toString());
				if (frontSearch.getGender() != null && !frontSearch.getGender().isEmpty()) {
					query = query.setParameter("gender", frontSearch.getGender());
				}
				if (frontSearch.getZoneInfos() != null && !frontSearch.getZoneInfos().isEmpty()) {
					query = query.setParameterList("zoneInfoIdList", frontSearch.getZoneInfos().toArray());
				}
				if (frontSearch.getIncallOutcall() != null && !frontSearch.getIncallOutcall().isEmpty()) {
					query = query.setParameter("incallOutcall", frontSearch.getIncallOutcall());
				}
				freeAgentGirlInfoList = (List<GirlInfo>)query.list();
				Iterator it = freeAgentGirlInfoList.iterator();
				while(it.hasNext()) {
					girlInfo = (GirlInfo) it.next();
					girlLocations = getGirlLocationListByGirlInfoId(session, girlInfo.getGirlInfoId());
					girlInfo.setGirlLocations(girlLocations);
				}
				girlInfos.addAll(freeAgentGirlInfoList);
			}

			if (frontSearch.getChkEnGirls() != null && Boolean.TRUE.toString().toLowerCase().equals(frontSearch.getChkEnGirls())) {
				sql = "select girlInfo from GirlInfo girlInfo, UserInfo userInfo ";
				sql += "where DTYPE = 'EnGirlInfo' and girlInfo.girlInfoId = userInfo.girlInfoId ";
				if (frontSearch.getGender() != null && !frontSearch.getGender().isEmpty()) {
					sql += " and girlInfo.gender = :gender ";
				}
				if (frontSearch.getZoneInfos() != null && !frontSearch.getZoneInfos().isEmpty()) {
					sql += " and exists(select 1 from GirlLocation gl ";
					sql += "where gl.primaryKey.girlInfo.girlInfoId = girlInfo.girlInfoId ";
					sql += "and gl.primaryKey.zoneInfo.zoneInfoId in (:zoneInfoIdList)) ";
				}
				if (frontSearch.getIncallOutcall() != null && !frontSearch.getIncallOutcall().isEmpty()) {
					sql += " and girlInfo.incallOutcall = :incallOutcall ";
				}
				query = session.createQuery(sql.toString());
				if (frontSearch.getGender() != null && !frontSearch.getGender().isEmpty()) {
					query = query.setParameter("gender", frontSearch.getGender());
				}
				if (frontSearch.getZoneInfos() != null && !frontSearch.getZoneInfos().isEmpty()) {
					query = query.setParameterList("zoneInfoIdList", frontSearch.getZoneInfos().toArray());
				}
				if (frontSearch.getIncallOutcall() != null && !frontSearch.getIncallOutcall().isEmpty()) {
					query = query.setParameter("incallOutcall", frontSearch.getIncallOutcall());
				}
				enGirlInfoList = (List<GirlInfo>)query.list();
				Iterator it = enGirlInfoList.iterator();
				while(it.hasNext()) {
					girlInfo = (GirlInfo) it.next();
					girlLocations = getGirlLocationListByGirlInfoId(session, girlInfo.getGirlInfoId());
					girlInfo.setGirlLocations(girlLocations);
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
}
