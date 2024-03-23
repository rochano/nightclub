package com.nightclub.controller;

import java.util.ArrayList;
import java.util.List;

import net.viralpatel.contact.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.classic.Session;

import com.nightclub.model.StatisticGirlInfo;
import com.nightclub.model.StatisticGirlInfoPK;

public class StatisticGirlInfoManager extends HibernateUtil  {
	
	public StatisticGirlInfo add(StatisticGirlInfo statisticGirlInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(statisticGirlInfo);
		session.getTransaction().commit();
		return statisticGirlInfo;
	}
	
	public StatisticGirlInfo update(StatisticGirlInfo statisticGirlInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(statisticGirlInfo);
		session.getTransaction().commit();
		return statisticGirlInfo;
	}
	
	public StatisticGirlInfo getStatisticGirlInfo(StatisticGirlInfoPK statisticGirlInfoPK) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		StatisticGirlInfo statisticGirlInfo = null;
		try {
			
			statisticGirlInfo = (StatisticGirlInfo)session.createQuery("from StatisticGirlInfo " +
					"where statisticGirlInfoPK.ipaddress = :ipaddress " +
					"and statisticGirlInfoPK.accessDt = :accessDt " +
					"and statisticGirlInfoPK.girlInfoId = :girlInfoId")
					.setParameter("ipaddress", statisticGirlInfoPK.getIpaddress())
					.setParameter("accessDt", statisticGirlInfoPK.getAccessDt())
					.setParameter("girlInfoId", statisticGirlInfoPK.getGirlInfoId()).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return statisticGirlInfo;
	}
	
	@SuppressWarnings("unchecked")
	public List<StatisticGirlInfo> getStatisticGirlInfosByGirlInfoId(String girlInfoId) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<StatisticGirlInfo> statisticGirlInfos = null;
		try {
			
			statisticGirlInfos = (List<StatisticGirlInfo>)session
					.createQuery("from StatisticGirlInfo where statisticGirlInfoPK.girlInfoId like :girlInfoId")
					.setParameter("girlInfoId", girlInfoId)
					.list();
			
			if(statisticGirlInfos == null) {
				statisticGirlInfos = new ArrayList<StatisticGirlInfo>();
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		
		return statisticGirlInfos;
	}
}
