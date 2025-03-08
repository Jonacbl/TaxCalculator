package utils;

import java.util.Map;
import java.util.HashMap;

/**
 * TaxTable 类用于存储和管理个人所得税的税率表，并提供计算税收的方法。
 * 该类包含税率表、税级边界，并提供修改税率和计算税额的功能。
 */
public class TaxTable {
    private double[] taxRate;     // 税率表
    private int[] taxBrackets;    // 税级边界

    /**
     * 构造函数，初始化税率表和税级边界。
     */
    public TaxTable() {
        taxRate = new double[]{5.0, 10.0, 15.0, 20.0, 25.0}; // 初始化税率表
        taxBrackets = new int[]{0, 500, 2000, 5000, 20000};   // 初始化税级边界
    }

    /**
     * 获取指定索引处的税率。
     *
     * @param index 税率索引
     * @return 对应索引处的税率
     * @throws ArrayIndexOutOfBoundsException 如果索引超出范围
     */
    public double getTaxRate(int index) {
        if (index < 0 || index >= taxRate.length) {
            throw new ArrayIndexOutOfBoundsException("Input index is out of range: " + index);
        }
        return taxRate[index];
    }

    /**
     * 获取税率的条目数，即行数
     * @return 税率表的行数，类型为int
     */
    public int getTableNumber() {
        return taxRate.length;
    }

    /**
     * 获取完整的税率表和税级边界。
     *
     * @return 包含 "taxRate" 和 "taxBrackets" 的 Map
     */
    public Map<String, Object> getTaxTable() {
        Map<String, Object> taxData = new HashMap<>();
        taxData.put("taxRate", taxRate);
        taxData.put("taxBrackets", taxBrackets);
        return taxData;
    }

    /**
     * 计算指定收入的总税额。
     *
     * @param target 需要计算税额的收入
     * @return 计算后的税额
     */
    public double calculateAllTax(int target) {
        double res = 0;
        for (int i = 1; i < taxBrackets.length; i++) {
            if (target > taxBrackets[i]) {
                // 计算当前税级的税额
                res += (taxBrackets[i] - taxBrackets[i - 1]) * taxRate[i - 1] / 100.0;
            } else {
                // 计算最后适用税级的税额
                res += (target - taxBrackets[i - 1]) * taxRate[i - 1] / 100.0;
                break;
            }
        }

        // 若收入超过最高税级，计算超出部分的税额
        if (target > taxBrackets[taxBrackets.length - 1]) {
            res += (target - taxBrackets[taxBrackets.length - 1]) * taxRate[taxRate.length - 1] / 100.0;
        }
        return res;
    }


    /**
     * 修改指定索引的税率。
     *
     * @param index 要修改的税率索引
     * @param rate  新的税率值
     * @throws ArrayIndexOutOfBoundsException 如果索引超出范围
     */
    public void modifyRate(int index, double rate) {
        if (index < 0 || index >= taxRate.length) {
            throw new ArrayIndexOutOfBoundsException("Input index is out of range: " + index);
        }
        if (rate < 0.0 || rate > 100.0) {
            throw new IllegalArgumentException("Tax rate is out of range: " + rate);
        }

        taxRate[index] = rate;
    }
}
