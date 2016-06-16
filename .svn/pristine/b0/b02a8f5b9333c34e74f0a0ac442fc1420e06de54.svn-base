package com.nightclub.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.viralpatel.contact.util.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.nightclub.model.EventInfo;
import com.nightclub.model.EventSearch;
import com.nightclub.model.ScheduleInfo;
import com.nightclub.model.ScheduleSearch;

public class ScheduleInfoManager extends HibernateUtil {
	
	private static Logger log_ = Logger.getLogger(ScheduleInfoManager.class);
	
	public ScheduleInfo add(ScheduleInfo scheduleInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(scheduleInfo);
		session.getTransaction().commit();
		return scheduleInfo;
	}
	
	public ScheduleInfo update(ScheduleInfo scheduleInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(scheduleInfo);
		session.getTransaction().commit();
		return scheduleInfo;
	}
	
	public ScheduleInfo delete(String scheduleInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		ScheduleInfo scheduleInfo = (ScheduleInfo) session.load(ScheduleInfo.class, scheduleInfoId);
		if(null != scheduleInfo) {
			session.delete(scheduleInfo);
		}
		session.getTransaction().commit();
		return scheduleInfo;
	}

	@SuppressWarnings("unchecked")
	public List<ScheduleInfo> list(String shopInfoId) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<ScheduleInfo> scheduleInfos = null;
		try {
			
			scheduleInfos = (List<ScheduleInfo>)session.createQuery("from ScheduleInfo where shopInfoId = :shopInfoId ")
					.setParameter("shopInfoId", shopInfoId).list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		
		return scheduleInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<ScheduleInfo> scheduleGirlList(String shopInfoId, int dayOfWeek) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<ScheduleInfo> scheduleInfos = null;
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("from ScheduleInfo ");
			sql.append("where shopInfoId = :shopInfoId ");
			if(dayOfWeek == Calendar.MONDAY) {
				sql.append("and workMon = :true ");
			} 
			else if(dayOfWeek == Calendar.TUESDAY) {
				sql.append("and workTue = :true ");
			} 
			else if(dayOfWeek == Calendar.WEDNESDAY) {
				sql.append("and workWed = :true ");
			} 
			else if(dayOfWeek == Calendar.THURSDAY) {
				sql.append("and workThu = :true ");
			} 
			else if(dayOfWeek == Calendar.FRIDAY) {
				sql.append("and workFri = :true ");
			} 
			else if(dayOfWeek == Calendar.SATURDAY) {
				sql.append("and workSat = :true ");
			} 
			else if(dayOfWeek == Calendar.SUNDAY) {
				sql.append("and workSun = :true ");
			}
			
			Query query = session.createQuery(sql.toString());
			query.setParameter("shopInfoId", shopInfoId);
			query.setParameter("true", Boolean.TRUE.toString().toLowerCase());
			
			scheduleInfos = (List<ScheduleInfo>)query.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		
		return scheduleInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<ScheduleInfo> search(ScheduleSearch scheduleSearch ) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<ScheduleInfo> scheduleInfos = null;
		try {
			
			log_.info("shopInfoId >> [" + scheduleSearch.getShopInfoId() + "]");
			log_.info("work girl code >> [" + scheduleSearch.getGirlCode() + "]");
			log_.info("work mon >> [" + scheduleSearch.getWorkMon() + "]");
			log_.info("work tue >> [" + scheduleSearch.getWorkTue() + "]");
			log_.info("work wed >> [" + scheduleSearch.getWorkWed() + "]");
			log_.info("work thu >> [" + scheduleSearch.getWorkThu() + "]");
			log_.info("work fri >> [" + scheduleSearch.getWorkFri() + "]");
			log_.info("work sat >> [" + scheduleSearch.getWorkSat() + "]");
			log_.info("work sun >> [" + scheduleSearch.getWorkSun() + "]");
			log_.info("start time >> [" + scheduleSearch.getStartTime() + "]");
			log_.info("end time >> [" + scheduleSearch.getEndTime() + "]");
			
			StringBuffer sql = new StringBuffer();
			sql.append("from ScheduleInfo ");
			sql.append("where shopInfoId = :shopInfoId ");
			if(!scheduleSearch.getGirlCode().isEmpty()) {
				sql.append("and girlInfo.girlCode = :girlCode ");
			}
			if(scheduleSearch.getWorkMon().equals(Boolean.TRUE.toString().toLowerCase())) {
				sql.append("and workMon = :workMon ");
			}
			if(scheduleSearch.getWorkTue().equals(Boolean.TRUE.toString().toLowerCase())) {
				sql.append("and workTue = :workTue ");
			}
			if(scheduleSearch.getWorkWed().equals(Boolean.TRUE.toString().toLowerCase())) {
				sql.append("and workWed = :workWed ");
			}
			if(scheduleSearch.getWorkThu().equals(Boolean.TRUE.toString().toLowerCase())) {
				sql.append("and workThu = :workThu ");
			}
			if(scheduleSearch.getWorkFri().equals(Boolean.TRUE.toString().toLowerCase())) {
				sql.append("and workFri = :workFri ");
			}
			if(scheduleSearch.getWorkSat().equals(Boolean.TRUE.toString().toLowerCase())) {
				sql.append("and workSat = :workSat ");
			}
			if(scheduleSearch.getWorkSun().equals(Boolean.TRUE.toString().toLowerCase())) {
				sql.append("and workSun = :workSun ");
			}
			if(!scheduleSearch.getStartTime().isEmpty()) {
				sql.append("and startTime >= :startTime ");
			}
			if(!scheduleSearch.getEndTime().isEmpty()) {
				sql.append("and endTime >= :endTime ");
			}
			
			Query query = session.createQuery(sql.toString());
			query.setParameter("shopInfoId", scheduleSearch.getShopInfoId());
			if(!scheduleSearch.getGirlCode().isEmpty()) {
				query.setParameter("girlCode", scheduleSearch.getGirlCode());
			}
			if(scheduleSearch.getWorkMon().equals(Boolean.TRUE.toString().toLowerCase())) {
				query.setParameter("workMon", scheduleSearch.getWorkMon());
			}
			if(scheduleSearch.getWorkTue().equals(Boolean.TRUE.toString().toLowerCase())) {
				query.setParameter("workTue", scheduleSearch.getWorkTue());
			}
			if(scheduleSearch.getWorkWed().equals(Boolean.TRUE.toString().toLowerCase())) {
				query.setParameter("workWed", scheduleSearch.getWorkWed());
			}
			if(scheduleSearch.getWorkThu().equals(Boolean.TRUE.toString().toLowerCase())) {
				query.setParameter("workThu", scheduleSearch.getWorkThu());
			}
			if(scheduleSearch.getWorkFri().equals(Boolean.TRUE.toString().toLowerCase())) {
				query.setParameter("workFri", scheduleSearch.getWorkFri());
			}
			if(scheduleSearch.getWorkSat().equals(Boolean.TRUE.toString().toLowerCase())) {
				query.setParameter("workSat", scheduleSearch.getWorkSat());
			}
			if(scheduleSearch.getWorkSun().equals(Boolean.TRUE.toString().toLowerCase())) {
				query.setParameter("workSun", scheduleSearch.getWorkSun());
			}
			if(!scheduleSearch.getStartTime().isEmpty()) {
				query.setParameter("startTime", scheduleSearch.getStartTime());
			}
			if(!scheduleSearch.getEndTime().isEmpty()) {
				query.setParameter("endTime", scheduleSearch.getEndTime());
			}
			
			scheduleInfos = (List<ScheduleInfo>)query.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return scheduleInfos;
	}
	
	public ScheduleInfo getSchduleInfo(String schduleInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		ScheduleInfo scheduleInfo = null;
		try {
			
			scheduleInfo = (ScheduleInfo)session.createQuery("from ScheduleInfo where scheduleInfoId = :scheduleInfoId ")
					.setParameter("scheduleInfoId", schduleInfoId).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return scheduleInfo;
	}
	
	public ScheduleInfo getSchduleInfoByGirlInfoId(String girlInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		ScheduleInfo scheduleInfo = null;
		try {
			
			scheduleInfo = (ScheduleInfo)session.createQuery("from ScheduleInfo where girlInfoId = :girlInfoId ")
					.setParameter("girlInfoId", girlInfoId).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return scheduleInfo;
	}
}
