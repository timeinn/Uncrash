package io.tooko.core.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

@ApiModel(description = "响应结果")
public class ResponseMessage<T> implements Serializable {

    private static final long serialVersionUID = 6978192400980412769L;

    protected String message = "";

    protected T result;

    protected int status;

    protected Long timestamp;

    private String code;

    @ApiModelProperty(value = "调用结果消息", name = "message")
    public String getMessage() {
        return message;
    }

    @ApiModelProperty(value = "状态码", required = true, name = "status")
    public int getStatus() {
        return status;
    }

    @ApiModelProperty(value = "成功时响应数据", name = "result")
    public T getResult() {
        return result;
    }

    @ApiModelProperty(value = "时间戳", required = true, dataType = "Long")
    public Long getTimestamp() {
        return timestamp;
    }

    @ApiModelProperty(value = "业务代码", dataType = "String")
    public String getCode() {
        return code;
    }

    public static <T> ResponseMessage<T> error(String message) {
        return error(500, message);
    }

    public static <T> ResponseMessage<T> error(int status) {
        return error(status, null);
    }

    public static <T> ResponseMessage<T> error(int status, String message) {
        ResponseMessage<T> response = new ResponseMessage<T>();
        response.setMessage(message);
        response.setStatus(status);
        return response.putTimeStamp();
    }

    public static <T> ResponseMessage<T> ok() {
        return ok(null);
    }

    public static <T> ResponseMessage<T> ok(T result) {
        return new ResponseMessage<T>()
            .result(result)
            .status(200)
            .putTimeStamp();
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>(5);
        map.put("result", this.getResult());
        map.put("message", this.getMessage());
        map.put("status", this.getStatus());
        if (map.containsKey("code")) {
            map.put("code", this.getCode());
        }
        map.put("timestamp", this.getTimestamp());
        return map;
    }

    public ResponseMessage<T> result(T result) {
        this.result = result;
        return this;
    }

    public ResponseMessage<T> status(int status) {
        this.status = status;
        return this;
    }

    private ResponseMessage<T> putTimeStamp() {
        this.timestamp = System.currentTimeMillis();
        return this;
    }

    @JsonIgnore
    private transient Map<Class<?>, Set<String>> includes;

    @JsonIgnore
    private transient Map<Class<?>, Set<String>> excludes;

    public ResponseMessage include(Class<?> type, String... fields) {
        return include(type, Arrays.asList(fields));
    }

    public ResponseMessage exclude(Class type, String... fields) {
        return exclude(type, Arrays.asList(fields));
    }

    public ResponseMessage include(Class<?> type, Collection<String> fields) {
        if (includes == null) {
            includes = new HashMap<>();
        }
        if (fields == null || fields.isEmpty()) {
            return this;
        }
        fields.forEach(field -> {
            if (field.contains(".")) {
                String[] tmp = field.split("[.]", 2);
                try {
                    Field field1 = type.getDeclaredField(tmp[0]);
                    if (field1 != null) {
                        include(field1.getType(), tmp[1]);
                    }
                } catch (Throwable e) {
                }
            } else {
                getStringListFormMap(includes, type).add(field);
            }
        });
        return this;
    }

    public ResponseMessage exclude(Class type, Collection<String> fields) {
        if (excludes == null) {
            excludes = new HashMap<>();
        }
        if (fields == null || fields.isEmpty()) {
            return this;
        }
        fields.forEach(field -> {
            if (field.contains(".")) {
                String[] tmp = field.split("[.]", 2);
                try {
                    Field field1 = type.getDeclaredField(tmp[0]);
                    if (field1 != null) {
                        exclude(field1.getType(), tmp[1]);
                    }
                } catch (Throwable e) {
                }
            } else {
                getStringListFormMap(excludes, type).add(field);
            }
        });
        return this;
    }

    public ResponseMessage exclude(Collection<String> fields) {
        if (excludes == null) {
            excludes = new HashMap<>();
        }
        if (fields == null || fields.isEmpty()) {
            return this;
        }
        Class type;
        if (result != null) {
            type = result.getClass();
        } else {
            return this;
        }
        exclude(type, fields);
        return this;
    }

    public ResponseMessage include(Collection<String> fields) {
        if (includes == null) {
            includes = new HashMap<>();
        }
        if (fields == null || fields.isEmpty()) {
            return this;
        }
        Class type;
        if (result != null) {
            type = result.getClass();
        } else {
            return this;
        }
        include(type, fields);
        return this;
    }

    public ResponseMessage include(String... fields) {
        return include(Arrays.asList(fields));
    }

    public ResponseMessage exclude(String... fields) {
        return exclude(Arrays.asList(fields));
    }

    protected Set<String> getStringListFormMap(Map<Class<?>, Set<String>> map, Class type) {
        Set<String> list = map.get(type);
        if (list == null) {
            list = new HashSet<>();
            map.put(type, list);
        }
        return list;
    }

    public ResponseMessage() {
    }

    public ResponseMessage<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public ResponseMessage<T> setStatus(int status) {
        this.status = status;
        return this;
    }

    public ResponseMessage<T> setResult(T result) {
        this.result = result;
        return this;
    }

    public ResponseMessage<T> setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public ResponseMessage<T> setCode(String code) {
        this.code = code;
        return this;
    }

    @ApiModelProperty(hidden = true)
    public Map<Class<?>, Set<String>> getExcludes() {
        return excludes;
    }

    @ApiModelProperty(hidden = true)
    public Map<Class<?>, Set<String>> getIncludes() {
        return includes;
    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
            "message='" + message + '\'' +
            ", result=" + result +
            ", status=" + status +
            ", timestamp=" + timestamp +
            ", code='" + code + '\'' +
            '}';
    }
}
