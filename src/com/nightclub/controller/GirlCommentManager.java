package com.nightclub.controller;

import java.util.List;
import java.util.logging.Logger;

import net.viralpatel.contact.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.classic.Session;

import com.nightclub.model.GirlComment;

public class GirlCommentManager extends HibernateUtil {
	
	private Logger log_ = Logger.getLogger(this.getClass().getName());
	
	public GirlComment add(GirlComment girlComment) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(girlComment);
		session.getTransaction().commit();
		return girlComment;
	}
	
	public GirlComment update(GirlComment girlComment) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(girlComment);
		session.getTransaction().commit();
		return girlComment;
	}
	
	public GirlComment delete(String girlCommentId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		GirlComment girlComment = (GirlComment) session.load(GirlComment.class, girlCommentId);
		if(null != girlComment) {
			session.delete(girlComment);
		}
		session.getTransaction().commit();
		return girlComment;
	}

	@SuppressWarnings("unchecked")
	public List<GirlComment> list(String girlInfoId) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlComment> girlComment = null;
		try {
			
			girlComment = (List<GirlComment>)session.createQuery(
					"from GirlComment where girlInfoId = :girlInfoId order by created_date desc ")
					.setParameter("girlInfoId", girlInfoId).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		
		return girlComment;
	}
}
