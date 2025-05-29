package com.sasha.mts.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HttpHeaderFilterTest {

    @Mock
    private HeaderProperties properties;

    @Mock
    private HttpServletResponse mockResponse;

    @InjectMocks
    private HeaderFilter headerFilter;

    @Test
    void shouldAddHeaderWhenEnabled() throws Exception {
        when(properties.isEnabled()).thenReturn(true);
        when(properties.getName()).thenReturn("Foo");
        when(properties.getValue()).thenReturn("Bar");

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockFilterChain chain = new MockFilterChain();

        headerFilter.doFilter(request, mockResponse, chain);

        verify(mockResponse).addHeader("Foo", "Bar");
    }

    @Test
    void shouldNotAddHeaderWhenDisabled() throws Exception {
        when(properties.isEnabled()).thenReturn(false);

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockFilterChain chain = new MockFilterChain();

        headerFilter.doFilter(request, mockResponse, chain);

        verify(mockResponse, never()).addHeader(anyString(), anyString());
    }
}