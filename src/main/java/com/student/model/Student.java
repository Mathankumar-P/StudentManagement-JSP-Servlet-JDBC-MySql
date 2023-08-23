package com.student.model;

public class Student {
	private int id;
	private String fname;
	private String email;
	private String gender;
	private String dob;

	public Student(String fname, String email, String gender, String dob) {
		super();
		this.fname = fname;
		this.email = email;
		this.gender = gender;
		this.dob = dob;
	}

	public Student(int id, String fname, String email, String gender, String dob) {
		super();
		this.id = id;
		this.fname = fname;
		this.email = email;
		this.gender = gender;
		this.dob = dob;
	}

	public Student() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

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

	@Override
	public String toString() {
		return "Student [id=" + id + ", fname=" + fname + ", email=" + email + ", gender=" + gender + ", dob=" + dob
				+ "]";
	}

}
