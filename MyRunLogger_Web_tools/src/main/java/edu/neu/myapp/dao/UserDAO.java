package edu.neu.myapp.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;


import edu.neu.myapp.exceptions.MyAppException;
import edu.neu.myapp.pojo.User;

public class UserDAO extends DAO {

	public UserDAO() {
	}

	public User getUser(String username) throws MyAppException {
		try {
			begin();
			Query q = getSession().createQuery("from User where name = :username");
			q.setString("username", username);
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new MyAppException("Could not get user " + username, e);
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

	public User create(long personID, String username, String emailId, String firstName, String lastName,
			String profilePictureURI) throws MyAppException {
		try {
			begin();
			System.out.println("inside DAO");

			// Email email=new Email(emailId);
			User user = new User();
			user.setPersonID(personID);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setName(username);
			user.setEmail(emailId);
			user.setProfilePictureURI(profilePictureURI);
			/* email.setUser(user); */

			getSession().save(user);

			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			// throw new MyAppException("Could not create user " + username, e);
			throw new MyAppException("Exception while creating user: " + e.getMessage());
		}
	}
	
	public void updateUser(User user) throws MyAppException {
        try {
            begin();
            //getSession().update(user);
            getSession().merge(user);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new MyAppException("Could not save the user", e);
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