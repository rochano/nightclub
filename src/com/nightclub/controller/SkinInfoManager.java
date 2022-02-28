package com.nightclub.controller;

import java.util.List;
import java.util.logging.Logger;

import net.viralpatel.contact.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.nightclub.model.SkinInfo;
import com.nightclub.model.ZoneInfo;

public class SkinInfoManager extends HibernateUtil {
	
	private Logger log_ = Logger.getLogger(this.getClass().getName());
	
	public SkinInfo add(SkinInfo skinInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(skinInfo);
		session.getTransaction().commit();
		return skinInfo;
	}
	
	public SkinInfo update(SkinInfo skinInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(skinInfo);
		session.getTransaction().commit();
		return skinInfo;
	}
	
	public SkinInfo delete(String skinInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		SkinInfo skinInfo = (SkinInfo) session.load(SkinInfo.class, skinInfoId);
		if(null != skinInfo) {
			session.delete(skinInfo);
		}
		session.getTransaction().commit();
		return skinInfo;
	}
	
	public boolean isRelatedGirlInfo(String skinInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		boolean bResult = false;
		try {
			ZoneInfo zoneInfo = (ZoneInfo)session
					.createQuery("from EnGirlInfo g where g.skinInfoId = :skinInfoId ")
					.setParameter("skinInfoId", skinInfoId).uniqueResult();
			
			if(zoneInfo != null && zoneInfo.getCategoryZones().size() > 0) {
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
	public List<SkinInfo> list() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<SkinInfo> skinInfos = null;
		try {
			
			skinInfos = (List<SkinInfo>)session.createQuery("from SkinInfo").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		
		return skinInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<SkinInfo> search(SkinInfo skinInfo ) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<SkinInfo> skinInfos = null;
		try {
			
//			log_.info("skin code >> [" + skinInfo.getSkinCode() + "]");
			log_.info("skin name jp >> [" + skinInfo.getSkinNameJp() + "]");
			log_.info("skin name en >> [" + skinInfo.getSkinNameEn() + "]");
			
			StringBuffer sql = new StringBuffer();
			sql.append("from SkinInfo where 1 = 1 ");
//			if(!skinInfo.getSkinCode().isEmpty()) {
//				sql.append("and skinCode like :skinCode ");
//			}
			if(!skinInfo.getSkinNameJp().isEmpty()) {
				sql.append("and skinNameJp like :skinNameJp ");
			}
			if(!skinInfo.getSkinNameEn().isEmpty()) {
				sql.append("and skinNameEn like :skinNameEn ");
			}
			
			Query query = session.createQuery(sql.toString());
//			if(!skinInfo.getSkinCode().isEmpty()) {
//				query.setParameter("skinCode", '%'+skinInfo.getSkinCode()+'%');
//			}
			if(!skinInfo.getSkinNameJp().isEmpty()) {
				query.setParameter("skinNameJp", '%'+skinInfo.getSkinNameJp()+'%');
			}
			if(!skinInfo.getSkinNameEn().isEmpty()) {
				query.setParameter("skinNameEn", '%'+skinInfo.getSkinNameEn()+'%');
			}
			
			skinInfos = (List<SkinInfo>)query.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return skinInfos;
	}
	
	public SkinInfo getSkinInfo(String skinInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		SkinInfo skinInfo = null;
		try {
			
			skinInfo = (SkinInfo)session.createQuery("from SkinInfo where skinInfoId = :skinInfoId ")
					.setParameter("skinInfoId", skinInfoId).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return skinInfo;
	}
}
