package com.jojoldu.ex00.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        // 내장 WAS(Web Application Server)를 실행
        // 내장이라서 톰캣을 설치할 필요가 없게 되고, 스프링 부트로 만들어진 Jar파일로 실행
        SpringApplication.run(Application.class, args);

        System.out.println("http://localhost:8080");
    }

}
