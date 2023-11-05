package com.nightclub.controller;

import java.util.List;
import java.util.logging.Logger;

import net.viralpatel.contact.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.nightclub.model.CountryInfo;
import com.nightclub.model.ProvinceInfo;

public class CountryInfoManager extends HibernateUtil {

	private Logger log_ = Logger.getLogger(this.getClass().getName());

	public CountryInfo add(CountryInfo countryInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(countryInfo);
		session.getTransaction().commit();
		return countryInfo;
	}

	public CountryInfo update(CountryInfo countryInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(countryInfo);
		session.getTransaction().commit();
		return countryInfo;
	}

	public CountryInfo delete(String countryInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		CountryInfo countryInfo = (CountryInfo) session.load(CountryInfo.class, countryInfoId);
		if(null != countryInfo) {
			session.delete(countryInfo);
		}
		session.getTransaction().commit();
		return countryInfo;
	}

	public boolean isRelatedProvince(String countryInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		boolean bResult = false;
		try {
			List<ProvinceInfo> provinceInfos = (List<ProvinceInfo>)session
					.createQuery("from ProvinceInfo where countryInfoId = :countryInfoId ")
					.setParameter("countryInfoId", countryInfoId).list();
			
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
	public List<CountryInfo> list() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<CountryInfo> countryInfos = null;
		try {
			
			countryInfos = (List<CountryInfo>)session.createQuery("from CountryInfo where active = :active ")
					.setParameter("active", Boolean.TRUE.toString().toLowerCase())
					.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		
		return countryInfos;
	}

	@SuppressWarnings("unchecked")
	public List<CountryInfo> listAll() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<CountryInfo> countryInfos = null;
		try {
			
			countryInfos = (List<CountryInfo>)session.createQuery("from CountryInfo").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		
		return countryInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<CountryInfo> search(CountryInfo countryInfo ) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<CountryInfo> countryInfos = null;
		try {
			
			log_.info("country name jp >> [" + countryInfo.getCountryNameJp() + "]");
			log_.info("country name en >> [" + countryInfo.getCountryNameEn() + "]");
			
			StringBuffer sql = new StringBuffer();
			sql.append("from CountryInfo where 1 = 1 ");
			if(!countryInfo.getCountryNameJp().isEmpty()) {
				sql.append("and countryNameJp like :countryNameJp ");
			}
			if(!countryInfo.getCountryNameEn().isEmpty()) {
				sql.append("and countryNameEn like :countryNameEn ");
			}
			
			Query query = session.createQuery(sql.toString());
			if(!countryInfo.getCountryNameJp().isEmpty()) {
				query.setParameter("countryNameJp", '%'+countryInfo.getCountryNameJp()+'%');
			}
			if(!countryInfo.getCountryNameEn().isEmpty()) {
				query.setParameter("countryNameEn", '%'+countryInfo.getCountryNameEn()+'%');
			}
			
			countryInfos = (List<CountryInfo>)query.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return countryInfos;
	}
	
	public CountryInfo getCountryInfo(String countryInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		CountryInfo countryInfo = null;
		try {
			
			countryInfo = (CountryInfo)session.createQuery("from CountryInfo where countryInfoId = :countryInfoId ")
					.setParameter("countryInfoId", countryInfoId).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return countryInfo;
	}

	public void activeByCountryInfoId(List<String> allCountryInfoIdList, List<String> availableCountryInfoIdList) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			
			if(allCountryInfoIdList.size()> 0) {
				session.createQuery("update CountryInfo set active = :active where countryInfoId in (:countryInfoIdList) ")
						.setParameter("active", Boolean.FALSE.toString().toLowerCase())
						.setParameterList("countryInfoIdList", allCountryInfoIdList.toArray())
						.executeUpdate();

				if(availableCountryInfoIdList.size()> 0) {
					session.createQuery("update CountryInfo set active = :active where countryInfoId in (:countryInfoIdList) ")
							.setParameter("active", Boolean.TRUE.toString().toLowerCase())
							.setParameterList("countryInfoIdList", availableCountryInfoIdList.toArray())
							.executeUpdate();
				}
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
	}
}
