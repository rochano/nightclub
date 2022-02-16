package com.nightclub.controller;

import java.util.List;

import net.viralpatel.contact.util.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.classic.Session;

import com.nightclub.model.AgentInfo;
import com.nightclub.model.GirlInfo;

public class FreeAgentGirlInfoManager extends GirlInfoManager {
	
	private static Logger log_ = Logger.getLogger(GirlInfoManager.class);

//	@SuppressWarnings("unchecked")
//	public List<GirlInfo> random() {
//		
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
//		List<GirlInfo> girlInfos = null;
//		try {
//			
//			StringBuffer sql = new StringBuffer();
//			sql.append("from FreeAgentGirlInfo freeAgentGirlInfo, UserInfo userInfo ");
//			sql.append("where agentInfo.agentInfoId = userInfo.agentInfoId ");
//			sql.append("and userInfo.active = :active ");
//			sql.append("and current_date between userInfo.validDateFrom and userInfo.validDateTo");
//			sql.append("order by rand() ");
//			girlInfos = (List<GirlInfo>)session.createQuery(sql.toString())
//					.setParameter("active", Boolean.TRUE.toString().toLowerCase())
//					.setMaxResults(18)
//					.list();
//			
//		} catch (HibernateException e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//		session.getTransaction().commit();
//		return girlInfos;
//	}

	public GirlInfo getGirlInfo(String girlInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		GirlInfo girlInfo = null;
		try {
			
			girlInfo = (GirlInfo)session.createQuery("from FreeAgentGirlInfo where girlInfoId = :girlInfoId ")
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
		try {
			
			girlInfos = (List<GirlInfo>)session.createQuery("select freeAgentGirlInfo from FreeAgentGirlInfo freeAgentGirlInfo, UserInfo userInfo " +
					"where DTYPE = 'FreeAgentGirlInfo' " +
					"and freeAgentGirlInfo.girlInfoId = userInfo.girlInfoId " +
					"and userInfo.active = :active " +
					"and current_date between userInfo.validDateFrom and userInfo.validDateTo")
					.setParameter("active", Boolean.TRUE.toString().toLowerCase())
					.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfos;
	}
}
