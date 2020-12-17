package cn.jason.tool.test;

import cn.jason.tool.commons.ListCalculationUtils;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws ReflectiveOperationException {
        List<TestEntity> list = new ArrayList<>();
        TestEntity e = new TestEntity();
        e.setName("a");
        e.setAge(1);
        list.add(e);
        e = new TestEntity();
        e.setName("b");
        e.setAge(2);
        list.add(e);
//        int.class
        TestEntity res = ListCalculationUtils.totalList(list, TestEntity.class, int.class);

        System.out.println(res);

//        int a = 1;
//        System.out.println((Number) a);
//        int x = (int) Convert.convert(1.2d);

//        System.out.println((Number) a);
//Double d = 32.22d;
//        Convert.convert(int.class,d);
//        int o = Convert.convert(int.class,d);
//        System.out.println( Convert.convert(int.class,d));
//        Field[] fields = TestEntity.class.getDeclaredFields();
//        for (Field field : fields) {
//            System.out.println("name: "+ field.getName() +"type: "+field.getType());
//        }
    }
}
