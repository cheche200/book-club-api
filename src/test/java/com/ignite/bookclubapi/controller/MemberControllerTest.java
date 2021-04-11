package com.ignite.bookclubapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ignite.bookclubapi.domain.Member;
import com.ignite.bookclubapi.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;


@WebMvcTest(MemberController.class)
class MemberControllerTest {

    private @Autowired MockMvc mockMvc;
    private @MockBean MemberService service;
    private Member member =  new Member("Jose");

    @Test
    void getById() throws Exception {
        when(service.findById("123")).thenReturn(Optional.of(member));

        mockMvc.perform(get("/member/123")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Jose")));
    }

    @Test
    void create() throws Exception {
       mockMvc.perform(post("/member")
       .content(asJsonString(member))
       .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isCreated());

       verify(service, times(1)).save(member);
    }

    private String asJsonString(Member member) {
        try{
            return new ObjectMapper().writeValueAsString(member);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}