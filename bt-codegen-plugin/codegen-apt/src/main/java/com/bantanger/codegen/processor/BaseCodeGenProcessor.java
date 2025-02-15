package com.bantanger.codegen.processor;

import com.bantanger.codegen.context.ProcessingEnvironmentHolder;
import com.bantanger.codegen.processor.api.GenCreateRequest;
import com.bantanger.codegen.processor.api.GenCreateRequestProcessor;
import com.bantanger.codegen.processor.api.GenFeign;
import com.bantanger.codegen.processor.api.GenFeignProcessor;
import com.bantanger.codegen.processor.api.GenQueryRequest;
import com.bantanger.codegen.processor.api.GenQueryRequestProcessor;
import com.bantanger.codegen.processor.api.GenResponse;
import com.bantanger.codegen.processor.api.GenResponseProcessor;
import com.bantanger.codegen.processor.api.GenUpdateRequest;
import com.bantanger.codegen.processor.api.GenUpdateRequestProcessor;
import com.bantanger.codegen.processor.controller.GenController;
import com.bantanger.codegen.processor.controller.GenControllerProcessor;
import com.bantanger.codegen.processor.creator.CreatorCodeGenProcessor;
import com.bantanger.codegen.processor.creator.GenCreator;
import com.bantanger.codegen.processor.mapper.GenMapper;
import com.bantanger.codegen.processor.mapper.GenMapperProcessor;
import com.bantanger.codegen.processor.query.GenQuery;
import com.bantanger.codegen.processor.query.GenQueryProcessor;
import com.bantanger.codegen.processor.repository.GenRepository;
import com.bantanger.codegen.processor.repository.GenRepositoryProcessor;
import com.bantanger.codegen.processor.service.GenService;
import com.bantanger.codegen.processor.service.GenServiceImpl;
import com.bantanger.codegen.processor.service.GenServiceImplProcessor;
import com.bantanger.codegen.processor.service.GenServiceProcessor;
import com.bantanger.codegen.processor.updater.GenUpdater;
import com.bantanger.codegen.processor.updater.GenUpdaterProcessor;
import com.bantanger.codegen.processor.vo.GenVo;
import com.bantanger.codegen.processor.vo.VoCodeGenProcessor;
import com.bantanger.codegen.spi.CodeGenProcessor;
import com.bantanger.common.annotation.FieldDesc;
import com.bantanger.common.annotation.TypeConverter;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic.Kind;
import lombok.Data;

/**
 * @author chensongmin
 * @description 代码生成处理器基类
 * @date 2025/1/7
 */
@SuppressWarnings("all")
public abstract class BaseCodeGenProcessor implements CodeGenProcessor {

    public static final String PREFIX = "Base";
    public static final String FILE_COMMENT = "--- Auto Generated by BanTanger ---";

    @Override
    public void generate(TypeElement typeElement, RoundEnvironment roundEnvironment)
        throws Exception {
        //添加其他逻辑扩展
        generateClass(typeElement, roundEnvironment);
    }

    /**
     * 生成 class 类
     *
     * @param typeElement
     * @param roundEnvironment
     * @return
     */
    protected abstract void generateClass(TypeElement typeElement,
        RoundEnvironment roundEnvironment);

    /**
     * 过滤目标类对象或接口对象的属性
     *
     * @param typeElement 目标类对象或接口对象
     * @param predicate   过滤条件
     * @return
     */
    public Set<VariableElement> findFields(TypeElement typeElement,
        Predicate<VariableElement> predicate) {
        // 相当于获取 花括号 {} 内的所有元素信息，成员变量或方法
        List<? extends Element> fieldTypes = typeElement.getEnclosedElements();
        Set<VariableElement> variableElements = new LinkedHashSet<>();
        // ElementFilter.fieldsIn 获取所有的成员变量
        for (VariableElement e : ElementFilter.fieldsIn(fieldTypes)) {
            if (predicate.test(e)) {
                variableElements.add(e);
            }
        }
        return variableElements;
    }

