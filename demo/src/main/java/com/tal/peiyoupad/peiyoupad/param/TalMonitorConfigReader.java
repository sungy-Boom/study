package com.tal.peiyoupad.peiyoupad.param;

import com.alibaba.fastjson.JSONObject;
import jdk.nashorn.internal.parser.JSONParser;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TalMonitorConfigReader {

    /**
     * TalMonitor配置文件读取，配置文件默认名字：talmonitor.json
     * <p>
     * 只用 fastjson 数据解析出错，
     *
     * @author 牛冬
     * @date 2017年09月26日
     * @version 1.0
     */
    private static final Pattern RTN0 = Pattern.compile("(\\\\r|\\\\t|\\\\n|\\\\u0000|\\\\)");

    public static JSONObject readFromCongigFile() {
        String fileName = "D:\\self\\study\\talmonitor.json";
        StringBuffer sb = new StringBuffer();
        try {
            BufferedInputStream bufferedInputStream1 = new BufferedInputStream(
                    new BufferedInputStream(new FileInputStream(new File(fileName))));
            byte[] bs = new byte[1024];
            while (bufferedInputStream1.read(bs) != -1) {
                sb.append(new String(bs));
            }
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String res = JSONParser.quote(sb.toString());

        Matcher m = RTN0.matcher(res);
        if (m.find()) {
            res = m.replaceAll("");
        }
        System.out.println(res.substring(1, res.length() - 1));
        return JSONObject.parseObject(res.substring(1, res.length() - 1));
    }

    public static void main(String[] args) {
        System.out.println(readFromCongigFile().toJSONString());
    }

}
