package com.nightclub.controller;

import java.util.Iterator;
import java.util.List;

import net.viralpatel.contact.util.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.classic.Session;

import com.nightclub.model.GirlInfo;
import com.nightclub.model.GirlLocation;

public class EnGirlInfoManager extends GirlInfoManager {
	
	private static Logger log_ = Logger.getLogger(EnGirlInfoManager.class);

	public GirlInfo getGirlInfo(String girlInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		GirlInfo girlInfo = null;
		try {
			
			girlInfo = (GirlInfo)session.createQuery("from EnGirlInfo where girlInfoId = :girlInfoId ")
					.setParameter("girlInfoId", girlInfoId).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfo;
	}
	
	@SuppressWarnings("unchecked")
	public List<GirlInfo> list() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlInfo> girlInfos = null;
		GirlInfo girlInfo;
		List<GirlLocation> girlLocations;
		try {
			
			girlInfos = (List<GirlInfo>)session.createQuery("select enGirlInfo from EnGirlInfo enGirlInfo, UserInfo userInfo " +
					"where DTYPE = 'EnGirlInfo' " +
					"and enGirlInfo.girlInfoId = userInfo.girlInfoId " +
					"and COALESCE(userInfo.deleteFlg, :deleteFlg) = :deleteFlg")
//					"and userInfo.active = :active " +
//					"and current_date between userInfo.validDateFrom and userInfo.validDateTo")
//					.setParameter("active", Boolean.TRUE.toString().toLowerCase())
					.setParameter("deleteFlg", Boolean.FALSE.toString().toLowerCase())
					.list();
			Iterator it = girlInfos.iterator();
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
		return girlInfos;
	}
}
