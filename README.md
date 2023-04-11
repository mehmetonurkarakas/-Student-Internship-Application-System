# Programming Assignment 1 - BBM 104 - Fall 2022

# Introduction
In Programming Assignment 1, you will practice Object Oriented Programming in Java language. Your task is to implement a Student Internship Application system. This system allows software companies to declare internship postings where they can define how many interns are needed, and at least which minimum GPA and year are required to apply to their internship program. Also, students can apply to current internship postings if their qualifications are matching with that posting's minimal requirements. Students are represented with their student IDs, names, surnames, GPAs and their years.

# Input Files

- ### `students.txt`: 
This file lists the IDs, names, surnames, years, and GPAs of students. Each field is separated by TAB character. That is while you parse this file, you should search for `\t` character to split fields from each other. The first line is showing the column names of fields, therefore you should skip the first line while you are instantiating Student objects.

```
id<TAB>name<TAB>surname<TAB>year<TAB>gpa
22054860<TAB>Metin<TAB>Doruk<TAB>2<TAB>3.57
21829779<TAB>Fatma<TAB>Erdinc<TAB>4<TAB>2.79
21821940<TAB>Tahir<TAB>Yumlu<TAB>4<TAB>3.64
21930438<TAB>Derya<TAB>Boran<TAB>3<TAB>3.02
21952384<TAB>Metin<TAB>Keskin<TAB>3<TAB>3.19
```

- ### `companies.txt`:
This file lists the IDs, names, admission quotas, application quotas, minimum accepted years and GPAs of companies. Each field is separated by TAB character. That is while you parse this file, you should search for `\t` character to split fields from each other. The first line is showing the column names of fields, therefore you should skip the first line while you are instantiating Company objects.

```
id<TAB>name<TAB>admission_quota<TAB>application_quota<TAB>min_accepted_year<TAB>min_accepted_gpa
C116<TAB>SAP America Inc<TAB>2<TAB>10<TAB>4<TAB>3.0
C560<TAB>Westmark  Software Co.<TAB>4<TAB>20<TAB>4<TAB>3.0
C302<TAB>Buildxact<TAB>6<TAB>30<TAB>4<TAB>2.5
```

- ### `commands.txt`:
Before processing commands, you must have instantiated all `Company` and `Student` objects from given input files. Each line in this file represents a command sent to your application. Whenever you process a command, you should write the necessary output to the `output.txt` file. Below is a minimal sample of commands file:

```
APPLY TO C560 BY 21811858
ADMIT 21851376 TO C101
INFO COMPANY C165
LIST APPLICATIONS TO C165
INFO STUDENT 22054860
LIST APPLICATIONS BY 22054860
```

# Commands

### - `APPLY TO <COMPANY_ID> BY <STUDENT_ID>`:

When you take this command, you should register an application from a student with `<STUDENT_ID>` to the company with `<COMPANY_ID>`. Before registering, you should check these conditions:
1. The number of current applicants to that company are less than `application_quota`.
2. The applicant student should not have reached **5 applications** limit.
3. The GPA of student should be at least the company's `min_accepted_gpa`.
4. The student's current year in university should be at least the company's `min_accepted_year`.

- **If these conditions hold**, register the student's application to the company's application list. Then, write to the `output.txt` file the following line: `Application from <STUDENT_NAME> <STUDENT_SURNAME> (<STUDENT_ID>) to <COMPANY_NAME> (<COMPANY_ID>) was registered successfully.` For example if the command was `APPLY TO C179 BY 22054860` and if the application was successful, output line should be `Application from Metin Doruk (22054860) to Evantage Software (C179) was registered successfully.`.
- **If these conditions do not hold and application is unsuccessful**, then write to the `output.txt` file the following line: `Application from <STUDENT_NAME> <STUDENT_SURNAME> (<STUDENT_ID>) to <COMPANY_NAME> (<COMPANY_ID>) was failed.` For example if the command was `APPLY TO C890 BY 22054860`, then the output line should be `Application from Metin Doruk (22054860) to Macro Mobile Solution (C890) was failed.`

#
### - `ADMIT <STUDENT_ID> TO <COMPANY_ID>`:

For this command, you should admit a previously registered application from a student with `<STUDENT_ID>` to the company with `<COMPANY_ID>`. That is, a company cannot admit a student unless he/she is registered in application list of the company. Also, each student may apply up to 5 companies, but only one company can admit him/her. After a student is admitted by a company, other companies cannot perform admission command for that student anymore. Before admitting, you should check these conditions:

1. The student should exist in that company's applications list. A student who is not registered in the company's applications cannot be admitted.
2. The number of admitted students at that company should not have reached the company's `admission_quota`.
3. The student to be admitted should not have been admitted by another company previously. That is, each student can only be admitted by a single company.

