package university;

/**
 * This class represents a university education system.
 * 
 * It manages students and courses.
 *
 */
public class University {
	
	final static int MaxStudents = 1000;
	final static int StartStudentID = 10000;
	final static int StartCourses = 10;
	final static int MaxCourses = 50;
	
	private String Name;
	private String RectorName, RectorSurname;
	private Studente[] Students = new Studente[MaxStudents];
	private Corso[] Courses = new Corso[MaxCourses];
	private int IndexStudents = 0, UniStudentID = StartStudentID;
	private int IndexCourse = 0, Code = StartCourses;

	/**
	 * Constructor
	 * @param name name of the university
	 */
	public University(String name){
		//TODO: to be implemented
		this.Name = name;
	}
	
	/**
	 * Getter for the name of the university
	 * @return name of university
	 */
	public String getName(){
		//TODO: to be implemented
		return this.Name;
	}
	
	/**
	 * Defines the rector for the university
	 * 
	 * @param first
	 * @param last
	 */
	public void setRector(String first, String last){
		//TODO: to be implemented
		this.RectorName = first;
		this.RectorSurname = last;
	}
	
	/**
	 * Retrieves the rector of the university
	 * 
	 * @return
	 */
	public String getRector(){
		//TODO: to be implemented
		return this.RectorName + " " + this.RectorSurname;
	}
	
	/**
	 * Enroll a student in the university
	 * 
	 * @param first first name of the student
	 * @param last last name of the student
	 * @return
	 */
	public int enroll(String first, String last){
		//TODO: to be implemented
		for(int i=0; i<MaxStudents && this.Students[i]!= null; i++) {
			if(this.Students[i].getName().equals(first) && this.Students[i].getSurname().equals(last)) {
				return -1;
			}
		}
		if(this.IndexStudents <= MaxStudents) {
			this.Students[this.IndexStudents] = new Studente(first, last, this.UniStudentID);
			this.IndexStudents++;
			this.UniStudentID++;
			return Students[this.IndexStudents-1].getStudentID();
		}
		else {
			return -1;
		}
	}
	
	/**
	 * Retrieves the information for a given student
	 * 
	 * @param id the id of the student
	 * @return information about the student
	 */
	public String student(int id){
		//TODO: to be implemented
		return this.Students[id - StartStudentID].getStudentID() + " " + this.Students[id - StartStudentID].getName() + " " + this.Students[id - StartStudentID].getSurname();
	}
	
	/**
	 * Activates a new course with the given teacher
	 * 
	 * @param title title of the course
	 * @param teacher name of the teacher
	 * @return the unique code assigned to the course
	 */
	public int activate(String title, String teacher){
		//TODO: to be implemented
		if(this.IndexCourse <= MaxCourses) {
			this.Courses[this.IndexCourse] = new Corso(title, teacher, this.Code);
			this.IndexCourse++;
			this.Code++;
			return this.Courses[this.IndexCourse-1].getCode();
		}
		return -1;
	}
	
	/**
	 * Retrieve the information for a given course
	 * 
	 * @param code unique code of the course
	 * @return information about the course
	 */
	public String course(int code){
		//TODO: to be implemented
		return this.Courses[code - MaxCourses].getCode() + ", " + Courses[code - MaxCourses].getTitle() + ", " + Courses[code - MaxCourses].getTeacher();
	}
	
	/**
	 * Register a student to attend a course
	 * @param studentID id of the student
	 * @param courseCode id of the course
	 */
	public void register(int studentID, int courseCode){
		//TODO: to be implemented
		int i, j;
		for(i=0; i<MaxStudents && this.Students[i].getStudentID() != studentID ; i++);
		for(j=0; j<MaxCourses && this.Courses[j].getCode() != courseCode ; j++);
		this.Students[i].addCourse(this.Courses[j]);
		this.Courses[j].addStudent(this.Students[i]);
	}
	
	/**
	 * Retrieve a list of attendees
	 * 
	 * @param courseCode unique id of the course
	 * @return list of attendees separated by "\n"
	 */
	public String listAttendees(int courseCode){
		//TODO: to be implemented		
		return this.Courses[courseCode - StartCourses].listStudents();
	}

	/**
	 * Retrieves the study plan for a student
	 * 
	 * @param studentID id of the student
	 * @return list of courses the student is registered for
	 */
	public String studyPlan(int studentID){
		//TODO: to be implemented		
		return this.Students[studentID - StartStudentID].listCourses();
	}
}
