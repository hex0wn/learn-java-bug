package org.example;

import com.alibaba.fastjson.JSON;
import  static org.reflections.ReflectionUtils.*;
import org.reflections.Reflections;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.io.Closeable;
import java.io.File;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.net.URL;
import java.rmi.server.UnicastRemoteObject;
import java.util.Set;
import java.util.*;
import java.sql.Date;
import org.springframework.scripting.ScriptSource;
import javax.lang.model.element.*;
import javax.lang.model.element.AnnotationValue;

public class Test {
    public static void main( String[] args ) throws Exception {

        getByReflection();
    }

    public static void getBySpring(){
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AssignableTypeFilter(AutoCloseable.class));

        // scan in org.example.package
        Set<BeanDefinition> components = provider.findCandidateComponents("java.rmi");
        for (BeanDefinition component : components)
        {
            System.out.println(component.getBeanClassName());
            //Class cls = Class.forName(component.getBeanClassName());
            // use class cls found
        }
    }

    public static void getByReflection() throws Exception{
        Reflections reflections = new Reflections(".*");
        //Set<Class<? extends OutputStream>> subTypes = reflections.getSubTypesOf(OutputStream.class);
        Set<Class<? extends Exception>> subTypes = reflections.getSubTypesOf(Exception.class);

        for (Class<? extends Exception> cls : subTypes) {
            // 检查黑名单
            if (Util.inBlackList(cls.getTypeName())) {
                continue;
            }

            Constructor[] constructors = cls.getConstructors();
            if (constructors.length > 0) {
                Set<Method> getters = getAllMethods(cls,

                        withModifier(Modifier.PUBLIC), withPrefix("get"), withParametersCount(0)/*,withReturnType(URL.class)*/);
                for (Method getter: getters){
                    Class x = getter.getReturnType();
                    if (!x.isPrimitive() && x!=String.class) {

                        System.out.println(getter.toGenericString());
                    }
                }
            }
            /*
            for (Constructor constructor : constructors) {
                Class<?>[] parameterTypes = constructor.getParameterTypes();

                for(Class<?> parameterType : parameterTypes){
                    // 检查构造函数的参数类型
                    if (parameterType== File.class || parameterType == URL.class|| parameterType == URI.class ||parameterType == String.class) {
                        // 检查getter方法
                        Set<Method> getters = getAllMethods(cls,

                                withModifier(Modifier.PUBLIC), withPrefix("get"));
                        if (!getters.isEmpty()) {
                            System.out.println(constructor.toGenericString());
                        }
                    }
                }
            }*/
        }
    }
}
