package com.nightclub.controller;

import java.util.List;
import java.util.logging.Logger;

import net.viralpatel.contact.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.nightclub.model.NewsInfo;
import com.nightclub.model.NewsSearch;
import com.nightclub.model.GirlInfo;
import com.nightclub.model.NewsSearch;
import com.nightclub.model.ScheduleInfo;
import com.nightclub.model.NewsInfo;

public class NewsInfoManager extends HibernateUtil {
	
	private Logger log_ = Logger.getLogger(this.getClass().getName());
	
	public NewsInfo add(NewsInfo newsInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(newsInfo);
		session.getTransaction().commit();
		return newsInfo;
	}
	
	public NewsInfo update(NewsInfo newsInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(newsInfo);
		session.getTransaction().commit();
		return newsInfo;
	}
	
	public NewsInfo delete(String newsInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		NewsInfo newsInfo = (NewsInfo) session.load(NewsInfo.class, newsInfoId);
		if(null != newsInfo) {
			session.delete(newsInfo);
		}
		session.getTransaction().commit();
		return newsInfo;
	}

	@SuppressWarnings("unchecked")
	public List<NewsInfo> list() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<NewsInfo> newsInfos = null;
		try {
			
			newsInfos = (List<NewsInfo>)session.createQuery("from NewsInfo").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		
		return newsInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<NewsInfo> search(NewsSearch newsSearch ) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<NewsInfo> newsInfos = null;
		try {
			
			log_.info("title >> [" + newsSearch.getTitle() + "]");
			log_.info("date from >> [" + newsSearch.getNewsDateFrom() + "]");
			log_.info("date to >> [" + newsSearch.getNewsDateTo() + "]");
			
			StringBuffer sql = new StringBuffer();
			sql.append("from NewsInfo where 1=1 ");
			if(!newsSearch.getTitle().isEmpty()) {
				sql.append("and title like :title ");
			}
			if(newsSearch.getNewsDateFrom() != null && newsSearch.getNewsDateTo() != null) {
				sql.append("and news_date between :news_date_from and :news_date_to ");
			} else if(newsSearch.getNewsDateFrom() != null ) {
				sql.append("and news_date >= :news_date_from ");
			} else if(newsSearch.getNewsDateTo() != null ) {
				sql.append("and news_date <= :news_date_to ");
			}
			
			Query query = session.createQuery(sql.toString());
			if(!newsSearch.getTitle().isEmpty()) {
				query.setParameter("title", '%'+newsSearch.getTitle()+'%');
			}
			if(newsSearch.getNewsDateFrom() != null && newsSearch.getNewsDateTo() != null) {
				query.setParameter("news_date_from", newsSearch.getNewsDateFrom());
				query.setParameter("news_date_to", newsSearch.getNewsDateTo());
			} else if(newsSearch.getNewsDateFrom() != null ) {
				query.setParameter("news_date_from", newsSearch.getNewsDateFrom());
			} else if(newsSearch.getNewsDateTo() != null ) {
				query.setParameter("news_date_to", newsSearch.getNewsDateTo());
			}
			
			newsInfos = (List<NewsInfo>)query.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return newsInfos;
	}
	
	public NewsInfo getNewsInfo(String newsInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		NewsInfo newsInfo = null;
		try {
			
			newsInfo = (NewsInfo)session.createQuery("from NewsInfo where newsInfoId = :newsInfoId ")
					.setParameter("newsInfoId", newsInfoId).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return newsInfo;
	}
}
