package week8assignment;

interface MembershipPlan {
    double calculateFee();
    String getName();
}

class MonthlyPlan implements MembershipPlan {
    public double calculateFee() {
        return 1000;
    }

    public String getName() {
        return "Monthly";
    }
}

class QuarterlyPlan implements MembershipPlan {
    public double calculateFee() {
        return 1000 * 3 * 0.90;
    }

    public String getName() {
        return "Quarterly";
    }
}

class AnnualPlan implements MembershipPlan {
    public double calculateFee() {
        return 1000 * 12 * 0.75;
    }

    public String getName() {
        return "Annual";
    }
}

class Member {
    private String name;

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Membership {
    private Member member;
    private MembershipPlan plan;
    private String status;

    public Membership(Member member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;
        this.status = "Active";

        System.out.println(plan.getName() +
                " membership created for " +
                member.getName() + ".");

        System.out.printf("Fee: ₹%.2f.%n", plan.calculateFee());
        System.out.println("Status: " + status);
    }

    public void checkIn() {
        if (status.equals("Active")) {
            System.out.println(member.getName()
                    + " checked in successfully.");
        } else {
            System.out.println("Check-in denied: "
                    + member.getName()
                    + "'s membership is " + status + ".");
        }
    }

    public void freeze() {
        if (status.equals("Active")) {
            status = "Frozen";

            System.out.println(member.getName()
                    + "'s membership frozen.");
            System.out.println("Status: " + status);
        } else if (status.equals("Expired")) {
            System.out.println("Cannot freeze an Expired membership.");
        } else {
            System.out.println("Membership is already Frozen.");
        }
    }

    public void unfreeze() {
        if (status.equals("Frozen")) {
            status = "Active";

            System.out.println(member.getName()
                    + "'s membership unfrozen.");
            System.out.println("Status: " + status);
        } else if (status.equals("Expired")) {
            System.out.println("Cannot unfreeze an Expired membership.");
        }
    }

    public void expire() {
        status = "Expired";

        System.out.println(member.getName()
                + "'s membership expired.");
        System.out.println("Status: " + status);
    }
}

public class q4{
    public static void main(String[] args) {
        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        Membership ashaMembership =
                new Membership(asha, new QuarterlyPlan());

        Membership raviMembership =
                new Membership(ravi, new MonthlyPlan());

        ashaMembership.checkIn();

        ashaMembership.freeze();

        ashaMembership.checkIn();

        raviMembership.expire();

        raviMembership.freeze();
    }
}