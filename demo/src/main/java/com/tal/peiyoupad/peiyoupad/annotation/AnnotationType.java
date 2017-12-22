package com.tal.peiyoupad.peiyoupad.annotation;

public class AnnotationType {

  /**
   * PV:接口访问一次算一次PV
   */
  public static final String PV = "pv";
  /**
   * OCCURS:每秒请求数量
   */
  public static final String OCCURS = "occurs";
  /**
   * QPS = OCCURS/LATENCY
   */
  public static final String QPS = "qps";
  /**
   * LATENCY:请求的平均处理时间，单位毫秒
   */
  public static final String LATENCY = "latency";
}
