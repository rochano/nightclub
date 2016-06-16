package com.nightclub.controller;

import java.util.List;

import net.viralpatel.contact.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.classic.Session;

import com.nightclub.model.BasicInfo;
import com.nightclub.model.MapInfo;

public class MapInfoManager extends HibernateUtil {
	
	public MapInfo add(MapInfo mapInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(mapInfo);
		session.getTransaction().commit();
		return mapInfo;
	}
	
	public MapInfo update(MapInfo mapInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(mapInfo);
		session.getTransaction().commit();
		return mapInfo;
	}
	
	public MapInfo delete(String mapInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		MapInfo mapInfo = (MapInfo) session.load(MapInfo.class, mapInfoId);
		if(null != mapInfo) {
			session.delete(mapInfo);
		}
		session.getTransaction().commit();
		return mapInfo;
	}

	@SuppressWarnings("unchecked")
	public List<MapInfo> list() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<MapInfo> mapInfos = null;
		try {
			
			mapInfos = (List<MapInfo>)session.createQuery("from MapInfo").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return mapInfos;
	}
	
	public MapInfo getMapInfo(String shopInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		MapInfo mapInfo = null;
		try {
			
			mapInfo = (MapInfo)session.createQuery("from MapInfo where shopInfoId = :shopInfoId ")
					.setParameter("shopInfoId", shopInfoId).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return mapInfo;
	}
}
