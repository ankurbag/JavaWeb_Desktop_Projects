package edu.neu.myapp.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="run_Log")
public class RunLog {
	
	/*@Id
	@GeneratedValue
	@Column(name = "runlogid", unique = true, nullable = false)
	private int runlogid;
	*/
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;

	
	@Column(name = "date_of_run", nullable = false)
	private String runDate;
	
	@Column(name = "time_of_run",  nullable = false)
	private long runTime;
	
	public long getRunTime() {
		return runTime;
	}

	public void setRunTime(long runTime) {
		this.runTime = runTime;
	}

	@Column(name = "distance",  nullable = false)
	private long distance;
	
	@Column(name = "feedback")
	private String feedback;
	
	@Column(name = "privacy")
	private String privacy;
	
	@ManyToOne(fetch = FetchType.EAGER,optional=false)
	@JoinColumn(name="personID")
	private User user;
	
	
	/*@JoinColumn(name="personID")
    private long personID;

	public long getPersonID() {
		return personID;
	}

	public void setPersonID(long personID) {
		this.personID = personID;*/
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	

	public String getRunDate() {
		return runDate;
	}

	public void setRunDate(String runDate) {
		this.runDate = runDate;
	}

	public long getDistance() {
		return distance;
	}

	public void setDistance(long distance) {
		this.distance = distance;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getPrivacy() {
		return privacy;
	}

	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}

	public RunLog() {
		
	}

	public RunLog(long id, String runDate, long runTime, long distance, String feedback, String privacy, User user) {
		super();
		this.id = id;
		this.runDate = runDate;
		this.runTime = runTime;
		this.distance = distance;
		this.feedback = feedback;
		this.privacy = privacy;
		this.user = user;
	}
	

	
	
	
	
}
