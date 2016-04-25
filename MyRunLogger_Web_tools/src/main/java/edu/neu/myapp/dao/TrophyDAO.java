package edu.neu.myapp.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import edu.neu.myapp.exceptions.MyAppException;
import edu.neu.myapp.pojo.RunLog;
import edu.neu.myapp.pojo.TrophyBean;
import edu.neu.myapp.pojo.User;

public class TrophyDAO extends DAO {

	public TrophyDAO() {
	}

	public List getTrophyDetails() throws MyAppException {
		try {
			begin();
			Query q = getSession().createQuery("from TrophyBean");
			List trophyList = q.list();
			commit();
			return trophyList;
		} catch (HibernateException e) {
			rollback();
			throw new MyAppException("Could not get trophyList");
		}
	}
	public TrophyBean getTrophy(long trophyId) throws MyAppException {
		TrophyBean trophyBean = new TrophyBean();
		try {
			begin();
			Query q = getSession().createQuery("from TrophyBean where trophyId = :trophyId");
			q.setParameter("trophyId", trophyId);
			trophyBean = (TrophyBean)q.uniqueResult();
			commit();
			return trophyBean;
		} catch (HibernateException e) {
			rollback();
			throw new MyAppException("Could not get trophyList");
			
		}
		
	}

	public int updateTrophyDetails(TrophyBean trophyBean) throws MyAppException {
		int result = 0;
		try {
			begin();
			Query q = getSession().createQuery("UPDATE TrophyBean set trophyName = :trophyName"
					+ ",trophyDesc = :trophyDesc ,achievement = :achievement where trophyId = :trophyId");
			q.setParameter("trophyId", trophyBean.getTrophyId());
			q.setParameter("trophyName", trophyBean.getTrophyName());
			q.setParameter("trophyDesc", trophyBean.getTrophyDesc());
			q.setParameter("achievement", trophyBean.getAchievement());
			result =q.executeUpdate();
			commit();
			
		} catch (HibernateException e) {
			rollback();
			throw new MyAppException("Could update trophy with id- " + trophyBean.getTrophyId(), e);
		}
		return result;
	}

	public void saveToDb(TrophyBean trophyBean) throws MyAppException {
		try {
			begin();
			System.out.println("inside TrophyDAO");
			getSession().save(trophyBean);
			commit();
		} catch (HibernateException e) {
			rollback();
			// throw new MyAppException("Could not create user " + username, e);
			throw new MyAppException("Exception while creating trophy: " + e.getMessage());
		}
	}

	public void delete(TrophyBean trophyBean) throws MyAppException {
		try {
			begin();
			getSession().delete(trophyBean);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new MyAppException("Could not delete trophyBean " + trophyBean.getTrophyName(), e);
		}
	}
}