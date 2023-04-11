import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Company{

    public ArrayList<Student> applicantStudentList = new ArrayList<>();
    public ArrayList<Student> admittedStudentList = new ArrayList<>();


    public String Id;
    public String name;
    public int admissionQuota;
    public int admissionCount=0;
    public int applicationQuota;
    public int applicationCount=0;
    public int year;
    public double gpa;

    public String ApplyTo (Company c,Student s){
        if (c.applicationCount<c.applicationQuota){
            if (s.applicationLimit>0){
                if (s.year>=c.year){
                    if (s.gpa>=c.gpa) {
                        s.applicationLimit--;
                        c.applicationCount++;
                        c.applicantStudentList.add(s);
                        s.applicationToCompanyList.add(c);
                        return "Application from " + s.name + " " + s.surname +" ("+s.Id+")"+
                                " to " + c.name + " (" + c.Id + ") was registered successfully.";
                    }
                }
            }
        }
        return "Application from " + s.name + " " + s.surname +" ("+s.Id+")"+
                " to " + c.name + " (" + c.Id + ") was failed.";
    }
    public String AdmitTo(Company c,Student s){
        Student student = new Student();
        for (Student stu:this.applicantStudentList) {
            if (stu == s){
                student=s;
            }
        }
        if (student!=null){
            if (c.admissionQuota>0){
                if (!s.admitted){
                    c.admittedStudentList.add(s);
                    s.admitted = true;
                    c.admissionCount++;
                    s.admitToCompanyList.add(c);
                    return c.name + " (" +c.Id+") " + "admitted " + s.name +" "+ s.surname + " (" + s.Id+").";
                }
            }
        }
        return c.name + " (" +c.Id+") " + "COULD NOT admit " + s.name +" "+ s.surname + " (" + s.Id+").";
    }
    public String InfoCompany(Company c){
        String ss = String.format("%.2f", c.gpa);
        ss = ss.replaceAll(",",".");
        return "\n### START OF COMPANY INFO ###\n" +
                c.name + " (" + c.Id +"):\n"+
                "Min. Accepted GPA: "+ss +"\n"+
                "Min. Accepted Year: "+c.year +"\n"+
                "Applications: "+c.applicantStudentList.size()+"/"+c.applicationQuota+"\n"+
                "Admissions: "+c.admissionCount +"/"+c.admissionQuota+"\n"+
                "### END OF COMPANY INFO ###";
    }
    public String ListApplications(Company c){
        c.applicantStudentList.sort(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                if (s1.gpa==s2.gpa&&s1.year>s2.year){
                    return -1;
                }
                else if (s1.gpa==s2.gpa&&s1.year<s2.year){
                    return 1;
                }
                else return Double.compare(s2.gpa, s1.gpa);
            }
        });
        c.admittedStudentList.sort(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Double.compare(s2.gpa, s1.gpa);
            }
        });
        String strin ="\n### START OF COMPANY APPLICATIONS ###\n" + "Admitted Students:\n";
        for (Student s:c.admittedStudentList) {
            String ss = String.format("%.2f", s.gpa);
            ss = ss.replaceAll(",",".");
            strin += s.name +" " + s.surname+" (" + s.Id +") - Year: " + s.year +", GPA: "+ss+"\n";
        }
        strin += "\nApplied Students:";
        for (Student s:c.applicantStudentList){
            String ss = String.format("%.2f", s.gpa);
            ss = ss.replaceAll(",",".");
            strin += "\n"+ s.name +" " + s.surname+" (" + s.Id +") - Year: " + s.year +", GPA: "+ss;
        }
        strin += "\n### END OF COMPANY APPLICATIONS ###";
        return strin;

    }
}