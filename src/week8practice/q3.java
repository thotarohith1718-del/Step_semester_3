package week8practice;

abstract class Question {
    private String questionText;
    private int marks;

    public Question(String questionText, int marks) {
        this.questionText = questionText;
        this.marks = marks;
    }

    public int getMarks() {
        return marks;
    }

    public abstract boolean evaluate(String answer);
}

class MultipleChoiceQuestion extends Question {
    private String correctAnswer;

    public MultipleChoiceQuestion(String questionText, int marks,
                                  String correctAnswer) {
        super(questionText, marks);
        this.correctAnswer = correctAnswer;
    }

    public boolean evaluate(String answer) {
        return answer.equalsIgnoreCase(correctAnswer);
    }
}

class TrueFalseQuestion extends Question {
    private String correctAnswer;

    public TrueFalseQuestion(String questionText, int marks,
                             String correctAnswer) {
        super(questionText, marks);
        this.correctAnswer = correctAnswer;
    }

    public boolean evaluate(String answer) {
        return answer.equalsIgnoreCase(correctAnswer);
    }
}

class ShortAnswerQuestion extends Question {
    private String correctAnswer;

    public ShortAnswerQuestion(String questionText, int marks,
                               String correctAnswer) {
        super(questionText, marks);
        this.correctAnswer = correctAnswer;
    }

    public boolean evaluate(String answer) {
        return answer.equalsIgnoreCase(correctAnswer);
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Examination {
    private String name;
    private Question[] questions;
    private int count;

    public Examination(String name) {
        this.name = name;
        questions = new Question[10];
        count = 0;
    }

    public void addQuestion(Question question) {
        questions[count] = question;
        count++;
    }

    public String getName() {
        return name;
    }

    public Question getQuestion(int index) {
        return questions[index];
    }

    public int getCount() {
        return count;
    }
}

class Attempt {
    private Student student;
    private Examination examination;
    private String[] answers;
    private boolean submitted;

    public Attempt(Student student, Examination examination) {
        this.student = student;
        this.examination = examination;
        answers = new String[10];
        submitted = false;

        System.out.println(examination.getName()
                + " started by "
                + student.getName() + ".");
    }

    public void answer(int questionNumber, String answer) {
        if (submitted) {
            System.out.println(
                    "Cannot change answers for a submitted examination.");
            return;
        }

        answers[questionNumber - 1] = answer;

        System.out.println("Answer recorded for Question "
                + questionNumber + ".");
    }

    public void submit() {
        submitted = true;

        System.out.println(examination.getName()
                + " submitted by "
                + student.getName() + ".");

        int total = 0;
        int score = 0;

        for (int i = 0; i < examination.getCount(); i++) {
            Question question = examination.getQuestion(i);
            total += question.getMarks();

            boolean correct =
                    question.evaluate(answers[i]);

            if (correct) {
                score += question.getMarks();

                System.out.println("Result: Question "
                        + (i + 1)
                        + ": Correct ("
                        + question.getMarks()
                        + " points)");
            } else {
                System.out.println("Result: Question "
                        + (i + 1)
                        + ": Incorrect (0 points)");
            }
        }

        System.out.println("Total score: "
                + score + "/" + total + ".");
    }
}

public class q3 {
    public static void main(String[] args) {
        Student student = new Student("Student 1");

        Examination exam = new Examination("Exam A");

        exam.addQuestion(
                new MultipleChoiceQuestion(
                        "Which is a programming language?",
                        5,
                        "C"));

        exam.addQuestion(
                new TrueFalseQuestion(
                        "Java is an operating system.",
                        5,
                        "False"));

        Attempt attempt = new Attempt(student, exam);

        attempt.answer(1, "C");
        attempt.answer(2, "True");

        attempt.submit();

        attempt.answer(1, "B");
    }
}