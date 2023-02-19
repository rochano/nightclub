package com.nightclub.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.viralpatel.contact.util.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.nightclub.model.AgentGirlInfo;
import com.nightclub.model.ReserveGirlService;
import com.nightclub.model.ReserveInfo;

public class GirlReserveInfoManager extends HibernateUtil {
	
	private static Logger log_ = Logger.getLogger(GirlReserveInfoManager.class);

	public ReserveInfo add(ReserveInfo reserveInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(reserveInfo);
		session.getTransaction().commit();
		return reserveInfo;
	}
	
	public ReserveInfo update(ReserveInfo reserveInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(reserveInfo);
		session.getTransaction().commit();
		return reserveInfo;
	}
	
	public ReserveInfo delete(String reserveInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		ReserveInfo reserveInfo = (ReserveInfo) session.load(ReserveInfo.class, reserveInfoId);
		if(null != reserveInfo) {
			session.delete(reserveInfo);
		}
		session.getTransaction().commit();
		return reserveInfo;
	}
	
	public List<ReserveInfo> getReserveInfosByGirlInfoId(String girlInfoId, Date reserveDate) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<ReserveInfo> reserveInfos = null;
		ReserveInfo reserveInfo = null;
		try {
			
			reserveInfos = (List<ReserveInfo>)session.createQuery(
					"from ReserveInfo where girlInfoId = :girlInfoId "
					+ "and date_format(reserveDate,'%Y%m%d') = date_format(:reserveDate,'%Y%m%d') order by startTime ")
					.setParameter("girlInfoId", girlInfoId)
					.setParameter("reserveDate", reserveDate)
					.list();
			Iterator it = reserveInfos.iterator();
			while(it.hasNext()) {
				reserveInfo = (ReserveInfo) it.next();
//				((AgentGirlInfo)reserveInfo.getGirlInfo()).setBasicInfo(null);
				((AgentGirlInfo)reserveInfo.getGirlInfo()).setGirlLocations(new ArrayList());
//				reserveInfo.getClientInfo().setGirlFavourite(new ArrayList());
				((AgentGirlInfo)reserveInfo.getGirlInfo()).setGirlProvinces(new ArrayList());
				((AgentGirlInfo)reserveInfo.getGirlInfo()).setGirlServices(new ArrayList());
				((AgentGirlInfo)reserveInfo.getGirlInfo()).getCountryInfo().setProvinceInfos(new ArrayList());
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		
		return reserveInfos;
	}
	
	public List<ReserveInfo> getReserveInfosByGirlInfoIdAndClientId(String girlInfoId, String clientInfoId, Date reserveDate) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<ReserveInfo> reserveInfos = null;
		ReserveInfo reserveInfo = null;
		try {
			
			reserveInfos = (List<ReserveInfo>)session.createQuery(
					"from ReserveInfo where girlInfoId = :girlInfoId "
//					+ "and clientInfoId = :clientInfoId "
					+ "and date_format(reserveDate,'%Y%m%d') = date_format(:reserveDate,'%Y%m%d') order by startTime ")
					.setParameter("girlInfoId", girlInfoId)
//					.setParameter("clientInfoId", clientInfoId)
					.setParameter("reserveDate", reserveDate)
					.list();
			Iterator it = reserveInfos.iterator();
			while(it.hasNext()) {
				reserveInfo = (ReserveInfo) it.next();
//				((AgentGirlInfo)reserveInfo.getGirlInfo()).setBasicInfo(null);
				((AgentGirlInfo)reserveInfo.getGirlInfo()).setGirlLocations(new ArrayList());
				((AgentGirlInfo)reserveInfo.getGirlInfo()).setGirlProvinces(new ArrayList());
				((AgentGirlInfo)reserveInfo.getGirlInfo()).setGirlServices(new ArrayList());
				((AgentGirlInfo)reserveInfo.getGirlInfo()).getCountryInfo().setProvinceInfos(new ArrayList());
//				reserveInfo.getClientInfo().setGirlFavourite(new ArrayList());
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		
		return reserveInfos;
	}
	
	public List<ReserveInfo> getReserveInfosByAgentInfoId(String agentInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<ReserveInfo> reserveInfos = null;
		ReserveInfo reserveInfo = null;
		try {
			
			reserveInfos = (List<ReserveInfo>)session.createQuery(
					"from ReserveInfo where girlInfo.agentInfoId = :agentInfoId ")
					.setParameter("agentInfoId", agentInfoId)
					.list();
			Iterator it = reserveInfos.iterator();
			while(it.hasNext()) {
				reserveInfo = (ReserveInfo) it.next();
//				((ShopGirlInfo)reserveInfo.getGirlInfo()).setBasicInfo(null);
				((AgentGirlInfo)reserveInfo.getGirlInfo()).setGirlLocations(new ArrayList());
				((AgentGirlInfo)reserveInfo.getGirlInfo()).setGirlProvinces(new ArrayList());
				((AgentGirlInfo)reserveInfo.getGirlInfo()).setGirlServices(new ArrayList());
				((AgentGirlInfo)reserveInfo.getGirlInfo()).getCountryInfo().setProvinceInfos(new ArrayList());
//				reserveInfo.getClientInfo().setGirlFavourite(new ArrayList());
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		
		return reserveInfos;
	}
	
	public ReserveInfo getReserveInfo(String reserveInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		ReserveInfo reserveInfo = null;
		try {
			
			reserveInfo = (ReserveInfo)session.createQuery("from ReserveInfo where reserveInfoId = :reserveInfoId ")
					.setParameter("reserveInfoId", reserveInfoId).uniqueResult();
			reserveInfo.setReserveGirlServices(getReserveGirlServiceListByGirlInfoId(session, reserveInfoId));
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return reserveInfo;
	}

	@SuppressWarnings("unchecked")
	public List<ReserveGirlService> getReserveGirlServiceListByGirlInfoId(Session session, String reserveInfoId) {
		List<ReserveGirlService> reserveGirlServices = null;
		reserveGirlServices = (List<ReserveGirlService>)session.createQuery("from ReserveGirlService rgs where rgs.primaryKey.reserveInfo.reserveInfoId = :reserveInfoId")
					.setParameter("reserveInfoId", reserveInfoId)
					.list();
		
		return reserveGirlServices;
	}

	public List<ReserveInfo> search(String agentInfoId, ReserveInfo reserveSearch) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<ReserveInfo> reserveInfos = null;
		ReserveInfo reserveInfo = null;
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("from ReserveInfo r where r.girlInfo.agentInfoId = :agentInfoId ");
			if(!reserveSearch.getGirlInfo().getNickName().isEmpty()) {
				sql.append("and r.girlInfo.nickName like :nickName ");
			}
			if(reserveSearch.getReserveDate() != null) {
				sql.append("and date_format(r.reserveDate,'%Y%m%d') = date_format(:reserveDate,'%Y%m%d') ");
			}
			Query query = session.createQuery(sql.toString());
			query.setParameter("agentInfoId", agentInfoId);
			if(!reserveSearch.getGirlInfo().getNickName().isEmpty()) {
				query.setParameter("nickName", "%" + reserveSearch.getGirlInfo().getNickName() + "%");
			}
			if(reserveSearch.getReserveDate() != null) {
				query.setParameter("reserveDate", reserveSearch.getReserveDate());
			}
			reserveInfos = (List<ReserveInfo>)query.list();
			Iterator it = reserveInfos.iterator();
			while(it.hasNext()) {
				reserveInfo = (ReserveInfo) it.next();
//				((ShopGirlInfo)reserveInfo.getGirlInfo()).setBasicInfo(null);
				((AgentGirlInfo)reserveInfo.getGirlInfo()).setGirlLocations(new ArrayList());
				((AgentGirlInfo)reserveInfo.getGirlInfo()).setGirlProvinces(new ArrayList());
				((AgentGirlInfo)reserveInfo.getGirlInfo()).setGirlServices(new ArrayList());
				((AgentGirlInfo)reserveInfo.getGirlInfo()).getCountryInfo().setProvinceInfos(new ArrayList());
//				reserveInfo.getClientInfo().setGirlFavourite(new ArrayList());
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		
		return reserveInfos;
	}
}
