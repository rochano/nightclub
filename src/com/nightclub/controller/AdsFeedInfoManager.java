package com.nightclub.controller;

import java.util.List;
import java.util.logging.Logger;

import net.viralpatel.contact.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.nightclub.model.AdsFeedInfo;
import com.nightclub.model.AdsFeedSearch;

public class AdsFeedInfoManager extends HibernateUtil {

	private Logger log_ = Logger.getLogger(this.getClass().getName());

	public AdsFeedInfo add(AdsFeedInfo adsFeedInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(adsFeedInfo);
		session.getTransaction().commit();
		return adsFeedInfo;
	}

	public AdsFeedInfo update(AdsFeedInfo adsFeedInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(adsFeedInfo);
		session.getTransaction().commit();
		return adsFeedInfo;
	}

	public AdsFeedInfo delete(String adsFeedInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		AdsFeedInfo adsFeedInfo = (AdsFeedInfo) session.load(AdsFeedInfo.class, adsFeedInfoId);
		if(null != adsFeedInfo) {
			session.delete(adsFeedInfo);
		}
		session.getTransaction().commit();
		return adsFeedInfo;
	}

	@SuppressWarnings("unchecked")
	public List<AdsFeedInfo> list() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<AdsFeedInfo> adsFeedInfos = null;
		try {

			adsFeedInfos = (List<AdsFeedInfo>)session.createQuery("from AdsFeedInfo order by adsFeedInfoId").list();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();

		return adsFeedInfos;
	}

	@SuppressWarnings("unchecked")
	public List<AdsFeedInfo> search(AdsFeedSearch adsFeedSearch ) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<AdsFeedInfo> adsFeedInfos = null;
		try {
			
			log_.info("title >> [" + adsFeedSearch.getTitle() + "]");
			
			StringBuffer sql = new StringBuffer();
			sql.append("from AdsFeedInfo where 1=1 ");
			if(adsFeedSearch.getTitle()!= null && "".equals(adsFeedSearch.getTitle())) {
				sql.append("and title like :title ");
			}
			if(Boolean.TRUE.equals(adsFeedSearch.getIsFilterActive())) {
				sql.append("and active = :active ");
			}
			if (adsFeedSearch.getSearchRandom() >= 1 && adsFeedSearch.getSearchRandom() <= 36) {
				sql.append("order by substring(adsFeedInfoId,:searchrandom,1) ");
			}

			Query query = session.createQuery(sql.toString());
			if(adsFeedSearch.getTitle()!= null && "".equals(adsFeedSearch.getTitle())) {
				query.setParameter("title", '%'+adsFeedSearch.getTitle()+'%');
			}
			if(Boolean.TRUE.equals(adsFeedSearch.getIsFilterActive())) {
				query.setParameter("active", Boolean.TRUE.toString().toLowerCase());
			}
			if (adsFeedSearch.getSearchRandom() >= 1 && adsFeedSearch.getSearchRandom() <= 36) {
				query.setParameter("searchrandom", adsFeedSearch.getSearchRandom());
			}
			
			adsFeedInfos = (List<AdsFeedInfo>)query.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return adsFeedInfos;
	}
	
	public AdsFeedInfo getAdsFeedInfo(String adsFeedInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		AdsFeedInfo adsFeedInfo = null;
		try {
			
			adsFeedInfo = (AdsFeedInfo)session.createQuery("from AdsFeedInfo where adsFeedInfoId = :adsFeedInfoId ")
					.setParameter("adsFeedInfoId", adsFeedInfoId).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return adsFeedInfo;
	}

	public List<AdsFeedInfo> active() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<AdsFeedInfo> adsFeedInfos = null;
		try {
			
			adsFeedInfos = (List<AdsFeedInfo>)session.createQuery("from AdsFeedInfo where active = 'true' order by adsFeedInfoId ").list();

		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		
		return adsFeedInfos;
	}
}
