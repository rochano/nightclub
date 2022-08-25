package com.nightclub.controller;

import java.util.Date;
import java.util.List;

import net.viralpatel.contact.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.nightclub.model.AdminSearch;
import com.nightclub.model.UserInfo;

public class UserInfoManager extends HibernateUtil {
	
	public UserInfo add(UserInfo userInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(userInfo);
		session.getTransaction().commit();
		return userInfo;
	}
	
	public UserInfo update(UserInfo userInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(userInfo);
		session.getTransaction().commit();
		return userInfo;
	}
	
	public UserInfo delete(String username) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		UserInfo userInfo = (UserInfo) session.load(UserInfo.class, username);
		if(null != userInfo) {
			session.delete(userInfo);
		}
		session.getTransaction().commit();
		return userInfo;
	}
	
	public UserInfo getUserInfo(String username, String phone) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		UserInfo userInfo = null;
		try {
			
			userInfo = (UserInfo)session.createQuery("from UserInfo where username = :username or phone = :phone ")
					.setParameter("username", username)
					.setParameter("phone", phone)
					.uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return userInfo;
	}
	
	public UserInfo authenticate(String username, String password) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		UserInfo userInfo = null;
		try {
			
			userInfo = (UserInfo)session
					.createQuery("from UserInfo " + 
						"where (username = :username or phone = :username) " + 
						"and password = :password " + 
						"and COALESCE(deleteFlg, :deleteFlg) = :deleteFlg ")
					.setParameter("username", username)
					.setParameter("password", password)
					.setParameter("deleteFlg", Boolean.FALSE.toString().toLowerCase())
					.uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return userInfo;
	}
	
	public UserInfo getUserByUserInfoId(String userInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		UserInfo userInfo = null;
		try {
			
			userInfo = (UserInfo)session.createQuery("from UserInfo where userInfoId = :userInfoId ")
					.setParameter("userInfoId", userInfoId).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return userInfo;
	}
	
	public void deleteByUserInfoId(String userInfoId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			
			session.createQuery("update UserInfo set deleteFlg = :deleteFlg where userInfoId = :userInfoId ")
					.setParameter("deleteFlg", Boolean.TRUE.toString().toLowerCase())
					.setParameter("userInfoId", userInfoId)
					.executeUpdate();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<UserInfo> list(String userType) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<UserInfo> userInfos = null;
		try {
			
			userInfos = (List<UserInfo>)session.createQuery("from UserInfo where userType = :userType and COALESCE(deleteFlg, :deleteFlg) = :deleteFlg ")
					.setParameter("userType", userType)
					.setParameter("deleteFlg", Boolean.FALSE.toString().toLowerCase())
					.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return userInfos;
	}
	
	public void activeByUserInfoId(List<String> allUserInfoIdList, List<String> availableUserInfoIdList, String userType) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			
			if(allUserInfoIdList.size()> 0) {
				session.createQuery("update UserInfo set active = :active where userType = :userType and userInfoId in (:userInfoIdList) ")
						.setParameter("active", Boolean.FALSE.toString().toLowerCase())
						.setParameter("userType", userType)
						.setParameterList("userInfoIdList", allUserInfoIdList.toArray())
						.executeUpdate();
				
				if(availableUserInfoIdList.size()> 0) {
					session.createQuery("update UserInfo set active = :active where userType = :userType and userInfoId in (:userInfoIdList) ")
							.setParameter("active", Boolean.TRUE.toString().toLowerCase())
							.setParameter("userType", userType)
							.setParameterList("userInfoIdList", availableUserInfoIdList.toArray())
							.executeUpdate();
				}
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
	}
	
	public UserInfo getUserByColumnName(String columnName, String value) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		UserInfo userInfo = null;
		try {
			
			userInfo = (UserInfo)session.createQuery("from UserInfo where " + columnName + " = :param1 ")
					.setParameter("param1", value).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return userInfo;
	}

	@SuppressWarnings("unchecked")
	public List<UserInfo> searchShop(String userType, AdminSearch search) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<UserInfo> userInfos = null;
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("from UserInfo where userType = :userType "
			+ "and COALESCE(deleteFlg, :deleteFlg) = :deleteFlg ");
			if(!search.getUserName().isEmpty()) {
				sql.append("and userName like :userName ");
			}
			if(!search.getJapaneseName().isEmpty()) {
				sql.append("and shopInfo.shopNameJp like :japaneseName ");
			}
			if(!search.getEnglishName().isEmpty()) {
				sql.append("and shopInfo.shopNameEn like :englishName ");
			}
			Query query = session.createQuery(sql.toString());
			query.setParameter("userType", userType);
			query.setParameter("deleteFlg", Boolean.FALSE.toString().toLowerCase());
			if(!search.getUserName().isEmpty()) {
				query.setParameter("userName", '%'+search.getUserName()+'%');
			}
			if(!search.getJapaneseName().isEmpty()) {
				query.setParameter("japaneseName", '%'+search.getJapaneseName()+'%');
			}
			if(!search.getEnglishName().isEmpty()) {
				query.setParameter("englishName", '%'+search.getEnglishName()+'%');
			}
			userInfos = (List<UserInfo>)query.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return userInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserInfo> searchAgent(String userType, AdminSearch search) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<UserInfo> userInfos = null;
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("from UserInfo where userType = :userType and COALESCE(deleteFlg, :deleteFlg) = :deleteFlg ");
			if(!search.getUserName().isEmpty()) {
				sql.append("and userName like :userName ");
			}
			if(!search.getAgentName().isEmpty()) {
				sql.append("and agentInfo.agentName like :agentName ");
			}
			Query query = session.createQuery(sql.toString());
			query.setParameter("userType", userType);
			query.setParameter("deleteFlg", Boolean.FALSE.toString().toLowerCase());
			if(!search.getUserName().isEmpty()) {
				query.setParameter("userName", '%'+search.getUserName()+'%');
			}
			if(!search.getAgentName().isEmpty()) {
				query.setParameter("agentName", '%'+search.getAgentName()+'%');
			}
			userInfos = (List<UserInfo>)query.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return userInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserInfo> searchFreeAgent(String userType, AdminSearch search) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<UserInfo> userInfos = null;
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("from UserInfo where userType = :userType and COALESCE(deleteFlg, :deleteFlg) = :deleteFlg ");
			if(!search.getUserName().isEmpty()) {
				sql.append("and userName like :userName ");
			}
			if(!search.getNickName().isEmpty()) {
				sql.append("and freeAgentGirlInfo.nickName like :nickName ");
			}
			Query query = session.createQuery(sql.toString());
			query.setParameter("userType", userType);
			query.setParameter("deleteFlg", Boolean.FALSE.toString().toLowerCase());
			if(!search.getUserName().isEmpty()) {
				query.setParameter("userName", '%'+search.getUserName()+'%');
			}
			if(!search.getNickName().isEmpty()) {
				query.setParameter("nickName", '%'+search.getNickName()+'%');
			}
			userInfos = (List<UserInfo>)query.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return userInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserInfo> searchClient(String userType, AdminSearch search) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<UserInfo> userInfos = null;
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("from UserInfo where userType = :userType and COALESCE(deleteFlg, :deleteFlg) = :deleteFlg ");
			if(!search.getUserName().isEmpty()) {
				sql.append("and userName like :userName ");
			}
			if(!search.getNickName().isEmpty()) {
				sql.append("and clientInfo.nickName like :nickName ");
			}
			Query query = session.createQuery(sql.toString());
			query.setParameter("userType", userType);
			query.setParameter("deleteFlg", Boolean.FALSE.toString().toLowerCase());
			if(!search.getUserName().isEmpty()) {
				query.setParameter("userName", '%'+search.getUserName()+'%');
			}
			if(!search.getNickName().isEmpty()) {
				query.setParameter("nickName", '%'+search.getNickName()+'%');
			}
			userInfos = (List<UserInfo>)query.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return userInfos;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserInfo> searchEnGirl(String userType, AdminSearch search) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<UserInfo> userInfos = null;
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("from UserInfo where userType = :userType and COALESCE(deleteFlg, :deleteFlg) = :deleteFlg ");
			if(!search.getUserName().isEmpty()) {
				sql.append("and userName like :userName ");
			}
			if(!search.getNickName().isEmpty()) {
				sql.append("and enGirlInfo.nickName like :nickName ");
			}
			Query query = session.createQuery(sql.toString());
			query.setParameter("userType", userType);
			query.setParameter("deleteFlg", Boolean.FALSE.toString().toLowerCase());
			if(!search.getUserName().isEmpty()) {
				query.setParameter("userName", '%'+search.getUserName()+'%');
			}
			if(!search.getNickName().isEmpty()) {
				query.setParameter("nickName", '%'+search.getNickName()+'%');
			}
			userInfos = (List<UserInfo>)query.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return userInfos;
	}
	
	public void updateValidDateByUserInfoId(List<String> updateUserInfoIdList, Date validDateFrom, Date validDateTo, String userType) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			
			if(updateUserInfoIdList.size()> 0) {
				session.createQuery("update UserInfo set validDateFrom = :validDateFrom, validDateTo = :validDateTo where userType = :userType and userInfoId in (:userInfoIdList) ")
						.setParameter("validDateFrom", validDateFrom)
						.setParameter("validDateTo", validDateTo)
						.setParameter("userType", userType)
						.setParameterList("userInfoIdList", updateUserInfoIdList.toArray())
						.executeUpdate();
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
	}
}
