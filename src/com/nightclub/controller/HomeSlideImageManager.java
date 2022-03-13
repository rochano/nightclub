package com.nightclub.controller;

import java.util.List;

import net.viralpatel.contact.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.classic.Session;

import com.nightclub.model.HomeSlideImage;

public class HomeSlideImageManager extends HibernateUtil {
	
	public List<HomeSlideImage> add(List<HomeSlideImage> homeSlideImages) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.createQuery("delete from HomeSlideImage").executeUpdate();
		for(HomeSlideImage homeSlideImage : homeSlideImages) {
			session.save(homeSlideImage);
		}
		session.getTransaction().commit();
		return homeSlideImages;
	}

	@SuppressWarnings("unchecked")
	public List<HomeSlideImage> list() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<HomeSlideImage> homeSlideImages = null;
		try {
			
			homeSlideImages = (List<HomeSlideImage>)session.createQuery(
						"from HomeSlideImage")
					.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return homeSlideImages;
	}
}