- **If these conditions hold**, add the student to the company's admission list. Then, write to the `output.txt` file the following line:  
  `<COMPANY_NAME> (<COMPANY_ID>) admitted <STUDENT_NAME> <STUDENT_SURNAME> (<STUDENT_ID>).` For example if the command was `ADMIT 22023721 TO C179` and if the admission was successful, output line should be `Evantage Software (C179) admitted Abdulkadir Babaoglu (22023721).`
- **If these conditions do not hold and application is unsuccessful**, then write to the `output.txt` file the following line: `<COMPANY_NAME> (<COMPANY_ID>) COULD NOT admit <STUDENT_NAME> <STUDENT_SURNAME> (<STUDENT_ID>).` For example if the command was `ADMIT 22023721 TO C179` and if the admission was unsuccessful, output line should be `Evantage Software (C179) COULD NOT admit Abdulkadir Babaoglu (22023721).`

#
### - `INFO COMPANY <COMPANY_ID>`:

This command writes to `output.txt` file the basic information of the company with `<COMPANY_ID>` in the following format:

```
### START OF COMPANY INFO ###
<COMPANY_NAME> (<COMPANY_ID>):
Min. Accepted GPA: <MIN_ACCEPTED_GPA>
Min. Accepted Year: <MIN_ACCEPTED_YEAR>
Applications: <NUMBER_OF_APPLICATIONS>/<APPLICATION_QUOTA>
Admissions: <NUMBER_OF_ADMISSIONS>/<ADMISSION_QUOTA>
### END OF COMPANY INFO ###
```

For example the command `INFO COMPANY C560` outputs the following:

```
### START OF COMPANY INFO ###
Westmark  Software Co. (C560):
Min. Accepted GPA: 3.00
Min. Accepted Year: 4
Applications: 8/20
Admissions: 2/4
### END OF COMPANY INFO ###
```

***Reminder:*** Don't forget to include lines `### START OF COMPANY INFO ###` and `### END OF COMPANY INFO ###` as the first and last lines in your output. 


#
### - `LIST APPLICATIONS TO <COMPANY_ID>`:

This command writes to `output.txt` file the applications made to the company with `<COMPANY_ID>` and also admissions in the following format:

```
### START OF COMPANY APPLICATIONS ###
Admitted Students:
<STUDENT_NAME> <STUDENT_SURNAME> (<STUDENT_ID>) - Year: <STUDENT_YEAR>, GPA: <STUDENT_GPA>
<STUDENT_NAME> <STUDENT_SURNAME> (<STUDENT_ID>) - Year: <STUDENT_YEAR>, GPA: <STUDENT_GPA>
....
<STUDENT_NAME> <STUDENT_SURNAME> (<STUDENT_ID>) - Year: <STUDENT_YEAR>, GPA: <STUDENT_GPA>

Applied Students:
<STUDENT_NAME> <STUDENT_SURNAME> (<STUDENT_ID>) - Year: <STUDENT_YEAR>, GPA: <STUDENT_GPA>
<STUDENT_NAME> <STUDENT_SURNAME> (<STUDENT_ID>) - Year: <STUDENT_YEAR>, GPA: <STUDENT_GPA>
....
<STUDENT_NAME> <STUDENT_SURNAME> (<STUDENT_ID>) - Year: <STUDENT_YEAR>, GPA: <STUDENT_GPA>
### END OF COMPANY APPLICATIONS ###
```

For example the command `LIST APPLICATIONS TO C560` outputs the following:

```
### START OF COMPANY APPLICATIONS ###
Admitted Students:
Azad Ozbey (21810119) - Year: 4, GPA: 3.29
Osman Kartal (21863860) - Year: 4, GPA: 3.05

Applied Students:
Ozan Dede (21851376) - Year: 4, GPA: 3.80
Metin Yilmaz (21811858) - Year: 4, GPA: 3.70
Zekai Aytac (21885074) - Year: 4, GPA: 3.68
Tahir Yumlu (21821940) - Year: 4, GPA: 3.64
Azad Ozbey (21810119) - Year: 4, GPA: 3.29
Zeynep Dikmen (21817932) - Year: 4, GPA: 3.27
Erol Gokdemir (21822157) - Year: 4, GPA: 3.05
Osman Kartal (21863860) - Year: 4, GPA: 3.05
### END OF COMPANY APPLICATIONS ###
```

If no admitted or applied student exists, it should print `None` like below:

```
### START OF COMPANY APPLICATIONS ###
Admitted Students:
None

Applied Students:
None
### END OF COMPANY APPLICATIONS ###
```


***Reminder:***
1. An admitted student will appear **both** in Admitted Students and Applied Students.
2. In both lists, the students should be ordered in **decreasing order by GPA.** If two students' GPAs are equal, the one with higher year should come first.


#
### - `INFO STUDENT <STUDENT_ID>`:

This command writes to `output.txt` file the basic information of the student with `<STUDENT_ID>` in the following format:

