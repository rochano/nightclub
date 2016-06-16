package com.nightclub.controller;

import net.viralpatel.contact.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.classic.Session;

import com.nightclub.model.UserInfo;

public class UserInfoManager extends HibernateUtil {
	
	public UserInfo add(UserInfo userInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(userInfo);
		session.getTransaction().commit();
		return userInfo;
	}
	
	public UserInfo update(UserInfo userInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(userInfo);
		session.getTransaction().commit();
		return userInfo;
	}
	
	public UserInfo delete(String username) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		UserInfo userInfo = (UserInfo) session.load(UserInfo.class, username);
		if(null != userInfo) {
			session.delete(userInfo);
		}
		session.getTransaction().commit();
		return userInfo;
	}
	
	public UserInfo getUserInfo(String username) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		UserInfo userInfo = null;
		try {
			
			userInfo = (UserInfo)session.createQuery("from UserInfo where username = :username ")
					.setParameter("username", username).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return userInfo;
	}
	
	public UserInfo authenticate(String username, String password, String userType) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		UserInfo userInfo = null;
		try {
			
			userInfo = (UserInfo)session
					.createQuery("from UserInfo " + 
						"where username = :username " + 
						"and password = :password " + 
						"and userType = :userType")
					.setParameter("username", username)
					.setParameter("password", password)
					.setParameter("userType", userType).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return userInfo;
	}

}
