package com.king.lib.frame_compiler;

import com.google.auto.service.AutoService;
import com.king.lib.frame_annotations.Loading;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
public class FrameProcessor extends AbstractProcessor {

    private Elements mElementUtils;

    private Filer mFiler;

    private Messager mMessager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mElementUtils = processingEnv.getElementUtils();
        mFiler = processingEnv.getFiler();
        mMessager = processingEnv.getMessager();
    }

    /**
     * 设置需要处理的注解类型
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes(){
        Set<String> supportedType = new LinkedHashSet<String>();
        supportedType.add(Loading.class.getCanonicalName());
        return supportedType;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> rsAnnotations = roundEnvironment.getElementsAnnotatedWith(Loading.class);
        mMessager.printMessage(Diagnostic.Kind.NOTE,"process");
        List<LoadingClass> loadingList = new ArrayList<>();
        for (Element element:rsAnnotations) {
            if (element.getKind() == ElementKind.CLASS) {
                TypeElement typeElement = (TypeElement) element;
                Loading annotation = typeElement.getAnnotation(Loading.class);
                String value = annotation.value();
                ClassName targetLoading = ClassName.get(mElementUtils.getPackageOf(typeElement).getQualifiedName().toString(), typeElement.getSimpleName().toString());
                loadingList.add(new LoadingClass(targetLoading, value));
            }
        }
        if (loadingList.size() > 0) {
            createProviderJava(loadingList);
        }
        return false;
    }

    private void createProviderJava(List<LoadingClass> list) {
        mMessager.printMessage(Diagnostic.Kind.NOTE,"createJavaFile");

        ClassName uiProvider = ClassName.get("com.king.lib.frame.annotations", "UiProvider");

        TypeSpec.Builder classBuilder = TypeSpec.classBuilder("CustomProvider")
                .addModifiers(Modifier.PUBLIC)
                .superclass(uiProvider);

        ClassName dialogFragment = ClassName.get("androidx.fragment.app", "DialogFragment");
        MethodSpec.Builder createLoadingBuilder = MethodSpec.methodBuilder("createLoading")
                .addModifiers(Modifier.PUBLIC).returns(dialogFragment)
                .addAnnotation(Override.class)
                .addParameter(String.class, "identity");
        for (int i = 0; i < list.size(); i ++) {
            String value = list.get(i).getIdentity();
            ClassName className = list.get(i).getClassName();
            String condition;
            if (i == 0) {
                condition = "if (identity.equals($S))";
            }
            else {
                condition = "else if (identity.equals($S))";
            }
            createLoadingBuilder.beginControlFlow(condition, value)
                    .addStatement("return new $T()", className)
                    .endControlFlow();
        }
        createLoadingBuilder.addStatement("return null");


        TypeSpec customProvider = classBuilder
                .addMethod(createLoadingBuilder.build())
                .build();

        JavaFile javaFile = JavaFile.builder("com.king.lib.frame", customProvider)
                .build();
        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}