package com.goatedtech.goateddbspring;

import com.goatedtech.goateddbspring.RecordLibrary.RecordLibrary;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GoatedDbSpringApplication {

    public static void main(String[] args) {

        DBManager.prepareRecords();

        SpringApplication.run(GoatedDbSpringApplication.class, args);
    }

}
