package com.nightclub.controller;

import java.util.List;

import net.viralpatel.contact.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.classic.Session;

import com.nightclub.model.BasicInfo;
import com.nightclub.model.ShopLocation;
import com.nightclub.model.SystemInfo;

public class BasicInfoManager extends HibernateUtil {
	
	public BasicInfo add(BasicInfo basicInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(basicInfo);
		session.getTransaction().commit();
		return basicInfo;
	}
	
	public BasicInfo update(BasicInfo basicInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		deleteShopLocationInfo(basicInfo.getShopInfoId());
		session.update(basicInfo);
		session.getTransaction().commit();
		return basicInfo;
	}
	
	public BasicInfo delete(String basicInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		BasicInfo basicInfo = (BasicInfo) session.load(BasicInfo.class, basicInfoId);
		if(null != basicInfo) {
			deleteShopLocationInfo(basicInfo.getShopInfoId());
			session.delete(basicInfo);
		}
		session.getTransaction().commit();
		return basicInfo;
	}

	@SuppressWarnings("unchecked")
	public List<BasicInfo> list() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<BasicInfo> basicInfos = null;
		try {
			
			basicInfos = (List<BasicInfo>)session.createQuery(
						"select basicInfo from BasicInfo basicInfo, UserInfo userInfo " + 
						"where basicInfo.shopInfoId = userInfo.shopInfoId " +
						"and userInfo.active = :active " +
						"and current_date between userInfo.validDateFrom and userInfo.validDateTo " + 
						"and COALESCE(userInfo.deleteFlg, :deleteFlg) = :deleteFlg")
					.setParameter("active", Boolean.TRUE.toString().toLowerCase())
					.setParameter("deleteFlg", Boolean.FALSE.toString().toLowerCase())
					.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return basicInfos;
	}

	@SuppressWarnings("unchecked")
	public List<BasicInfo> filter(String categoryInfoId) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<BasicInfo> basicInfos = null;
		List<ShopLocation> shopLocations;
		try {
			
			basicInfos = (List<BasicInfo>)session
					.createQuery("select basicInfo from BasicInfo basicInfo, UserInfo userInfo " +
							"where basicInfo.categoryInfoId = :categoryInfoId " +
							"and basicInfo.shopInfoId = userInfo.shopInfoId " +
							"and userInfo.active = :active " +
							"and current_date between userInfo.validDateFrom and userInfo.validDateTo " + 
							"and COALESCE(userInfo.deleteFlg, :deleteFlg) = :deleteFlg")
					.setParameter("categoryInfoId", categoryInfoId)
					.setParameter("active", Boolean.TRUE.toString())
					.setParameter("deleteFlg", Boolean.FALSE.toString().toLowerCase())
					.list();
			
			if(basicInfos != null) {
				for(BasicInfo basicInfo : basicInfos) {
					SystemInfo systemInfo = (SystemInfo)session
						.createQuery("from SystemInfo s " + 
						"where s.shopInfoId = :shopInfoId ")
						.setParameter("shopInfoId", basicInfo.getShopInfoId())
						.setMaxResults(1)
						.uniqueResult();
					if(systemInfo != null) {
						basicInfo.setSystemInfo(systemInfo);
					}
					shopLocations = getShopLocationListByShopInfoId(session, basicInfo.getShopInfoId());
					basicInfo.setShopLocations(shopLocations);
				}
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return basicInfos;
	}

	@SuppressWarnings("unchecked")
	public List<BasicInfo> filter(String categoryInfoId, String zoneInfoId) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<BasicInfo> basicInfos = null;
		try {
			
			basicInfos = (List<BasicInfo>)session
					.createQuery("select basicInfo from BasicInfo basicInfo, UserInfo userInfo " +
							"where basicInfo.categoryInfoId = :categoryInfoId " +
							"and basicInfo.zoneInfoId = :zoneInfoId " +
							"and userInfo.active = :active " +
							"and current_date between userInfo.validDateFrom and userInfo.validDateTo " + 
							"and COALESCE(userInfo.deleteFlg, :deleteFlg) = :deleteFlg")
					.setParameter("categoryInfoId", categoryInfoId)
					.setParameter("zoneInfoId", zoneInfoId)
					.setParameter("active", Boolean.TRUE.toString())
					.setParameter("deleteFlg", Boolean.FALSE.toString().toLowerCase())
					.list();
			
			if(basicInfos != null) {
				for(BasicInfo basicInfo : basicInfos) {
					SystemInfo systemInfo = (SystemInfo)session
						.createQuery("from SystemInfo s " + 
						"where s.shopInfoId = :shopInfoId " +
						"and s.price = (select min(ss.price) from SystemInfo ss where ss.shopInfoId = s.shopInfoId group by ss.shopInfoId) ")
						.setParameter("shopInfoId", basicInfo.getShopInfoId())
						.setMaxResults(1)
						.uniqueResult();
					if(systemInfo != null) {
						basicInfo.setSystemInfo(systemInfo);
					}
				}
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return basicInfos;
	}
	
	public BasicInfo getBasicInfo(String shopInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		BasicInfo basicInfo = null;
		try {
			
			basicInfo = (BasicInfo)session.createQuery("from BasicInfo where shopInfoId = :shopInfoId ")
					.setParameter("shopInfoId", shopInfoId).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return basicInfo;
	}
	
//	public BasicInfo validateshopInfoId(String shopInfoId, String shopCd) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
//		BasicInfo basicInfo = null;
//		try {
//			
//			basicInfo = (BasicInfo)session
//					.createQuery("from BasicInfo where shopInfoId != :shopInfoId and shopCd = :shopCd ")
//					.setParameter("shopInfoId", shopInfoId)
//					.setParameter("shopCd", shopCd)
//					.uniqueResult();
//			
//		} catch (HibernateException e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//		session.getTransaction().commit();
//		return basicInfo;
//	}
	
	public BasicInfo getBasicInfoById(String shopInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		BasicInfo basicInfo = null;
		try {
			
			basicInfo = (BasicInfo)session.createQuery("from BasicInfo where shopInfoId = :shopInfoId ")
					.setParameter("shopInfoId", shopInfoId).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return basicInfo;
	}

	@SuppressWarnings("unchecked")
	public List<ShopLocation> getShopLocationListByShopInfoId(String shopInfoId) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<ShopLocation> shopLocations = null;
		try {
			
			shopLocations = getShopLocationListByShopInfoId(session, shopInfoId);
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return shopLocations;
	}
	
	@SuppressWarnings("unchecked")
	public List<ShopLocation> getShopLocationListByShopInfoId(Session session, String shopInfoId) {
		List<ShopLocation> shopLocations = null;
		shopLocations = (List<ShopLocation>)session.createQuery("from ShopLocation sl where sl.primaryKey.basicInfo.shopInfoId = :shopInfoId")
					.setParameter("shopInfoId", shopInfoId)
					.list();
		return shopLocations;
	}
	
	public void deleteShopLocationInfo(String shopInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.createQuery("delete from ShopLocation sl where sl.primaryKey.basicInfo.shopInfoId = :shopInfoId")
			.setParameter("shopInfoId", shopInfoId).executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
}
