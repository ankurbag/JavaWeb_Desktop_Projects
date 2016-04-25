package edu.neu.myapp.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import edu.neu.myapp.exceptions.MyAppException;
import edu.neu.myapp.pojo.RunLog;
import edu.neu.myapp.pojo.TrophyBean;
import edu.neu.myapp.pojo.User;

public class RunLogDAO extends DAO {

	public RunLogDAO() {
	}

	public List getUserRuns() throws MyAppException {
		try {
			begin();
			Query q = getSession().createQuery("from RunLog");
			List userruns = q.list();
			commit();
			return userruns;
		} catch (HibernateException e) {
			rollback();
			throw new MyAppException("Could not get user Runs");
		}
	}
	public List getUserRuns(long personId) throws MyAppException {
		try {
			begin();
			Query q = getSession().createQuery("from RunLog where user.personID = :personId");
			q.setParameter("personId", personId);
			List userruns = q.list();
			commit();
			return userruns;
		} catch (HibernateException e) {
			rollback();
			throw new MyAppException("Could not get user Runs");
		}
	}

	public RunLog getRunLog(long runLogId) throws MyAppException {
		try {
			begin();
			Query q = getSession().createQuery("from RunLog where id = :runLogId");
			q.setParameter("runLogId", runLogId);
			RunLog runLog = (RunLog) q.uniqueResult();
			commit();
			return runLog;
		} catch (HibernateException e) {
			rollback();
			throw new MyAppException("Could not get runlog with id- " + runLogId, e);
		}
	}

	public int updateRunDetails(RunLog runLog) throws MyAppException {
		int result = 0;
		try {
			begin();
			Query q = getSession().createQuery("UPDATE RunLog set runDate = :runDate"
					+ ",runTime = :runTime ,distance = :distance,feedback = :feedback where id = :id");
			q.setParameter("id", runLog.getId());
			q.setParameter("runDate", runLog.getRunDate());
			q.setParameter("runTime", runLog.getRunTime());
			q.setParameter("distance", runLog.getDistance());
			q.setParameter("feedback", runLog.getFeedback());
			result =q.executeUpdate();
			commit();
			
		} catch (HibernateException e) {
			rollback();
			throw new MyAppException("Could update trophy with id- " + runLog.getId(), e);
		}
		return result;
	}
	public void updateRunLog(RunLog runLog) throws MyAppException {
		try {
			begin();
			System.out.println("inside RunLogDAO");
			getSession().update(runLog);
			commit();
		} catch (HibernateException e) {
			rollback();
			// throw new MyAppException("Could not create user " + username, e);
			throw new MyAppException("Exception while creating user: " + e.getMessage());
		}
	}
	
	public void saveToDb(RunLog runLog) throws MyAppException {
		try {
			begin();
			System.out.println("inside RunLogDAO");
			getSession().save(runLog);
			commit();
		} catch (HibernateException e) {
			rollback();
			// throw new MyAppException("Could not create user " + username, e);
			throw new MyAppException("Exception while creating user: " + e.getMessage());
		}
	}

	public int delete(RunLog runLog) throws MyAppException {
		int result=0;
		try {
			begin();
			Query q = getSession().createQuery("DELETE from RunLog where id = :id");
			q.setParameter("id", runLog.getId());
			result =q.executeUpdate();
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new MyAppException("Could not delete runlog " + runLog.getId(), e);
		}
		return result;
	}
}