package week8assignment;

abstract class WashType {
    public abstract int getDuration();
    public abstract double getCharge();
    public abstract String getName();
}

class QuickWash extends WashType {
    public int getDuration() {
        return 30;
    }

    public double getCharge() {
        return 20;
    }

    public String getName() {
        return "Quick";
    }
}

class NormalWash extends WashType {
    public int getDuration() {
        return 45;
    }

    public double getCharge() {
        return 30;
    }

    public String getName() {
        return "Normal";
    }
}

class HeavyWash extends WashType {
    public int getDuration() {
        return 60;
    }

    public double getCharge() {
        return 45;
    }

    public String getName() {
        return "Heavy";
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

class WashCycle {
    private Student student;
    private WashingMachine machine;
    private WashType washType;

    public WashCycle(Student student, WashingMachine machine, WashType washType) {
        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }

    public void start() {
        System.out.printf("%s wash started on %s for %s (%d min). Charge: ₹%.2f.%n",
                washType.getName(),
                machine.getId(),
                student.getName(),
                washType.getDuration(),
                washType.getCharge());
    }
}

class WashingMachine {
    private String id;
    private boolean busy;

    public WashingMachine(String id) {
        this.id = id;
        busy = false;
    }

    public String getId() {
        return id;
    }

    public boolean isBusy() {
        return busy;
    }

    public WashCycle startWash(Student student, WashType washType) {
        if (busy) {
            System.out.println("Machine " + id + " is currently busy.");
            return null;
        }

        busy = true;
        WashCycle cycle = new WashCycle(student, this, washType);
        cycle.start();
        return cycle;
    }

    public void completeWash() {
        if (busy) {
            busy = false;
            System.out.println(id + " cycle completed.");
            System.out.println(id + " is now free.");
        }
    }
}

public class q1 {
    public static void main(String[] args) {
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        m1.startWash(asha, new QuickWash());

        m1.startWash(ravi, new HeavyWash());

        m2.startWash(ravi, new HeavyWash());

        m1.completeWash();

        m1.startWash(neha, new NormalWash());
    }
}