import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Student {
    public int Id;
    public String name;
    public String surname;
    public int year;
    public double gpa;
    public int applicationLimit=5;
    public boolean admitted = false;
    public ArrayList<Company> applicationToCompanyList = new ArrayList<>();
    public ArrayList<Company> admitToCompanyList = new ArrayList<>();

    public String InfoStudent(Student s){
        String ss = String.format("%.2f", s.gpa);
        ss = ss.replaceAll(",",".");
        return "\n### START OF STUDENT INFO ###\n" +
                s.name +" " + s.surname + " (" +s.Id +") - Year: "+ s.year+"," +" GPA: "+ ss+"\n"+
                "### END OF STUDENT INFO ###";
    }
    public String ListApplications(Student c){
        String str= "\n### START OF STUDENT APPLICATIONS ###\n" +
                "Admitted Company:\n";
        if (c.admitToCompanyList.size()==0){
            str += "None\n";
        }
        else {
            str += c.admitToCompanyList.get(0).name + " (" +c.admitToCompanyList.get(0).Id +")\n";
        }
        str += "\nApplied Companies:\n";
        if (c.applicationToCompanyList.size()==0){
            str += "None\n";
        }
        else {

            for (Company company:c.applicationToCompanyList) {
                str += company.name +" ("+ company.Id+ ")\n";
            }
        }
        str += "### END OF STUDENT APPLICATIONS ###";
        return str;
    }
}