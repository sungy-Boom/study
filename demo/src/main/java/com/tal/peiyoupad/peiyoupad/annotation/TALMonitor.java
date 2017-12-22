package com.tal.peiyoupad.peiyoupad.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.METHOD})
public @interface TALMonitor {
  /**
   * TALMonitor
   * 
   * @author 牛冬
   * @date 2017年09月26日
   * @version 1.0
   */

  /* 注解的属性：type.值为AnnotationType中的一个或几个 */
  String[] type() default {AnnotationType.PV, AnnotationType.LATENCY, AnnotationType.OCCURS,
      AnnotationType.QPS};
}
