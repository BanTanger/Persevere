package com.bantanger.codegen.processor.dto;

import com.bantanger.codegen.processor.BaseCodeGenProcessor;
import com.bantanger.codegen.spi.CodeGenProcessor;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;
import java.lang.annotation.Annotation;
import java.util.Objects;
import java.util.Set;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import lombok.Getter;
import lombok.Setter;


/**
 * @author gim vo 代码生成器
 */
@AutoService(value = CodeGenProcessor.class)
public class DtoCodeGenProcessor extends BaseCodeGenProcessor {

  public static final String SUFFIX = "DTO";

  @Override
  public Class<? extends Annotation> getAnnotation() {
    return GenDto.class;
  }

  @Override
  public String generatePackage(TypeElement typeElement) {
    return typeElement.getAnnotation(GenDto.class).pkgName();
  }

  @Override
  protected void generateClass(TypeElement typeElement, RoundEnvironment roundEnvironment) {
    // 获取没有打上 @IgnoreDto 注解的成员变量
    Set<VariableElement> fields = findFields(typeElement,
        ve -> Objects.isNull(ve.getAnnotation(IgnoreDto.class)));
    String className = PREFIX + typeElement.getSimpleName() + SUFFIX;
    String sourceClassName = typeElement.getSimpleName() + SUFFIX;
    Builder builder = TypeSpec.classBuilder(className)
//        .superclass(AbstractBaseJpaVO.class)
        .addModifiers(Modifier.PUBLIC)
//        .addAnnotation(Schema.class)
        .addAnnotation(Getter.class)
        .addAnnotation(Setter.class);
    addSetterAndGetterMethod(builder, fields);
    // 生成构造方法，指定参数名和访问类型
    MethodSpec.Builder constructorSpecBuilder = MethodSpec.constructorBuilder()
        .addParameter(TypeName.get(typeElement.asType()), "source")
        .addModifiers(Modifier.PUBLIC);
    // 生成构造方法的代码语句
    constructorSpecBuilder.addStatement("super(source)");
    fields.forEach(f -> {
      // $L 占位符，会替换为字段名
      constructorSpecBuilder.addStatement("this.set$L(source.get$L())", getFieldDefaultName(f),
          getFieldDefaultName(f));
    });
    builder.addMethod(MethodSpec.constructorBuilder()
        .addModifiers(Modifier.PROTECTED)
        .build());
    builder.addMethod(constructorSpecBuilder.build());
    String packageName = generatePackage(typeElement);
    // 生成代码到 target 目录
//    genJavaFile(packageName, builder);
//    genJavaFile(packageName, getSourceTypeWithConstruct(typeElement,sourceClassName, packageName, className));

    // 生成代码到 Java 目录下
    genJavaSourceFile(packageName,
        typeElement.getAnnotation(GenDto.class).sourcePath(), builder);
  }
}