    /**
     * 获取名称默认上下文
     *
     * @param typeElement
     * @return
     */
    public DefaultNameContext getNameContext(TypeElement typeElement) {
        DefaultNameContext context = new DefaultNameContext();
        String serviceName = GenServiceProcessor.SERVICE_PREFIX + typeElement.getSimpleName()
            + GenServiceProcessor.SERVICE_SUFFIX;
        String implName = typeElement.getSimpleName() + GenServiceImplProcessor.IMPL_SUFFIX;
        String repositoryName =
            typeElement.getSimpleName() + GenRepositoryProcessor.REPOSITORY_SUFFIX;
        String mapperName = typeElement.getSimpleName() + GenMapperProcessor.SUFFIX;
        String voName = typeElement.getSimpleName() + VoCodeGenProcessor.SUFFIX;
        String queryName = typeElement.getSimpleName() + GenQueryProcessor.QUERY_SUFFIX;
        String creatorName = typeElement.getSimpleName() + CreatorCodeGenProcessor.SUFFIX;
        String updaterName = typeElement.getSimpleName() + GenUpdaterProcessor.SUFFIX;
        String createRequestName =
            typeElement.getSimpleName() + GenCreateRequestProcessor.CREATE_REQUEST_SUFFIX;
        String updateRequestName =
            typeElement.getSimpleName() + GenUpdateRequestProcessor.UPDATE_REQUEST_SUFFIX;
        String queryRequestName =
            typeElement.getSimpleName() + GenQueryRequestProcessor.QUERY_REQUEST_SUFFIX;
        String responseName = typeElement.getSimpleName() + GenResponseProcessor.RESPONSE_SUFFIX;
        String feignName = typeElement.getSimpleName() + GenFeignProcessor.FEIGN_SUFFIX;
        String controllerName =
            typeElement.getSimpleName() + GenControllerProcessor.CONTROLLER_SUFFIX;
        context.setServiceClassName(serviceName);
        context.setRepositoryClassName(repositoryName);
        context.setMapperClassName(mapperName);
        context.setVoClassName(voName);
        context.setQueryClassName(queryName);
        context.setCreatorClassName(creatorName);
        context.setUpdaterClassName(updaterName);
        context.setImplClassName(implName);
        context.setCreateClassName(createRequestName);
        context.setUpdateClassName(updateRequestName);
        context.setQueryRequestClassName(queryRequestName);
        context.setResponseClassName(responseName);
        context.setFeignClassName(feignName);
        context.setControllerClassName(controllerName);
        Optional.ofNullable(typeElement.getAnnotation(GenCreator.class)).ifPresent(anno -> {
            context.setCreatorPackageName(anno.pkgName());
        });
        Optional.ofNullable(typeElement.getAnnotation(GenUpdater.class)).ifPresent(anno -> {
            context.setUpdaterPackageName(anno.pkgName());
        });
        Optional.ofNullable(typeElement.getAnnotation(GenQuery.class)).ifPresent(anno -> {
            context.setQueryPackageName(anno.pkgName());
        });
        Optional.ofNullable(typeElement.getAnnotation(GenVo.class)).ifPresent(anno -> {
            context.setVoPackageName(anno.pkgName());
        });
        Optional.ofNullable(typeElement.getAnnotation(GenRepository.class)).ifPresent(anno -> {
            context.setRepositoryPackageName(anno.pkgName());
        });
        Optional.ofNullable(typeElement.getAnnotation(GenMapper.class)).ifPresent(anno -> {
            context.setMapperPackageName(anno.pkgName());
        });
        Optional.ofNullable(typeElement.getAnnotation(GenService.class)).ifPresent(anno -> {
            context.setServicePackageName(anno.pkgName());
        });
        Optional.ofNullable(typeElement.getAnnotation(GenServiceImpl.class)).ifPresent(anno -> {
            context.setImplPackageName(anno.pkgName());
        });
        Optional.ofNullable(typeElement.getAnnotation(GenCreateRequest.class)).ifPresent(anno -> {
            context.setCreatePackageName(anno.pkgName());
        });
        Optional.ofNullable(typeElement.getAnnotation(GenUpdateRequest.class)).ifPresent(anno -> {
            context.setUpdatePackageName(anno.pkgName());
        });
        Optional.ofNullable(typeElement.getAnnotation(GenQueryRequest.class)).ifPresent(anno -> {
            context.setQueryRequestPackageName(anno.pkgName());
        });
        Optional.ofNullable(typeElement.getAnnotation(GenResponse.class)).ifPresent(anno -> {
            context.setResponsePackageName(anno.pkgName());
        });
        Optional.ofNullable(typeElement.getAnnotation(GenFeign.class)).ifPresent(anno -> {
            context.setFeignPackageName(anno.pkgName());
        });
        Optional.ofNullable(typeElement.getAnnotation(GenController.class)).ifPresent(anno -> {
            context.setControllerPackageName(anno.pkgName());
        });
        return context;
    }


