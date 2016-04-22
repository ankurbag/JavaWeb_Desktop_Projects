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
@Table(name="trophy_table")
public class TrophyBean {
	
	
	@Id
	@GeneratedValue
	@Column(name="trophy_id")
	private long trophyId;


	@Column(name="trophy_name")
	private long trophyName;
	
	
	public long getTrophyId() {
		return trophyId;
	}

	public void setTrophyId(long trophyId) {
		this.trophyId = trophyId;
	}

	public long getTrophyName() {
		return trophyName;
	}

	public void setTrophyName(long trophyName) {
		this.trophyName = trophyName;
	}

	public TrophyBean() {
		
	}

	public TrophyBean(long trophyId, long trophyName) {
		super();
		this.trophyId = trophyId;
		this.trophyName = trophyName;
	}

	
}
