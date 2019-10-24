package it.polito.oop.elective;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Manages elective courses enrollment.
 * 
 *
 */
public class ElectiveManager {
	
	private SortedSet<Course> courses = new TreeSet<>((a,b) -> a.getName().compareTo(b.getName()));
	public Map<String, Student> students =  new HashMap<>();
	private List<Notifier> notifier = new ArrayList<>();
	private List<Student> no = new ArrayList<>();

    /**
     * Define a new course offer.
     * A course is characterized by a name and a number of available positions.
     * 
     * @param name : the label for the request type
     * @param availablePositions : the number of available positions
     */
    public void addCourse(String name, int availablePositions) {
        Course c = new Course(name, availablePositions);
        this.courses.add(c);
    }
    
    /**
     * Returns a list of all defined courses
     * @return
     */
    public SortedSet<String> getCourses(){
    	SortedSet<String> set = new TreeSet<>();
    	
    	this.courses.stream().map(Course::getName).forEach(set::add);
//    	this.courses.stream().map(Course::getName).forEach(a -> set.add(a));
    	
    	return set;
//        return this.courses.stream().map(Course::getName).collect(TreeSet::new, TreeSet::add, TreeSet::addAll);
    }
    
    /**
     * Adds a new student info.
     * 
     * @param id : the id of the student
     * @param gradeAverage : the grade average
     */
    public void loadStudent(String id, double gradeAverage){
        if(this.students.containsKey(id)) {
        	this.students.get(id).setAverage(gradeAverage);
        }
        else {
        	Student s = new Student(id, gradeAverage);
        	this.students.put(id, s);
        }
    }

    /**
     * Lists all the students.
     * 
     * @return : list of students ids.
     */
    public Collection<String> getStudents(){
        return this.students.keySet().stream().collect(Collectors.toList());
    }
    
    /**
     * Lists all the students with grade average in the interval.
     * 
     * @param inf : lower bound of the interval (inclusive)
     * @param sup : upper bound of the interval (inclusive)
     * @return : list of students ids.
     */
    public Collection<String> getStudents(double inf, double sup){
        return this.students.entrySet().stream().filter(a -> a.getValue().getAverage() >= inf && a.getValue().getAverage() <= sup).map(a -> a.getValue().getID()).collect(Collectors.toList());
    }


    /**
     * Adds a new enrollment request of a student for a set of courses.
     * <p>
     * The request accepts a list of course names listed in order of priority.
     * The first in the list is the preferred one, i.e. the student's first choice.
     * 
     * @param id : the id of the student
     * @param selectedCourses : a list of of requested courses, in order of decreasing priority
     * 
     * @return : number of courses the user expressed a preference for
     * 
     * @throws ElectiveException : if the number of selected course is not in [1,3] or the id has not been defined.
     */
    public int requestEnroll(String id, List<String> courses)  throws ElectiveException {
    	if(!this.students.containsKey(id))
    		throw new ElectiveException();
    	if(courses.size() < 1 || courses.size() > 3)
    		throw new ElectiveException();
    	for(String str : courses) {
    		if(!this.courses.stream().map(Course::getName).collect(Collectors.toList()).contains(str))
    			throw new ElectiveException();
    	}
    	
    	List<Course> list = new ArrayList<>();
    	for(String str : courses) {
    		for(Course c : this.courses) {
    			if(str.equals(c.getName())) {
    				list.add(c);
    				break;
    			}
    		}
    	}
    	
    	for(Notifier n : this.notifier) {
    		n.requestReceived(id);
    	}
    	
    	this.students.get(id).setCourses(list);
    	
        return courses.size();
    }
    