    /**
     * 获取父类
     *
     * @param element
     * @return
     */
    public TypeElement getSuperClass(TypeElement element) {
        TypeMirror parent = element.getSuperclass();
        if (parent instanceof DeclaredType) {
            Element elt = ((DeclaredType) parent).asElement();
            if (elt instanceof TypeElement) {
                return (TypeElement) elt;
            }
        }
        return null;
    }

    public void addSetterAndGetterMethod(TypeSpec.Builder builder,
        Set<VariableElement> variableElements) {
        for (VariableElement ve : variableElements) {
            TypeName typeName = TypeName.get(ve.asType());
            FieldSpec.Builder fieldSpec = FieldSpec
                .builder(typeName, ve.getSimpleName().toString(), Modifier.PRIVATE)
                .addAnnotation(AnnotationSpec.builder(Schema.class)
                    .addMember("title", "$S", getFieldDesc(ve))
                    .build());
            builder.addField(fieldSpec.build());
            String fieldName = getFieldDefaultName(ve);
            MethodSpec.Builder getMethod = MethodSpec.methodBuilder("get" + fieldName)
                .returns(typeName)
                .addModifiers(Modifier.PUBLIC)
                .addStatement("return $L", ve.getSimpleName().toString());
            MethodSpec.Builder setMethod = MethodSpec.methodBuilder("set" + fieldName)
                .returns(void.class)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(typeName, ve.getSimpleName().toString())
                .addStatement("this.$L = $L", ve.getSimpleName().toString(),
                    ve.getSimpleName().toString());
            builder.addMethod(getMethod.build());
            builder.addMethod(setMethod.build());
        }
    }

    /**
     * 应用转化器
     *
     * @param builder
     * @param variableElements
     */
    public void addSetterAndGetterMethodWithConverter(TypeSpec.Builder builder,
        Set<VariableElement> variableElements) {
        for (VariableElement ve : variableElements) {
            TypeName typeName;
            if (Objects.nonNull(ve.getAnnotation(TypeConverter.class))) {
                //这里处理下泛型的情况，比如List<String> 这种，TypeConverter FullName 用逗号分隔"java.lang.List
                String fullName = ve.getAnnotation(TypeConverter.class).toTypeFullName();
                Iterable<String> classes = Splitter.on(",").split(fullName);
                int size = Iterables.size(classes);
                if (size > 1) {
                    //泛型生成像这样
                    //ParameterizedTypeName.get(ClassName.get(JsonObject.class), ClassName.get(String.class))
                    typeName = ParameterizedTypeName.get(
                        ClassName.bestGuess(Iterables.get(classes, 0)),
                        ClassName.bestGuess(Iterables.get(classes, 1)));
                } else {
                    typeName = ClassName.bestGuess(
                        ve.getAnnotation(TypeConverter.class).toTypeFullName());
                }
            } else {
                typeName = TypeName.get(ve.asType());
            }
            FieldSpec.Builder fieldSpec = FieldSpec
                .builder(typeName, ve.getSimpleName().toString(), Modifier.PRIVATE)
                .addAnnotation(AnnotationSpec.builder(Schema.class)
                    .addMember("title", "$S", getFieldDesc(ve))
                    .build());
            builder.addField(fieldSpec.build());
            String fieldName = getFieldDefaultName(ve);
            MethodSpec.Builder getMethod = MethodSpec.methodBuilder("get" + fieldName)
                .returns(typeName)
                .addModifiers(Modifier.PUBLIC)
                .addStatement("return $L", ve.getSimpleName().toString());
            MethodSpec.Builder setMethod = MethodSpec.methodBuilder("set" + fieldName)
                .returns(void.class)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(typeName, ve.getSimpleName().toString())
                .addStatement("this.$L = $L", ve.getSimpleName().toString(),
                    ve.getSimpleName().toString());
            builder.addMethod(getMethod.build());
            builder.addMethod(setMethod.build());
        }
    }


