package cn.jason.tool.commons;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class ListCalculationUtils {

    /**
     * @param list       list集合
     * @param clazz      list内部泛型
     * @param tarClasses 合计完成的实体
     * @param <T>
     * @return
     * @throws ReflectiveOperationException
     */
    @SuppressWarnings("unchecked")
    public static <T> T totalList(List<T> list, Class<T> clazz, Class<? extends Number>... tarClasses) throws ReflectiveOperationException {
        List<Class<?>> classes = Arrays.asList(tarClasses);
        T res = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Class<?> type = field.getType();
            if (classes.contains(type)) {
                String fieldName = field.getName();
                fieldName = firstLetterToUpper(fieldName);
                Method method = clazz.getMethod("get" + fieldName);
                Double d = 0d;
                for (T l : list) {
                    T e = (T) method.invoke(l);
                    d = ArithUtils.add(d, e);
                }
                String setMethodName = "set" + fieldName;
                Method setMethod = clazz.getMethod(setMethodName, type);
                ConvertUtils convertNumber = new ConvertUtils();
                Object n = convertNumber.convertNumber(d, type);
                //类型转换
                setMethod.invoke(res, n);
            }
        }

        return res;
    }

    private static String firstLetterToUpper(String str) {
        char[] array = str.toCharArray();
        array[0] -= 32;
        return String.valueOf(array);
    }
}
