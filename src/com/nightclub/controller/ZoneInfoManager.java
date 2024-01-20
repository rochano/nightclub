package com.nightclub.controller;

import java.util.List;
import java.util.logging.Logger;

import net.viralpatel.contact.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.nightclub.model.CategoryInfo;
import com.nightclub.model.GirlInfo;
import com.nightclub.model.ScheduleInfo;
import com.nightclub.model.ZoneInfo;

public class ZoneInfoManager extends HibernateUtil {
	
	private Logger log_ = Logger.getLogger(this.getClass().getName());
	
	public ZoneInfo add(ZoneInfo zoneInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(zoneInfo);
		session.getTransaction().commit();
		return zoneInfo;
	}
	
	public ZoneInfo update(ZoneInfo zoneInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(zoneInfo);
		session.getTransaction().commit();
		return zoneInfo;
	}
	
	public ZoneInfo delete(String zoneInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		ZoneInfo zoneInfo = (ZoneInfo) session.load(ZoneInfo.class, zoneInfoId);
		if(null != zoneInfo) {
			session.delete(zoneInfo);
		}
		session.getTransaction().commit();
		return zoneInfo;
	}
	
	public boolean isRelatedCategory(String zoneInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		boolean bResult = false;
		try {
			ZoneInfo zoneInfo = (ZoneInfo)session
					.createQuery("from ZoneInfo z left join fetch z.categoryZones cz where z.zoneInfoId = :zoneInfoId ")
					.setParameter("zoneInfoId", zoneInfoId).uniqueResult();
			
			if(zoneInfo != null && zoneInfo.getCategoryZones().size() > 0) {
				bResult = true;
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return bResult;
	}

	public boolean isRelatedGirlInfo(String zoneInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		boolean bResult = false;
		try {
			List<GirlInfo> girlInfos = (List<GirlInfo>)session
					.createQuery("from GirlLocation where primaryKey.zoneInfo.zoneInfoId = :zoneInfoId ")
					.setParameter("zoneInfoId", zoneInfoId).list();
			
			if(girlInfos.size() > 0) {
				bResult = true;
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return bResult;
	}

	@SuppressWarnings("unchecked")
	public List<ZoneInfo> list() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<ZoneInfo> zoneInfos = null;
		try {
			
			zoneInfos = (List<ZoneInfo>)session.createQuery("from ZoneInfo").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		
		return zoneInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<ZoneInfo> search(ZoneInfo zoneInfo ) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<ZoneInfo> zoneInfos = null;
		try {
			
//			log_.info("zone code >> [" + zoneInfo.getZoneCode() + "]");
			log_.info("zone name jp >> [" + zoneInfo.getZoneNameJp() + "]");
			log_.info("zone name en >> [" + zoneInfo.getZoneNameEn() + "]");
			log_.info("province info id >> [" + zoneInfo.getProvinceInfoId() + "]");
			
			StringBuffer sql = new StringBuffer();
			sql.append("from ZoneInfo where 1 = 1 ");
//			if(!zoneInfo.getZoneCode().isEmpty()) {
//				sql.append("and zoneCode like :zoneCode ");
//			}
			if(!zoneInfo.getZoneNameJp().isEmpty()) {
				sql.append("and zoneNameJp like :zoneNameJp ");
			}
			if(!zoneInfo.getZoneNameEn().isEmpty()) {
				sql.append("and zoneNameEn like :zoneNameEn ");
			}
			if(zoneInfo.getProvinceInfoId() != null && !zoneInfo.getProvinceInfoId().isEmpty()) {
				sql.append("and provinceInfoId = :provinceInfoId ");
			}
			
			Query query = session.createQuery(sql.toString());
//			if(!zoneInfo.getZoneCode().isEmpty()) {
//				query.setParameter("zoneCode", '%'+zoneInfo.getZoneCode()+'%');
//			}
			if(!zoneInfo.getZoneNameJp().isEmpty()) {
				query.setParameter("zoneNameJp", '%'+zoneInfo.getZoneNameJp()+'%');
			}
			if(!zoneInfo.getZoneNameEn().isEmpty()) {
				query.setParameter("zoneNameEn", '%'+zoneInfo.getZoneNameEn()+'%');
			}
			if(zoneInfo.getProvinceInfoId() != null && !zoneInfo.getProvinceInfoId().isEmpty()) {
				query.setParameter("provinceInfoId", zoneInfo.getProvinceInfoId());
			}
			
			zoneInfos = (List<ZoneInfo>)query.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return zoneInfos;
	}
	
	public ZoneInfo getZoneInfo(String zoneInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		ZoneInfo zoneInfo = null;
		try {
			
			zoneInfo = (ZoneInfo)session.createQuery("from ZoneInfo where zoneInfoId = :zoneInfoId ")
					.setParameter("zoneInfoId", zoneInfoId).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return zoneInfo;
	}
}