    protected void addIdSetterAndGetter(TypeSpec.Builder builder) {
        MethodSpec.Builder getMethod = MethodSpec.methodBuilder("getId")
            .returns(ClassName.get(Long.class))
            .addModifiers(Modifier.PUBLIC)
            .addStatement("return $L", "id");
        MethodSpec.Builder setMethod = MethodSpec.methodBuilder("setId")
            .returns(void.class)
            .addModifiers(Modifier.PUBLIC)
            .addParameter(TypeName.LONG, "id")
            .addStatement("this.$L = $L", "id", "id");
        builder.addMethod(getMethod.build());
        builder.addMethod(setMethod.build());
    }

    protected String getFieldDesc(VariableElement ve) {
        return Optional.ofNullable(ve.getAnnotation(FieldDesc.class))
            .map(s -> s.name()).orElse(ve.getSimpleName().toString());
    }

    protected String getFieldDefaultName(VariableElement ve) {
        return ve.getSimpleName().toString().substring(0, 1).toUpperCase() + ve.getSimpleName()
            .toString().substring(1);
    }

    /**
     * 生成 java 源文件到指定目录下
     *
     * @param packageName     包名
     * @param pathStr         生成文件的目录
     * @param typeSpecBuilder 类型构建器
     */
    public void genJavaSourceFile(String packageName, String pathStr,
        TypeSpec.Builder typeSpecBuilder) {
        TypeSpec typeSpec = typeSpecBuilder.build();
        JavaFile javaFile = JavaFile
            .builder(packageName, typeSpec)
            .indent("   ")
            .addFileComment(FILE_COMMENT)
            .build();
        String packagePath =
            packageName.replace(".", File.separator) + File.separator + typeSpec.name + ".java";
        try {
            Path path = Paths.get(pathStr);
            File file = new File(path.toFile().getAbsolutePath());
            if (!file.exists()) {
                return;
            }
            String sourceFileName = path.toFile().getAbsolutePath() + File.separator + packagePath;
            File sourceFile = new File(sourceFileName);
            if (!sourceFile.exists()) {
                javaFile.writeTo(file);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public TypeSpec.Builder getSourceType(String sourceName, String packageName,
        String superClassName) {
        return TypeSpec.classBuilder(sourceName)
            .superclass(ClassName.get(packageName, superClassName))
            .addModifiers(Modifier.PUBLIC)
            .addAnnotation(Schema.class)
            .addAnnotation(Data.class);
    }

    public TypeSpec.Builder getSourceTypeWithConstruct(TypeElement e, String sourceName,
        String packageName, String superClassName) {
        MethodSpec.Builder constructorSpecBuilder = MethodSpec.constructorBuilder()
            .addParameter(TypeName.get(e.asType()), "source")
            .addModifiers(Modifier.PUBLIC);
        constructorSpecBuilder.addStatement("super(source)");
        return TypeSpec.classBuilder(sourceName)
            .superclass(ClassName.get(packageName, superClassName))
            .addModifiers(Modifier.PUBLIC)
            .addMethod(MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .build())
            .addMethod(constructorSpecBuilder.build())
            .addAnnotation(Schema.class)
            .addAnnotation(Data.class);
    }

    /**
     * 生成 java 文件到 target 目录
     *
     * @param packageName     包名
     * @param typeSpecBuilder 类型构建器
     */
    protected void genJavaFile(String packageName, TypeSpec.Builder typeSpecBuilder) {
        JavaFile javaFile = JavaFile.builder(packageName, typeSpecBuilder.build())
            .addFileComment(FILE_COMMENT)
            .indent("   ")
            .build();
        try {
            javaFile.writeTo(ProcessingEnvironmentHolder.getEnvironment().getFiler());
        } catch (IOException e) {
            ProcessingEnvironmentHolder.getEnvironment().getMessager()
                .printMessage(Kind.ERROR, e.getMessage());
        }
    }

}
