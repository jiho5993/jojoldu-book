package com.jojoldu.ex00.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter // 필드의 get메소드를 생성
@RequiredArgsConstructor // final 필드가 포함된 생성자를 생성
public class HelloResponseDto {

    private final String name;
    private final int amount;

}
