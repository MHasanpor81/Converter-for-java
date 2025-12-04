package util;


import java.lang.reflect.Method;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

public class Converter {
    public static <S, T> T convert(S source, T target, FieldMapper<S, T> mapper) {
        if (source == null) {
            throw new IllegalArgumentException("Source cannot be null");
        }

        if (target == null) {
            target = createInstance(mapper.getTargetClass());
        }

        Map<String, String> mapping = mapper.getSourceToTargetMapping();

        for (Method getter : getGetters(source.getClass())) {
            String propertyName = extractPropertyName(getter);
            if (propertyName == null) continue;
            try {
                Object value = getter.invoke(source);
                String targetFieldName = mapping.getOrDefault(propertyName, propertyName);
                Method setter = findSetter(target.getClass(), targetFieldName, getter.getReturnType());

                if (setter != null) {
                    setter.invoke(target, value);
                }
            } catch (Exception ignored) {
            }
        }

        return target;
    }
    private static Method[] getGetters(Class<?> clazz) {
        List<Method> getters = new ArrayList<>();
        for (Method m : clazz.getMethods()) {
            String name = m.getName();
            if (m.getParameterCount() != 0 || m.getReturnType() == void.class) {
                continue;
            }
            if ((name.startsWith("get") && name.length() > 3) ||
                    (name.startsWith("is") && name.length() > 2)) {
                getters.add(m);
            }
        }
        return getters.toArray(new Method[0]);
    }
    private static String extractPropertyName(Method getter) {
        String name = getter.getName();
        if (name.startsWith("get")) {
            name = name.substring(3);
        } else if (name.startsWith("is")) {
            name = name.substring(2);
        } else {
            return null;
        }
        if (name.length() == 0) return null;
        return Character.toLowerCase(name.charAt(0)) + name.substring(1);
    }
    private static Method findSetter(Class<?> clazz, String propertyName,Class<?> paramType){
        String setterName = "set" + Character.toUpperCase(propertyName.charAt(0)) + propertyName.substring(1);
        try{
            return clazz.getMethod(setterName,paramType);
        }catch(Exception e){
            return null;
        }
    }
    private static <T> T createInstance(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error for make new Class " + clazz.getSimpleName(), e);
        }
    }
}


