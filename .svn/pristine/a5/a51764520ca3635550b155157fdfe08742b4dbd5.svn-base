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
		session.update(systemInfo);
		session.getTransaction().commit();
		return systemInfo;
	}
	
	public SystemInfo delete(String systemInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		SystemInfo systemInfo = (SystemInfo) session.load(SystemInfo.class, systemInfoId);
		if(null != systemInfo) {
			session.delete(systemInfo);
		}
		session.getTransaction().commit();
		return systemInfo;
	}

	@SuppressWarnings("unchecked")
	public List<SystemInfo> list(String shopInfoId) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<SystemInfo> systemInfos = null;
		try {
			
			systemInfos = (List<SystemInfo>)session.createQuery("from SystemInfo where shopInfoId = :shopInfoId order by infoName ")
					.setParameter("shopInfoId", shopInfoId).list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		
		return systemInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<SystemInfo> search(SystemInfo systemInfo ) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<SystemInfo> systemInfos = null;
		try {
			
			log_.info("shopInfoId >> [" + systemInfo.getShopInfoId() + "]");
			log_.info("name >> [" + systemInfo.getInfoName() + "]");
			
			StringBuffer sql = new StringBuffer();
			sql.append("from SystemInfo ");
			sql.append("where shopInfoId = :shopInfoId ");
			if(!systemInfo.getInfoName().isEmpty()) {
				sql.append("and infoName like :infoName ");
			}
			
			Query query = session.createQuery(sql.toString());
			query.setParameter("shopInfoId", systemInfo.getShopInfoId());
			if(!systemInfo.getInfoName().isEmpty()) {
				query.setParameter("infoName", '%'+systemInfo.getInfoName()+'%');
			}
			
			systemInfos = (List<SystemInfo>)query.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return systemInfos;
	}
	
	public SystemInfo getSystemInfo(String systemInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		SystemInfo systemInfo = null;
		try {
			
			systemInfo = (SystemInfo)session.createQuery("from SystemInfo where systemInfoId = :systemInfoId ")
					.setParameter("systemInfoId", systemInfoId).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return systemInfo;
	}
}
