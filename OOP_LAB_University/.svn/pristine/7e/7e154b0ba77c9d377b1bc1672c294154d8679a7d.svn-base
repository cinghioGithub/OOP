package university;

/**
 * This class represents a course.
 * 
 * @author enrico
 *
 */

public class Corso {
	
	final static int MaxStudents = 100;
	
	private String Title;
	private String Teacher;
	private int Code;
	private Studente[] Students = new Studente[MaxStudents];
	private int IndexStudents = 0;
	
	/**
	 * Constructor
	 * @param title Course's name
	 * @param teacher Course's teacher
	 * @param cod Course's code
	 */
	public Corso (String title, String teacher, int cod) {
		this.Title = title;
		this.Teacher= teacher;
		this.Code = cod;
	}

	/**
	 * 
	 * @return Title
	 */
	public String getTitle() {
		return this.Title;
	}
	
	/**
	 * 
	 * @return Teacher
	 */
	public String getTeacher() {
		return this.Teacher;
	}
	
	/**
	 * 
	 * @return Code
	 */
	public int getCode() {
		return this.Code;
	}
	
	/**
	 * Register a student to a course
	 * @param student
	 */
	public void addStudent(Studente student) {
		int i;
		if(this.IndexStudents <= MaxStudents) {
			for(i=0; this.Students[i] != null && i<MaxStudents; i++) {
				if(this.Students[i] == student) {
					return;
				}
			}
			this.Students[this.IndexStudents] = student;
			this.IndexStudents++;
		}
	}
	
	/**
	 * 
	 * @return List of course's students
	 */
	public String listStudents() {
		int i;
		StringBuffer buff = new StringBuffer();
		for(i=0; i<MaxStudents && this.Students[i] != null; i++) {
			buff.append(this.Students[i].getStudentID() + " " + this.Students[i].getName() + " " + this.Students[i].getSurname() + "\n");
		}
		return buff.toString();
	}
}
