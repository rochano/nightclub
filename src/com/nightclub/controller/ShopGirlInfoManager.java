package com.nightclub.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.viralpatel.contact.util.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.nightclub.model.GirlInfo;
import com.nightclub.model.GirlLocation;
import com.nightclub.model.ShopGirlInfo;

public class ShopGirlInfoManager extends GirlInfoManager {
	
	private static Logger log_ = Logger.getLogger(GirlInfoManager.class);

	@SuppressWarnings("unchecked")
	public List<GirlInfo> list(String shopInfoId) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlInfo> girlInfos = null;
		GirlInfo girlInfo;
		List<GirlLocation> girlLocations;
		try {
			
			girlInfos = (List<GirlInfo>)session.createQuery("from ShopGirlInfo where shopInfoId = :shopInfoId ")
					.setParameter("shopInfoId", shopInfoId).list();
			Iterator it = girlInfos.iterator();
			while(it.hasNext()) {
				girlInfo = (GirlInfo) it.next();
				girlLocations = getGirlLocationListByGirlInfoId(session, girlInfo.getGirlInfoId());
				girlInfo.setGirlLocations(girlLocations);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<GirlInfo> newFaceList(String shopInfoId, Date prevMonth) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlInfo> girlInfos = null;
		try {
			
			girlInfos = (List<GirlInfo>)session
				.createQuery("from GirlInfo where shopInfoId = :shopInfoId and createdDate >= :prevMonth ")
				.setParameter("shopInfoId", shopInfoId)
				.setParameter("prevMonth", prevMonth)
				.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<GirlInfo> rankingList(String shopInfoId) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlInfo> girlInfos = null;
		try {
			
			girlInfos = (List<GirlInfo>)session
				.createQuery("from ShopGirlInfo where shopInfoId = :shopInfoId order by ranking asc ")
				.setParameter("shopInfoId", shopInfoId).list();
			
			if(girlInfos.size() > 5) {
				girlInfos = girlInfos.subList(0, 5);
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<GirlInfo> rankingBodySizeList(String shopInfoId) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlInfo> girlInfos = null;
		try {
			
			girlInfos = (List<GirlInfo>)session
				.createQuery("from ShopGirlInfo where shopInfoId = :shopInfoId order by bustSize desc, hipSize desc ")
				.setParameter("shopInfoId", shopInfoId).list();
			
			if(girlInfos.size() > 5) {
				girlInfos = girlInfos.subList(0, 5);
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<GirlInfo> rankingMiniSize(String shopInfoId) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlInfo> girlInfos = null;
		try {
			
			girlInfos = (List<GirlInfo>)session
				.createQuery("from ShopGirlInfo where shopInfoId = :shopInfoId order by height asc ")
				.setParameter("shopInfoId", shopInfoId).list();
			
			if(girlInfos.size() > 5) {
				girlInfos = girlInfos.subList(0, 5);
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<GirlInfo> rankingHeightSize(String shopInfoId) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlInfo> girlInfos = null;
		try {
			
			girlInfos = (List<GirlInfo>)session
				.createQuery("from ShopGirlInfo where shopInfoId = :shopInfoId order by height desc ")
				.setParameter("shopInfoId", shopInfoId).list();
			
			if(girlInfos.size() > 5) {
				girlInfos = girlInfos.subList(0, 5);
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<GirlInfo> random() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlInfo> girlInfos = null;
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("from ShopGirlInfo ");
			sql.append("where basicInfo.categoryInfo.hideZoneFlag = 'false' ");
			sql.append("order by rand() ");
			girlInfos = (List<GirlInfo>)session.createQuery(sql.toString())
					.setMaxResults(18)
					.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<GirlInfo> search(ShopGirlInfo searchGirlInfo, List<String> searchGirlLocations ) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlInfo> girlInfos = null;
		GirlInfo girlInfo;
		List<GirlLocation> girlLocations;
		try {
			
			log_.info("shopInfoId >> [" + searchGirlInfo.getShopInfoId() + "]");
			log_.info("nickName >> [" + searchGirlInfo.getNickName() + "]");
			log_.info("category >> [" + searchGirlInfo.getCategory() + "]");
			log_.info("location >> [" + searchGirlLocations + "]");
			
			StringBuffer sql = new StringBuffer();
			sql.append("from ShopGirlInfo g ");
			sql.append("where g.shopInfoId = :shopInfoId ");
			if(!searchGirlInfo.getNickName().isEmpty()) {
				sql.append("and g.nickName like :nickName ");
			}
			if(!searchGirlInfo.getCategory().isEmpty()) {
				sql.append("and g.category like :category ");
			}
			if(searchGirlLocations != null && searchGirlLocations.size() > 0) {
				sql.append("and exists(select 1 from GirlLocation gl "
						+ "where gl.primaryKey.girlInfo.girlInfoId = g.girlInfoId "
						+ "and gl.primaryKey.zoneInfo.zoneInfoId in (:location)) ");
			}
			
			Query query = session.createQuery(sql.toString());
			query.setParameter("shopInfoId", searchGirlInfo.getShopInfoId());
			if(!searchGirlInfo.getNickName().isEmpty()) {
				query.setParameter("nickName", '%'+searchGirlInfo.getNickName()+'%');
			}
			if(!searchGirlInfo.getCategory().isEmpty()) {
				query.setParameter("category", '%'+searchGirlInfo.getCategory()+'%');
			}
			if(searchGirlLocations != null && searchGirlLocations.size() > 0) {
				query.setParameterList("location", searchGirlLocations.toArray());
			}
			
			girlInfos = (List<GirlInfo>)query.list();
			Iterator it = girlInfos.iterator();
			while(it.hasNext()) {
				girlInfo = (GirlInfo) it.next();
				girlLocations = getGirlLocationListByGirlInfoId(session, girlInfo.getGirlInfoId());
				girlInfo.setGirlLocations(girlLocations);
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfos;
	}
	
	public GirlInfo getGirlInfo(String girlInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		GirlInfo girlInfo = null;
		try {
			
			girlInfo = (GirlInfo)session.createQuery("from ShopGirlInfo where girlInfoId = :girlInfoId ")
					.setParameter("girlInfoId", girlInfoId).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfo;
	}

	public void avaiableByGirlInfoId(List<String> allGirlInfoIdList, List<String> availableGirlInfoIdList) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			
			if(allGirlInfoIdList.size()> 0) {
				session.createQuery("update ShopGirlInfo set available = :available where girlInfoId in (:girlInfoIdList) ")
						.setParameter("available", Boolean.FALSE.toString().toLowerCase())
						.setParameterList("girlInfoIdList", allGirlInfoIdList.toArray())
						.executeUpdate();
				
				if(availableGirlInfoIdList.size()> 0) {
					session.createQuery("update ShopGirlInfo set available = :available where girlInfoId in (:girlInfoIdList) ")
							.setParameter("available", Boolean.TRUE.toString().toLowerCase())
							.setParameterList("girlInfoIdList", availableGirlInfoIdList.toArray())
							.executeUpdate();
				}
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
	}
}
