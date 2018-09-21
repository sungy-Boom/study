package com.daily.learn.json.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * json工具类
 */
public class JsonUtil {

    private static final Logger log = Logger.getLogger("JsonUtil");

    public static final String DATEFORMAT_ISO8601 = "yyyy-MM-dd HH:mm:ss";
    public static final String DATEFORMAT_UTC = "yyyy-MM-dd'T'HH:mm:ss'+08:00'";
    static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.setDateFormat(new SimpleDateFormat(DATEFORMAT_UTC));
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    }

    /**
     * JSON转Map对象
     *
     * @param json 字符串
     * @return map对象
     */
    public static Map<String, Object> jsonToMap(String json) {
        Map<String, Object> map = null;
        try {
            map = mapper.readValue(json, Map.class);
        } catch (IOException e) {
            log.info("jsonToMap is error.json is:  " + json);
            log.info("jsonToMap is error.error is:{} " + e.getMessage());
            return null;
        }
        return map;
    }

    /**
     * 对象转JSON字符串
     *
     * @param object
     * @return
     */
    public static String ObjectToJson(Object object) {
        if (object == null) {
            return null;
        }
        if (object.getClass() == String.class) {
            return object.toString();
        }
        String json = null;
        try {
            json = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.info("ObjectToJson is error.error is: " + e.getMessage());
            return null;
        }
        return json;
    }

    /**
     * JSON转List
     *
     * @param json
     * @return
     */
    public static List<Map<String, Object>> jsonToList(String json) {
        List<Map<String, Object>> list = null;
        try {
            list = mapper.readValue(json, List.class);
        } catch (Exception e) {
            log.info("jsonToList is error.json is: " + json);
            log.info("jsonToList is error.error is: " + e.getMessage());
            return null;
        }
        return list;
    }

    /**
     * json转实体对象
     *
     * @param content   json对象
     * @param classType 实体类
     * @param <T>
     * @return
     */
    public static <T> T jsonToEntity(String content, Class<T> classType) {
        if (!jsonCheck(content)) {
            return null;
        }
        if (classType == String.class) {
            return (T) content;
        }
        try {
            return mapper.readValue(content, classType);
        } catch (IOException e) {
            log.info("jsonToEntity is error.json is: " + content);
            log.info("jsonToEntity error msg : " + e.getMessage());
            return null;
        }
    }

    /**
     * json转实体对象
     *
     * @param content      json
     * @param valueTypeRef 实体类
     * @param <T>
     * @return
     */
    public static <T> T jsonToEntity(String content, TypeReference<T> valueTypeRef) {
        if (!jsonCheck(content)) {
            return null;
        }
        try {
            return (T) mapper.readValue(content, valueTypeRef);
        } catch (IOException e) {
            log.info("jsonToEntity is error.json is: " + content);
            log.info("jsonToEntity error msg : " + e.getMessage());
            return null;
        }
    }

    private static boolean jsonCheck(String content) {
        if (content == null || content.equals("")) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String json = "[{\"CITY_ID\":1,\"LAG_REG_SUM\":0,\"OPEN_CLASS_COUNT\":0,\"STU_ON_READ_PEOPLE\":0,\"TEACHER_RETURN_RATIO\":0,\"CLA_TERM_ID\":\"1\",\"CONTINUE_RATIO_C\":0,\"CONTINUE_RATIO_B\":0,\"SUM_RETURN_REG\":0,\"CONTINUE_RATIO_A\":0,\"AFTER_RETURN_REG\":0,\"CITY_CODE\":\"010\",\"REG_SUM\":0,\"AFTER_RETURN_RATIO\":0,\"CONTINUE_COUNT_A\":0,\"CONTINUE_COUNT_C\":0,\"CONTINUE_COUNT_B\":0,\"LIMIT_STU_COUNT\":2,\"CLA_YEAR\":\"2016\",\"CITY_NAME\":\"北京\",\"STU_ON_READ_COUNT\":0,\"FULL_CLASS_RATIO\":0,\"JR_AFTER_RETURN_REG\":0,\"PRE_RETURN_RATIO\":0,\"PRE_RETURN_REG\":0,\"ALL_RETURN_RATIO\":0},{\"CLA_TERM_NAME\":\"2\",\"LAG_REG_SUM\":0,\"OPEN_CLASS_COUNT\":0,\"STU_ON_READ_PEOPLE\":0,\"TEACHER_RETURN_RATIO\":0,\"CLA_TERM_ID\":\"2\",\"CONTINUE_RATIO_C\":0,\"CONTINUE_RATIO_B\":0,\"SUM_RETURN_REG\":0,\"CONTINUE_RATIO_A\":0,\"AFTER_RETURN_REG\":0,\"CITY_CODE\":\"020\",\"REG_SUM\":0,\"AFTER_RETURN_RATIO\":0,\"CONTINUE_COUNT_A\":0,\"CONTINUE_COUNT_C\":0,\"CONTINUE_COUNT_B\":0,\"LIMIT_STU_COUNT\":0,\"CLA_YEAR\":\"2016\",\"CITY_NAME\":\"上海\",\"STU_ON_READ_COUNT\":0,\"FULL_CLASS_RATIO\":0,\"JR_AFTER_RETURN_REG\":0,\"PRE_RETURN_RATIO\":0,\"PRE_RETURN_REG\":0,\"ALL_RETURN_RATIO\":0}]";
        List<Map<String, Object>> list = jsonToList(json);
        System.out.println(list);
    }
}
