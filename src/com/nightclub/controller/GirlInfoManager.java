package com.nightclub.controller;

import java.util.Date;
import java.util.List;

import net.viralpatel.contact.util.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.nightclub.model.GirlInfo;

public class GirlInfoManager extends HibernateUtil {
	
	private static Logger log_ = Logger.getLogger(GirlInfoManager.class);
	
	public GirlInfo add(GirlInfo girlInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(girlInfo);
		session.getTransaction().commit();
		return girlInfo;
	}
	
	public GirlInfo update(GirlInfo girlInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(girlInfo);
		session.getTransaction().commit();
		return girlInfo;
	}
	
	public GirlInfo delete(String girlInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		GirlInfo girlInfo = (GirlInfo) session.load(GirlInfo.class, girlInfoId);
		if(null != girlInfo) {
			session.delete(girlInfo);
		}
		session.getTransaction().commit();
		return girlInfo;
	}

	@SuppressWarnings("unchecked")
	public List<GirlInfo> list(String shopInfoId) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlInfo> girlInfos = null;
		try {
			
			girlInfos = (List<GirlInfo>)session.createQuery("from GirlInfo where shopInfoId = :shopInfoId ")
					.setParameter("shopInfoId", shopInfoId).list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<GirlInfo> newFaceList(String shopInfoId, Date prevMonth) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlInfo> girlInfos = null;
		try {
			
			girlInfos = (List<GirlInfo>)session
				.createQuery("from GirlInfo where shopInfoId = :shopInfoId and createdDate >= :prevMonth ")
				.setParameter("shopInfoId", shopInfoId)
				.setParameter("prevMonth", prevMonth)
				.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<GirlInfo> rankingList(String shopInfoId) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlInfo> girlInfos = null;
		try {
			
			girlInfos = (List<GirlInfo>)session
				.createQuery("from GirlInfo where shopInfoId = :shopInfoId order by ranking asc ")
				.setParameter("shopInfoId", shopInfoId).list();
			
			if(girlInfos.size() > 5) {
				girlInfos = girlInfos.subList(0, 5);
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<GirlInfo> rankingBodySizeList(String shopInfoId) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlInfo> girlInfos = null;
		try {
			
			girlInfos = (List<GirlInfo>)session
				.createQuery("from GirlInfo where shopInfoId = :shopInfoId order by bustSize desc, hipSize desc ")
				.setParameter("shopInfoId", shopInfoId).list();
			
			if(girlInfos.size() > 5) {
				girlInfos = girlInfos.subList(0, 5);
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<GirlInfo> rankingMiniSize(String shopInfoId) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlInfo> girlInfos = null;
		try {
			
			girlInfos = (List<GirlInfo>)session
				.createQuery("from GirlInfo where shopInfoId = :shopInfoId order by height asc ")
				.setParameter("shopInfoId", shopInfoId).list();
			
			if(girlInfos.size() > 5) {
				girlInfos = girlInfos.subList(0, 5);
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<GirlInfo> rankingHeightSize(String shopInfoId) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlInfo> girlInfos = null;
		try {
			
			girlInfos = (List<GirlInfo>)session
				.createQuery("from GirlInfo where shopInfoId = :shopInfoId order by height desc ")
				.setParameter("shopInfoId", shopInfoId).list();
			
			if(girlInfos.size() > 5) {
				girlInfos = girlInfos.subList(0, 5);
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<GirlInfo> random() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlInfo> girlInfos = null;
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("from GirlInfo ");
			sql.append("where basicInfo.categoryInfo.hideZoneFlag = 'false' ");
			sql.append("order by rand() ");
			girlInfos = (List<GirlInfo>)session.createQuery(sql.toString())
					.setMaxResults(18)
					.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<GirlInfo> search(GirlInfo girlInfo ) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlInfo> girlInfos = null;
		try {
			
			log_.info("shopInfoId >> [" + girlInfo.getShopInfoId() + "]");
			log_.info("code >> [" + girlInfo.getCode() + "]");
			log_.info("firstName >> [" + girlInfo.getFirstName() + "]");
			log_.info("lastName >> [" + girlInfo.getLastName() + "]");
			log_.info("age >> [" + girlInfo.getAge() + "]");
			
			StringBuffer sql = new StringBuffer();
			sql.append("from GirlInfo ");
			sql.append("where shopInfoId = :shopInfoId ");
			if(!girlInfo.getCode().isEmpty()) {
				sql.append("and code like :code ");
			}
			if(!girlInfo.getFirstName().isEmpty()) {
				sql.append("and firstName like :firstName ");
			}
			if(!girlInfo.getLastName().isEmpty()) {
				sql.append("and lastName like :lastName ");
			}
			if(girlInfo.getAge() != null) {
				sql.append("and age like :age ");
			}
			
			Query query = session.createQuery(sql.toString());
			query.setParameter("shopInfoId", girlInfo.getShopInfoId());
			if(!girlInfo.getCode().isEmpty()) {
				query.setParameter("code", '%'+girlInfo.getCode()+'%');
			}
			if(!girlInfo.getFirstName().isEmpty()) {
				query.setParameter("firstName", '%'+girlInfo.getFirstName()+'%');
			}
			if(!girlInfo.getLastName().isEmpty()) {
				query.setParameter("lastName", '%'+girlInfo.getLastName()+'%');
			}
			if(girlInfo.getAge() != null) {
				query.setParameter("age", '%'+girlInfo.getAge()+'%');
			}
			
			girlInfos = (List<GirlInfo>)query.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfos;
	}
	
	public GirlInfo getGirlInfo(String girlInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		GirlInfo girlInfo = null;
		try {
			
			girlInfo = (GirlInfo)session.createQuery("from GirlInfo where girlInfoId = :girlInfoId ")
					.setParameter("girlInfoId", girlInfoId).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfo;
	}
	
	public GirlInfo getGirlInfoByCode(String shopInfoId, String girlCode) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		GirlInfo girlInfo = null;
		try {
			
			girlInfo = (GirlInfo)session.createQuery("from GirlInfo where shopInfoId = :shopInfoId and code = :code ")
					.setParameter("shopInfoId", shopInfoId)
					.setParameter("code", girlCode)
					.uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfo;
	}
}
