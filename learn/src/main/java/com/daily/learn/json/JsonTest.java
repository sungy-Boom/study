package com.daily.learn.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.daily.learn.json.entity.JsonEntity;
import com.daily.learn.json.entity.StuInfo;
import com.daily.learn.json.utils.HandleDateUtils;
import com.daily.learn.json.utils.JsonUtil;

import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by SunGuiyong
 * on 2018/9/21.
 */
public class JsonTest {

    private static String str = "{\n" +
            "  \"stuInfos\": [{\n" +
            "    \"rubrics\": [],\n" +
            "    \"clickTime\": 1537519705,\n" +
            "    \"studentId\": \"8d2ecb491a6e4537b34ea26cd171404a\",\n" +
            "    \"studentName\": \"张梓琪\",\n" +
            "    \"correctId\":\"\",\n" +
            "    \"correctTime\":\"(date类型)\"\n" +
            "  },\n" +
            "{\n" +
            "    \"rubrics\": [],\n" +
            "    \"clickTime\": 1537519762,\n" +
            "    \"studentId\": \"8d2ecb491a6e4537b34ea26cd171404a\",\n" +
            "    \"studentName\": \"张梓琪\",\n" +
            "    \"correctId\":\"\",\n" +
            "    \"correctTime\":\"(date类型)\"\n" +
            "  }],\n" +
            "  \"answeredList\":[\"id1\",\"id2\"]\n" +
            "}";

    public static void main(String[] args) throws ParseException {
        json2List();
        System.out.println();
        json2Entity();

    }

    private static void json2List(){
        JSONObject obj = JSON.parseObject(str);
        String array = obj.getString("stuInfos");

        List<LinkedHashMap<String, Object>> listStr = JsonUtil.jsonToEntity(array, List.class);
        System.out.println(listStr);
        System.out.println(listStr.get(0));

        sort(listStr);
        System.out.println(listStr.get(0));
    }

    private static void json2Entity(){
        StuInfo listStr = JsonUtil.jsonToEntity(str, StuInfo.class);
        System.out.println(JSON.toJSONString(listStr.getStuInfos().get(0)));

        sort2(listStr.getStuInfos());
        System.out.println(JSON.toJSONString(listStr.getStuInfos().get(0)));
    }

    private static void sort(List<LinkedHashMap<String, Object>> list) {
        Collections.sort(list, new Comparator<LinkedHashMap<String, Object>>() {
            @Override
            public int compare(LinkedHashMap<String, Object> o1, LinkedHashMap<String, Object> o2) {
                return HandleDateUtils.timestamp2Date((int) o1.get("clickTime")).compareTo(HandleDateUtils
                        .timestamp2Date((int) o2.get("clickTime")));
            }
        });
    }

    private static void sort2(List<JsonEntity> list) {
        Collections.sort(list, new Comparator<JsonEntity>() {
            @Override
            public int compare(JsonEntity o1, JsonEntity o2) {
                return HandleDateUtils.timestamp2Date(o1.getClickTime()).compareTo(HandleDateUtils
                        .timestamp2Date(o2.getClickTime()));
            }
        });
    }

}
