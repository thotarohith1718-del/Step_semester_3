package m1;

public class Main3 {
    public static void main(String[] args) {

        Course theoryOnly =
                new Course("21CSC201", "Data Structures", 4);

        Course courseWithLab =
                new Course("21CSC205L", "DSA Lab", 3, 1);

        System.out.println(theoryOnly.code +
                " total credits: " + theoryOnly.totalCredits());

        System.out.println(courseWithLab.code +
                " total credits: " + courseWithLab.totalCredits());
    }
}
