package utils;

import java.util.Map;

/**
 * TaxInterface 是调用后端关于税率的一系列函数，并提供给前端调用接口API的中间件。
 * 它提供读取个人所得税、写税率、写起征点以及读取税率表的功能。
 */
public class TaxInterface {
    private int threshold; // 个人所得税的起征点
    private TaxTable taxtable; // 税率表对象

    /**
     * 构造函数，初始化税率表，并设置默认的个税起征点为 1600。
     */
    public TaxInterface() {
        taxtable = new TaxTable();
        threshold = 1600;
    }

    /**
     * 获取指定税级的税率。
     *
     * @param index 税级索引（从 0 开始）
     * @return 对应税级的税率
     * @throws ArrayIndexOutOfBoundsException 如果索引超出范围
     */
    public double readTaxRate(int index) {
        if (index < 0 || index >= taxtable.getTableNumber()) {
            throw new ArrayIndexOutOfBoundsException("Input index is out of range: " + index);
        }
        return taxtable.getTaxRate(index);
    }

    /**
     * 获取当前的个税起征点。
     *
     * @return 当前的个税起征点
     */
    public int readThreshold() {
        return threshold;
    }

    /**
     * 计算个人所得税。
     *
     * @param salary 工资收入（单位：元）
     * @return 计算后的个人所得税金额（单位：元），如果收入低于起征点，则返回 0
     */
    public double readPersonalTax(int salary) {
        salary -= threshold; // 计算应纳税所得额
        return (salary <= 0) ? 0 : taxtable.calculateAllTax(salary);
    }

    /**
     * 修改个税起征点。
     *
     * @param newThreshold 新的个税起征点
     * @throws IllegalArgumentException 起征点必须为非负数
     */
    public void writeThreshold(int newThreshold) {
        if (newThreshold < 0) {
            throw new IllegalArgumentException("Threshold must be non-negative");
        }
        threshold = newThreshold;
    }

    /**
     * 修改指定税级的税率。
     *
     * @param index 税级索引（从 0 开始）
     * @param rate  新的税率（百分比，范围 0-100）
     * @throws ArrayIndexOutOfBoundsException 如果索引超出范围
     */
    public void writeRate(int index, double rate) {
        if (index < 0 || index >= taxtable.getTableNumber()) {
            throw new ArrayIndexOutOfBoundsException("Input index is out of range: " + index);
        }
        if (rate < 0.0 || rate > 100.0) {
            throw new IllegalArgumentException("Tax rate is out of range: " + rate);
        }
        taxtable.modifyRate(index, rate);
    }

    /**
     * 获取税率表，包括各税级的边界和对应的税率。
     *
     * @return 包含 "taxRate"（税率数组）和 "taxBrackets"（税级边界数组）的 Map
     */
    public Map<String, Object> readTaxTable() {
        return taxtable.getTaxTable();
    }
}
