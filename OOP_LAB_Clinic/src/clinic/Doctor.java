package clinic;

import java.util.HashSet;
import java.util.Set;

public class Doctor extends Person{
	
	private int ID;
	private String Specialization;
	
	private Set<Patient> MyPatients = new HashSet<>();

	public Doctor(String first, String last, String sSN, int iD, String specialization) {
		super(first, last, sSN);
		this.ID = iD;
		this.Specialization = specialization;
	}

	public int getID() {
		return this.ID;
	}

	public String getSpecialization() {
		return this.Specialization;
	}
	
	public void addPatient(Patient p) {
		this.MyPatients.add(p);
	}

	public Set<Patient> getMyPatients() {
		return this.MyPatients;
	}
	
	
}
