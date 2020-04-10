package Model;
import java.util.ArrayList;
import java.io.Serializable;

public class Student implements Serializable {

    private String studentName;
    private int studentId;

    //Completed courses
    private ArrayList<Registration> completeCourses;

    //Represents all course currently registered in
    private ArrayList<Registration> studentRegList;

    public Student(String studentName, int studentId) {
        this.setStudentName(studentName);
        this.setStudentId(studentId);
        completeCourses = new ArrayList<Registration>();
        studentRegList = new ArrayList<Registration>();
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public ArrayList<Registration> getStudentRegList() {
        return studentRegList;
    }

    public void addCompleteCourse(Registration a) {
        completeCourses.add(a);
    }

    @Override
    public String toString() {
        String st = "Student Name: " + getStudentName() +
                " ID: " + getStudentId() + "\n";
        return st;
    }

    public String printStudentCourse() {
        String st = "Completed courses: \n";
        for (int i = 0; i < completeCourses.size(); i++) {
            CourseOffering o = completeCourses.get(i).getTheOffering();
            st += o.getTheCourse().getCourseName() + " " + o.getTheCourse().getCourseNum();
            st += "    Grade: " + completeCourses.get(i).getGrade() + "\n";
        }
        st += "Current registration: \n";
        for (int i = 0; i < studentRegList.size(); i++) {
            CourseOffering o = studentRegList.get(i).getTheOffering();
            st += o.getTheCourse().getCourseName() + " " + o.getTheCourse().getCourseNum() + "\n";
            st += "Section Num: " + o.getSecNum() + ", section cap: " + o.getSecCap();
            if (o.isRunnable())
                st += " (Offered)\n";
            else
                st += " (Not enough students)\n";
        }
        return st;
    }

    public void addRegistration(Registration registration) {
        // TODO Auto-generated method stub
        studentRegList.add(registration);
    }


    public void removeCourse(int i) {
        Registration x = studentRegList.get(i);
        CourseOffering a = x.getTheOffering();
        studentRegList.remove(i);
        a.removeRegistration(x);
    }


    public boolean checkPre(Course a) {
        ArrayList<Course> o = a.getPreReq();
        boolean x = true;
        if (o.size() > 0) {
            for (int i = 0; i < o.size(); i++) {
                for (int y = 0; y < completeCourses.size(); y++) {
                    Course course = completeCourses.get(y).getTheOffering().getTheCourse();
                    if (course.equals(o.get(i))) {
                        x = true;
                        break;
                    }
                    x = false;
                }
            }
        }
        return x;
    }

    public void addCourse(Course myCourse, int section) {
        if (myCourse == null)
            return;
        if (studentRegList.size() >= 6) {
            System.out.println("Maximum registration reached");
            return;
        }
        if (!checkPre(myCourse)) {
            System.out.println("Prerequisite not met");
            return;
        }
        if (myCourse.getCourseOfferingAt(section) == null) {
            System.out.println("No such section");
            return;
        }
        if (myCourse.getCourseOfferingAt(section).getSecCap() <= myCourse.getCourseOfferingAt(section).getOfferingRegList().size()) {
            System.out.println("Section is full");
            return;
        }
        Registration reg = new Registration();
        reg.completeRegistration(this, myCourse.getCourseOfferingAt(section));
        System.out.println("Registration successful");
        if (myCourse.getCourseOfferingAt(section).isRunnable())
            System.out.println("Minimum student fulfilled, section is run");
        else
            System.out.println("Minimum student unfulfilled, waiting for more student before adding to offering list");
    }
}
