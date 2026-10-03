package week8practice;

abstract class Employee {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract boolean canTakeLeave(int days);
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name) {
        super(name);
    }

    public boolean canTakeLeave(int days) {
        return days <= 20;
    }
}

class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String name) {
        super(name);
    }

    public boolean canTakeLeave(int days) {
        return days <= 10;
    }
}

class Contractor extends Employee {
    public Contractor(String name) {
        super(name);
    }

    public boolean canTakeLeave(int days) {
        return days <= 5;
    }
}

class LeaveRequest {
    private Employee employee;
    private String startDate;
    private String endDate;
    private int days;
    private String status;

    public LeaveRequest(Employee employee, String startDate,
                        String endDate, int days) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        status = "Pending";
    }

    public void submit() {
        if (employee.canTakeLeave(days)) {
            System.out.println("Leave request submitted for "
                    + employee.getName()
                    + " (" + startDate + "-" + endDate + ").");
            System.out.println("Status: " + status);
        } else {
            System.out.println("Leave request denied for "
                    + employee.getName());
        }
    }

    public void approve(String reviewer) {
        if (status.equals("Pending")) {
            status = "Approved";

            System.out.println(employee.getName()
                    + "'s leave request ("
                    + startDate + "-" + endDate
                    + ") approved.");
            System.out.println("Status: " + status);
        }
    }

    public void reject(String reviewer) {
        if (status.equals("Pending")) {
            status = "Rejected";

            System.out.println(employee.getName()
                    + "'s leave request ("
                    + startDate + "-" + endDate
                    + ") rejected.");
            System.out.println("Status: " + status);
        }
    }

    public void changeToPending() {
        if (!status.equals("Pending")) {
            System.out.println("Cannot change leave request status from "
                    + status + " to Pending.");
        }
    }
}

public class q2 {
    public static void main(String[] args) {
        Employee john = new FullTimeEmployee("John");
        Employee jane = new PartTimeEmployee("Jane");

        LeaveRequest request1 =
                new LeaveRequest(john, "Jan 1", "Jan 5", 5);

        request1.submit();
        request1.approve("Alice");

        LeaveRequest request2 =
                new LeaveRequest(jane, "Feb 10", "Feb 11", 2);

        request2.submit();
        request2.reject("Bob");

        request1.changeToPending();
    }
}