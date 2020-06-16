package com.jojoldu.ex00.springboot;

import com.jojoldu.ex00.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 Bean을 주입 받음.
    private MockMvc mockMvc; // 웹 API를 테스트할 때 사용. GET, POST 등에 대한 API 테스트 가능

    @Test
    public void hello() throws Exception {
        String hello = "hello";

        mockMvc.perform(get("/hello")) // /hello로 get 요청
                .andExpect(status().isOk()) // HTTP 상태 검증
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto() throws Exception {
        String name = "hello";
        int amount = 1000;

        mockMvc.perform(
                get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }

}
