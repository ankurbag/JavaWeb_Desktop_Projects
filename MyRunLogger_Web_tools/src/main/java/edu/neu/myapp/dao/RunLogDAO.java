package edu.neu.myapp.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import edu.neu.myapp.exceptions.MyAppException;
import edu.neu.myapp.pojo.RunLog;
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

	public User getUser(long personId) throws MyAppException {
		try {
			begin();
			Query q = getSession().createQuery("from User where personID = :personID");
			q.setParameter("personID", personId);
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new MyAppException("Could not get user with id- " + personId, e);
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

	public void delete(User user) throws MyAppException {
		try {
			begin();
			getSession().delete(user);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new MyAppException("Could not delete user " + user.getFirstName(), e);
		}
	}
}