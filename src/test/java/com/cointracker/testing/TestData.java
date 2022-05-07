package com.cointracker.testing;


import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import java.nio.charset.StandardCharsets;

public class TestData {

    public static RequestPostProcessor getAddressRequest() {

        String addressRequestBody = "{\n" +
                "  \"address\": \"12xQ9k5ousS8MqNsMBqHKtjAtCuKezm2Ju\"\n" +
                "}";

        RequestPostProcessor requestPostProcessor = new RequestPostProcessor() {
            @Override
            public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                request.setSession(new MockHttpSession());
                request.setContentType("application/json");
                request.setContent(addressRequestBody.getBytes(StandardCharsets.UTF_8));
                return request;
            }
        };
        return requestPostProcessor;
    }
}
