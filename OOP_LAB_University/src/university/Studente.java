package university;

/**
 * This class represents a student.
 * 
 * @author enrico
 *
 */

public class Studente {
	
	final static int MaxCourses = 25;
	
	private int StudentID;
	private String Name, Surname;
	private Corso[] Courses= new Corso[MaxCourses];
	private int IndexCourses = 0;
	
	/**
	 * Constructor
	 * @param first Student's name
	 * @param last Student's surname
	 * @param mat Student's code
	 */
	public Studente(String first, String last, int mat) {
		this.Name = first;
		this.Surname = last;
		this.StudentID = mat;
	}
	
	/**
	 * 
	 * @return StudentID
	 */
	public int getStudentID() {
		return this.StudentID;
	}
	
	/**
	 * 
	 * @return Name
	 */
	public String getName() {
		return this.Name;
	}
	
	/**
	 * 
	 * @return Surname
	 */
	public String getSurname() {
		return this.Surname;
	}

	/**
	 * Add course to a student career
	 * @param course
	 */
	public void addCourse(Corso course) {
		if(this.IndexCourses <= MaxCourses) {
			for(int i=0; this.Courses[i] != null && i<MaxCourses; i++) {
				if(this.Courses[i] == course) {
					return;
				}
			}
			this.Courses[this.IndexCourses] = course;
			this.IndexCourses++;
		}
	}
	
	/**
	 * 
	 * @return List of student's courses
	 */
	public String listCourses() {
		int i;
		StringBuffer buff = new StringBuffer();
		for(i=0; i<MaxCourses && this.Courses[i] != null; i++) {
			buff.append(this.Courses[i].getCode() + ", " + Courses[i].getTitle() + ", " + Courses[i].getTeacher() + "\n");
		}
		return buff.toString();
	}

}
