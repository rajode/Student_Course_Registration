import java.util.ArrayList;

public class CourseOffering {

    private int secNum;
    private int secCap;
    private Course theCourse;
    private boolean runnable;

    private ArrayList<Registration> offeringRegList;

    public CourseOffering() {
        secNum = 0;
        secCap = 0;
        theCourse = null;
        runnable = false;
        offeringRegList = new ArrayList<Registration>();
    }

    public CourseOffering(int secNum, int secCap) {
        this.setSecNum(secNum);
        this.setSecCap(secCap);
        offeringRegList = new ArrayList<Registration>();
    }

    public boolean isRunnable() {
        return runnable;
    }

    public int getSecNum() {
        return secNum;
    }

    public void setSecNum(int secNum) {
        this.secNum = secNum;
    }

    public int getSecCap() {
        return secCap;
    }

    public void setSecCap(int secCap) {
        this.secCap = secCap;
    }

    public Course getTheCourse() {
        return theCourse;
    }

    public void setTheCourse(Course theCourse) {
        this.theCourse = theCourse;
    }

    public ArrayList<Registration> getOfferingRegList() {
        return offeringRegList;
    }

    @Override
    public String toString() {
        String st = "\n";
        st += getTheCourse().getCourseName() + " " + getTheCourse().getCourseNum() + "\n";
        st += "Section Num: " + getSecNum() + ", section cap: " + getSecCap();
        if (runnable)
            st += " (Offered)\n";
        else
            st += " (Not enough students)\n";
        st += "Students list:\n\n";
        for (int i = 0; i < offeringRegList.size(); i++)
            st += offeringRegList.get(i).getTheStudent();

        return st;
    }

    public void addRegistration(Registration registration) {
        // TODO Auto-generated method stub
        offeringRegList.add(registration);
        manageOffering();
    }


    public void removeRegistration(Registration a) {
        offeringRegList.remove(a);
        manageOffering();
    }

    // Remove all student from studentList until there more than 8 student registered
    public void manageOffering() {
        if (offeringRegList.size() < 8)
            runnable = false;
        else
            runnable = true;
    }

}
