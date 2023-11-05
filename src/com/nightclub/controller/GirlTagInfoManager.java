package com.nightclub.controller;

import java.util.List;
import java.util.logging.Logger;

import net.viralpatel.contact.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.nightclub.model.GirlTagInfo;
import com.nightclub.model.ProvinceInfo;

public class GirlTagInfoManager extends HibernateUtil {

	private Logger log_ = Logger.getLogger(this.getClass().getName());

	public GirlTagInfo add(GirlTagInfo GirlTagInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(GirlTagInfo);
		session.getTransaction().commit();
		return GirlTagInfo;
	}

	public GirlTagInfo update(GirlTagInfo GirlTagInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(GirlTagInfo);
		session.getTransaction().commit();
		return GirlTagInfo;
	}

	public GirlTagInfo delete(String GirlTagInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		GirlTagInfo GirlTagInfo = (GirlTagInfo) session.load(GirlTagInfo.class, GirlTagInfoId);
		if(null != GirlTagInfo) {
			session.delete(GirlTagInfo);
		}
		session.getTransaction().commit();
		return GirlTagInfo;
	}

	public boolean isRelatedProvince(String GirlTagInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		boolean bResult = false;
		try {
			List<ProvinceInfo> provinceInfos = (List<ProvinceInfo>)session
					.createQuery("from ProvinceInfo where GirlTagInfoId = :GirlTagInfoId ")
					.setParameter("GirlTagInfoId", GirlTagInfoId).list();
			
			if(provinceInfos.size() > 0) {
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
	public List<GirlTagInfo> list() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlTagInfo> GirlTagInfos = null;
		try {
			
			GirlTagInfos = (List<GirlTagInfo>)session.createQuery("from GirlTagInfo").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		
		return GirlTagInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<GirlTagInfo> search(GirlTagInfo girlTagInfo ) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlTagInfo> GirlTagInfos = null;
		try {
			
			log_.info("GirlTag name jp >> [" + girlTagInfo.getGirlTagNameJp() + "]");
			log_.info("GirlTag name en >> [" + girlTagInfo.getGirlTagNameEn() + "]");
			
			StringBuffer sql = new StringBuffer();
			sql.append("from GirlTagInfo where 1 = 1 ");
			if(!girlTagInfo.getGirlTagNameJp().isEmpty()) {
				sql.append("and GirlTagNameJp like :girlTagNameJp ");
			}
			if(!girlTagInfo.getGirlTagNameEn().isEmpty()) {
				sql.append("and GirlTagNameEn like :girlTagNameEn ");
			}
			
			Query query = session.createQuery(sql.toString());
			if(!girlTagInfo.getGirlTagNameJp().isEmpty()) {
				query.setParameter("girlTagNameJp", '%'+girlTagInfo.getGirlTagNameJp()+'%');
			}
			if(!girlTagInfo.getGirlTagNameEn().isEmpty()) {
				query.setParameter("girlTagNameEn", '%'+girlTagInfo.getGirlTagNameEn()+'%');
			}
			
			GirlTagInfos = (List<GirlTagInfo>)query.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return GirlTagInfos;
	}
	
	public GirlTagInfo getGirlTagInfo(String girlTagInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		GirlTagInfo GirlTagInfo = null;
		try {
			
			GirlTagInfo = (GirlTagInfo)session.createQuery("from GirlTagInfo where girlTagInfoId = :girlTagInfoId ")
					.setParameter("girlTagInfoId", girlTagInfoId).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return GirlTagInfo;
	}
}
