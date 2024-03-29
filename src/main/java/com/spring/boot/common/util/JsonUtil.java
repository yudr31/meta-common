package com.spring.boot.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.common.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonUtil {

    private static Logger LOG = LoggerFactory.getLogger(JsonUtil.class);

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.getSerializationConfig().with(df);
        mapper.getDeserializationConfig().with(df);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * object为null时，返回字符串”null“；为空Map时，返回字符串”{}“；为空集合和数组时，返回”[]“
     * 
     * @param object
     * @return
     */
    public static String toString(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOG.error("json 格式转换错误！", e);
            throw new BaseException("json 格式转换错误！", e.toString());
        }
    }

    /**
     * object为null时，返回字符串”null“；为空Map时，返回字符串”{}“；为空集合和数组时，返回”[]“
     * 
     * @param object
     * @return
     */
    public static String toPrettyString(Object object) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOG.error("json 格式转换错误！", e);
            throw new BaseException("json 格式转换错误！", e.toString());
        }
    }

    /**
     * jsonStr为null、”“或”null“时，返回null；为”{}“时，返回空Map。
     * 
     * @param jsonStr
     * @return
     */
    public static Map<String, Object> toMap(String jsonStr) {
        if (jsonStr == null || jsonStr.trim().length() == 0) {
            return null;
        }

        try {
            return mapper.readValue(jsonStr, new TypeReference<Map<String, Object>>() {
            });
        } catch (IOException e) {
            LOG.error("json 格式转换错误！", e);
            throw new BaseException("json 格式转换错误！", e.toString());
        }
    }

    /**
     * jsonStr为null、”“或”null“时，返回null；为”[]“时，返回空List。
     * 
     * @param jsonStr
     * @return
     */
    public static List<Map<String, Object>> toListMap(String jsonStr) {
        if (jsonStr == null || jsonStr.trim().length() == 0) {
            return null;
        }

        try {
            return mapper.readValue(jsonStr, new TypeReference<List<Map<String, Object>>>() {
            });
        } catch (IOException e) {
            LOG.error("json 格式转换错误！", e);
            throw new BaseException("json 格式转换错误！", e.toString());
        }
    }

    /**
     * jsonStr为null、”“或”null“时，返回null；为”{}“时，返回对象所有属性为null。
     * 
     * @param jsonStr
     * @param clazz
     * @return
     */
    public static <T> T toObject(String jsonStr, Class<T> clazz) {
        if (jsonStr == null || jsonStr.trim().length() == 0) {
            return null;
        }

        try {
            return mapper.readValue(jsonStr, clazz);
        } catch (IOException e) {
            LOG.error("json 格式转换错误！", e);
            throw new BaseException("json 格式转换错误！", e.toString());
        }
    }

    /**
     * jsonStr为null、”“或”null“时，返回null；为”[]“时，返回空List。
     * 
     * @param jsonStr
     * @param clazz
     * @return
     */
    @SuppressWarnings("deprecation")
    public static <T> List<T> toListObject(String jsonStr, Class<?> clazz) {
        if (jsonStr == null || jsonStr.trim().length() == 0) {
            return null;
        }

        try {
            return mapper.readValue(jsonStr,
                    mapper.getTypeFactory().constructParametrizedType(ArrayList.class, List.class, clazz));
        } catch (Exception e) {
            LOG.error("json 格式转换错误！", e);
            throw new BaseException("json 格式转换错误！", e.toString());
        }
    }
}
