package com.nightclub.controller;

import java.util.Iterator;
import java.util.List;

import net.viralpatel.contact.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.classic.Session;

import com.nightclub.model.ClientInfo;
import com.nightclub.model.GirlFavourite;
import com.nightclub.model.GirlFavouriteId;
import com.nightclub.model.GirlInfo;
import com.nightclub.model.GirlLocation;

public class GirlFavouriteManager extends HibernateUtil {
	
	public GirlFavourite add(GirlFavourite girlFavourite) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(girlFavourite);
		session.getTransaction().commit();
		return girlFavourite;
	}
	
	public GirlFavourite update(GirlFavourite girlFavourite) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(girlFavourite);
		session.getTransaction().commit();
		return girlFavourite;
	}
	
	public GirlFavourite delete(String clientInfoId, String girlInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		GirlFavouriteId girlFavouriteId = new GirlFavouriteId();
		ClientInfo clientInfo = new ClientInfo();
		GirlInfo girlInfo = new GirlInfo();
		clientInfo.setClientInfoId(clientInfoId);
		girlInfo.setGirlInfoId(girlInfoId);
		girlFavouriteId.setClientInfo(clientInfo);
		girlFavouriteId.setGirlInfo(girlInfo);
		GirlFavourite girlFavourite = (GirlFavourite) session.load(GirlFavourite.class, girlFavouriteId);
		if(null != girlFavourite) {
			session.delete(girlFavourite);
		}
		session.getTransaction().commit();
		return girlFavourite;
	}
	
	public GirlFavourite getGirlFavourite(String clientInfoId, String girlInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		GirlFavourite girlFavourite = null;
		try {
			
			girlFavourite = (GirlFavourite)session.createQuery("from GirlFavourite " + 
					"where primaryKey.clientInfo.clientInfoId = :clientInfoId " + 
					"and primaryKey.girlInfo.girlInfoId = :girlInfoId ")
					.setParameter("clientInfoId", clientInfoId)
					.setParameter("girlInfoId", girlInfoId)
					.uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlFavourite;
	}
	
	public List<String> listByCustomerInfoId(String clientInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<String> girlFavourites = null;
		try {
			
			girlFavourites = session.createQuery("select primaryKey.girlInfo.girlInfoId from GirlFavourite " + 
					"where primaryKey.clientInfo.clientInfoId = :clientInfoId ")
					.setParameter("clientInfoId", clientInfoId)
					.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlFavourites;
	}
	
	public List<GirlInfo> list(String clientInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlInfo> girlFavourites = null;
		GirlInfo girlInfo;
		List<GirlLocation> girlLocations;
		try {
			
			girlFavourites = session.createQuery("select girlInfo from GirlFavourite girlFavourite, GirlInfo girlInfo " + 
					"where girlFavourite.primaryKey.girlInfo.girlInfoId = girlInfo.girlInfoId " +
					"and girlFavourite.primaryKey.clientInfo.clientInfoId = :clientInfoId ")
					.setParameter("clientInfoId", clientInfoId)
					.list();
			Iterator it = girlFavourites.iterator();
			while(it.hasNext()) {
				girlInfo = (GirlInfo) it.next();
				girlLocations = getGirlLocationListByGirlInfoId(session, girlInfo.getGirlInfoId());
				girlInfo.setGirlLocations(girlLocations);
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlFavourites;
	}
	
	@SuppressWarnings("unchecked")
	public List<GirlLocation> getGirlLocationListByGirlInfoId(Session session, String girlInfoId) {
		List<GirlLocation> girlLocations = null;
		girlLocations = (List<GirlLocation>)session.createQuery("from GirlLocation gl where gl.primaryKey.girlInfo.girlInfoId = :girlInfoId")
					.setParameter("girlInfoId", girlInfoId)
					.list();
		return girlLocations;
	}
}
