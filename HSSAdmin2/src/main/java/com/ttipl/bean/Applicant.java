package com.ttipl.bean;


public class Applicant {
    private String tid;
    private String fullName;
//    private String fatherName;
    private String email;
    private String gender;
    private String dob;
    private String aadhar;
    private String applicationStatus;
    
    // constructor, getters and setters here
    
    
    
    public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
//	public String getFatherName() {
//		return fatherName;
//	}
//	public void setFatherName(String fatherName) {
//		this.fatherName = fatherName;
//	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getAadhar() {
		return aadhar;
	}
	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	
}
