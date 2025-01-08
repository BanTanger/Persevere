package com.bantanger.dao;

import io.vavr.Function0;
import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.control.Option;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author chensongmin
 * @description
 * @create 2024/12/30
 */
public class VavrTest {

    @Test
    public void optionTest() {
        Option<Object> nonObj = Option.of(null);
        Option<String> val = Option.of("val");
        Assertions.assertEquals("None", nonObj.toString());
        Assertions.assertEquals("Some(val)", val.toString());
    }

    @Test
    public void optionDefaultTest() {
        // 空指针情况返回默认值
        String name = null;
        Option<String> nameOption = Option.of(name);
        Assertions.assertEquals("bantanger", nameOption.getOrElse("bantanger"));

        // 有值情况返回具体值
        String xx = "bantanger";
        Option<String> stringOption = Option.of(xx);
        Assertions.assertEquals("nobantanger", stringOption.getOrElse("nobantanger"));
    }

    @Test
    public void functionalInterfacesTest() {
        // 原生语法只支持 Function<T, R> 和 Function<T, E, R> 两种 API，接收一个、两个入参，vavr 可以接收八个
        Function1<Integer, Integer> square = (num) -> num * num;
        Assertions.assertEquals(4, square.apply(2));
        Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
        Assertions.assertEquals(4, sum.apply(1, 3));
        // 另一种语义表达
        Function2<Integer, Integer, Integer> sum2 = Function2.of(Integer::sum);

        // 无入参有出参，在原生语法要用 Supplier
        Function0<String> getClassName = () -> this.getClass().getName();
        Assertions.assertEquals("com.bantanger.dao.VavrTest", getClassName.apply());
    }
}
