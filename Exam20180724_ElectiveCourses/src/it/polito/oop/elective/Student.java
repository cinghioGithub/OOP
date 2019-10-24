package it.polito.oop.elective;

import java.util.List;

public class Student {

	private String ID;
	private double average;
	private List<Course> courses;
	private Course assignment;
	
	public Course getAssignment() {
		return this.assignment;
	}

	public void setAssignment(Course assignment) {
		this.assignment = assignment;
	}

	public Student(String id, double avg) {
		this.ID = id;
		this.average = avg;
	}

	public String getID() {
		return this.ID;
	}

	public void setAverage(double average) {
		this.average = average;
	}
	
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public double getAverage() {
		return this.average;
	}
	
	public List<Course> getCourses() {
		return this.courses;
	}
}
