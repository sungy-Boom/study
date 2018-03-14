package com.daily.learn.http_client;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by SunGuiyong
 * on 2018/3/7.
 */
public class HttpConnection {

    private static CountDownLatch latch = new CountDownLatch(10);
    private static ExecutorService service = Executors.newFixedThreadPool(10);

    private static String url = "http://dev.padht.speiyou.cn/peiyou-pad-mgmt/online-disk/create?";

    private static String url2 = "http://fz.padht.speiyou.cn/peiyou-pad-mgmt/online-disk/create?areaCode=10&parentId=5&nodeType=1&fileSize=0&curLevel=2&fileUrl=&nodeName=";
    private static String url2_ = "http://fz.padht.speiyou.cn/peiyou-pad-mgmt/online-disk/create?areaCode=10&parentId=5&nodeType=1&fileSize=0&curLevel=2&fileUrl=&nodeName=";
    private static String other = "&fileType=-1";

    private static String fileUrl = "";
    private static String fileUrl_id = "http://fz.padht.speiyou.cn/peiyou-pad-mgmt/online-disk/create?parentId=";
    private static String fileUrl_name = "&curLevel=3&nodeName=";
    private static String fileUrl_other = "&fileUrl=http://peiyou-pad-mgmt.oss-cn-beijing.aliyuncs.com/dev/course_file/20180213/1f063c91-1cfe-43cb-8043-cec3cecaf3b4.pdf&fileSize=2059879&fileType=0&nodeType=2&pageNo=1&pageSize=12&areaCode=10";


    //local
    private static String url_local = "http://localhost:8080/peiyou-pad-mgmt/online-disk/create?";
    private static String url2_local = "http://localhost:8080/peiyou-pad-mgmt/online-disk/create?areaCode=10&parentId=5&nodeType=1&fileSize=0&curLevel=2&fileUrl=&nodeName=";
    private static String url2__local = "http://localhost:8080/peiyou-pad-mgmt/online-disk/create?areaCode=10&parentId=5&nodeType=1&fileSize=0&curLevel=2&fileUrl=&nodeName=";
    private static String other_local = "&fileType=-1";

    private static String fileUrl_local = "";
    private static String fileUrl_id_local = "http://localhost:8080/peiyou-pad-mgmt/online-disk/create?parentId=";
    private static String fileUrl_name_local = "&curLevel=3&nodeName=";
    private static String fileUrl_other_local = "&fileUrl=http://peiyou-pad-mgmt.oss-cn-beijing.aliyuncs.com/dev/course_file/20180213/1f063c91-1cfe-43cb-8043-cec3cecaf3b4.pdf&fileSize=2059879&fileType=0&nodeType=2&pageNo=1&pageSize=12&areaCode=10";

    private static int[] idArrs = new int[]{433, 434, 435, 436, 437, 438, 439, 440, 441, 442};
    private static int[] idArrs_local = new int[]{242, 243, 244, 245, 246, 247, 248, 249, 250};

    public static void main(String[] args) {

        //创建10个文件夹
//        for (int i = 0; i < 10; i++) {
//            String name = "name_" + i;
//            url2 = url2_ + name + other;
//            System.out.println(doGet(url2));
//        }

        //文件夹id 578 579 580 581 582 583 584 585 586 587
//        for (int item : idArrs) {
//            System.out.println(item);
//            for (int i = 0; i < 1000; i++) {
//                fileUrl = fileUrl_id + item + fileUrl_name + "name_" + i + ".pdf" + fileUrl_other;
//                System.out.println(doGet(fileUrl));
//            }
//        }

//        for (int i = 0; i < 10; i++) {
//            String name = "name_" + i;
//            url2_local = url2__local + name + other_local;
//            System.out.println(doGet(url2_local));
//        }
        for (int item : idArrs_local) {
            System.out.println(item);
            for (int i = 0; i < 1000; i++) {
                fileUrl_local = fileUrl_id_local + item + fileUrl_name_local + "name_" + i + ".pdf" + fileUrl_other_local;
                System.out.println(doGet(fileUrl_local));
            }
        }
    }

    private static void getFun() {
//        HttpClientUtil.sendGet();
    }

    public static String doGet(String url) {
        try {
            HttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpGet request = new HttpGet(url);
            request.addHeader("Connection", "keep-alive");
            request.addHeader("Content-Type", "application/json;charset=UTF-8");
            request.addHeader("Cookie", "JSESSIONID=1580635AEE51FE2DC84DF8AD1E66F9E2; SERVERID=34eb29ebbec54f7ca579b2ec2dbea277|1520405993|1520401813");
            request.addHeader("areaCode", "10");
            request.addHeader("phone", "15280923281");
            request.addHeader("userId", "262");

            HttpResponse response = client.execute(request);

            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity());

                return strResult;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
