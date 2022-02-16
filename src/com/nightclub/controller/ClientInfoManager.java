package com.nightclub.controller;

import java.util.List;

import net.viralpatel.contact.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.classic.Session;

import com.nightclub.model.ClientInfo;

public class ClientInfoManager extends HibernateUtil {
	
	public ClientInfo add(ClientInfo clientInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(clientInfo);
		session.getTransaction().commit();
		return clientInfo;
	}
	
	public ClientInfo update(ClientInfo clientInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(clientInfo);
		session.getTransaction().commit();
		return clientInfo;
	}
	
	public ClientInfo delete(String clientInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		ClientInfo clientInfo = (ClientInfo) session.load(ClientInfo.class, clientInfoId);
		if(null != clientInfo) {
			session.delete(clientInfo);
		}
		session.getTransaction().commit();
		return clientInfo;
	}

	@SuppressWarnings("unchecked")
	public List<ClientInfo> list() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<ClientInfo> clientInfos = null;
		try {
			
			clientInfos = (List<ClientInfo>)session.createQuery("from ClientInfo").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return clientInfos;
	}

	public ClientInfo getClientInfo(String clientInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		ClientInfo clientInfo = null;
		try {
			
			clientInfo = (ClientInfo)session.createQuery("from ClientInfo where clientInfoId = :clientInfoId ")
					.setParameter("clientInfoId", clientInfoId).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return clientInfo;
	}
}
