package com.jojoldu.ex00.springboot.web;

import com.jojoldu.ex00.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // JSON을 반환하는 컨트롤러로 만듦
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    // ex) /hello/dto?name=?&amount=?
    public HelloResponseDto helloDto(@RequestParam("name") String name, // 외부에서 API로 넘긴 파라미터를 가져옴
                                     @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }

}
