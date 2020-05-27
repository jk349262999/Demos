package cn.jason.tool.commons;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

/**
 * 由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精
 * 确的浮点数运算，包括加减乘除和四舍五入。
 */
public class ArithUtils { //默认除法运算精度
    private static final int DEF_DIV_SCALE = 2; //这个类不能实例化

    private ArithUtils() {
    }

    /**
     * 提供精确的加法运算。
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static Double add(Object v1, Object v2) {
        BigDecimal b = new BigDecimal(0);
        if (v1 != null && !v1.toString().isEmpty())
            b = b.add(new BigDecimal(v1.toString()));
        if (v2 != null && !v2.toString().isEmpty())
            b = b.add(new BigDecimal(v2.toString()));
        return b.doubleValue();
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static Double sub(Object v1, Object v2) {
        BigDecimal b = new BigDecimal(0);
        if (v1 != null && !v1.toString().isEmpty())
            b = b.add(new BigDecimal(v1.toString()));
        if (v2 != null && !v2.toString().isEmpty())
            b = b.subtract(new BigDecimal(v2.toString()));
        return b.doubleValue();
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static Double mul(Object v1, Object v2) {
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供(相对)精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以后10位，以后的数字四舍五入。
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static Double div(Object v1, Object v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供(相对)精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(Object v1, Object v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        if (v1 == null || v1.toString().isEmpty() || v2 == null || v2.toString().isEmpty() || (new BigDecimal(v2.toString())).compareTo(ZERO) == 0) {
            throw new NumberFormatException("param Exception v1: "+v1+",v2: "+v2);
        }
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static Double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 被除数判断
     * @param v2
     * @return
     */
    public static boolean checkDivisor(Object v2){
        if(v2 == null || v2.toString().isEmpty() || (new BigDecimal(v2.toString())).compareTo(ZERO) == 0)
            return false;
        else
            return true;
    }
};    