import java.time.OffsetDateTime;
import java.util.Scanner;
import java.util.ArrayList;

public class FrontEnd {
    private Scanner scanner;
    private Student student;
    private CourseCatalogue cat;

    FrontEnd() {
        scanner = new Scanner(System.in);
        student = new Student("Mike Wazowski", 300233);
        cat = new CourseCatalogue();
        initializedMike();
    }

    public CourseCatalogue getCat() {
        return cat;
    }

    private void initializedMike() {
        CourseOffering o = new CourseOffering();
        o.setTheCourse(cat.getCourseList().get(0));
        student.addCompleteCourse(new Registration(student, o, 'A'));
        o = new CourseOffering();
        o.setTheCourse(cat.getCourseList().get(1));
        student.addCompleteCourse(new Registration(student, o, 'A'));
        o = new CourseOffering();
        o.setTheCourse(cat.getCourseList().get(2));
        student.addCompleteCourse(new Registration(student, o, 'A'));
    }

    public void printChoices() {
        System.out.println("\nStudent: " + student.getStudentName() + " ID: " + student.getStudentId());
        System.out.println("Menu:");
        System.out.println("1. Search catalogue courses");
        System.out.println("2. Add course to student courses ");
        System.out.println("3. Remove course from student courses ");
        System.out.println("4. View All courses in catalogue ");
        System.out.println("5. View all courses taken by student ");
        System.out.println("6. Quit ");
    }

    public void menu() {
        String name;
        int num;
        while (true) {
            printChoices();

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    searchCat();
                    break;
                case 2:
                    addCourse();
                    break;
                case 3:
                    removeCourse();
                    break;
                case 4:
                    System.out.println(cat);
                    break;
                case 5:
                    printStudentCourse();
                    break;
                case 6:
                    System.out.println("Quitting");
                    return;
                default:
                    System.out.println("Invalid selection. Please try again.");
            }
        }
    }

    public Course searchCourse() {
        String name = getCourseName();
        int iD = getCourseID();
        Course o = cat.searchCat(name, iD);
        return o;
    }

    public void searchCat() {
        Course o = searchCourse();
        if (o != null)
            System.out.println(o);
    }

    public void addCourse() {
        Course myCourse = searchCourse();
        System.out.println("Select course section: ");
        int section = scanner.nextInt();
        section--;
        student.addCourse(myCourse, section);
    }

    public void removeCourse() {
        ArrayList<Registration> a = student.getStudentRegList();
        if (a.size() == 0) {
            System.out.println("Currently not registered in any course");
            return;
        }
        System.out.println("Current registered courses:");
        for (int i = 0; i < a.size(); i++) {
            Course o = a.get(i).getTheOffering().getTheCourse();
            System.out.println((i + 1) + ". " + a.get(i).getTheOffering().getTheCourse().getCourseName() + " "
                    + a.get(i).getTheOffering().getTheCourse().getCourseNum());
        }
        System.out.println("Enter a number to choose which course to remove: ");
        int index = scanner.nextInt();
        while (index < 1 || index > (a.size() + 1)) {
            System.out.println("Invalid input. Try again: ");
            index = scanner.nextInt();
        }
        index--;
        student.removeCourse(index);
    }

    public void printStudentCourse() {
        String c = student.printStudentCourse();
        System.out.println(c);
    }

    private String getName() {
        System.out.println("Enter student's name: ");
        String name = scanner.nextLine();
        return name;
    }

    private int getCourseID() {
        System.out.println("Enter course ID: ");
        int iD = scanner.nextInt();
        return iD;
    }

    private String getCourseName() {
        System.out.println("Enter course: ");
        String name = scanner.nextLine();
        return name;
    }

    public void emulateStudentBody() {
        ArrayList<Student> studentList = new ArrayList<Student>();
        studentList.add(new Student("James P. Sullivan", 300673));
        studentList.add(new Student("Randall Boggs", 300866));
        studentList.add(new Student("Abigail Hardscrabble", 300447));
        studentList.add(new Student("Scott Squibbles", 300064));
        studentList.add(new Student("Don Carlton", 300383));
        studentList.add(new Student("Terry Perry", 300284));
        studentList.add(new Student("Derek Knight", 300083));
        studentList.add(new Student("Brock Pearson", 300459));
        studentList.add(new Student("Johnny J. Worthington", 300351));
        studentList.add(new Student("Claire Wheeler", 300664));
        studentList.add(new Student("Chet Alexander,", 300363));
        studentList.add(new Student("Sheri Squibbles", 300671));
        studentList.add(new Student("Karen Graves, ", 300399));
        studentList.add(new Student("Frank McCay", 300413));
        studentList.add(new Student("Carrie Williams", 300228));

        for (int i = 0; i < 8; i++) {
            CourseOffering o = new CourseOffering();
            o.setTheCourse(cat.getCourseList().get(0));
            studentList.get(i).addCompleteCourse(new Registration(studentList.get(i), o, 'A'));
            o = new CourseOffering();
            o.setTheCourse(cat.getCourseList().get(1));
            studentList.get(i).addCompleteCourse(new Registration(studentList.get(i), o, 'B'));
            o = new CourseOffering();
            o.setTheCourse(cat.getCourseList().get(2));
            studentList.get(i).addCompleteCourse(new Registration(studentList.get(i), o, 'B'));

            studentList.get(i).addCourse(cat.getCourseList().get(3), 0);
            studentList.get(i).addCourse(cat.getCourseList().get(4), 0);
            studentList.get(i).addCourse(cat.getCourseList().get(5), 0);
        }

        for (int i = 8; i < 15; i++) {
            CourseOffering o = new CourseOffering();
            o.setTheCourse(cat.getCourseList().get(0));
            studentList.get(i).addCompleteCourse(new Registration(studentList.get(i), o, 'A'));
            o = new CourseOffering();
            o.setTheCourse(cat.getCourseList().get(1));
            studentList.get(i).addCompleteCourse(new Registration(studentList.get(i), o, 'B'));
            o = new CourseOffering();
            o.setTheCourse(cat.getCourseList().get(2));
            studentList.get(i).addCompleteCourse(new Registration(studentList.get(i), o, 'B'));

            studentList.get(i).addCourse(cat.getCourseList().get(3), 1);
            studentList.get(i).addCourse(cat.getCourseList().get(4), 1);
            studentList.get(i).addCourse(cat.getCourseList().get(5), 1);
        }
        System.out.println("Emulate a student body \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public static void main(String[] args) {
        FrontEnd frontEnd = new FrontEnd();
        frontEnd.emulateStudentBody();
        frontEnd.menu();
    }
}