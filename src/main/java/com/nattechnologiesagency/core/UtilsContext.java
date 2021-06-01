package com.nattechnologiesagency.core;

import com.nattechnologiesagency.core.anotations.GetRoute;
import com.nattechnologiesagency.core.schema.ContextPath;
import com.nattechnologiesagency.core.schema.Response;
import com.nattechnologiesagency.core.schema.Route;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UtilsContext {

    public static void readByClass(Class clazz, ContextPath contextPath) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        for (Method method : clazz.getDeclaredMethods()){

            for (Annotation annotation : method.getAnnotations()){
                if (annotation instanceof GetRoute){
                    Object instance = clazz.newInstance();
                    GetRoute getRoute = method.getAnnotation(GetRoute.class);

                    String data = (String) method.invoke(instance);

                    Route route = new Route();

                    route.setPath(getRoute.path());
                    route.setTypeFile(getRoute.typeFile());
                    route.setAnnotation(true);
                    route.setFile(data);

                    contextPath.getRoutes().add(route);
                }
            }
        }
        return;
    }
}
