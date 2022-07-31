package com.nightclub.controller;

import java.util.List;
import java.util.logging.Logger;

import net.viralpatel.contact.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.nightclub.model.GirlInfo;
import com.nightclub.model.NationalityInfo;
import com.nightclub.model.NationalityInfo;

public class NationalityInfoManager extends HibernateUtil {
	
	private Logger log_ = Logger.getLogger(this.getClass().getName());
	
	public NationalityInfo add(NationalityInfo nationalityInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(nationalityInfo);
		session.getTransaction().commit();
		return nationalityInfo;
	}
	
	public NationalityInfo update(NationalityInfo nationalityInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(nationalityInfo);
		session.getTransaction().commit();
		return nationalityInfo;
	}
	
	public NationalityInfo delete(String nationalityInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		NationalityInfo nationalityInfo = (NationalityInfo) session.load(NationalityInfo.class, nationalityInfoId);
		if(null != nationalityInfo) {
			session.delete(nationalityInfo);
		}
		session.getTransaction().commit();
		return nationalityInfo;
	}
	
	public boolean isRelatedGirlInfo(String nationalityInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		boolean bResult = false;
		try {
			List<GirlInfo> girlInfos = (List<GirlInfo>)session
					.createQuery("from GirlInfo g where g.nationalityInfoId = :nationalityInfoId ")
					.setParameter("nationalityInfoId", nationalityInfoId).list();
			
			if(girlInfos.size() > 0) {
				bResult = true;
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return bResult;
	}

	@SuppressWarnings("unchecked")
	public List<NationalityInfo> list() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<NationalityInfo> nationalityInfos = null;
		try {
			
			nationalityInfos = (List<NationalityInfo>)session.createQuery("from NationalityInfo").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		
		return nationalityInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<NationalityInfo> search(NationalityInfo nationalityInfo ) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<NationalityInfo> nationalityInfos = null;
		try {
			log_.info("nationality name jp >> [" + nationalityInfo.getNationalityNameJp() + "]");
			log_.info("nationality name en >> [" + nationalityInfo.getNationalityNameEn() + "]");
			
			StringBuffer sql = new StringBuffer();
			sql.append("from NationalityInfo where 1 = 1 ");
			if(!nationalityInfo.getNationalityNameJp().isEmpty()) {
				sql.append("and nationalityNameJp like :nationalityNameJp ");
			}
			if(!nationalityInfo.getNationalityNameEn().isEmpty()) {
				sql.append("and nationalityNameEn like :nationalityNameEn ");
			}
			
			Query query = session.createQuery(sql.toString());
			if(!nationalityInfo.getNationalityNameJp().isEmpty()) {
				query.setParameter("nationalityNameJp", '%'+nationalityInfo.getNationalityNameJp()+'%');
			}
			if(!nationalityInfo.getNationalityNameEn().isEmpty()) {
				query.setParameter("nationalityNameEn", '%'+nationalityInfo.getNationalityNameEn()+'%');
			}
			
			nationalityInfos = (List<NationalityInfo>)query.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return nationalityInfos;
	}
	
	public NationalityInfo getNationalityInfo(String nationalityInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		NationalityInfo nationalityInfo = null;
		try {
			
			nationalityInfo = (NationalityInfo)session.createQuery("from NationalityInfo where nationalityInfoId = :nationalityInfoId ")
					.setParameter("nationalityInfoId", nationalityInfoId).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return nationalityInfo;
	}
}
