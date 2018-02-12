package com.daily.html;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by SunGuiyong
 * on 2018/2/11.
 */
public class DownLoadFile {
    /**
     * 从网络Url中下载文件
     *
     * @param urlStr
     * @param fileName
     * @param savePath
     * @throws IOException
     */
    public static void downLoadFromUrl(String urlStr, String fileName, String savePath) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(3 * 1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //得到输入流
        InputStream inputStream = conn.getInputStream();
        //获取自己数组
        byte[] getData = readInputStream(inputStream);

        //文件保存位置
        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }
        File file = new File(saveDir + File.separator + fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if (fos != null) {
            fos.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }
        System.out.println("info:" + url + " download success");
    }


    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    public static void main(String[] args) {
        try {
            downLoadFromUrl("http://peiyou-pad-mgmt.oss-cn-beijing.aliyuncs.com/dev/course_file/20180209/a5217a12-5410-4878-9a83-4668ef37c7b7.pdf",
                    "文件.pdf", "d:/");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

   /* public void OssAction(HttpServletRequest request, HttpServletResponse response){
        try {
            String fileName="txt.jpg";
            String ossKey="txt.jpg";
            // 从阿里云进行下载
            OSSObject ossObject = ossClient.getObject("xudy",ossKey);//bucketName需要自己设置
            // 已缓冲的方式从字符输入流中读取文本，缓冲各个字符，从而提供字符、数组和行的高效读取
            BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));

            InputStream inputStream = ossObject.getObjectContent();

            //缓冲文件输出流
            BufferedOutputStream outputStream=new BufferedOutputStream(response.getOutputStream());
            //通知浏览器以附件形式下载
            // response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));
// 为防止 文件名出现乱码
            response.setContentType("application/doc");
            final String userAgent = request.getHeader("USER-AGENT");
            if(StringUtils.contains(userAgent, "MSIE")){//IE浏览器
                fileName = URLEncoder.encode(fileName,"UTF-8");
            }else if(StringUtils.contains(userAgent, "Mozilla")){//google,火狐浏览器
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            }else{
                fileName = URLEncoder.encode(fileName,"UTF-8");//其他浏览器
            }
            response.addHeader("Content-Disposition", "attachment;filename=" +fileName);//这里设置一下让浏览器弹出下载提示框，而不是直接在浏览器中打开

            byte[] car = new byte[1024];
            int L;

            while((L = inputStream.read(car)) != -1){
                if (car.length!=0){
                    outputStream.write(car, 0,L);
                }
            }

            if(outputStream!=null){
                outputStream.flush();
                outputStream.close();
            }


        } catch (IOException e) {
            e.printStackTrace();

        } catch (OSSException e){

        }
    }*/
}
