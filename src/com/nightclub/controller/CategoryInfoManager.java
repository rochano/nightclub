package com.nightclub.controller;

import java.util.List;
import java.util.logging.Logger;

import net.viralpatel.contact.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.classic.Session;

import com.nightclub.model.CategoryInfo;
import com.nightclub.model.SystemInfo;

public class CategoryInfoManager extends HibernateUtil {
	
	private Logger log_ = Logger.getLogger(this.getClass().getName());

	public CategoryInfo add(CategoryInfo categoryInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		log_.info("getCategoryNameJp >> " + categoryInfo.getCategoryNameJp());
		deleteCategoryZone(categoryInfo.getCategoryInfoId());
		session.save(categoryInfo);
		session.getTransaction().commit();
		return categoryInfo;
	}
	
	public CategoryInfo update(CategoryInfo categoryInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		log_.info("getCategoryNameJp >> " + categoryInfo.getCategoryNameJp());
		deleteCategoryZone(categoryInfo.getCategoryInfoId());
		session.saveOrUpdate(categoryInfo);
		session.getTransaction().commit();
		return categoryInfo;
	}

	public void deleteCategoryZone(String categoryInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.createQuery("delete from CategoryZone cz where cz.primaryKey.categoryInfo.categoryInfoId = :categoryInfoId")
			.setParameter("categoryInfoId", categoryInfoId).executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	public CategoryInfo delete(String categoryInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		CategoryInfo categoryInfo = (CategoryInfo) session.load(CategoryInfo.class, categoryInfoId);
		if(null != categoryInfo) {
			deleteCategoryZone(categoryInfoId);
			session.delete(categoryInfo);
		}
		session.getTransaction().commit();
		return categoryInfo;
	}

	@SuppressWarnings("unchecked")
	public List<CategoryInfo> list() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<CategoryInfo> categoryInfos = null;
		try {
			
			categoryInfos = (List<CategoryInfo>)session.createQuery("from CategoryInfo").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		
		return categoryInfos;
	}

	public CategoryInfo getCategoryInfo(String categoryInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		CategoryInfo categoryInfo = null;
		try {
			
			categoryInfo = (CategoryInfo)session
					.createQuery("from CategoryInfo c " + 
								" left join fetch c.categoryZones cz " + 
								" left join fetch cz.primaryKey.zoneInfo " + 
								" where c.categoryInfoId = :categoryInfoId order by cz.primaryKey.orderNo ")
					.setParameter("categoryInfoId", categoryInfoId).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return categoryInfo;
	}
}
