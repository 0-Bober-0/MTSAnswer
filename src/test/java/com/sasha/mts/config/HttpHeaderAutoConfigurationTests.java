package com.sasha.mts.config;

import com.sasha.mts.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class HttpHeaderAutoConfigurationTests extends AbstractIntegrationTest {

    @Test
    void shouldAddDefaultHeader() throws Exception {
        mockMvc.perform(get("/test"))
                .andExpect(status().isOk())
                .andExpect(header().string("Foo", "Bar"));
    }

    @Test
    void shouldNotAddHeaderWhenDisabled() throws Exception {
        mockMvc.perform(get("/test"))
                .andExpect(status().isOk())
                .andExpect(header().string("Foo", "Bar"));
    }
}