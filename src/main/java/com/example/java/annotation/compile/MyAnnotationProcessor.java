package com.example.java.annotation.compile;

/**
 * @author LingengMa
 * @date 2025/09/28 10:47
 * @Description:
 */


import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import java.util.Set;

@SupportedAnnotationTypes("com.example.java.annotation.compile.MyCompileTimeAnnotation")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class MyAnnotationProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(annotation);
            for (Element element : elements) {
                String message = element.getAnnotation(MyCompileTimeAnnotation.class).message();
                System.out.println("====================================");
                System.out.println("找到注解：" + element.getSimpleName());
                System.out.println("注解消息：" + message);
                System.out.println("====================================");
                
                // 可以通过Messager输出更可靠的信息
                processingEnv.getMessager().printMessage(
                    javax.tools.Diagnostic.Kind.NOTE,
                    "处理了注解：" + message,
                    element
                );
            }
        }
        return true;
    }
}