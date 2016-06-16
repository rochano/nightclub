package com.nightclub.controller;

import java.util.List;

import net.viralpatel.contact.util.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.nightclub.model.EventInfo;
import com.nightclub.model.EventSearch;

public class EventInfoManager extends HibernateUtil {
	
	private static Logger log_ = Logger.getLogger(EventInfoManager.class);
	
	public EventInfo add(EventInfo eventInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(eventInfo);
		session.getTransaction().commit();
		return eventInfo;
	}
	
	public EventInfo update(EventInfo eventInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(eventInfo);
		session.getTransaction().commit();
		return eventInfo;
	}
	
	public EventInfo delete(String eventInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		EventInfo eventInfo = (EventInfo) session.load(EventInfo.class, eventInfoId);
		if(null != eventInfo) {
			session.delete(eventInfo);
		}
		session.getTransaction().commit();
		return eventInfo;
	}

	@SuppressWarnings("unchecked")
	public List<EventInfo> list(String shopInfoId) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<EventInfo> eventInfos = null;
		try {
			
			eventInfos = (List<EventInfo>)session.createQuery("from EventInfo where shopInfoId = :shopInfoId ")
					.setParameter("shopInfoId", shopInfoId).list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return eventInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<EventInfo> search(EventSearch eventSearch ) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<EventInfo> eventInfos = null;
		try {
			
			log_.info("shopInfoId >> [" + eventSearch.getShopInfoId() + "]");
			log_.info("title >> [" + eventSearch.getTitle() + "]");
			log_.info("date from >> [" + eventSearch.getEventDateFrom() + "]");
			log_.info("date to >> [" + eventSearch.getEventDateTo() + "]");
			
			StringBuffer sql = new StringBuffer();
			sql.append("from EventInfo ");
			sql.append("where shopInfoId = :shopInfoId ");
			if(!eventSearch.getTitle().isEmpty()) {
				sql.append("and title like :title ");
			}
			if(eventSearch.getEventDateFrom() != null && eventSearch.getEventDateTo() != null) {
				sql.append("and event_date between :event_date_from and :event_date_to ");
			} else if(eventSearch.getEventDateFrom() != null ) {
				sql.append("and event_date >= :event_date_from ");
			} else if(eventSearch.getEventDateTo() != null ) {
				sql.append("and event_date <= :event_date_to ");
			}
			
			Query query = session.createQuery(sql.toString());
			query.setParameter("shopInfoId", eventSearch.getShopInfoId());
			if(!eventSearch.getTitle().isEmpty()) {
				query.setParameter("title", '%'+eventSearch.getTitle()+'%');
			}
			if(eventSearch.getEventDateFrom() != null && eventSearch.getEventDateTo() != null) {
				query.setParameter("event_date_from", eventSearch.getEventDateFrom());
				query.setParameter("event_date_to", eventSearch.getEventDateTo());
			} else if(eventSearch.getEventDateFrom() != null ) {
				query.setParameter("event_date_from", eventSearch.getEventDateFrom());
			} else if(eventSearch.getEventDateTo() != null ) {
				query.setParameter("event_date_to", eventSearch.getEventDateTo());
			}
			
			eventInfos = (List<EventInfo>)query.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return eventInfos;
	}
	
	public EventInfo getEventInfo(String eventInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		EventInfo eventInfo = null;
		try {
			
			eventInfo = (EventInfo)session.createQuery("from EventInfo where eventInfoId = :eventInfoId ")
					.setParameter("eventInfoId", eventInfoId).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return eventInfo;
	}
}
