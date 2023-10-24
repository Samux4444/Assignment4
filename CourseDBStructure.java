import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This class is responsible for managing a hash table that stores CourseDBElement objects.
 * The hashing mechanism ensures efficient insertion and retrieval based on the course CRN.
 */
public class CourseDBStructure implements CourseDBStructureInterface {

    private LinkedList<CourseDBElement>[] hashTable;
    private int tableSize;
    private static final double LOAD_FACTOR = 1.5;

    /**
     * Constructor that initializes the hash table based on the number of courses provided.
     * The size of the hash table is determined using a formula that takes load factor into consideration.
     *
     * @param numOfCourses The expected number of courses.
     */
    public CourseDBStructure(int numOfCourses) {
        tableSize = findNext4K3Prime((int) (numOfCourses / LOAD_FACTOR));
        hashTable = new LinkedList[tableSize];
    }

    /**
     * Constructor primarily for testing purposes. 
     * Allows explicit setting of the hash table size.
     *
     * @param testing A placeholder string for differentiation.
     * @param size    The desired size of the hash table.
     */
    public CourseDBStructure(String testing, int size) {
        this.tableSize = size;
        hashTable = new LinkedList[tableSize];
    }

    /**
     * Adds a CourseDBElement to the hash table. If an element with the same CRN exists, it's replaced.
     *
     * @param element The CourseDBElement to be added.
     */
    @Override
    public void add(CourseDBElement element) {
        int hashCode = Integer.toString(element.getCRN()).hashCode() % tableSize;
        if (hashTable[hashCode] == null) {
            hashTable[hashCode] = new LinkedList<>();
        } else {
            for (CourseDBElement e : hashTable[hashCode]) {
                if (e.getCRN() == element.getCRN()) {
                    hashTable[hashCode].remove(e);
                    break;
                }
            }
        }
        hashTable[hashCode].add(element);
    }

    /**
     * Retrieves a CourseDBElement based on its CRN.
     *
     * @param crn The CRN of the course.
     * @return The corresponding CourseDBElement.
     * @throws IOException if the course with the given CRN is not found.
     */
    @Override
    public CourseDBElement get(int crn) throws IOException {
        int hashCode = Integer.toString(crn).hashCode() % tableSize;
        if (hashTable[hashCode] != null) {
            for (CourseDBElement element : hashTable[hashCode]) {
                if (element.getCRN() == crn) {
                    return element;
                }
            }
        }
        throw new IOException("Course not found");
    }

    /**
     * Retrieves all elements in the hash table.
     *
     * @return An ArrayList containing all CourseDBElement objects in the hash table.
     */
    public ArrayList<CourseDBElement> getElements() {
        ArrayList<CourseDBElement> elements = new ArrayList<>();
        for (LinkedList<CourseDBElement> list : hashTable) {
            if (list != null) {
                elements.addAll(list);
            }
        }
        return elements;
    }

    /**
     * Provides the current size of the hash table.
     *
     * @return The size of the hash table.
     */
    public int getTableSize() {
        return tableSize;
    }

    /**
     * Finds the next prime number in the form of 4k+3 greater than or equal to a given number.
     *
     * @param n The starting number.
     * @return The next 4k+3 prime number.
     */
    private int findNext4K3Prime(int n) {
        n = (n % 4 == 3) ? n : n + (3 - (n % 4));
        while (true) {
            if (is4K3Prime(n)) {
                return n;
            }
            n += 4;
        }
    }

    /**
     * Checks if a given number is prime and in the form of 4k+3.
     *
     * @param n The number to check.
     * @return True if n is a 4k+3 prime, otherwise false.
     */
    private boolean is4K3Prime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Provides a String representation of all elements in the hash table.
     *
     * @return An ArrayList containing string representations of all CourseDBElement objects in the hash table.
     */
    @Override
    public ArrayList<String> showAll() {
        ArrayList<String> list = new ArrayList<>();
        for (LinkedList<CourseDBElement> linkedList : hashTable) {
            if (linkedList != null) {
                for (CourseDBElement element : linkedList) {
                    list.add(String.format("\nCourse:%s CRN:%d Credits:%d Instructor:%s Room:%s",
                            element.getID(), element.getCRN(), element.getCredits(), element.getInstructor(), element.getRoomNum()));
                }
            }
        }
        return list;
    }
}