package utils;

import java.util.Scanner;
import java.util.Map;
/**
 * Menu 类用于管理用户菜单选项。
 * 它提供了一个交互式菜单，允许用户选择不同的功能，如计算个人所得税、修改税率等。
 */
public class Menu {
    private TaxInterface taxint;

    /**
     * 构造函数，初始化 TaxInterface。
     */
    public Menu() {
        taxint = new TaxInterface();
    }

    /**
     * 显示主菜单并处理用户输入。
     * 该方法会持续显示菜单，直到用户选择退出。
     */
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        taxint = new TaxInterface();

        while (true) {

            try {

                printOption();
                int number = scanner.nextInt();

                if (number == 1) {
                    // Option 1: Calculate Personal Income Tax
                    System.out.print("Enter your salary: ");
                    int salary = scanner.nextInt();
                    double tax = taxint.readPersonalTax(salary); // Call calculatePersonalTax
                    System.out.println("Personal Income Tax: " + tax);
                    sleepForOneSecond(); // Sleep for 1 second

                } else if (number == 2) {
                    // Option 2: Modify Tax Threshold
                    System.out.print("Enter the new tax threshold: ");
                    int newThreshold = scanner.nextInt();
                    if (newThreshold >= 0) {
                        taxint.writeThreshold(newThreshold); // Call modifyThreshold
                        System.out.println("Tax threshold updated to: " + taxint.readThreshold());
                    } else {
                        System.out.println("Tax threshold must be non-negative");
                    }

                    sleepForOneSecond(); // Sleep for 1 second

                } else if (number == 3) {
                    // Option 3: Modify Tax Rate
                    printTaxTable(); // Print the tax rate table first

                    System.out.print("Enter the level of the tax rate to modify (1-5): ");
                    int index = scanner.nextInt();
                    if (index < 1 || index > 5) {
                        throw new IllegalArgumentException("Invalid range, it must be between 1 and 5");
                    }

                    System.out.print("Enter the new tax rate (0-100): ");
                    double newRate = scanner.nextDouble();
                    if (newRate < 0 || newRate > 100) {
                        throw new IllegalArgumentException("Invalid range, it must be between 0.0 and 100.0");
                    }

                    taxint.writeRate(index - 1, newRate); // Call modifyRate
                    System.out.println("Tax rate at level " + index + " updated to: " + taxint.readTaxRate(index - 1));

                    sleepForOneSecond(); // Sleep for 1 second

                } else if (number == 4) {
                    // Option 4: Print Tax Rate Table
                    printTaxTable(); // Call printTaxTable
                    sleepForOneSecond(); // Sleep for 1 second

                } else if (number == 5) {
                    // Option 5: Exit the program
                    System.out.println("Exiting the program. Goodbye!");
                    sleepForOneSecond(); // Sleep for 1 second
                    break;

                } else {
                    throw new IllegalArgumentException("Invalid choice, please retry!");
                }

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                sleepForOneSecond(); // Sleep for 1 second

            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                scanner.nextLine(); // Clear the invalid input
                sleepForOneSecond(); // Sleep for 1 second
            }
        }

        scanner.close();
    }


    /**
     * 使程序暂停一秒钟。
     * 该方法用于在用户操作后暂停程序，以便用户有时间阅读输出。
     */
    public void sleepForOneSecond() {
        try {
            Thread.sleep(1000); // Sleep for 1000 milliseconds (1 second)
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted: " + e.getMessage());
        }
    }

    /**
     * 打印菜单的选项
     * 该方法用于重复打印选择菜单
     */
    public void printOption() {
        System.out.println("------------------------------------------");
        System.out.println("Please enter 1-5 to choose the function");
        System.out.println("1. Calculate Personal Income Tax");
        System.out.println("2. Modify Tax Threshold");
        System.out.println("3. Modify Tax Rate");
        System.out.println("4. Print Tax Rate Table");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    /**
     * 打印税率表，显示各个税级的适用范围和税率。
     */
    public void printTaxTable() {
        
        Map<String, Object> table = taxint.readTaxTable();
        double[] rates = (double[]) table.get("taxRate");
        int[] brackets = (int[]) table.get("taxBrackets");

        System.out.println("------------------------------------------");
        System.out.println("Tax Rate Table:");
        System.out.println("Level\tTaxable Income Range\tTax Rate (%)");
        for (int i = 0; i < rates.length; i++) {
            if (i == 0) {
                System.out.println((i + 1) + "\tUp to " + brackets[i + 1] + "\t\t\t" + rates[i]);
            } else if (i < rates.length - 1) {
                System.out.println((i + 1) + "\t" + brackets[i] + " - " + brackets[i + 1] + "\t\t\t" + rates[i]);
            } else {
                System.out.println((i + 1) + "\tOver " + brackets[i] + "\t\t\t" + rates[i]);
            }
        }
        System.out.println("------------------------------------------");

    }
}