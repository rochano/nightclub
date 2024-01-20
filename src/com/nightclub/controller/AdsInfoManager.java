package com.nightclub.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import net.viralpatel.contact.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.nightclub.model.AdsInfo;
import com.nightclub.model.AdsSearch;

public class AdsInfoManager extends HibernateUtil {
	
	private Logger log_ = Logger.getLogger(this.getClass().getName());
	
	public AdsInfo add(AdsInfo adsInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(adsInfo);
		session.getTransaction().commit();
		return adsInfo;
	}
	
	public AdsInfo update(AdsInfo adsInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(adsInfo);
		session.getTransaction().commit();
		return adsInfo;
	}
	
	public AdsInfo delete(String adsInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		AdsInfo adsInfo = (AdsInfo) session.load(AdsInfo.class, adsInfoId);
		if(null != adsInfo) {
			session.delete(adsInfo);
		}
		session.getTransaction().commit();
		return adsInfo;
	}

	@SuppressWarnings("unchecked")
	public List<AdsInfo> list() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<AdsInfo> adsInfos = null;
		try {
			
			adsInfos = (List<AdsInfo>)session.createQuery("from AdsInfo order by adsInfoId").list();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		
		return adsInfos;
	}

	private Calendar findDateRange(Calendar calRangeFrom, AdsInfo adsInfo) {
		Calendar calRangeTo = (Calendar) calRangeFrom.clone();
		calRangeTo.set(Calendar.DATE, 1);
		calRangeTo.add(Calendar.MONTH, 1);
		if (calRangeTo.getMaximum(Calendar.DATE) > adsInfo.getAdsDateFrom().getDate()) {
			calRangeTo.set(Calendar.DATE, adsInfo.getAdsDateFrom().getDate());
		} else {
			calRangeTo.set(Calendar.DATE, calRangeTo.getMaximum(Calendar.DATE));
		}
		calRangeTo.add(Calendar.DATE, -1);
		return calRangeTo;
	}
	
	@SuppressWarnings("unchecked")
	public List<AdsInfo> search(AdsSearch adsSearch ) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<AdsInfo> adsInfos = null;
		try {
			
			log_.info("title >> [" + adsSearch.getTitle() + "]");
			log_.info("date from >> [" + adsSearch.getAdsDateFrom() + "]");
			log_.info("date to >> [" + adsSearch.getAdsDateTo() + "]");
			
			StringBuffer sql = new StringBuffer();
			sql.append("from AdsInfo where 1=1 ");
			if(!adsSearch.getTitle().isEmpty()) {
				sql.append("and title like :title ");
			}

			Query query = session.createQuery(sql.toString());
			if(!adsSearch.getTitle().isEmpty()) {
				query.setParameter("title", '%'+adsSearch.getTitle()+'%');
			}
			
			List<AdsInfo> tempAdsInfos = (List<AdsInfo>)query.list();
			adsInfos = new ArrayList<AdsInfo>();
			
			Iterator it = tempAdsInfos.iterator();

			while(it.hasNext()) {
				AdsInfo adsInfo = (AdsInfo)it.next();
				if (adsSearch.getAdsDateFrom() != null && adsSearch.getAdsDateTo() != null) {
					if (adsSearch.getAdsDateFrom().compareTo(adsInfo.getAdsDateFrom()) <= 0
							&& adsSearch.getAdsDateTo().compareTo(adsInfo.getAdsDateTo()) >= 0) {
						adsInfos.add(adsInfo);
					}
				} else if (adsSearch.getAdsDateFrom() != null) {
					if (adsSearch.getAdsDateFrom().compareTo(adsInfo.getAdsDateFrom()) <= 0) {
						adsInfos.add(adsInfo);
					}
				} else if (adsSearch.getAdsDateTo() != null) {
					if (adsSearch.getAdsDateTo().compareTo(adsInfo.getAdsDateTo()) >= 0) {
						adsInfos.add(adsInfo);
					}
				} else {
					adsInfos.add(adsInfo);
				}
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return adsInfos;
	}
	
	public AdsInfo getAdsInfo(String adsInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		AdsInfo adsInfo = null;
		try {
			
			adsInfo = (AdsInfo)session.createQuery("from AdsInfo where adsInfoId = :adsInfoId ")
					.setParameter("adsInfoId", adsInfoId).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return adsInfo;
	}

	public List<AdsInfo> active() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<AdsInfo> adsInfos = null;
		try {
			
			adsInfos = (List<AdsInfo>)session.createQuery("from AdsInfo where active = 'true' order by adsInfoId ").list();

		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		
		return adsInfos;
	}
}
