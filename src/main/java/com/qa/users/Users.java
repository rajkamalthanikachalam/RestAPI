package com.qa.users;


/*POJO = Plain Old Java Objects
 * Marshalling : Object to JSON
 * UnMarshalling : JSON to Object
 * 
 */

public class Users {

	String Name;
	String Job;
	String id;
	String createdAt;
	
	public Users(){
		}
	
	public Users(String Name, String Job){
		this.Name = Name;
		this.Job = Job;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getJob() {
		return Job;
	}

	public void setJob(String job) {
		Job = job;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
}
