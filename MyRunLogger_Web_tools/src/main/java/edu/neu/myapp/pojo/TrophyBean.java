package edu.neu.myapp.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="trophy_table")
public class TrophyBean {
	
	
	@Id
	@GeneratedValue
	@Column(name="trophy_id")
	private long trophyId;


	@Column(name="trophy_name")
	private String trophyName;
	
	@Column(name="trophy_description")
	private String trophyDesc;
	
	@Column(name="trophy_achievement_criteria")
	private int achievement;//In Miles
	
	public String getTrophyDesc() {
		return trophyDesc;
	}

	public void setTrophyDesc(String trophyDesc) {
		this.trophyDesc = trophyDesc;
	}

	public int getAchievement() {
		return achievement;
	}

	public void setAchievement(int achievement) {
		this.achievement = achievement;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_trophy", joinColumns = { 
			@JoinColumn(name = "trophy_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "personID", 
					nullable = false, updatable = false) })
	private Set<User> user;
	
	
	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}

	public long getTrophyId() {
		return trophyId;
	}

	public void setTrophyId(long trophyId) {
		this.trophyId = trophyId;
	}

	public String getTrophyName() {
		return trophyName;
	}

	public void setTrophyName(String trophyName) {
		this.trophyName = trophyName;
	}

	public TrophyBean() {
		
	}

	public TrophyBean(long trophyId, String trophyName) {
		super();
		this.trophyId = trophyId;
		this.trophyName = trophyName;
	}

	
}
