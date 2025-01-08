package com.bantanger.codegen.processor.mapper;

import com.bantanger.codegen.processor.BaseCodeGenProcessor;
import com.bantanger.codegen.processor.DefaultNameContext;
import com.bantanger.codegen.spi.CodeGenProcessor;
import com.bantanger.codegen.utils.StringUtils;
import com.bantanger.common.mapper.DateMapper;
import com.bantanger.common.mapper.GenericEnumMapper;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import java.lang.annotation.Annotation;
import java.util.Optional;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/8
 */
@AutoService(value = CodeGenProcessor.class)
public class GenEntityMapperProcessor extends BaseCodeGenProcessor {

    public static final String SUFFIX = "EntityMapper";

    @Override
    protected void generateClass(TypeElement typeElement, RoundEnvironment roundEnvironment) {
        String className = typeElement.getSimpleName() + SUFFIX;
        String packageName = typeElement.getAnnotation(GenEntityMapper.class).pkgName();
        AnnotationSpec mapperAnnotation = AnnotationSpec.builder(Mapper.class)
            .addMember("uses", "$T.class", GenericEnumMapper.class)
            .addMember("uses", "$T.class", DateMapper.class)
            .build();
        TypeSpec.Builder typeSpecBuilder = TypeSpec.interfaceBuilder(className)
            .addAnnotation(mapperAnnotation)
            .addModifiers(Modifier.PUBLIC);
        FieldSpec instance = FieldSpec
            .builder(ClassName.get(packageName, className), "INSTANCE")
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
            .initializer("$T.getMapper($T.class)",
                Mappers.class, ClassName.get(packageName, className))
            .build();
        typeSpecBuilder.addField(instance);
        DefaultNameContext nameContext = getNameContext(typeElement);
        Optional<MethodSpec> dtoToEntityMethod = dtoToEntityMethod(typeElement, nameContext);
        dtoToEntityMethod.ifPresent(typeSpecBuilder::addMethod);
        genJavaSourceFile(generatePackage(typeElement),
            typeElement.getAnnotation(GenEntityMapper.class).sourcePath(), typeSpecBuilder);
    }


    @Override
    public Class<? extends Annotation> getAnnotation() {
        return GenEntityMapper.class;
    }

    @Override
    public String generatePackage(TypeElement typeElement) {
        return typeElement.getAnnotation(GenEntityMapper.class).pkgName();
    }

    private Optional<MethodSpec> dtoToEntityMethod(TypeElement typeElement, DefaultNameContext nameContext) {
        boolean containsNull = StringUtils.containsNull(nameContext.getCreatorPackageName());
        if (!containsNull) {
            return Optional.of(MethodSpec
                .methodBuilder("dtoToEntity")
                .returns(ClassName.get(typeElement))
                .addParameter(
                    ClassName.get(nameContext.getCreatorPackageName(), nameContext.getCreatorClassName()),
                    "dto")
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                .build());
        }
        return Optional.empty();
    }

}
