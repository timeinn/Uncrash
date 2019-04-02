package net.uncrash.logging.api;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Function;

public class AccessLoggerInfo implements Serializable {

    /**
     * 日志 id
     */
    private String id;

    /**
     * 访问操作
     */
    private String action;

    /**
     * 描述
     */
    private String describe;

    /**
     * 访问的 java 方法
     */
    private Method method;

    /**
     * 访问的 java 类
     */
    private Class target;

    /**
     * 请求的参数, 参数为 java 方法的参数而不是 http 参数
     * key 为参数名, value 为参数值.
     */
    private Map<String, Object> parameters;

    private String ip;

    private String url;

    private Map<String, String> httpHeaders;

    /**
     * http 请求方式 GET, POST, PUT...
     */
    private String httpMethod;

    private Object response;

    /**
     * 请求时间戳
     */
    private long requestTime;

    private long responseTime;

    private Throwable exception;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Class getTarget() {
        return target;
    }

    public void setTarget(Class target) {
        this.target = target;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getHttpHeaders() {
        return httpHeaders;
    }

    public void setHttpHeaders(Map<String, String> httpHeaders) {
        this.httpHeaders = httpHeaders;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }

    public Map<String, Object> toSimpleMap(Function<Object, Serializable> noSerialExchange) {
        return toSimpleMap(noSerialExchange, new LinkedHashMap<>());
    }

    public Map<String, Object> toSimpleMap(Function<Object, Serializable> objectFilter, Map<String, Object> map) {
        map.put("action", action);
        map.put("describe", describe);
        if (method != null) {
            StringJoiner methodAppender = new StringJoiner(",", method.getName(), "()");
            String[] parameterNames = parameters.keySet().toArray(new String[parameters.size()]);
            Class[] parameterTypes = method.getParameterTypes();

            for (int i = 0; i < parameterTypes.length; i++) {
                methodAppender.add(parameterTypes[i].getSimpleName().concat(" ").concat(parameterNames.length >= i ? parameterNames[i] : ("arg" + i)));
            }
            map.put("method", methodAppender.toString());
        }
        map.put("target", target != null ? target.getName() : "");
        Map<String, Object> parameter = new LinkedHashMap<>(parameters);
        parameter.entrySet().forEach(entry -> {
            if (entry.getValue() != null) {
                entry.setValue(objectFilter.apply(entry.getValue()));
            }
        });

        map.put("parameters", parameter);
        map.put("httpHeaders", httpHeaders);
        map.put("httpMethod", httpMethod);
        map.put("ip", ip);
        map.put("url", url);
        map.put("response", objectFilter.apply(response));
        map.put("requestTime", requestTime);
        map.put("responseTime", responseTime);
        map.put("id", id);
        map.put("useTime", responseTime - requestTime);

        if (exception != null) {
            StringWriter writer = new StringWriter();
            exception.printStackTrace(new PrintWriter(writer));
            map.put("exception", writer.toString());
        }

        return map;
    }
}
