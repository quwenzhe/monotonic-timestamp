package com.quwenzhe.timestamp;

import static com.quwenzhe.timestamp.Constant.DEFAULT_PRECISION;

/**
 * @Description double比较器
 * @Author quwenzhe
 * @Date 2020/8/24 10:23 AM
 */
public class DoubleComparer {

    /**
     * 比较两个double值是否相等
     *
     * @param numberOne 数值一
     * @param numberTwo 数值二
     * @return true:相等;false:不相等
     */
    public static boolean considerEqual(double numberOne, double numberTwo) {
        return considerEqual(numberOne, numberTwo, Double.parseDouble(DEFAULT_PRECISION));
    }

    /**
     * 根据精度比较两个double值是否相等
     *
     * @param numberOne 数值一
     * @param numberTwo 数值二
     * @param precision 比较精度
     * @return true:相等;false:不相等
     */
    public static boolean considerEqual(double numberOne, double numberTwo, double precision) {
        return Double.compare(numberOne, numberTwo) == 0 || considerZero(numberOne - numberTwo, precision);
    }

    /**
     * 比较两个double值的差是否超过精度
     *
     * @param delta     两个double值的差
     * @param precision 比较精度
     * @return true:未超过精度;false:超过精度
     */
    private static boolean considerZero(double delta, double precision) {
        return Math.abs(delta) <= precision;
    }
}
