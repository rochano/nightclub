package com.nightclub.controller;

import java.util.List;

import net.viralpatel.contact.util.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.nightclub.model.AgentGirlInfo;
import com.nightclub.model.GirlInfo;

public class AgentGirlInfoManager extends GirlInfoManager {
	
	private static Logger log_ = Logger.getLogger(GirlInfoManager.class);

	@SuppressWarnings("unchecked")
	public List<GirlInfo> list(String agentInfoId) {
		return list(agentInfoId, false);
	}
	
	@SuppressWarnings("unchecked")
	public List<GirlInfo> list(String agentInfoId, boolean checkAvailable) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlInfo> girlInfos = null;
		try {
			String sql = "from AgentGirlInfo where agentInfoId = :agentInfoId ";
			if(checkAvailable) {
				sql += "and available = :available ";
			}
			Query query = session.createQuery(sql);
			query = query.setParameter("agentInfoId", agentInfoId);
			if(checkAvailable) {
				query = query.setParameter("available", Boolean.TRUE.toString().toLowerCase());
			}
			girlInfos = (List<GirlInfo>)query.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfos;
	}

	@SuppressWarnings("unchecked")
	public List<GirlInfo> search(AgentGirlInfo girlInfo ) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlInfo> girlInfos = null;
		try {
			
			log_.info("agentInfoId >> [" + girlInfo.getAgentInfoId() + "]");
			log_.info("nickName >> [" + girlInfo.getNickName() + "]");
			log_.info("location >> [" + girlInfo.getLocation() + "]");
			
			StringBuffer sql = new StringBuffer();
			sql.append("from AgentGirlInfo ");
			sql.append("where agentInfoId = :agentInfoId ");
			if(!girlInfo.getNickName().isEmpty()) {
				sql.append("and nickName like :nickName ");
			}
			if(!girlInfo.getLocation().isEmpty()) {
				sql.append("and location like :location ");
			}
			
			Query query = session.createQuery(sql.toString());
			query.setParameter("agentInfoId", girlInfo.getAgentInfoId());
			if(!girlInfo.getNickName().isEmpty()) {
				query.setParameter("nickName", '%'+girlInfo.getNickName()+'%');
			}
			if(!girlInfo.getLocation().isEmpty()) {
				query.setParameter("location", '%'+girlInfo.getLocation()+'%');
			}
			
			girlInfos = (List<GirlInfo>)query.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfos;
	}

	public GirlInfo getGirlInfo(String girlInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		GirlInfo girlInfo = null;
		try {
			
			girlInfo = (GirlInfo)session.createQuery("from AgentGirlInfo where girlInfoId = :girlInfoId ")
					.setParameter("girlInfoId", girlInfoId).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfo;
	}
	
	public void avaiableByGirlInfoId(String agentInfoId, List<String> girlInfoIdList) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			
			session.createQuery("update AgentGirlInfo set available = :available where agentInfoId = :agentInfoId ")
					.setParameter("available", Boolean.FALSE.toString().toLowerCase())
					.setParameter("agentInfoId", agentInfoId)
					.executeUpdate();
			
			if(girlInfoIdList.size()> 0) {
				session.createQuery("update AgentGirlInfo set available = :available where girlInfoId in (:girlInfoIdList) ")
						.setParameter("available", Boolean.TRUE.toString().toLowerCase())
						.setParameterList("girlInfoIdList", girlInfoIdList.toArray())
						.executeUpdate();
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<GirlInfo> list() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlInfo> girlInfos = null;
		try {
			
			girlInfos = (List<GirlInfo>)session.createQuery("select agentGirlInfo " + 
							"from AgentGirlInfo agentGirlInfo, UserInfo userInfo " + 
							"where agentGirlInfo.agentInfoId = userInfo.agentInfoId " +
							"and userInfo.active = :active " +
							"and current_date between userInfo.validDateFrom and userInfo.validDateTo " +
							"and agentGirlInfo.available = :available ")
							.setParameter("active", Boolean.TRUE.toString().toLowerCase())
							.setParameter("available", Boolean.TRUE.toString().toLowerCase())
							.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfos;
	}
}
