package com.scaler.sampleprojectname;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SampleProjectNameApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleProjectNameApplication.class, args);
    }

}
