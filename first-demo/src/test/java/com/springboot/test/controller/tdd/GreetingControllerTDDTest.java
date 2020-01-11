package com.springboot.test.controller.tdd;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;


@ExtendWith(SpringExtension.class)
@WebMvcTest
class GreetingControllerTDDTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @Order(1)
    public void whenGenderMale() throws Exception {
        String name = "Tom";
        String gender = "Male";
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/greetings")
                .param("name", name)
                .param("gender", gender)
                ).andExpect(
                    MockMvcResultMatchers.status().isOk()
                ).andExpect(
                    MockMvcResultMatchers.content().string(containsString(
                            String.format("Hello Mr. %s. How are you?", name))
                ));
    }

    @Test
    @Order(2)
    public void whenGenderFemale() throws Exception {
        String name = "Rehana";
        String gender = "Female";
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/greetings")
                    .param("name", name)
                    .param("gender", gender)
                ).andExpect(
                    MockMvcResultMatchers.status().isOk()
                ).andExpect(
                    MockMvcResultMatchers.content().string(containsString(
                            String.format("Hello Mrs. %s. How are you?", name))
                ));
    }
}