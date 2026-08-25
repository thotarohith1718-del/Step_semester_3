package m1;


public class Main5 {
    public static void main(String[] args) {

        Student student1 = new Student("Ravi", 90);

        Student student2 = new Student("Anitha", 95);

        System.out.println(Student.studentCount + " Student objects created");

        Student.printCollegeInfo();
    }
}