package com.bantanger.lambda;

/**
 * @author chensongmin
 * @description
 * <p>the checkPerson interface is a functional interface</p>
 * <pre>
 * what is a functional interface?
 * A functional interface is any interface that contains only one abstract method.
 * and may contain one or more default methods or static methods.
 * </pre>
 * @create 2025/1/1
 */
public interface CheckPerson {

    boolean test(Person p);

}
