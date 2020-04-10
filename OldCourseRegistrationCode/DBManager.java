import java.util.ArrayList;

//This class is simulating a database for our
//program
public class DBManager {

    ArrayList<Course> courseList;

    public DBManager() {
        courseList = new ArrayList<Course>();
    }

    public ArrayList readFromDataBase() {
        // TODO Auto-generated method stub
        Course a = new Course("ENGG", 233);
        Course b = new Course("ENSF", 337);
        Course c = new Course("ENEL", 353);
        Course d = new Course("ENSF", 409);
        Course e = new Course("CPSC", 319);
        Course f = new Course("ENCM", 369);
        Course g = new Course("ENSF", 480);
        Course h = new Course("ECON", 201);
        Course i = new Course("ECON", 203);
        Course j = new Course("ECON", 209);
        Course k = new Course("COMS", 363);


        f.addPre(c);
        f.addPre(b);
        d.addPre(b);
        e.addPre(b);
        g.addPre(d);
        g.addPre(f);

        courseList.add(a);
        courseList.add(b);
        courseList.add(c);
        courseList.add(d);
        courseList.add(e);
        courseList.add(f);
        courseList.add(g);
        courseList.add(h);
        courseList.add(i);
        courseList.add(j);
        courseList.add(k);

        return courseList;
    }

}
