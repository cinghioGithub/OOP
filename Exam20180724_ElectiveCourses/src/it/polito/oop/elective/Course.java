package it.polito.oop.elective;

import java.util.ArrayList;
import java.util.List;

public class Course {
	
	private String name;
	private int numPosti;
	private List<Student> sutdents = new ArrayList<>();
	
	public List<Student> getSutdents() {
		return this.sutdents;
	}

	public Course(String name, int n) {
		this.name = name;
		this.numPosti = n;
	}

	public String getName() {
		return this.name;
	}

	public int getNumPosti() {
		return this.numPosti;
	}

}
