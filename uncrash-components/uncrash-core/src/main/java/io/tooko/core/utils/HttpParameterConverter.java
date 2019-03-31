package io.tooko.core.utils;

import io.tooko.core.utils.time.DateFormatter;
import org.apache.commons.beanutils.BeanMap;

import java.util.*;
import java.util.function.Function;

public class HttpParameterConverter {

    private Map<String, Object> beanMap;

    private Map<String, String> parameter = new HashMap<>();

    private String prefix = "";

    private static final Map<Class, Function<Object, String>> CONVERT_MAP = new HashMap<>();

    private static Function<Object, String> defaultConvert = String::valueOf;

    private static final Set<Class> BASIC_CLASS = new HashSet<>();

    static {
        BASIC_CLASS.add(int.class);
        BASIC_CLASS.add(double.class);
        BASIC_CLASS.add(float.class);
        BASIC_CLASS.add(byte.class);
        BASIC_CLASS.add(short.class);
        BASIC_CLASS.add(char.class);
        BASIC_CLASS.add(boolean.class);

        BASIC_CLASS.add(Integer.class);
        BASIC_CLASS.add(Double.class);
        BASIC_CLASS.add(Float.class);
        BASIC_CLASS.add(Byte.class);
        BASIC_CLASS.add(Short.class);
        BASIC_CLASS.add(Character.class);
        BASIC_CLASS.add(String.class);
        BASIC_CLASS.add(Boolean.class);

        BASIC_CLASS.add(Date.class);


        putConvert(Date.class, (date) -> DateFormatter.toString(date, "yyyy-MM-dd HH:mm:ss"));


    }

    @SuppressWarnings("unchecked")
    private static <T> void putConvert(Class<T> type, Function<T, String> convert) {
        CONVERT_MAP.put(type, (Function) convert);

    }

    private String convertValue(Object value) {
        return CONVERT_MAP.getOrDefault(value.getClass(), defaultConvert).apply(value);
    }

    @SuppressWarnings("unchecked")
    public HttpParameterConverter(Object bean) {
        if (bean instanceof Map) {
            beanMap = ((Map) bean);
        } else {
            beanMap = new HashMap<>((Map) new BeanMap(bean));
            beanMap.remove("class");
            beanMap.remove("declaringClass");
        }
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    private void doConvert(String key, Object value) {
        if (value == null) {
            return;
        }
        if(value instanceof Class){
            return;
        }
        Class type = org.springframework.util.ClassUtils.getUserClass(value);

        if (BASIC_CLASS.contains(type) || value instanceof Number || value instanceof Enum) {
            parameter.put(getParameterKey(key), convertValue(value));
            return;
        }

        if (value instanceof Object[]) {
            value = Arrays.asList(((Object[]) value));
        }

        if (value instanceof Collection) {
            Collection coll = ((Collection) value);
            int count = 0;
            for (Object o : coll) {
                doConvert(key + "[" + count++ + "]", o);
            }
        } else {
            HttpParameterConverter converter = new HttpParameterConverter(value);
            converter.setPrefix(getParameterKey(key).concat("."));
            parameter.putAll(converter.convert());
        }
    }

    private void doConvert() {
        beanMap.forEach(this::doConvert);
    }


    private String getParameterKey(String property) {
        return prefix.concat(property);
    }

    public Map<String, String> convert() {
        doConvert();

        return parameter;
    }

}