```
### START OF STUDENT INFO ###
<STUDENT_NAME> <STUDENT_SURNAME> (<STUDENT_ID>) - Year: <STUDENT_YEAR>, GPA: <STUDENT_GPA>
### END OF STUDENT INFO ###
```

For example the command `INFO STUDENT 21811858` outputs the following:

```
### START OF STUDENT INFO ###
Metin Yilmaz (21811858) - Year: 4, GPA: 3.70
### END OF STUDENT INFO ###
```

***Reminder:*** Don't forget to include lines `### START OF STUDENT INFO ###` and `### END OF STUDENT INFO ###` as the first and last lines in your output.


#
### - `LIST APPLICATIONS BY <STUDENT_ID>`:

This command writes to `output.txt` file the applications made by the student with `<STUDENT_ID>` and also the admitted company (if any) in the following format:

```
### START OF STUDENT APPLICATIONS ###
Admitted Company:
<COMPANY_NAME> (<COMPANY_ID>)

Applied Companies:
<COMPANY_NAME> (<COMPANY_ID>)
<COMPANY_NAME> (<COMPANY_ID>)
...
<COMPANY_NAME> (<COMPANY_ID>)
### END OF STUDENT APPLICATIONS ###
```

For example the command `LIST APPLICATIONS BY 21917567` will give such an output:

```
### START OF STUDENT APPLICATIONS ###
Admitted Company:
Evantage Software (C179)

Applied Companies:
Evantage Software (C179)
Future Tec Crunch (C165)
Studio Software (C447)
### END OF STUDENT APPLICATIONS ###
```

***Reminder:***
1. The company admitted will appear **both** in Admitted Company and Applied Companies sections.
2. Admitted company can only be one.


# Constraints
1. A student can apply up to 5 companies at maximum.
2. A company could receive `application_quota` number of applications at maximum.
3. A company could admit `admission_quota` number of applications at most.
4. A company cannot receive an application from a student whose GPA is lower than the company's 
5. A company cannot receive an application from a student whose year is lower than the company's `min_accepted_year`.

# Testing

Before you submit, you should control that your code is working correctly. The two steps of testing is:

1. Run your `Main.java` with four arguments: `io/companies.txt`, `io/students.txt`,    `io/commands.txt`,   `io/output.txt`. It should process all commands from commands file, and write all necessary output to output file `io/output.txt`.
2. Execute `Test.java` with two command-line arguments, output filepath and expected output file path. Expected output file `expected_output.txt` is provided to you in the template project and it represents correct output generated with `students.txt`, `companies.txt`, `commands.txt`.  When you call `java Test io/output.txt io/expected_output.txt`, it will print to the console your overall score. Score is calculated by comparing your program's output `output.txt` with `expected_output.txt` line by line.

***Note***: Input files and the expected output file is provided to you for your convenience. During grading, your program will be tested against larger input files to check that you implemented each requirement correctly. That means if your implementation is not totally correct, you MAY (or may not) see a bit lower scores in your grades.

# Hints
- For reading lines from input files and writing lines to output file, you can use such a code block (required Java 1.8):
```
try {
   Files.write(Paths.get(OUTPUT_FILE_PATH), ARRAY_LIST_OF_LINES); // for writing to a file
   Files.readAllLines(Paths.get(INPUT_FILE_PATH)); // for reading from a line
} catch (IOException e) {
   e.printStackTrace();
}
```

- For sorting a list of objects, you can use Collections class from standard Java library:

```
Collections.sort(applicants, new Comparator<Student>() {
   @Override
   public int compare(Student s1, Student s2) {
      // Compare two student objects. If s1 is greater than s2, return 1. 
      // Otherwise return -1. If two students are equivalent return 0. 
   }
});
```

# Submission Rules

You should obey these constraints. Otherwise your experiment will not be evaluated.
1. The methods’ and attributes’ names should be satisfied the most common naming conventions in Java.
2. You should model students and companies with provided template classes.
3. All the input files and output file will be taken as command line arguments. (as we showed in template code)
4. Take care about the output file format explained above. **Your experiments will be evaluated automatically, so your output files should be comply exactly the same with the example expected output.**
5. You should use IntelliJ IDEA for development environment. Please ensure that your code is well working.
6. **Don't change file hierarchy of the template project.** If you change, your code will be potentially not compiled and result in zero grade.
7. **Don't change the names of classes which are provided to you**. `Main.java`, `Student.java`, `Company.java`, `Test.java`.
8. Don't modify `Test.java`. It is a class which you should use only for testing your program. **Don't add anything to it** because we will discard that file during grading.
9. The file hierarchy in your GitHub repository should match the following:

   ```
   io/
     --commands.txt
     --companies.txt
     --students.txt
     --expected_output.txt
     --output.txt
   src/
     --Main.java
     --Student.java
     --Company.java
     --Test.java
     --*.java (You can add additional java classes)
   ```

# Grading

- Code compiled: 10%
- Code organization: 10%
- Output test score: 80%

