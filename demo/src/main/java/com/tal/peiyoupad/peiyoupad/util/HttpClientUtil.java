package com.tal.peiyoupad.peiyoupad.util;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HttpClientUtil {

  private static String USER_AGENT = "Mozilla/4.0 "
      + "(compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0; "
      + "SLCC1; .NET CLR 2.0.50727; .NET CLR 3.0.04506; customie8)";

  public static String sendGet(String url) {
    CloseableHttpClient client = HttpClients.createDefault();
    StringBuffer result = null;
    try {
      HttpGet request = new HttpGet(new String(url.getBytes("UTF-8")));

      // add request header
      request.addHeader("User-Agent", USER_AGENT);

      HttpResponse response = client.execute(request);

      BufferedReader rd = new BufferedReader(
          new InputStreamReader(response.getEntity().getContent(), "utf-8"));

      result = new StringBuffer();
      String line = "";
      while ((line = rd.readLine()) != null) {
        result.append(line);
      }
      return result.toString();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        client.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return result.toString();
  }

  public static String doPost(String url, Map<String, String> map, String charset) {
    CloseableHttpClient httpClient = null;
    HttpPost httpPost = null;
    String result = null;
    try {
      httpClient = HttpClients.createDefault();
      httpPost = new HttpPost(url);
      // 设置参数
      List<NameValuePair> list = new ArrayList<NameValuePair>();
      Iterator iterator = map.entrySet().iterator();
      while (iterator.hasNext()) {
        Entry<String, String> elem = (Entry<String, String>) iterator.next();
        list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
      }
      if (list.size() > 0) {
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
        httpPost.setEntity(entity);
      }
      HttpResponse response = httpClient.execute(httpPost);
      if (response != null) {
        HttpEntity resEntity = response.getEntity();
        if (resEntity != null) {
          result = EntityUtils.toString(resEntity, charset);
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return result;
  }


  public static String sendGet(String url, Map<String, String> headers) {
    CloseableHttpClient client = HttpClients.createDefault();
    StringBuffer result = null;
    try {
      HttpGet request = new HttpGet(url);

      // add request header
      request.addHeader("User-Agent", USER_AGENT);
      for (String key : headers.keySet()) {
        request.addHeader(key, headers.get(key));
      }

      HttpResponse response = client.execute(request);

      BufferedReader rd =
          new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));

      result = new StringBuffer();
      String line = "";
      while ((line = rd.readLine()) != null) {
        result.append(line);
      }
      return result.toString();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        client.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return result.toString();
  }

  public static String doPost(String url, Map<String, Object> headers, Map<String, Object> params,
      String charset) {
    CloseableHttpClient httpClient = null;
    HttpPost httpPost = null;
    String result = null;
    try {
      httpClient = HttpClients.createDefault();
      httpPost = new HttpPost(url);
      // 设置头信息
      if (!CollectionUtils.isEmpty(headers)) {
        Iterator headersIterator = headers.entrySet().iterator();
        while (headersIterator.hasNext()) {
          Entry<String, String> elem = (Entry<String, String>) headersIterator.next();
          httpPost.setHeader(elem.getKey(), elem.getValue());
        }
      }
      // 设置参数
      List<NameValuePair> list = new ArrayList<NameValuePair>();
      Iterator iterator = params.entrySet().iterator();
      while (iterator.hasNext()) {
        Entry<String, String> elem = (Entry<String, String>) iterator.next();
        list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
      }
      if (list.size() > 0) {
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
        httpPost.setEntity(entity);
      }

      HttpResponse response = httpClient.execute(httpPost);
      if (response != null) {
        HttpEntity resEntity = response.getEntity();
        if (resEntity != null) {
          result = EntityUtils.toString(resEntity, charset);
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return result;
  }

  public static String doPost(String url, Map<String, Object> headers, JSONObject json,
      String charset) {
    CloseableHttpClient httpClient = null;
    HttpPost httpPost = null;
    String result = null;
    try {
      httpClient = HttpClients.createDefault();
      httpPost = new HttpPost(url);
      // 设置头信息
      if (!CollectionUtils.isEmpty(headers)) {
        Iterator headersIterator = headers.entrySet().iterator();
        while (headersIterator.hasNext()) {
          Entry<String, String> elem = (Entry<String, String>) headersIterator.next();
          httpPost.setHeader(elem.getKey(), elem.getValue());
        }
      }
      // 设置参数
      if (!json.isEmpty()) {
        StringEntity stringEntity = new StringEntity(json.toJSONString(), "UTF-8");
        httpPost.setEntity(stringEntity);
      }
      HttpResponse response = httpClient.execute(httpPost);
      if (response != null) {
        HttpEntity resEntity = response.getEntity();
        if (resEntity != null) {
          result = EntityUtils.toString(resEntity, charset);
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return result;
  }

  /**
   * post请求
   */
  public static String doPostJson(String url, String json, Map<String, String> headerMap) {

    CloseableHttpClient httpclient = HttpClientBuilder.create().build();
    HttpPost post = new HttpPost(url);
    String result = null;

    try {
      headerMap.put("Content-Type", ContentType.APPLICATION_JSON.toString());
      Header[] headers = getHeaders(headerMap);
      StringEntity s = new StringEntity(json, "UTF-8");
      s.setContentType(ContentType.APPLICATION_JSON.toString());// 发送json数据需要设置contentType
      post.setEntity(s);
      post.setHeaders(headers);
      HttpResponse res = httpclient.execute(post);
      if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
        result = EntityUtils.toString(res.getEntity());// 返回json格式：
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  private static Header[] getHeaders(Map<String, String> headerMap) {
    if (headerMap.isEmpty()) {
      return new Header[0];
    }
    Header[] headers = new Header[headerMap.size()];
    int i = 0;
    for (Entry<String, String> entry : headerMap.entrySet()) {
      headers[i] = new BasicHeader(entry.getKey(), entry.getValue());
      i++;
    }
    return headers;
  }

  public static String getUrlWithParam(Map<String, String> params) {
    if (CollectionUtils.isEmpty(params)) {
      return "";
    }
    StringBuilder url = new StringBuilder();
    url.append("?");
    try {
      for (Entry<String, String> entry : params.entrySet()) {
        if (!Strings.isNullOrEmpty(entry.getKey()) && entry.getValue() != null) {
          url.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
          url.append("=");
          url.append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
          url.append("&");
        }
      }
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException("getUrlWithParam UnsupportedEncodingException ");
    }
    return url.toString();
  }

  public static void main(String args[]) {
    System.out.println(sendGet("https://www.baidu.com"));
    System.out.println(sendGet("========="));
    System.out.println(sendGet("https://www.baidu.com"));
  }
}
