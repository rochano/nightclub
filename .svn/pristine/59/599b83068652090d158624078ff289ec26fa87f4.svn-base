package com.nightclub.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import net.viralpatel.contact.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.classic.Session;

import com.nightclub.model.StatisticInfo;
import com.nightclub.model.StatisticInfoPK;
import com.nightclub.model.ZoneInfo;

public class StatisticInfoManager extends HibernateUtil  {
	
	public StatisticInfo add(StatisticInfo statisticInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(statisticInfo);
		session.getTransaction().commit();
		return statisticInfo;
	}
	
	public StatisticInfo update(StatisticInfo statisticInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(statisticInfo);
		session.getTransaction().commit();
		return statisticInfo;
	}
	
	public StatisticInfo getStatisticInfo(StatisticInfoPK statisticInfoPK) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		StatisticInfo statisticInfo = null;
		try {
			
			statisticInfo = (StatisticInfo)session.createQuery("from StatisticInfo " +
					"where statisticInfoPK.ipaddress = :ipaddress " +
					"and statisticInfoPK.accessDt = :accessDt")
					.setParameter("ipaddress", statisticInfoPK.getIpaddress())
					.setParameter("accessDt", statisticInfoPK.getAccessDt()).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return statisticInfo;
	}
	
	@SuppressWarnings("unchecked")
	public List<StatisticInfo> getStatisticInfosByDate(String accessDt) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<StatisticInfo> statisticInfos = null;
		try {
			
			statisticInfos = (List<StatisticInfo>)session
					.createQuery("from StatisticInfo where statisticInfoPK.accessDt like :accessDt")
					.setParameter("accessDt", accessDt)
					.list();
			
			if(statisticInfos == null) {
				statisticInfos = new ArrayList<StatisticInfo>();
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		
		return statisticInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<StatisticInfo> getStatisticInfosOnline(Timestamp online) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<StatisticInfo> statisticInfos = null;
		try {
			
			statisticInfos = (List<StatisticInfo>)session
					.createQuery("from StatisticInfo where online > :online")
					.setParameter("online", online)
					.list();
			
			if(statisticInfos == null) {
				statisticInfos = new ArrayList<StatisticInfo>();
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		
		return statisticInfos;
	}

}
