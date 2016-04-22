package edu.neu.myapp.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name = "personID", unique = true, nullable = false)
	private long personID;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "username")
	private String username;

	@Column(name = "profile_picture")
	private String profilePictureURI;
	
	/*@OneToMany (fetch=FetchType.LAZY, mappedBy="personID")
	private Set<RunLog> userRuns = new HashSet<RunLog>();*/
	@OneToMany (fetch=FetchType.EAGER, mappedBy="user",cascade = CascadeType.ALL)
	private Set<RunLog> userRuns = new HashSet<RunLog>();
	
	public Set getUserRuns() {
		return userRuns;
	}

	public void setUserRuns(Set userRuns) {
		this.userRuns = userRuns;
	}
	
	public void addUserRuns(RunLog runLog){
		this.getUserRuns().add(runLog);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProfilePictureURI() {
		return profilePictureURI;
	}

	public void setProfilePictureURI(String profilePictureURI) {
		this.profilePictureURI = profilePictureURI;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User() {

	}

	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public long getPersonID() {
		return personID;
	}

	public void setPersonID(long personID) {
		this.personID = personID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	
}
