package utils;

/**
 * TaxInterface 类用于管理个人所得税的计算和税率表的修改。
 * 它提供了计算个人所得税、修改税率、修改起征点以及打印税率表的功能。
 */
public class TaxInterface {
    private int threshold = 1600; // Tax exemption threshold
    private double[] taxRate = {5.0, 10.0, 15.0, 20.0, 25.0}; // Tax rate table
    private int[] taxBrackets = {0, 500, 2000, 5000, 20000}; // Tax bracket boundaries

    /**
     * 暂时未使用的默认构造函数
     */
    public TaxInterface() {

    }

    /**
     * 获取指定税级的税率。
     *
     * @param index 税级索引（从 0 开始）W
     * @return 返回对应税级的税率，如果索引无效则返回 -1
     */
    public double getTaxRate(int index) {
        return (index >= 0 && index < taxRate.length) ? taxRate[index] : -1;
    }

    /**
     * 获取当前的个税起征点。
     *
     * @return 返回当前的个税起征点
     */
    public int getThreshold() {
        return threshold;
    }

    /**
     * 计算个人所得税。
     *
     * @param salary 工资收入
     * @return 返回计算后的个人所得税金额
     */
    public double calculatePersonalTax(int salary) {
        salary -= threshold; // Calculate taxable income
        if (salary <= 0) {
            return 0; // No tax if taxable income is less than or equal to 0
        }

        double tax = 0; // Initialize tax
        for (int i = 1; i < taxBrackets.length; i++) {
            if (salary > taxBrackets[i]) {
                // Calculate tax for the current bracket
                tax += (taxBrackets[i] - taxBrackets[i - 1]) * taxRate[i - 1] / 100.0;
            } else {
                // Calculate tax for the last applicable bracket
                tax += (salary - taxBrackets[i - 1]) * taxRate[i - 1] / 100.0;
                break;
            }
        }

        // If income exceeds the highest bracket, calculate the additional tax
        if (salary > taxBrackets[taxBrackets.length - 1]) {
            tax += (salary - taxBrackets[taxBrackets.length - 1]) * taxRate[taxRate.length - 1] / 100.0;
        }

        return tax;
    }

    /**
     * 修改个税起征点。
     *
     * @param _threshold 新的个税起征点
     */
    public void modifyThreshold(int _threshold) {
        if (_threshold >= 0) {
            threshold = _threshold;
        } else {
            System.out.println("Invalid threshold value. Threshold must be non-negative.");
        }
    }

    /**
     * 修改指定税级的税率。
     *
     * @param index 税级索引（从 0 开始）
     * @param rate  新的税率（0-100）
     */
    public void modifyRate(int index, double rate) {
        if (index >= 0 && index < taxRate.length && rate >= 0.0 && rate <= 100.0) {
            taxRate[index] = rate;
        } else {
            System.out.println("Invalid index or rate. Index must be within bounds, and rate must be in range: 0-100.");
        }
    }

    /**
     * 打印税率表。
     * 该方法会输出当前的税率表，显示每个税级的应纳税所得额区间和对应的税率。
     */
    public void printTaxTable() {
        System.out.println("------------------------------------------");
        System.out.println("Tax Rate Table:");
        System.out.println("Level\tTaxable Income Range\tTax Rate (%)");
        for (int i = 0; i < taxRate.length; i++) {
            if (i == 0) {
                System.out.println((i + 1) + "\tUp to " + taxBrackets[i + 1] + "\t\t\t" + taxRate[i]);
            } else if (i < taxRate.length - 1) {
                System.out.println((i + 1) + "\t" + taxBrackets[i] + " - " + taxBrackets[i + 1] + "\t\t\t" + taxRate[i]);
            } else {
                System.out.println((i + 1) + "\tOver " + taxBrackets[i] + "\t\t\t" + taxRate[i]);
            }
        }
        System.out.println("------------------------------------------");
    }

}