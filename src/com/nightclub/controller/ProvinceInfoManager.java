package com.nightclub.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import net.viralpatel.contact.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.nightclub.model.GirlInfo;
import com.nightclub.model.ProvinceInfo;

public class ProvinceInfoManager extends HibernateUtil {

	private Logger log_ = Logger.getLogger(this.getClass().getName());

	public ProvinceInfo add(ProvinceInfo provinceInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(provinceInfo);
		session.getTransaction().commit();
		return provinceInfo;
	}

	public ProvinceInfo update(ProvinceInfo provinceInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(provinceInfo);
		session.getTransaction().commit();
		return provinceInfo;
	}

	public ProvinceInfo delete(String provinceInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		ProvinceInfo provinceInfo = (ProvinceInfo) session.load(ProvinceInfo.class, provinceInfoId);
		if(null != provinceInfo) {
			session.delete(provinceInfo);
		}
		session.getTransaction().commit();
		return provinceInfo;
	}

	public boolean isRelatedGirlInfo(String provinceInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		boolean bResult = false;
		try {
			List<GirlInfo> girlInfos = (List<GirlInfo>)session
					.createQuery("from GirlProvince where primaryKey.provinceInfo.provinceInfoId = :provinceInfoId ")
					.setParameter("provinceInfoId", provinceInfoId).list();
			
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
	public List<ProvinceInfo> list() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<ProvinceInfo> provinceInfos = null;
		try {
			
			provinceInfos = (List<ProvinceInfo>)session.createQuery("from ProvinceInfo").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		
		return provinceInfos;
	}

	@SuppressWarnings("unchecked")
	public List<ProvinceInfo> search(ProvinceInfo provinceInfo ) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<ProvinceInfo> provinceInfos = null;
		try {
			
			log_.info("province name jp >> [" + provinceInfo.getProvinceNameJp() + "]");
			log_.info("province name en >> [" + provinceInfo.getProvinceNameEn() + "]");
			
			StringBuffer sql = new StringBuffer();
			sql.append("from ProvinceInfo where 1 = 1 ");

			if(!provinceInfo.getProvinceNameJp().isEmpty()) {
				sql.append("and provinceNameJp like :provinceNameJp ");
			}
			if(!provinceInfo.getProvinceNameEn().isEmpty()) {
				sql.append("and provinceNameEn like :provinceNameEn ");
			}
			
			Query query = session.createQuery(sql.toString());
			if(!provinceInfo.getProvinceNameJp().isEmpty()) {
				query.setParameter("provinceNameJp", '%'+provinceInfo.getProvinceNameJp()+'%');
			}
			if(!provinceInfo.getProvinceNameEn().isEmpty()) {
				query.setParameter("provinceNameEn", '%'+provinceInfo.getProvinceNameEn()+'%');
			}
			
			provinceInfos = (List<ProvinceInfo>)query.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return provinceInfos;
	}
	
	public ProvinceInfo getProvinceInfo(String provinceInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		ProvinceInfo provinceInfo = null;
		try {
			
			provinceInfo = (ProvinceInfo)session.createQuery("from ProvinceInfo where provinceInfoId = :provinceInfoId ")
					.setParameter("provinceInfoId", provinceInfoId).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return provinceInfo;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProvinceInfo> listByCountry(String countryInfoId) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<ProvinceInfo> provinceInfos = null;
		try {
			
			provinceInfos = (List<ProvinceInfo>)session.createQuery("from ProvinceInfo where countryInfoId = :countryInfoId")
					.setParameter("countryInfoId", countryInfoId)
					.list();
			Iterator it = provinceInfos.iterator();
			ProvinceInfo provinceInfo;
			while(it.hasNext()) {
				provinceInfo = (ProvinceInfo) it.next();
				provinceInfo.getCountryInfo().setProvinceInfos(new ArrayList());
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		
		return provinceInfos;
	}
}