    /**
     * Returns the number of students that selected each course.
     * <p>
     * Since each course can be selected as 1st, 2nd, or 3rd choice,
     * the method reports three numbers corresponding to the
     * number of students that selected the course as i-th choice. 
     * <p>
     * In case of a course with no requests at all
     * the method reports three zeros.
     * <p>
     * 
     * @return the map of list of number of requests per course
     */
    public Map<String,List<Long>> numberRequests(){
    	long p = 0, s = 0, t = 0;
    	
    	Map<String,List<Long>> ris = new HashMap<>();
    	
    	for(Course cu : this.courses) {
    		String str = cu.getName();
    		p = 0; s = 0; t = 0;
    		
    		for(Student st : this.students.values()) {
    			try {
    				if(st.getCourses().get(0).getName().equals(str))
    					p++;
    				else
	    				if(st.getCourses().get(1).getName().equals(str))
	    					s++;
	    				else
		    				if(st.getCourses().get(2).getName().equals(str))
		    					t++;
    			}
    			catch(IndexOutOfBoundsException e) {
    				
    			}
    		}
    		ArrayList<Long> l = new ArrayList<>();
    		l.add(p); l.add(s); l.add(t);
    		ris.put(str, l);
    	}
    	
        return ris;
    }
    
    
    /**
     * Make the definitive class assignments based on the grade averages and preferences.
     * <p>
     * Student with higher grade averages are assigned to first option courses while they fit
     * otherwise they are assigned to second and then third option courses.
     * <p>
     *  
     * @return the number of students that could not be assigned to one of the selected courses.
     */
    public long makeClasses() {
    	List<Student> no = new ArrayList<>();
    	List<Student> stu = this.students.values().stream().sorted((a,b) -> {
    		Double a1 = new Double(a.getAverage());
    		Double b1 = new Double(b.getAverage());
    		
    		return b1.compareTo(a1);
    	}).collect(Collectors.toList());
    	
    	for(Student s : stu) {
    		System.out.println(s.getID() + " " + s.getCourses());
    	}	
    	
    	for(Student s : stu) {
//    		System.out.println(s.getID()); // + stu.stream().map(Student::getID).collect(Collectors.toList())
    		System.out.println(s.getID() + " " + s.getCourses());
    		if(s.getCourses() != null) {
				for(int i=0; i<s.getCourses().size(); i++) {
	//				System.out.println(s.getID() + " " + s.getCourses().get(i).getName());
	//				System.out.println(s.getID() + " " + s.getCourses().stream().map(Course::getName).collect(Collectors.toList()));
					if(s.getCourses().get(i).getNumPosti() > s.getCourses().get(i).getSutdents().size()) {
						s.getCourses().get(i).getSutdents().add(s);
						s.setAssignment(s.getCourses().get(i));
	//					System.out.println(s.getCourses().get(i).getName() + " " + s.getCourses().get(i).getSutdents().size());
						for(Notifier n : this.notifier) {
				    		n.assignedToCourse(s.getID(), s.getCourses().get(i).getName());;
				    	}
						break;
					}
	    		}
    		}
//			System.out.println(s.getID() + " " + (s.getAssignment()!=null ? s.getAssignment().getName() : ""));
    		if(s.getAssignment() == null)
    			no.add(s);
    	}
    	
    	this.no = no;
    	
        return no.size();
    }
    
    
    /**
     * Returns the students assigned to each course.
     * 
     * @return the map course name vs. student id list.
     */
    public Map<String,List<String>> getAssignments(){
    	Map<String,List<String>> ris = new HashMap<>();
    	
    	for(Course c : this.courses) {
    		String str = c.getName();
    		List<String> list = c.getSutdents().stream().sorted((a,b) -> {
    			Double a1 = new Double(a.getAverage());
        		Double b1 = new Double(b.getAverage());
        		
        		return b1.compareTo(a1);
        	}).map(Student::getID).collect(Collectors.toList());
    		
    		ris.put(str, list);
    	}
        return ris;
    }
    
    
    /**
     * Adds a new notification listener for the announcements
     * issues by this course manager.
     * 
     * @param listener : the new notification listener
     */
    public void addNotifier(Notifier listener) {
    	this.notifier.add(listener);
    }
    
    /**
     * Computes the success rate w.r.t. to first 
     * (second, third) choice.
     * 
     * @param choice : the number of choice to consider.
     * @return the success rate (number between 0.0 and 1.0)
     */
    public double successRate(int choice){
    	double c = 0;
//    	System.out.println(this.students.values().stream().map(Student::getID).collect(Collectors.toList()));
    	for(Student s : this.students.values()) {
//    		System.out.println(s.getID() + " " + s.getCourses().stream().map(Course::getName).collect(Collectors.toList()));
    		try {
	    		if(s.getAssignment().getName().equals(s.getCourses().get(choice-1).getName())) {
//	    			System.out.println(s.getID());
	    			c++;
	    		}
    		}
    		catch(Exception e) {
    			//do nothing
    		}
    	}
    	
    	System.out.println(c);
    	
        return c/this.students.size();
    }

    
    /**
     * Returns the students not assigned to any course.
     * 
     * @return the student id list.
     */
    public List<String> getNotAssigned(){
        return this.no.stream().map(Student::getID).collect(Collectors.toList());
    }
    
    
}
