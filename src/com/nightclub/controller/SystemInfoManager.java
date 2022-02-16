package com.nightclub.controller;

import java.util.List;
import java.util.logging.Logger;

import net.viralpatel.contact.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.nightclub.model.GirlInfo;
import com.nightclub.model.ScheduleInfo;
import com.nightclub.model.SystemInfo;

public class SystemInfoManager extends HibernateUtil {
	
	private Logger log_ = Logger.getLogger(this.getClass().getName());
	
	public SystemInfo add(SystemInfo systemInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(systemInfo);
		session.getTransaction().commit();
		return systemInfo;
	}
	
	public SystemInfo update(SystemInfo systemInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(systemInfo);
		session.getTransaction().commit();
		return systemInfo;
	}
	
	public SystemInfo delete(String shopInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		SystemInfo systemInfo = (SystemInfo) session.load(SystemInfo.class, shopInfoId);
		if(null != systemInfo) {
			session.delete(systemInfo);
		}
		session.getTransaction().commit();
		return systemInfo;
	}

//	@SuppressWarnings("unchecked")
//	public List<SystemInfo> list(String shopInfoId) {
//		
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
//		List<SystemInfo> systemInfos = null;
//		try {
//			
//			systemInfos = (List<SystemInfo>)session.createQuery("from SystemInfo where shopInfoId = :shopInfoId")
//					.setParameter("shopInfoId", shopInfoId).list();
//			
//		} catch (HibernateException e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//		session.getTransaction().commit();
//		
//		return systemInfos;
//	}
	
//	@SuppressWarnings("unchecked")
//	public List<SystemInfo> search(SystemInfo systemInfo ) {
//		
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
//		List<SystemInfo> systemInfos = null;
//		try {
//			
//			log_.info("shopInfoId >> [" + systemInfo.getShopInfoId() + "]");
//			log_.info("className >> [" + systemInfo.getClassName() + "]");
//			
//			StringBuffer sql = new StringBuffer();
//			sql.append("from SystemInfo ");
//			sql.append("where shopInfoId = :shopInfoId ");
//			if(!systemInfo.getClassName().isEmpty()) {
//				sql.append("and className like :className ");
//			}
//			
//			Query query = session.createQuery(sql.toString());
//			query.setParameter("shopInfoId", systemInfo.getShopInfoId());
//			if(!systemInfo.getClassName().isEmpty()) {
//				query.setParameter("className", '%'+systemInfo.getClassName()+'%');
//			}
//			
//			systemInfos = (List<SystemInfo>)query.list();
//			
//		} catch (HibernateException e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//		session.getTransaction().commit();
//		return systemInfos;
//	}
//	
	public SystemInfo getSystemInfo(String shopInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		SystemInfo systemInfo = null;
		try {
			
			systemInfo = (SystemInfo)session.createQuery("from SystemInfo where shopInfoId = :shopInfoId ")
					.setParameter("shopInfoId", shopInfoId).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return systemInfo;
	}

	public void activeByClassType(String shopInfoId, String classType) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			log_.info("shopInfoId >> [" + shopInfoId + "]");
			log_.info("classType >> [" + classType + "]");

			if(!classType.isEmpty()) {
				session.createQuery("update SystemInfo set classType = :classType where shopInfoId = :shopInfoId ")
						.setParameter("classType", classType)
						.setParameter("shopInfoId", shopInfoId)
						.executeUpdate();
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
	}
}
