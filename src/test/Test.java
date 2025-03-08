package test;
import utils.*;

public class Test {
    public static void main(String[] args) {
        TaxInterface taxinterface = new TaxInterface();

        System.out.println("========== Starting Backend Tests ==========");

        // Test 1: Calculate tax
        System.out.println("Test 1: Calculate tax (Salary: 5000)");
        double tax1 = taxinterface.readPersonalTax(4300);
        System.out.println(tax1 == 280.0 ? "Pass: Expected 280.0, Actual: 280.0" : "Fail. Expected: 280.0, Actual: " + tax1);

        // Test 2: Get tax rate
        System.out.println("Test 2: Get tax rate (Bracket: 2)");
        double rate = taxinterface.readTaxRate(2);
        System.out.println(rate == 15.0 ? "Pass: Expected 15.0, Actual: 15.0" : "Fail. Expected: 15.0, Actual: " + rate);

        // Test 3: Modify tax rate
        System.out.println("Test 3: Modify tax rate (Bracket 2 -> 18%)");
        taxinterface.writeRate(2, 18.0);
        rate = taxinterface.readTaxRate(2);
        System.out.println(rate == 18.0 ? "Pass: Expected 18.0, Actual: 18.0" : "Fail. Expected: 18.0, Actual: " + rate);

        // Test 4: Modify threshold
        System.out.println("Test 4: Modify threshold (5000 -> 6000)");
        taxinterface.writeThreshold(6000);
        int newThreshold = taxinterface.readThreshold();
        System.out.println(newThreshold == 6000 ? "Pass: Expected 6000, Actual: 6000" : "Fail. Expected: 6000, Actual: " + newThreshold);


        System.out.println("========== Backend Tests Completed ==========");
    }
}
