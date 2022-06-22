package com.nightclub.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.viralpatel.contact.util.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.classic.Session;

import com.nightclub.model.ClientInfo;
import com.nightclub.model.ReserveInfo;
import com.nightclub.model.ShopGirlInfo;

public class ShopReserveInfoManager extends HibernateUtil {
	
	private static Logger log_ = Logger.getLogger(ShopReserveInfoManager.class);

	public ReserveInfo add(ReserveInfo reserveInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(reserveInfo);
		session.getTransaction().commit();
		return reserveInfo;
	}
	
	public ReserveInfo update(ReserveInfo reserveInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(reserveInfo);
		session.getTransaction().commit();
		return reserveInfo;
	}
	
	public ReserveInfo delete(String reserveInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		ReserveInfo reserveInfo = (ReserveInfo) session.load(ReserveInfo.class, reserveInfoId);
		if(null != reserveInfo) {
			session.delete(reserveInfo);
		}
		session.getTransaction().commit();
		return reserveInfo;
	}
	
	public List<ReserveInfo> getReserveInfosByGirlInfoId(String girlInfoId, Date reserveDate) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<ReserveInfo> reserveInfos = null;
		ReserveInfo reserveInfo = null;
		try {
			
			reserveInfos = (List<ReserveInfo>)session.createQuery(
					"from ReserveInfo where girlInfoId = :girlInfoId "
					+ "and date_format(reserveDate,'%Y%m%d') = date_format(:reserveDate,'%Y%m%d') order by startTime ")
					.setParameter("girlInfoId", girlInfoId)
					.setParameter("reserveDate", reserveDate)
					.list();
			Iterator it = reserveInfos.iterator();
			while(it.hasNext()) {
				reserveInfo = (ReserveInfo) it.next();
				((ShopGirlInfo)reserveInfo.getGirlInfo()).setBasicInfo(null);
				((ShopGirlInfo)reserveInfo.getGirlInfo()).setGirlLocations(new ArrayList());
				reserveInfo.getClientInfo().setGirlFavourite(new ArrayList());
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		
		return reserveInfos;
	}
	
	public List<ReserveInfo> getReserveInfosByGirlInfoIdAndClientId(String girlInfoId, String clientInfoId, Date reserveDate) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<ReserveInfo> reserveInfos = null;
		ReserveInfo reserveInfo = null;
		try {
			
			reserveInfos = (List<ReserveInfo>)session.createQuery(
					"from ReserveInfo where girlInfoId = :girlInfoId "
					+ "and clientInfoId = :clientInfoId "
					+ "and date_format(reserveDate,'%Y%m%d') = date_format(:reserveDate,'%Y%m%d') order by startTime ")
					.setParameter("girlInfoId", girlInfoId)
					.setParameter("clientInfoId", clientInfoId)
					.setParameter("reserveDate", reserveDate)
					.list();
			Iterator it = reserveInfos.iterator();
			while(it.hasNext()) {
				reserveInfo = (ReserveInfo) it.next();
				((ShopGirlInfo)reserveInfo.getGirlInfo()).setBasicInfo(null);
				((ShopGirlInfo)reserveInfo.getGirlInfo()).setGirlLocations(new ArrayList());
				reserveInfo.getClientInfo().setGirlFavourite(new ArrayList());
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		
		return reserveInfos;
	}
	
	public List<ReserveInfo> getReserveInfosByReserveDate(String shopInfoId, Date reserveDate) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<ReserveInfo> reserveInfos = null;
		ReserveInfo reserveInfo = null;
		try {
			
			reserveInfos = (List<ReserveInfo>)session.createQuery(
					"from ReserveInfo where shopInfoId = :shopInfoId "
					+ "and date_format(reserveDate,'%Y%m%d') = date_format(:reserveDate,'%Y%m%d') order by girlInfoId, startTime ")
					.setParameter("shopInfoId", shopInfoId)
					.setParameter("reserveDate", reserveDate)
					.list();
			Iterator it = reserveInfos.iterator();
			while(it.hasNext()) {
				reserveInfo = (ReserveInfo) it.next();
				((ShopGirlInfo)reserveInfo.getGirlInfo()).setBasicInfo(null);
				((ShopGirlInfo)reserveInfo.getGirlInfo()).setGirlLocations(new ArrayList());
				reserveInfo.getClientInfo().setGirlFavourite(new ArrayList());
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		
		return reserveInfos;
	}
}
