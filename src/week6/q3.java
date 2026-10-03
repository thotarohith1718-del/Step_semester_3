package week6;

import java.util.Arrays;

public class q3 {

    static class LibraryMember {
        protected String memberId;
        protected int borrowLimit;

        private int totalFine;
        private int[] fineHistory;
        private int fineCount;

        public LibraryMember(String memberId, int borrowLimit) {
            this.memberId = memberId;
            this.borrowLimit = borrowLimit;
            fineHistory = new int[10];
        }

        protected void chargeFine(int amount) {
            if (fineCount < 10) {
                fineHistory[fineCount] = amount;
                fineCount++;
            }

            totalFine += amount;
        }

        public int[] getFineHistory() {
            return Arrays.copyOf(fineHistory, fineCount);
        }

        public int getTotalFine() {
            return totalFine;
        }
    }

    static class StudentMember extends LibraryMember {

        public StudentMember(String memberId, int borrowLimit,
                             String course) {
            super(memberId, borrowLimit);
        }

        @Override
        protected void chargeFine(int amount) {
            super.chargeFine(amount / 2);
        }
    }

    public static void main(String[] args) {

        StudentMember s =
                new StudentMember("STU5", 3, "CSE");

        s.chargeFine(100);

        System.out.println(s.getTotalFine());

        int[] history = s.getFineHistory();
        history[0] = 999;

        System.out.println(Arrays.toString(s.getFineHistory()));
    }
}