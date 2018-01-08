package com.daily.learn.read;

import com.alibaba.fastjson.JSONObject;
import jdk.nashorn.internal.parser.JSONParser;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by SunGuiyong
 * on 2017/12/21.
 */
public class ReadJsonFile {

    public static JSONObject readFromCongigFile() {
        String fileName = "talmonitor.json";
        StringBuffer sb = new StringBuffer();
        try {
/*            BufferedInputStream bufferedInputStream =
                    (BufferedInputStream) Resources.getResource(fileName).getContent();*/
            BufferedInputStream bufferedInputStream1 = new BufferedInputStream(
                    new BufferedInputStream(new FileInputStream(fileName)));
            byte[] bs = new byte[1024];
            while (bufferedInputStream1.read(bs) != -1) {
                String str = new String(bs);
                sb.append(new String(bs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String res = JSONParser.quote(sb.toString());
        Pattern CRLF = Pattern.compile("(\\\\r|\\\\t|\\\\n|\\\\u0000|\\\\)");
        Matcher m = CRLF.matcher(res);
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
