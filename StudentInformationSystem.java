import java.util.ArrayList;
import java.util.Scanner;


public class StudentInformationSystem {
    public static void main(String[] args) {
         new StudentManeger().mainLogic();
    }
}

class Student {

    //variable declairations
    private String name;
    private int age;
    private double grade;
    private String studentId;
    private String contact;
    
    //class parameterized contructor 
    public Student(String name, int age, double grade, String studentId, String contact) {
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.studentId = studentId;
        this.contact = contact;
    }
    
    // Getters and setters for parameters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public int getAge() { return age; }
    public void setAge(int age) { 
        if(age > 18 && age < 60) this.age = age;
        else System.out.println("Age is not valid!");
    }
    public String getStudentId(){return studentId; }
    public double getGrade(){return grade;}
    public void setGrade(int grade) { 
        if(grade > 00 && grade <= 100) this.grade = grade;
        else System.out.println("Grade must be valid!");
    }
    public String getContact(){ return contact;}
    

    //information display method
    public void displayInfo() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Grade: " + grade);
        System.out.println("Contact: " + contact);
        System.out.println("-".repeat(30));
    }
}

class StudentManeger{

    // scanner object and arrayList 
    public static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);


    // main logic (while loop logic for ittration) 
    public static void mainLogic (){

        //condition check for while loop 
        boolean running = true;
        
        //main execution loop 
        while(running) {
            System.out.println("\n=== STUDENT INFORMATION SYSTEM ===");
            System.out.println("1. Add New Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            //switch case to evaluate use choice
            switch(choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    running = false;
                    System.out.println("Thank you for using Student Information System!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        //scanner class object closing 
        scanner.close();
    }

    // ---------- functionality adding methods here ----------------------

    //add student method 
    private static void addStudent() {
        System.out.println("\n=== ADD NEW STUDENT ===");

        //take inpit parameters
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        
        System.out.print("Enter Grade: ");
        double grade = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter Contact: ");
        String contact = scanner.nextLine();
        
        Student student = new Student(name, age, grade, studentId, contact);

        //loop to check if student already exist or not 
        for (Student std : students) {
            //condition for perticular id 
            if(String.valueOf(std.getStudentId()).equals(studentId)){
                System.out.println("Student already exsist ");
                return;
            }
        }

        //if student not present then add student 
        students.add(student);
        //sucess message
        System.out.println("Student added successfully!");
    }

    //method to view all student data at once line by line reading though arraylist of student objects from add student data method
    private static void viewAllStudents() {
        System.out.println("\n=== ALL STUDENTS ===");

        //check for empty arrayList 
        if(students.isEmpty()) {
            System.out.println("No students found!");
            return;
        }
        
        System.out.printf("%s %s %s %s %s\n", 
                         "Student ID", "Name", "Age", "Grade", "Contact");
        System.out.println("-".repeat(70));
        
        // display all student data one by one 
        for(Student student : students) {
            System.out.printf("%s %s %d %f %s\n", 
                            student.getStudentId(), student.getName(), 
                            student.getAge(), student.getGrade(), student.getContact());
        }
    }

    // method to search student from arrayList based on id 
    private static void searchStudent() {
        System.out.println("\n=== SEARCH STUDENTS ===");
        System.out.println("Enter student id for search :");
        String id = scanner.nextLine();
        int count =0;

        // itterate through arraylist for search purpose 
        for (Student std : students) {
            //condition for perticular id 
            if(String.valueOf(std.getStudentId()).equals(id)){
                System.out.printf("%s %s %d %f %s\n", 
                            std.getStudentId(), std.getName(), 
                            std.getAge(), std.getGrade(), std.getContact());
                count++;
            }
        }

        // message if no such id present 
        if(count == 0){
            System.out.println("No student found with " + id + " student id");
        }

    }

    //update student data of perticular id 
    private static void updateStudent() {
        System.out.println("\n=== UPDATE STUDENTS ===");
        System.out.println("Enter student id for update : ");
        String id = scanner.nextLine();

        // for loop to search student data present or not 
        for (int std = 0; std < students.size(); std++) {

            //condition for id
            if(students.get(std).getStudentId().equals(id)){


                //input parameters if id found
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
        
                System.out.print("Enter Age: ");
                int age = scanner.nextInt();
        
                System.out.print("Enter Grade: ");
                double grade = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
        
                System.out.print("Enter Contact: ");
                String contact = scanner.nextLine();
        
                //creating object of student class to store data in the arralist 
                Student student = new Student(name, age, grade, id, contact);
                students.set(std, student);
                System.out.println("Student updated successfully!");
                
            }
            // print line if no id matched 
            else
                System.out.println("No student found with " + id + " student id");
         }
    }
    
    //delete perticular student data 
    private static void deleteStudent() {
        System.out.println("\n=== DELETE STUDENTS ===");
        System.out.println("Enter student id for deletion : ");
        String id = scanner.nextLine();
        //boolean to check if data is deleted or not
        boolean haveDeleted =false;
        for (int std = 0; std < students.size(); std++) {
            if(students.get(std).getStudentId().equals(id)){
                students.remove(std);
                haveDeleted =true;
                break;
            }
        }

        //check condition if data deleted or not and accordingly print information 
        if(haveDeleted)
            System.out.println("Student deleted successfully!");
        else if(!haveDeleted)
            System.out.println("No Student Found with id " + id);
    }

}