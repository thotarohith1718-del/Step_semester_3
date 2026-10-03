package week6;

import java.util.Arrays;

public class assigmentq3 {

    static class GymMember {
        protected String memberId;
        protected int monthlyFee;

        private int totalLateFees;
        private int[] lateFeeHistory;
        private int feeCount;

        public GymMember(String memberId, int monthlyFee) {
            if (memberId == null || memberId.trim().length() < 4) {
                throw new IllegalArgumentException();
            }

            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
            this.lateFeeHistory = new int[10];
            this.feeCount = 0;
            this.totalLateFees = 0;
        }

        protected void chargeLateFee(int amount) {

            if (feeCount < 10) {
                lateFeeHistory[feeCount] = amount;
                feeCount++;
            }

            totalLateFees += amount;
        }

        public int[] getLateFeeHistory() {
            return Arrays.copyOf(lateFeeHistory, feeCount);
        }

        public int getTotalLateFees() {
            return totalLateFees;
        }
    }

    static class PremiumMember extends GymMember {
        private String trainerName;

        public PremiumMember(String memberId, int monthlyFee,
                             String trainerName) {
            super(memberId, monthlyFee);
            this.trainerName = trainerName;
        }

        @Override
        protected void chargeLateFee(int amount) {
            super.chargeLateFee(amount / 2);
        }
    }

    public static void main(String[] args) {

        PremiumMember p =
                new PremiumMember("MEM5", 2000, "Coach Riya");

        p.chargeLateFee(200);

        System.out.println(p.getTotalLateFees());

        int[] history = p.getLateFeeHistory();

        history[0] = 999;

        System.out.println(Arrays.toString(p.getLateFeeHistory()));
    }
}
