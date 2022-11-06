package com.nightclub.controller;

import java.util.Iterator;
import java.util.List;

import net.viralpatel.contact.util.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.nightclub.model.AgentGirlInfo;
import com.nightclub.model.GirlInfo;
import com.nightclub.model.GirlLocation;
import com.nightclub.model.GirlProvince;
import com.nightclub.model.GirlServiceInfo;

public class AgentGirlInfoManager extends GirlInfoManager {
	
	private static Logger log_ = Logger.getLogger(GirlInfoManager.class);

	@SuppressWarnings("unchecked")
	public List<GirlInfo> list(String agentInfoId) {
		return list(agentInfoId, false);
	}
	
	@SuppressWarnings("unchecked")
	public List<GirlInfo> list(String agentInfoId, boolean checkAvailable) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlInfo> girlInfos = null;
		GirlInfo girlInfo;
//		List<GirlLocation> girlLocations;
		List<GirlProvince> girlProvinces;
		try {
			String sql = "from AgentGirlInfo where agentInfoId = :agentInfoId ";
			if(checkAvailable) {
				sql += "and available = :available ";
			}
			Query query = session.createQuery(sql);
			query = query.setParameter("agentInfoId", agentInfoId);
			if(checkAvailable) {
				query = query.setParameter("available", Boolean.TRUE.toString().toLowerCase());
			}
			girlInfos = (List<GirlInfo>)query.list();
			Iterator it = girlInfos.iterator();
			while(it.hasNext()) {
				girlInfo = (GirlInfo) it.next();
//				girlLocations = getGirlLocationListByGirlInfoId(session, girlInfo.getGirlInfoId());
//				girlInfo.setGirlLocations(girlLocations);
				girlProvinces = getGirlProvinceListByGirlInfoId(session, girlInfo.getGirlInfoId());
				girlInfo.setGirlProvinces(girlProvinces);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfos;
	}

	@SuppressWarnings("unchecked")
	public List<GirlInfo> search(AgentGirlInfo searchGirlInfo, List<String> searchGirlLocations ) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlInfo> girlInfos = null;
		GirlInfo girlInfo;
		List<GirlLocation> girlLocations;
		List<GirlProvince> girlProvinces;
		try {
			
			log_.info("agentInfoId >> [" + searchGirlInfo.getAgentInfoId() + "]");
			log_.info("nickName >> [" + searchGirlInfo.getNickName() + "]");
			log_.info("country >> [" + searchGirlInfo.getCountryInfoId() + "]");
			log_.info("location >> [" + searchGirlLocations + "]");
			
			StringBuffer sql = new StringBuffer();
			sql.append("from AgentGirlInfo g ");
			sql.append("where g.agentInfoId = :agentInfoId ");
			if(!searchGirlInfo.getNickName().isEmpty()) {
				sql.append("and g.nickName like :nickName ");
			}
			if(!searchGirlInfo.getCountryInfoId().isEmpty()) {
				sql.append("and g.countryInfoId like :countryInfoId ");
			}
			if(searchGirlLocations != null && searchGirlLocations.size() > 0) {
				sql.append("and exists(select 1 from GirlLocation gl "
						+ "where gl.primaryKey.girlInfo.girlInfoId = g.girlInfoId "
						+ "and gl.primaryKey.zoneInfo.zoneInfoId in (:location)) ");
			}
//			if(searchGirlProvinces != null && searchGirlProvinces.size() > 0) {
//				sql.append("and exists(select 1 from GirlProvince gp "
//						+ "where gp.primaryKey.girlInfo.girlInfoId = g.girlInfoId "
//						+ "and gp.primaryKey.provinceInfo.provinceInfoId in (:province)) ");
//			}
			
			Query query = session.createQuery(sql.toString());
			query.setParameter("agentInfoId", searchGirlInfo.getAgentInfoId());
			if(!searchGirlInfo.getNickName().isEmpty()) {
				query.setParameter("nickName", '%'+searchGirlInfo.getNickName()+'%');
			}
			if(searchGirlLocations != null && searchGirlLocations.size() > 0) {
				query.setParameterList("location", searchGirlLocations.toArray());
			}
			if(!searchGirlInfo.getCountryInfoId().isEmpty()) {
				query.setParameter("countryInfoId", '%'+searchGirlInfo.getCountryInfoId()+'%');
			}
//			if(searchGirlProvinces != null && searchGirlProvinces.size() > 0) {
//				query.setParameterList("province", searchGirlProvinces.toArray());
//			}
			
			girlInfos = (List<GirlInfo>)query.list();
			Iterator it = girlInfos.iterator();
			while(it.hasNext()) {
				girlInfo = (GirlInfo) it.next();
				girlLocations = getGirlLocationListByGirlInfoId(session, girlInfo.getGirlInfoId());
				girlInfo.setGirlLocations(girlLocations);
				girlProvinces = getGirlProvinceListByGirlInfoId(session, girlInfo.getGirlInfoId());
				girlInfo.setGirlProvinces(girlProvinces);
			}
			
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
			
			girlInfo = (GirlInfo)session.createQuery("from AgentGirlInfo where girlInfoId = :girlInfoId ")
					.setParameter("girlInfoId", girlInfoId).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfo;
	}
	
	public void avaiableByGirlInfoId(List<String> allGirlInfoIdList, List<String> availableGirlInfoIdList) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			
			if(allGirlInfoIdList.size()> 0) {
				session.createQuery("update AgentGirlInfo set available = :available where girlInfoId in (:girlInfoIdList) ")
						.setParameter("available", Boolean.FALSE.toString().toLowerCase())
						.setParameterList("girlInfoIdList", allGirlInfoIdList.toArray())
						.executeUpdate();
				
				if(availableGirlInfoIdList.size()> 0) {
					session.createQuery("update AgentGirlInfo set available = :available where girlInfoId in (:girlInfoIdList) ")
							.setParameter("available", Boolean.TRUE.toString().toLowerCase())
							.setParameterList("girlInfoIdList", availableGirlInfoIdList.toArray())
							.executeUpdate();
				}
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<GirlInfo> list() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlInfo> girlInfos = null;
		GirlInfo girlInfo;
		List<GirlLocation> girlLocations;
		List<GirlProvince> girlProvinces;
		try {
			
			girlInfos = (List<GirlInfo>)session.createQuery("select agentGirlInfo " + 
							"from AgentGirlInfo agentGirlInfo, UserInfo userInfo " + 
							"where agentGirlInfo.agentInfoId = userInfo.agentInfoId " +
//							"and userInfo.active = :active " +
//							"and current_date between userInfo.validDateFrom and userInfo.validDateTo " +
							"and agentGirlInfo.available = :available " + 
							"and COALESCE(userInfo.deleteFlg, :deleteFlg) = :deleteFlg")
//							.setParameter("active", Boolean.TRUE.toString().toLowerCase())
							.setParameter("available", Boolean.TRUE.toString().toLowerCase())
							.setParameter("deleteFlg", Boolean.FALSE.toString().toLowerCase())
							.list();
			Iterator it = girlInfos.iterator();
			while(it.hasNext()) {
				girlInfo = (GirlInfo) it.next();
//				girlLocations = getGirlLocationListByGirlInfoId(session, girlInfo.getGirlInfoId());
//				girlInfo.setGirlLocations(girlLocations);
				girlProvinces = getGirlProvinceListByGirlInfoId(session, girlInfo.getGirlInfoId());
				girlInfo.setGirlProvinces(girlProvinces);
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<GirlServiceInfo> getGirlServiceInfoList() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<GirlServiceInfo> girlServiceInfos = null;
		try {
			
			girlServiceInfos = (List<GirlServiceInfo>)session.createQuery("from GirlServiceInfo order by orderNo").list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return girlServiceInfos;
	}
}
