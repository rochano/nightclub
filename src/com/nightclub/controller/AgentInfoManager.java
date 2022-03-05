package com.nightclub.controller;

import java.util.List;

import net.viralpatel.contact.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.classic.Session;

import com.nightclub.model.AgentInfo;

public class AgentInfoManager extends HibernateUtil {
	
	public AgentInfo add(AgentInfo agentInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(agentInfo);
		session.getTransaction().commit();
		return agentInfo;
	}
	
	public AgentInfo update(AgentInfo agentInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(agentInfo);
		session.getTransaction().commit();
		return agentInfo;
	}
	
	public AgentInfo delete(String agentInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		AgentInfo agentInfo = (AgentInfo) session.load(AgentInfo.class, agentInfoId);
		if(null != agentInfo) {
			session.delete(agentInfo);
		}
		session.getTransaction().commit();
		return agentInfo;
	}

	@SuppressWarnings("unchecked")
	public List<AgentInfo> list() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<AgentInfo> agentInfos = null;
		try {
			
			agentInfos = (List<AgentInfo>)session.createQuery("select agentInfo from AgentInfo agentInfo, UserInfo userInfo " +
					"where agentInfo.agentInfoId = userInfo.agentInfoId ")
//					"and userInfo.active = :active " +
//					"and current_date between userInfo.validDateFrom and userInfo.validDateTo")
//					.setParameter("active", Boolean.TRUE.toString().toLowerCase())
					.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return agentInfos;
	}

	public AgentInfo getAgentInfo(String agentInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		AgentInfo agentInfo = null;
		try {
			
			agentInfo = (AgentInfo)session.createQuery("from AgentInfo where agentInfoId = :agentInfoId ")
					.setParameter("agentInfoId", agentInfoId).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return agentInfo;
	}
}
