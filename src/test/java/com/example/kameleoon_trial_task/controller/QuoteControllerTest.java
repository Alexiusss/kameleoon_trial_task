package com.example.kameleoon_trial_task.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class QuoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL = QuoteController.REST_URL + "/";

    @Test
    void getTopTenQuotes() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .param("direction", "top"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void getWorseTenQuotes() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .param("direction", "worse"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}