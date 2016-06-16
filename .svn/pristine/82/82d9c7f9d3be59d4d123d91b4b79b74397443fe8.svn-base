package com.nightclub.controller;

import java.util.List;

import net.viralpatel.contact.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.classic.Session;

import com.nightclub.model.BasicInfo;
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
		session.update(basicInfo);
		session.getTransaction().commit();
		return basicInfo;
	}
	
	public BasicInfo delete(String basicInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		BasicInfo basicInfo = (BasicInfo) session.load(BasicInfo.class, basicInfoId);
		if(null != basicInfo) {
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
			
			basicInfos = (List<BasicInfo>)session.createQuery("from BasicInfo").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return basicInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<BasicInfo> filter(String categoryCode, String zoneCode) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<BasicInfo> basicInfos = null;
		try {
			
			basicInfos = (List<BasicInfo>)session
					.createQuery("from BasicInfo where categoryCode = :categoryCode and zoneCode = :zoneCode")
					.setParameter("categoryCode", categoryCode)
					.setParameter("zoneCode", zoneCode)
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
	
	public BasicInfo validateShopCode(String shopInfoId, String shopCode) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		BasicInfo basicInfo = null;
		try {
			
			basicInfo = (BasicInfo)session
					.createQuery("from BasicInfo where shopInfoId != :shopInfoId and shopCode = :shopCode ")
					.setParameter("shopInfoId", shopInfoId)
					.setParameter("shopCode", shopCode)
					.uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return basicInfo;
	}
	
	public BasicInfo getBasicInfoByCode(String shopCode) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		BasicInfo basicInfo = null;
		try {
			
			basicInfo = (BasicInfo)session.createQuery("from BasicInfo where shopCode = :shopCode ")
					.setParameter("shopCode", shopCode).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return basicInfo;
	}
}
