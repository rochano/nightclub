package com.nightclub.controller;

import net.viralpatel.contact.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.classic.Session;

import com.nightclub.model.BasicInfo;
import com.nightclub.model.GirlSetting;

public class GirlSettingManager extends HibernateUtil {
	
	public GirlSetting add(GirlSetting girlSetting) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(girlSetting);
		session.getTransaction().commit();
		return girlSetting;
	}
	
	public GirlSetting update(GirlSetting girlSetting) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(girlSetting);
		session.getTransaction().commit();
		return girlSetting;
	}
	
	public GirlSetting getGirlSetting() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		GirlSetting girlSetting = null;
		try {
			
			girlSetting = (GirlSetting)session.createQuery("from GirlSetting")
					.uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlSetting;
	}
}
