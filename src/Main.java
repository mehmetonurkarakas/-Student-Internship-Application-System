import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static String companiesFilepath;
    static String studentsFilepath;
    static String commandsFilepath;
    static String outputFilepath;

    public static List<Company> companyList = new ArrayList<>();

    public static List<Student> studentList = new ArrayList<>();

    public static List<String> output = new ArrayList<>();

    public static void main(String[] args) {

        companiesFilepath = args[0]; // companies.txt filepath
        studentsFilepath = args[1]; // students.txt filepath
        commandsFilepath = args[2]; // commands.txt filepath
        outputFilepath = args[3]; // output.txt filepath

        try {
            List<String> lines = Files.readAllLines(Paths.get("io/companies.txt"));
            List<String> lines1 = Files.readAllLines(Paths.get("io/students.txt"));
            List<String> lines2 = Files.readAllLines(Paths.get("io/commands.txt"));

            for (int i = 1;i<lines.size();i++){
                Company company = new Company();
                String[] line = lines.get(i).split("\\s+");
                company.Id = line[0];
                if (line.length==6){
                    company.name = line[1];
                    company.admissionQuota = Integer.parseInt(line[2]);
                    company.applicationQuota = Integer.parseInt(line[3]);
                    company.year = Integer.parseInt(line[4]);
                    company.gpa = Double.parseDouble(line[5]);
                }
                else if (line.length==7){
                    company.name = line[1]+" "+line[2];
                    company.admissionQuota = Integer.parseInt(line[3]);
                    company.applicationQuota = Integer.parseInt(line[4]);
                    company.year = Integer.parseInt(line[5]);
                    company.gpa = Double.parseDouble(line[6]);
                }else {
                    if (Objects.equals(line[1], "Westmark")){
                        company.name = line[1]+"  "+line[2]+" " + line[3];
                    }else {
                        company.name = line[1]+" "+line[2]+" " + line[3];
                    }
                    company.admissionQuota = Integer.parseInt(line[4]);
                    company.applicationQuota = Integer.parseInt(line[5]);
                    company.year = Integer.parseInt(line[6]);
                    company.gpa = Double.parseDouble(line[7]);
                }
                companyList.add(company);
            }
            for (int i = 1;i<lines1.size();i++){
                Student student = new Student();
                String[] line1 = lines1.get(i).split("\\s+");
                student.Id = Integer.parseInt(line1[0]);
                student.name = line1[1];
                student.surname = line1[2];
                student.year = Integer.parseInt(line1[3]);
                student.gpa = Double.parseDouble(line1[4]);
                studentList.add(student);
            }
            Company company = new Company();
            for (int i = 0;i<lines2.size();i++){
                String[] line2 = lines2.get(i).split("\\s+");
                if (Objects.equals(line2[0], "APPLY")){
                    String id = line2[2];
                    int StudentId = Integer.parseInt(line2[4]);
                    Company company1 = null;
                    for (Company c:companyList) {
                        if (Objects.equals(c.Id, id)){
                            company1 = c;
                        }
                    }
                    Student student = null;
                    for (Student s:studentList) {
                        if (Objects.equals(s.Id,StudentId)){
                            student = s;
                        }
                    }
                    output.add(company.ApplyTo(company1,student));
                }
                else if (Objects.equals(line2[0],"ADMIT")){
                    String id = line2[3];
                    int StudentId = Integer.parseInt(line2[1]);
                    Company company1 = new Company();
                    for (Company c:companyList) {
                        if (Objects.equals(c.Id, id)){
                            company1 = c;
                        }
                    }
                    Student student = new Student();
                    for (Student s:studentList) {
                        if (Objects.equals(s.Id,StudentId)){
                            student = s;
                        }
                    }
                    output.add(company.AdmitTo(company1,student));
                }
                else if (Objects.equals(line2[0],"INFO")){
                    if (Objects.equals(line2[1],"COMPANY")){
                        String companyId = line2[2];
                        Company company1 = new Company();
                        for (Company c:companyList) {
                            if (Objects.equals(c.Id, companyId)){
                                company1 = c;
                            }
                        }
                        output.add(company.InfoCompany(company1));
                    }
                    Student student = new Student();
                    if (Objects.equals(line2[1],"STUDENT")){
                        int studentId= Integer.parseInt(line2[2]);
                        Student student1 = null;
                        for (Student s:studentList) {
                            if (Objects.equals(s.Id,studentId)){
                                student1 = s;
                            }
                        }
                        output.add(student.InfoStudent(student1));
                    }
                }else if (Objects.equals(line2[0],"LIST")){
                    if (Objects.equals(line2[2],"TO")){//for company list
                        String companyId = line2[3];
                        Company company1 = new Company();
                        for (Company c:companyList) {
                            if (Objects.equals(c.Id, companyId)){
                                company1 = c;
                            }
                        }
                        output.add(company.ListApplications(company1));
                    }
                    Student student1 = new Student();
                    if (Objects.equals(line2[2],"BY")){
                        int studentId= Integer.parseInt(line2[3]);
                        Student student = new Student();
                        for (Student s:studentList) {
                            if (Objects.equals(s.Id,studentId)){
                                student = s;
                            }
                        }
                        output.add(student1.ListApplications(student));
                    }
                }else {
                    System.out.println("Hata");
                }
            }
            Files.write(Paths.get("io/output.txt"),output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}