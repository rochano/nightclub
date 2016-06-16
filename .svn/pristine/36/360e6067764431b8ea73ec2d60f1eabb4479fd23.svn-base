package com.nightclub.controller;

import net.viralpatel.contact.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.classic.Session;

import com.nightclub.model.HomeInfo;

public class HomeInfoManager extends HibernateUtil {
	
	public HomeInfo add(HomeInfo homeInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(homeInfo);
		session.getTransaction().commit();
		return homeInfo;
	}
	
	public HomeInfo update(HomeInfo homeInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(homeInfo);
		session.getTransaction().commit();
		return homeInfo;
	}
	
	public HomeInfo getHomeInfo(String homeInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		HomeInfo homeInfo = null;
		try {
			
			homeInfo = (HomeInfo)session.createQuery("from HomeInfo where homeInfoId = :homeInfoId ")
					.setParameter("homeInfoId", homeInfoId).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return homeInfo;
	}
}
