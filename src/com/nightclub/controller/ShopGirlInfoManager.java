package com.nightclub.controller;

import java.util.Date;
import java.util.List;

import net.viralpatel.contact.util.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.nightclub.model.GirlInfo;
import com.nightclub.model.ShopGirlInfo;

public class ShopGirlInfoManager extends GirlInfoManager {
	
	private static Logger log_ = Logger.getLogger(GirlInfoManager.class);

	@SuppressWarnings("unchecked")
	public List<GirlInfo> list(String shopInfoId) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlInfo> girlInfos = null;
		try {
			
			girlInfos = (List<GirlInfo>)session.createQuery("from ShopGirlInfo where shopInfoId = :shopInfoId ")
					.setParameter("shopInfoId", shopInfoId).list();
			
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
	public List<GirlInfo> search(ShopGirlInfo girlInfo ) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlInfo> girlInfos = null;
		try {
			
			log_.info("shopInfoId >> [" + girlInfo.getShopInfoId() + "]");
			log_.info("nickName >> [" + girlInfo.getNickName() + "]");
			log_.info("category >> [" + girlInfo.getCategory() + "]");
			log_.info("location >> [" + girlInfo.getLocation() + "]");
			
			StringBuffer sql = new StringBuffer();
			sql.append("from ShopGirlInfo ");
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

	public void avaiableByGirlInfoId(String shopInfoId, List<String> girlInfoIdList) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			
			session.createQuery("update ShopGirlInfo set available = :available where shopInfoId = :shopInfoId ")
					.setParameter("available", Boolean.FALSE.toString().toLowerCase())
					.setParameter("shopInfoId", shopInfoId)
					.executeUpdate();
			
			if(girlInfoIdList.size()> 0) {
				session.createQuery("update ShopGirlInfo set available = :available where girlInfoId in (:girlInfoIdList) ")
						.setParameter("available", Boolean.TRUE.toString().toLowerCase())
						.setParameterList("girlInfoIdList", girlInfoIdList.toArray())
						.executeUpdate();
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
	}
}
