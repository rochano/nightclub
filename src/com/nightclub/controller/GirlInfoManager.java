package com.nightclub.controller;

import java.util.List;

import net.viralpatel.contact.util.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.classic.Session;

import com.nightclub.model.GirlInfo;
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
		try {
			
			girlInfo = (GirlInfo)session.createQuery("from GirlInfo where girlInfoId = :girlInfoId ")
					.setParameter("girlInfoId", girlInfoId).uniqueResult();
			
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
}
