package com.daily.learn.http_client;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by SunGuiyong
 * on 2017/12/28.
 */
public class VisitWebServer {

    private static CountDownLatch latch/* = new CountDownLatch(10)*/;
    private static ExecutorService exec = Executors.newFixedThreadPool(20);

    public static void main(String[] args) throws Exception {
        int times = 0;
        long begintime = System.currentTimeMillis();
        URL url_1 = new URL("http://dev.padht.speiyou.cn/peiyou-pad-mgmt/announcement/suggestAnnouncement?announcementName=");
        URL url_2 = new URL(
                "http://dev.padht.speiyou.cn/peiyou-pad-mgmt/announcement/updateAnnouncement?id=8&areaCode=21&title=123&subtitle=12&content=12&firstPictureUrl=&contentAbstract=12");

        while (times < 100000) {
            long beginTimeIn = System.currentTimeMillis();
            try {
                latch = new CountDownLatch(10);
                for (int i = 0; i < 5; i++) {
                    exec.execute(new URLConntection(url_1, null));
                }

                for (int i = 0; i < 5; i++) {
                    exec.execute(new URLConntection(url_2, null));
                }
                try {
                    latch.await();
                } catch (Exception e) {
                    System.out.println("线程结束: " + e.getMessage());
                }
                times++;
                System.out.printf("第%s连接,执行时间为：%s毫秒\n",
                        times, (System.currentTimeMillis() - beginTimeIn));
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("总共执行时间为：" + (System.currentTimeMillis() - begintime) + "毫秒");
    }

    public static class URLConntection implements Runnable {

        private URL url;
//        private HttpURLConnection urlCon;

        public URLConntection(URL url, HttpURLConnection urlCon) {
            this.url = url;
//            this.urlCon = urlCon;
        }

        @Override
        public void run() {
            try {
                HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
                urlCon.setRequestProperty("areaCode", "10");
                urlCon.setRequestProperty("phone", "1");
                urlCon.setRequestProperty("userId", "32");

                urlCon.connect();         //获取连接
                urlCon.getInputStream();
                urlCon.disconnect();
            } catch (Exception e) {
                System.out.println("多线程问题：" + e.getMessage());
            } finally {
                latch.countDown();
            }
        }
    }
}
