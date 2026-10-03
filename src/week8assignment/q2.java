package week8assignment;

abstract class Assignment {
    private String title;
    private int maxMarks;
    private int dueDay;

    public Assignment(String title, int maxMarks, int dueDay) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDay = dueDay;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public int getDueDay() {
        return dueDay;
    }

    public abstract double applyPenalty(int marks, int lateDays);
}

class CodingAssignment extends Assignment {
    public CodingAssignment(String title, int maxMarks, int dueDay) {
        super(title, maxMarks, dueDay);
    }

    public double applyPenalty(int marks, int lateDays) {
        double penalty = lateDays * 0.10;
        if (penalty > 1) {
            penalty = 1;
        }
        return marks * (1 - penalty);
    }
}

class WrittenAssignment extends Assignment {
    public WrittenAssignment(String title, int maxMarks, int dueDay) {
        super(title, maxMarks, dueDay);
    }

    public double applyPenalty(int marks, int lateDays) {
        double penalty = lateDays * 0.20;
        if (penalty > 1) {
            penalty = 1;
        }
        return marks * (1 - penalty);
    }
}

class student{
    private String name;

    public student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Submission {
    private Student student;
    private Assignment assignment;
    private int submissionDay;
    private String status;
    private double finalMarks;

    public Submission(Student student, Assignment assignment, int submissionDay) {
        this.student = student;
        this.assignment = assignment;
        this.submissionDay = submissionDay;
        this.status = "Submitted";

        int lateDays = Math.max(0, submissionDay - assignment.getDueDay());

        if (lateDays == 0) {
            System.out.println(student.getName() + "'s submission for '" +
                    assignment.getTitle() + "' received (on time).");
        } else {
            System.out.println(student.getName() + "'s submission for '" +
                    assignment.getTitle() + "' received (" +
                    lateDays + " days late).");
        }

        System.out.println("Status: " + status);
    }

    public void grade(int marks) {
        if (!status.equals("Submitted")) {
            System.out.println("Cannot grade this submission.");
            return;
        }

        int lateDays = Math.max(0, submissionDay - assignment.getDueDay());

        finalMarks = assignment.applyPenalty(marks, lateDays);
        status = "Graded";

        if (lateDays == 0) {
            System.out.printf("%s graded: %.0f/%d.%n",
                    student.getName(),
                    finalMarks,
                    assignment.getMaxMarks());
        } else {
            int penalty = 0;

            if (assignment instanceof CodingAssignment) {
                penalty = lateDays * 10;
            } else if (assignment instanceof WrittenAssignment) {
                penalty = lateDays * 20;
            }

            System.out.printf("%s graded: %.0f/%d after %d%% late penalty.%n",
                    student.getName(),
                    finalMarks,
                    assignment.getMaxMarks(),
                    penalty);
        }

        System.out.println("Status: " + status);
    }

    public void resubmit(int newDay) {
        if (status.equals("Graded")) {
            System.out.println("Cannot resubmit: '" +
                    assignment.getTitle() +
                    "' has already been graded.");
        }
    }
}

public class q2 {
    public static void main(String[] args) {
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Assignment coding =
                new CodingAssignment("Linked List Lab", 50, 10);

        Assignment written =
                new WrittenAssignment("Design Essay", 50, 12);

        Submission s1 =
                new Submission(asha, coding, 10);

        Submission s2 =
                new Submission(ravi, written, 14);

        s1.grade(45);
        s2.grade(40);

        s1.resubmit(11);
    }
}
