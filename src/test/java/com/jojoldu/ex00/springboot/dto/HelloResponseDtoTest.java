package com.jojoldu.ex00.springboot.dto;

import com.jojoldu.ex00.springboot.web.dto.HelloResponseDto;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void lombokTest() {
        // given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto helloResponseDto = new HelloResponseDto(name, amount);

        // then
        assertThat(helloResponseDto.getName()).isEqualTo(name);
        assertThat(helloResponseDto.getAmount()).isEqualTo(amount);
    }

}
