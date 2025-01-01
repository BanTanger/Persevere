package com.bantanger.lambda;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/1
 */
public class LambdaExpressionsPractiseTest {

    // Specify Search Criteria Code With a Lambda Expression

    private List<Person> roster;

    @BeforeEach
    public void init() {
        roster = List.of(
                new Person("bantanger1", 18, Person.Sex.MALE),
                new Person("bantanger2", 15, Person.Sex.FEMALE));
    }

    @Test
    public void anonymousClassTest() {
        // Person(name=bantanger1, age=18, gender=MALE)
        SearchPerson.printPersons(roster, new CheckPerson() {
            @Override
            public boolean test(Person p) {
                return p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25;
            }
        });
    }

    @Test
    public void lambdaExpressionTest() {
        SearchPerson.printPersons(roster, p ->
                p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25);
    }

    @Test
    public void lambdaExpressionWithStandardFunctional_PredicateTest() {
        SearchPerson.printWithPredicate(roster, p ->
                p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25);
    }

    @Test
    public void lambdaExpressionWithStandardFunctional_ConsumeTest() {
        SearchPerson.processWithConsumer(roster,
                p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25,
                p -> System.out.println(p));
    }

    @Test
    public void lambdaExpressionWithStandardFunctional_ConsumeAndFunctionTest() {
        SearchPerson.processWithConsumerAndFunction(
                roster, // 从集合源获取对象源。集合 roster 是 List 类型，也就是 Iterable 类型的对象
                p -> p.getGender() == Person.Sex.MALE // 筛选与 Prodicate 对象测试器匹配的对象
                        && p.getAge() >= 18
                        && p.getAge() <= 25,
                p -> p.getGender(), // 将每个通过筛选的对象映射到 Function 对象映射器指定的值
                gender -> System.out.println(gender)); // 对映射后的对象执行 Consumer 对应操作
    }

    // 通过上面这个例子发现了什么？跟 Stream 很像，我们用聚合翻译上面的例子

    /**
     * <pre>
     * {@code
     * Stream<E> stream() // 获取对象的源
     * Stream<T> filter(Predicate<? super T> predicate); // 过滤符合 Predicate 测试器匹配的对象
     * <R> Stream<R> map(Function<? super T, ? extends R> mapper); // 将对象映射到 Function 对象指定的另一个值
     * void forEach(Consumer<? super T> action); // 执行 Consumer 对象指定的操作
     * }
     * </pre>
     * <p>
     * 更多可参考：<a href=https://docs.oracle.com/javase/tutorial/collections/streams/index.html>
     *     https://docs.oracle.com/javase/tutorial/collections/streams/index.html</a>
     * </p>
     */
    @Test
    public void streamTest() {
        roster.stream()
                .filter(p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25)
                .map(p -> p.getGender())
                .forEach(gender -> System.out.println(gender));
    }

}
