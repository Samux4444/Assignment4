class CourseDBElement {

    private String courseID;
    private int CRN;
    private int credits;
    private String room;
    private String instructor;

    /**
     * Constructs a CourseDBElement with the provided details.
     * 
     * @param courseID   the ID of the course.
     * @param CRN        the CRN code for the course.
     * @param credits    the number of credits awarded for the course.
     * @param room       the room where the course takes place.
     * @param instructor the instructor of the course.
     */
    public CourseDBElement(String courseID, int CRN, int credits, String room, String instructor) {
        this.courseID = courseID;
        this.CRN = CRN;
        this.credits = credits;
        this.room = room;
        this.instructor = instructor;
    }

    /**
     * Retrieves the course ID.
     * 
     * @return the ID of the course.
     */
    public String getID() {
        return courseID;
    }

    /**
     * Retrieves the CRN of the course.
     * 
     * @return the CRN code of the course.
     */
    public int getCRN() {
        return CRN;
    }

    /**
     * Retrieves the room number where the course takes place.
     * 
     * @return the room number.
     */
    public String getRoomNum() {
        return room;
    }

    /**
     * Retrieves the number of credits awarded for the course.
     * 
     * @return the number of credits.
     */
    public int getCredits() {
        return this.credits;
    }

    /**
     * Retrieves the instructor's name for the course.
     * 
     * @return the instructor's name.
     */
    public String getInstructor() {
        return this.instructor;
    }

    /**
     * Returns the string representation of the CourseDBElement.
     * The representation includes course details like course ID, CRN, credits, instructor, and room number.
     * 
     * @return the string representation of the course details.
     */
    @Override
    public String toString() {
        return "\nCourse:" + courseID + " CRN:" + CRN + " Credits:" + credits + " Instructor:" + instructor + " Room:" + room;
    }
}