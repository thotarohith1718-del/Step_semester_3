package m1;

public class Main {
    public static void main(String[] args) {

        m1.PlacementRecord[] records = {
                new m1.PlacementRecord("Ravi", "TCS", 4.5),
                new m1.PlacementRecord("Anitha", "Zoho", 6.2),
                new m1.PlacementRecord("Karthik", "Infosys", 4.0),

        };

        for (m1.PlacementRecord record : records) {
            record.printRecord();
        }
    }
}