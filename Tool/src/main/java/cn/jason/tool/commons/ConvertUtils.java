package cn.jason.tool.commons;

import cn.hutool.core.util.StrUtil;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ConvertUtils {

    /**
     * 把Object转换为数值类型
     * @param value 对象
     * @param targetType 目标类型
     * @return
     */
    public Object convertNumber(Object value, Class<?> targetType) {
        if (int.class == targetType || Integer.class == targetType) {
            return ((Number) value).intValue();
        } else if (long.class == targetType || Long.class == targetType) {
            return ((Number) value).longValue();
        } else if (float.class == targetType || Float.class == targetType) {
            return ((Number) value).floatValue();
        } else if (double.class == targetType || Double.class == targetType) {
            return ((Number) value).doubleValue();
        } else if (BigDecimal.class == targetType) {
            return toBigDecimal(value);
        }else if(Number.class == targetType){
            return (Number)value;
        }
        return null;
    }

    /**
     * 转换为BigDecimal<br>
     * 如果给定的值为空，或者转换失败，返回默认值<br>
     * 转换失败不会报错
     *
     * @param value 被转换的值
     * @return 结果
     */
    private BigDecimal toBigDecimal(Object value) {
        if (value instanceof Long) {
            return new BigDecimal((Long) value);
        } else if (value instanceof Integer) {
            return new BigDecimal((Integer) value);
        } else if (value instanceof BigInteger) {
            return new BigDecimal((BigInteger) value);
        } else if(value instanceof Boolean) {
            return new BigDecimal((boolean)value ? 1 : 0);
        }

        //对于Double类型，先要转换为String，避免精度问题
        final String valueStr = value.toString();
        if (valueStr.length() == 0) {
            return null;
        }
        return new BigDecimal(valueStr);
    }

}
