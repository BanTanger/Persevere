package com.bantanger.lambda;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/1
 */
public class SearchPerson {

    public static void printPersons(List<Person> roster, CheckPerson tester) {
        roster.forEach(p -> {
            if (tester.test(p)) {
                System.out.println(p);
            }
        });
    }

    /**
     * 可以用 Predicate 标准接口来代替自定义 lambda 接口 CheckPerson.
     * Predicate 标准接口的作用是接收一个任意类型的参数，回返一个 boolean。相当于检测数据
     * @param roster
     * @param tester
     * @param <T>
     */
    public static <T> void printWithPredicate(List<T> roster, Predicate<T> tester) {
        roster.forEach(p -> {
            if (tester.test(p)) {
                System.out.println(p);
            }
        });
    }

    /**
     * 如果想要自定义一些处理操作，可以使用 Consumer 标准接口
     * Consumer 标准接口的作用是接收一个任意类型的参数，不返回数据。相当于消费数据
     * @param param
     * @param tester
     * @param consumer
     * @param <T>
     */
    public static <T> void processWithConsumer(
            List<T> param, Predicate<T> tester, Consumer<T> consumer)
    {
        param.forEach(p -> {
            if (tester.test(p)) {
                consumer.accept(p);
            }
        });
    }

    public static <T, E> void processWithConsumerAndFunction(
            List<T> param, Predicate<T> tester, Function<T, E> function, Consumer<E> consumer)
    {
        param.forEach(p -> {
            if (tester.test(p)) {
                E data = function.apply(p);
                consumer.accept(data);
            }
        });
    }

}
