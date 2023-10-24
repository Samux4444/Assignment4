import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseDBManager implements CourseDBManagerInterface {
    
    private CourseDBStructure cds;

    /**
     * Default constructor initializes the CourseDBStructure with a default size of 20.
     */
    public CourseDBManager() {
        this.cds = new CourseDBStructure(20); // A default value of 20.
    }

    /**
     * Adds a new course to the CourseDBStructure.
     *
     * @param id         the ID of the course.
     * @param crn        the CRN of the course.
     * @param credits    the credits of the course.
     * @param room       the room of the course.
     * @param instructor the instructor of the course.
     */
    public void add(String id, int crn, int credits, String room, String instructor) {
        CourseDBElement element = new CourseDBElement(id, crn, credits, room, instructor);
        cds.add(element);
    }

    /**
     * Retrieves a course based on its CRN.
     *
     * @param CRN the CRN of the course.
     * @return the course details.
     */
    public CourseDBElement get(int CRN) {
        try {
            return cds.get(CRN);
        } catch (IOException e) {
            throw new RuntimeException("Error fetching the CRN: " + CRN, e);
        }
    }

    /**
     * Displays all the courses stored in the CourseDBStructure in descending order of CRN.
     *
     * @return an ArrayList of String representations of all courses.
     */
    public ArrayList<String> showAll() {
        ArrayList<CourseDBElement> elements = cds.getElements();
        elements.sort((e1, e2) -> Integer.compare(e2.getCRN(), e1.getCRN()));
        
        ArrayList<String> result = new ArrayList<>();
        for (CourseDBElement element : elements) {
            result.add(String.format("\nCourse:%s CRN:%d Credits:%d Instructor:%s Room:%s",
                    element.getID(), element.getCRN(), element.getCredits(), element.getInstructor(), element.getRoomNum()));
        }
        return result;
    }

    /**
     * Reads course data from a file and adds them to the CourseDBStructure.
     *
     * @param inputFile the file containing course data.
     * @throws FileNotFoundException if the file is not found.
     */
    public void readFile(File inputFile) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(inputFile)) {
            while (scanner.hasNext()) {
                String[] data = scanner.nextLine().split(" ");
                add(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), data[3], data[4]);
            }
        }
    }
}

