package utils;
import java.util.Scanner;

/**
 * Menu 类用于管理用户菜单选项。
 * 它提供了一个交互式菜单，允许用户选择不同的功能，如计算个人所得税、修改税率等。
 */
public class Menu {
    private TaxInterface taxint;

    /**
     * 暂时未使用的默认构造函数
     */
    public Menu() {

    }

    /**
     * 显示主菜单并处理用户输入。
     * 该方法会持续显示菜单，直到用户选择退出。
     */
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        taxint = new TaxInterface();

        while (true) {
            System.out.println("------------------------------------------");
            System.out.println("Please enter 1-5 to choose the function");
            System.out.println("1. Calculate Personal Income Tax");
            System.out.println("2. Modify Tax Threshold");
            System.out.println("3. Modify Tax Rate");
            System.out.println("4. Print Tax Rate Table");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int number = scanner.nextInt();
            try {
                if (number == 1) {
                    // Option 1: Calculate Personal Income Tax
                    System.out.print("Enter your salary: ");
                    int salary = scanner.nextInt();
                    double tax = taxint.calculatePersonalTax(salary); // Call calculatePersonalTax
                    System.out.println("Personal Income Tax: " + tax);
                    sleepForOneSecond(); // Sleep for 1 second

                } else if (number == 2) {
                    // Option 2: Modify Tax Threshold
                    System.out.print("Enter the new tax threshold: ");
                    int newThreshold = scanner.nextInt();
                    taxint.modifyThreshold(newThreshold); // Call modifyThreshold
                    System.out.println("Tax threshold updated to: " + taxint.getThreshold());
                    sleepForOneSecond(); // Sleep for 1 second

                } else if (number == 3) {
                    // Option 3: Modify Tax Rate
                    taxint.printTaxTable(); // Print the tax rate table first
                    System.out.print("Enter the level of the tax rate to modify (1-5): ");
                    int index = scanner.nextInt() - 1;
                    System.out.print("Enter the new tax rate (0-100): ");
                    double newRate = scanner.nextDouble();
                    taxint.modifyRate(index, newRate); // Call modifyRate
                    System.out.println("Tax rate at level " + (index + 1) + " updated to: " + taxint.getTaxRate(index));
                    sleepForOneSecond(); // Sleep for 1 second

                } else if (number == 4) {
                    // Option 4: Print Tax Rate Table
                    taxint.printTaxTable(); // Call printTaxTable
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
    private void sleepForOneSecond() {
        try {
            Thread.sleep(1000); // Sleep for 1000 milliseconds (1 second)
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted: " + e.getMessage());
        }
    }
}