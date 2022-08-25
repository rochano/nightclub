package com.nightclub.controller;

import java.util.List;
import java.util.logging.Logger;

import net.viralpatel.contact.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.nightclub.model.GenderInfo;
import com.nightclub.model.GirlInfo;
import com.nightclub.model.ProvinceInfo;

public class GenderInfoManager extends HibernateUtil {

	private Logger log_ = Logger.getLogger(this.getClass().getName());

	public GenderInfo add(GenderInfo genderInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(genderInfo);
		session.getTransaction().commit();
		return genderInfo;
	}

	public GenderInfo update(GenderInfo genderInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(genderInfo);
		session.getTransaction().commit();
		return genderInfo;
	}

	public GenderInfo delete(String genderInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		GenderInfo genderInfo = (GenderInfo) session.load(GenderInfo.class, genderInfoId);
		if(null != genderInfo) {
			session.delete(genderInfo);
		}
		session.getTransaction().commit();
		return genderInfo;
	}

	public boolean isRelatedGirlInfo(String provinceInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		boolean bResult = false;
		try {
			List<GirlInfo> girlInfos = (List<GirlInfo>)session
					.createQuery("from GirlInfo where genderInfoId = :genderInfoId ")
					.setParameter("genderInfoId", provinceInfoId).list();
			
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
	public List<GenderInfo> list() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GenderInfo> genderInfos = null;
		try {
			
			genderInfos = (List<GenderInfo>)session.createQuery("from GenderInfo").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		
		return genderInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<GenderInfo> search(GenderInfo genderInfo ) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GenderInfo> genderInfos = null;
		try {
			
			log_.info("gender name jp >> [" + genderInfo.getGenderNameJp() + "]");
			log_.info("gender name en >> [" + genderInfo.getGenderNameEn() + "]");
			
			StringBuffer sql = new StringBuffer();
			sql.append("from GenderInfo where 1 = 1 ");
			if(!genderInfo.getGenderNameJp().isEmpty()) {
				sql.append("and genderNameJp like :genderNameJp ");
			}
			if(!genderInfo.getGenderNameEn().isEmpty()) {
				sql.append("and genderNameEn like :genderNameEn ");
			}
			
			Query query = session.createQuery(sql.toString());
			if(!genderInfo.getGenderNameJp().isEmpty()) {
				query.setParameter("genderNameJp", '%'+genderInfo.getGenderNameJp()+'%');
			}
			if(!genderInfo.getGenderNameEn().isEmpty()) {
				query.setParameter("genderNameEn", '%'+genderInfo.getGenderNameEn()+'%');
			}
			
			genderInfos = (List<GenderInfo>)query.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return genderInfos;
	}
	
	public GenderInfo getGenderInfo(String genderInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		GenderInfo genderInfo = null;
		try {
			
			genderInfo = (GenderInfo)session.createQuery("from GenderInfo where genderInfoId = :genderInfoId ")
					.setParameter("genderInfoId", genderInfoId).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return genderInfo;
	}
}
