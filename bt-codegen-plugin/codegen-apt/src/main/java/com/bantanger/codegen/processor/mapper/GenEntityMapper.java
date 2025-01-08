package com.bantanger.codegen.processor.mapper;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/8
 */
public @interface GenEntityMapper {

    String pkgName();

    String sourcePath() default "src/main/java";

    boolean overrideSource() default false;

}
