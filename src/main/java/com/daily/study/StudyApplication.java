package com.daily.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author SunGuiyong
 */
@SpringBootApplication
public class StudyApplication extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(StudyApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(StudyApplication.class, args);
    }
}
